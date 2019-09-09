package Models;

public class MemberFeesModel {
	
	private MemberModel memberModel;

	private PaymentModel paymentModel;

	public MemberModel getMemberModel() {
		return memberModel;
	}

	public void setMemberModel(MemberModel memberModel) {
		this.memberModel = memberModel;
	}

	public PaymentModel getPaymentModel() {
		return paymentModel;
	}

	public void setPaymentModel(PaymentModel paymentModel) {
		this.paymentModel = paymentModel;
	}
	
}
	