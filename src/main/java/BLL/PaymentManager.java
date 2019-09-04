package BLL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import DAL.PaymentDAL;
import DB.DBManager;
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

}
