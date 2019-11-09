package financeGUI;

public class HighRisk extends FinancialAccounts {
	public HighRisk(String name, String dateStart, int mortgage, String mortType, int numOfRepayments, int billing) {
		super(name,dateStart, mortgage, mortType, numOfRepayments, billing);
		this.riskProfile = "High Risk";
		}
	@Override
	public void setRate() {
		rate = getBaseRate() + 0.04;
	}
	public void showInfo() {
		super.showInfo();
	}
}
