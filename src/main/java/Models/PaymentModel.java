package Models;

public class PaymentModel {
	
	private Integer memberID;
	private String recieverOfPayment;
	private String month;
	private String year;
	private String dateOfPayment;
	private Integer paidAmount;
	private Integer currentAmount;
	
	public String getRecieverOfPayment() {
		return recieverOfPayment;
	}
	public void setRecieverOfPayment(String recieverOfPayment) {
		this.recieverOfPayment = recieverOfPayment;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getDateOfPayment() {
		return dateOfPayment;
	}
	public void setDateOfPayment(String dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}
	public Integer getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Integer paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Integer getCurrentAmount() {
		return currentAmount;
	}
	public void setCurrentAmount(Integer currentAmount) {
		this.currentAmount = currentAmount;
	}
	public Integer getMemberID() {
		return memberID;
	}
	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

}
