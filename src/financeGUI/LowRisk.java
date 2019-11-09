package financeGUI;

public class LowRisk extends FinancialAccounts {
	public LowRisk(String name, String dateStart, int mortgage, String mortType, int numOfRepayments, int billing) {
		super(name,dateStart, mortgage, mortType, numOfRepayments, billing);
		this.riskProfile = "Low Risk";
		}
	@Override
	public void setRate() {
		rate = getBaseRate() - 0.02;
	}
	public void showInfo() {
		super.showInfo();
	}
}
