package a;

public class Withdraw {
	private Long accountId;
	private double amount;
	public Withdraw() {
	}
	public Withdraw(Long accountId, double amount) {
		this.accountId = accountId;
		this.amount = amount;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
