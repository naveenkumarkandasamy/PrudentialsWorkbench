package com.activiti.pru.model;

public class CashPaymentModel {

	public class CashPayment{
		private String transactionDate;
		private String valueDate;
		private String description;
		private String chequeNumber;
		private String creditAmount;
		private String debitAmount;
		private String runningBalance;

		public String getTransactionDate() {
			return transactionDate;
		}
		public void setTransactionDate(String transactionDate) {
			this.transactionDate = transactionDate;
		}
		public String getValueDate() {
			return valueDate;
		}
		public void setValueDate(String valueDate) {
			this.valueDate = valueDate;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getChequeNumber() {
			return chequeNumber;
		}
		public void setChequeNumber(String chequeNumber) {
			this.chequeNumber = chequeNumber;
		}
		public String getCreditAmount() {
			return creditAmount;
		}
		public void setCreditAmount(String creditAmount) {
			this.creditAmount = creditAmount;
		}
		public String getDebitAmount() {
			return debitAmount;
		}
		public void setDebitAmount(String debitAmount) {
			this.debitAmount = debitAmount;
		}
		public String getRunningBalance() {
			return runningBalance;
		}
		public void setRunningBalance(String runningBalance) {
			this.runningBalance = runningBalance;
		}
	}
	
	public CashPayment [] cashPayments;

	public CashPayment[] getCashPayments() {
		return cashPayments;
	}

	public void setCashPayments(CashPayment[] cashPayments) {
		this.cashPayments = cashPayments;
	}
}
