package BLL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import DAL.PaymentDAL;
import DB.DBManager;
import Models.MemberFeesArray;
import Models.PaymentArray;
import Models.PaymentModel;
import Models.ServerResponse;

public class PaymentManager {

	public static ServerResponse RegisterMonthlyFees(PaymentModel model) {
		Connection intermediateConnection = DBManager.getDBConn();
		ServerResponse response = new ServerResponse();
		try {
			response = PaymentDAL.RegisterMonthlyFees(model,intermediateConnection);
		} finally {
			try {
				intermediateConnection.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return response;
	}

	public static PaymentArray getTreasuryByYear(PaymentModel model) {
		PaymentArray paymentArray = new PaymentArray();
		Connection intermediateConnection = DBManager.getDBConn();
		ArrayList<PaymentModel> arrayList =new ArrayList<PaymentModel>();
		try {
			arrayList = PaymentDAL.getTreasuryByYear(model,intermediateConnection);
		} finally {
			try {
				intermediateConnection.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		paymentArray.setPaymentArray(arrayList);
		return paymentArray;
	}
	
	public static PaymentArray getTreasuryByMonthAndYear(PaymentModel model) {
		PaymentArray paymentArray = new PaymentArray();
		Connection intermediateConnection = DBManager.getDBConn();
		ArrayList<PaymentModel> arrayList =new ArrayList<PaymentModel>();
		try {
			arrayList = PaymentDAL.getTreasuryByMonthAndYear(model,intermediateConnection);
		} finally {
			try {
				intermediateConnection.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		paymentArray.setPaymentArray(arrayList);
		return paymentArray;
	}

	public static ServerResponse payFromTreasury(PaymentModel model) {
		Connection intermediateConnection = DBManager.getDBConn();
		ServerResponse response = new ServerResponse();
		try {
			response = PaymentDAL.payFromTreasury(model,intermediateConnection);
		} finally {
			try {
				intermediateConnection.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return response;
	}

	public static MemberFeesArray getTreasuryMemberRecord(PaymentModel model) {
		MemberFeesArray array = new MemberFeesArray();
		Connection intermediateConnection = DBManager.getDBConn();
		
		try {
			array = PaymentDAL.getTreasuryMemberRecord(model.getMemberID(),intermediateConnection);
		} finally {
			try {
				intermediateConnection.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return array;
	}
	
	public static MemberFeesArray getTreasuryMemberRecordByYear(PaymentModel model) {
		MemberFeesArray array = new MemberFeesArray();
		Connection intermediateConnection = DBManager.getDBConn();
		
		try {
			array = PaymentDAL.getTreasuryMemberRecordByYear(model.getMemberID(),model.getYear(),intermediateConnection);
		} finally {
			try {
				intermediateConnection.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return array;
	}

	public static MemberFeesArray getTreasuryPaidMembers(PaymentModel model) {
		MemberFeesArray array = new MemberFeesArray();
		Connection intermediateConnection = DBManager.getDBConn();
		
		try {
			array = PaymentDAL.getTreasuryPaidMembers(model.getMonth(),model.getYear(),intermediateConnection);
		} finally {
			try {
				intermediateConnection.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return array;
	}


}
