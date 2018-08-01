package com.activiti.pru.model;

public class CashPayment{
	private Integer policyNumber;
	private String transactionDate;
	private String valueDate;
	private String productDescription;
	private String creditAmount;
	private String transactionType;
	private String bank;
	private String chequeNumber;
	
	
	public Integer getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(Integer policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getChequeNumber() {
		return chequeNumber;
	}
	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	@Override
	public String toString() {
		return "CashPayment [policyNumber=" + policyNumber + ", transactionDate=" + transactionDate + ", valueDate="
				+ valueDate + ", productDescription=" + productDescription + ", creditAmount=" + creditAmount
				+ ", transactionType=" + transactionType + ", bank=" + bank + ", chequeNumber=" + chequeNumber + "]";
	}
	
	
}