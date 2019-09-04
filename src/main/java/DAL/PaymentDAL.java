package DAL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import Models.PaymentModel;
import Models.ServerResponse;

public class PaymentDAL {

	public static ServerResponse RegisterMonthlyFees(PaymentModel model, Connection conn) {
		
		String storedProcedure = "EXEC usp_Fees_Insert ?,?,?,?,?,?";
		ServerResponse response = new ServerResponse();
		
		try {
			CallableStatement cstmt = conn.prepareCall(storedProcedure);
			cstmt.setInt(1, model.getMemberID());
			cstmt.setInt(2, model.getPaidAmount());
			cstmt.setString(3, model.getMonth());
			cstmt.setString(4, model.getYear());
			cstmt.registerOutParameter(5, Types.NVARCHAR); //HexCode
			cstmt.registerOutParameter(6 ,Types.NVARCHAR); //HexMsg
			cstmt.executeUpdate();
			response.setResponseHexCode(cstmt.getString(5));
			response.setResponseMsg(cstmt.getString(6));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return response;
	}

	public static ArrayList<PaymentModel> getTreasuryByYear(PaymentModel model, Connection conn) {
		
		ArrayList<PaymentModel> array = new ArrayList<PaymentModel>();
		String storedProcedure = "EXEC usp_Treasury_GetByYear ?";
		ResultSet rs;
		try {
			CallableStatement cstmt = conn.prepareCall(storedProcedure);
			cstmt.setString(1, model.getYear());
			rs = cstmt.executeQuery();
			while(rs.next()) {
				
				PaymentModel paymentModel = new PaymentModel();
				paymentModel.setCurrentAmount(rs.getInt("CurrentAmount"));
				paymentModel.setDateOfPayment(rs.getString("DateOfChange"));
				
				array.add(paymentModel);
			}
			rs.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return array;
	}

	public static ArrayList<PaymentModel> getTreasuryByMonthAndYear(PaymentModel model, Connection conn) {
		
		ArrayList<PaymentModel> array = new ArrayList<PaymentModel>();
		String storedProcedure = "EXEC usp_Treasury_GetByMonthAndYear ?,?";
		ResultSet rs;
		try {
			CallableStatement cstmt = conn.prepareCall(storedProcedure);
			cstmt.setString(1, model.getYear());
			cstmt.setString(2, model.getMonth());
			rs = cstmt.executeQuery();
			while(rs.next()) {
				
				PaymentModel paymentModel = new PaymentModel();
				paymentModel.setCurrentAmount(rs.getInt("CurrentAmount"));
				paymentModel.setDateOfPayment(rs.getString("DateOfChange"));
				
				array.add(paymentModel);
			}
			rs.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return array;
	}

	public static ServerResponse payFromTreasury(PaymentModel model, Connection conn) {
		
		String storedProcedure = "EXEC usp_Payment_Pay ?,?,?,?";
		ServerResponse response = new ServerResponse();
		
		try {
			CallableStatement cstmt = conn.prepareCall(storedProcedure);
			cstmt.setInt(1, model.getPaidAmount());
			cstmt.setString(2, model.getRecieverOfPayment());
			
			cstmt.registerOutParameter(3, Types.NVARCHAR); //HexCode
			cstmt.registerOutParameter(4 ,Types.NVARCHAR); //HexMsg
			cstmt.executeUpdate();
			
			response.setResponseHexCode(cstmt.getString(3));
			response.setResponseMsg(cstmt.getString(4));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return response;
	}

	//----------------------------------------------------------------------------\\
}

