package finance;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class FinancialAccounts implements IBaseRate {
    private String name;
    protected String dateStart;
    protected int mortgage;
    protected double rate;
    protected String mortType;
    protected static int numOfRepayments;
    protected double repayment;
    protected static int billing;
    String riskProfile;
    DecimalFormat f = new DecimalFormat("##.00");

    public FinancialAccounts(String name, String dateStart, int mortgage, String mortType, int numOfRepayments, int billing ) {
        this.name = name;
        this.dateStart = dateStart;
        this.mortgage = mortgage;
        this.numOfRepayments = numOfRepayments;
        this.mortType = mortType;
        this.billing = billing;
        setRate();
    }
    public abstract void setRate();

    protected String[] calcDates(){
        //This method calculates each billing date
        String []dates = new String [getNumOfRepayments()];
        LocalDate date = LocalDate.parse(getDateStart());
        for(int i = 0; i < numOfRepayments; i++) {
            date = date.plus(getBilling(), ChronoUnit.MONTHS);
            dates[i] = date.toString();
        }
        return dates;
    }
    protected void adjustRate() {
        //Adjust interest rate converting from annual rate to fractionate rate
        double fraction = 0;
        fraction = (double) 1/(12/billing);
        rate = Math.pow(1 + rate,fraction) - 1;
    }

    public void showInfo() {
        AlertBox.displayTable(riskProfile+" account", "NAME: \nMORTGAGE: \nACCOUNT OPENED IN: \nRATE: \nMORTGAGE WILL END IN: \nNUM OF REPAYMENTS: \nFIRST REPAYMENT: \nLAST REPAYMENT: \nTYPE OF MORTGAGE: ",
                getName()+"\n"
                        +getMortgage()+"\n"
                        +dateStart+"\n"
                        +"0"+f.format(rate)+"%\n"
                        +calcDates()[numOfRepayments-1]+"\n"
                        +numOfRepayments+"\n"
                        +f.format(calculateMortgage()[0][0])+"\n"
                        +f.format(calculateMortgage()[0][numOfRepayments-1])+"\n"
                        +mortType+", "+billing+" months repayment");
    }
    protected void showMort(double[][] quotes) {
        String mortView = "";
        for(int i = 0; i < quotes[0].length; i++) {
            mortView += "\nRepayment: "+f.format(quotes[0][i])+"\nCapital quote: "+f.format(quotes[1][i])+
                    "\nInterest quote: "+f.format(quotes[2][i])+"\nDATE: "+calcDates()[i]+"\n";
        }
        AlertBox.display(getName(), mortView);
    }
    protected double[][] calculateMortgage() {
        //Method to calculate mortgages, french or italian
        double[][] quotes = new double[3][numOfRepayments];
        double capitalLeft = mortgage;
        double Interests = 0;
        double capitalQuote1 = 0;
        if (mortType.equalsIgnoreCase("Italian")){
            capitalQuote1 = mortgage/numOfRepayments;
            for(int n = 0; n < numOfRepayments; n++) {
                Interests = capitalLeft*(rate);
                repayment = capitalQuote1 + Interests;
                capitalLeft -= capitalQuote1;
                quotes[0][n] = repayment;
                quotes[1][n] = capitalQuote1;
                quotes[2][n] = Interests;
            }
        }
        else if(mortType.equalsIgnoreCase("French")){
            repayment = mortgage/((1-(Math.pow(1+rate, -numOfRepayments)))/rate);
            for(int n = 0; n < numOfRepayments;n++) {
                Interests = capitalLeft * rate;
                capitalQuote1 = repayment - Interests;
                capitalLeft -= capitalQuote1;
                quotes[0][n] = repayment;
                quotes[1][n] = capitalQuote1;
                quotes[2][n] = Interests;
            }
        }
        return quotes;
    }

    protected int getMortgage() {
        return mortgage;
    }
    protected String getName() {
        return name;
    }
    protected String getDateStart() {
        return this.dateStart;
    }
    protected static int getNumOfRepayments() {
        return numOfRepayments;
    }
    protected static int getBilling() {
        return billing;
    }
    protected double getRate() {
        return rate;
    }
}
