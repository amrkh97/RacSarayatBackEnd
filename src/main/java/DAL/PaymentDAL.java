package DAL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import Models.MemberFeesArray;
import Models.MemberFeesModel;
import Models.MemberModel;
import Models.PaymentModel;
import Models.ServerResponse;

public class PaymentDAL {

	public static ServerResponse RegisterMonthlyFees(PaymentModel model, Connection conn) {
		
		String storedProcedure = "USE RAC_SARAYAT; EXEC usp_Fees_Insert ?,?,?,?,?,?";
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
		String storedProcedure = "USE RAC_SARAYAT; EXEC usp_Treasury_GetByYear ?";
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
		String storedProcedure = "USE RAC_SARAYAT; EXEC usp_Treasury_GetByMonthAndYear ?,?";
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
		
		String storedProcedure = "USE RAC_SARAYAT; EXEC usp_Payment_Pay ?,?,?,?";
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

	public static MemberFeesArray getTreasuryMemberRecord(Integer memberID, Connection conn) {
		MemberFeesArray array = new MemberFeesArray();
		String storedProcedure = "USE RAC_SARAYAT; EXEC usp_Treasury_GetHistoryMember ?";
		ArrayList<MemberFeesModel> arrayList = new ArrayList<MemberFeesModel>();
		ResultSet rs;
		try {
			CallableStatement cstmt = conn.prepareCall(storedProcedure);
			cstmt.setInt(1, memberID);
			rs = cstmt.executeQuery();
			while(rs.next()) {
				
				MemberFeesModel memberFeesModel = new MemberFeesModel();
				MemberModel memberModel = new MemberModel();
				
				memberModel.setMemberID(rs.getInt(1));
				memberModel.setNationalID(rs.getString(2));
				memberModel.setFirstName(rs.getString(3));
				memberModel.setLastName(rs.getString(4));
				memberModel.setAge(rs.getInt(6));
				memberModel.setEmail(rs.getString(7));
				memberModel.setRotaryID(rs.getString(13));
				
				memberFeesModel.setMemberModel(memberModel);
				
				PaymentModel paymentModel = new PaymentModel();
				
				paymentModel.setPaidAmount(rs.getInt(16));
				paymentModel.setMonth(rs.getString(17));
				paymentModel.setYear(rs.getString(18));
				
				memberFeesModel.setPaymentModel(paymentModel);
				arrayList.add(memberFeesModel);
			}
			
			rs.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		array.setFeesModels(arrayList);
		return array;
	}
	
	public static MemberFeesArray getTreasuryMemberRecordByYear(Integer memberID, String year, Connection conn) {
		MemberFeesArray array = new MemberFeesArray();
		String storedProcedure = "USE RAC_SARAYAT; EXEC usp_Treasury_GetHistoryMemberByYear ?,?";
		ArrayList<MemberFeesModel> arrayList = new ArrayList<MemberFeesModel>();
		ResultSet rs;
		try {
			CallableStatement cstmt = conn.prepareCall(storedProcedure);
			cstmt.setInt(1, memberID);
			cstmt.setString(2, year);
			rs = cstmt.executeQuery();
			while(rs.next()) {
				
				MemberFeesModel memberFeesModel = new MemberFeesModel();
				MemberModel memberModel = new MemberModel();
				
				memberModel.setMemberID(rs.getInt(1));
				memberModel.setNationalID(rs.getString(2));
				memberModel.setFirstName(rs.getString(3));
				memberModel.setLastName(rs.getString(4));
				memberModel.setAge(rs.getInt(6));
				memberModel.setEmail(rs.getString(7));
				memberModel.setRotaryID(rs.getString(13));
				
				memberFeesModel.setMemberModel(memberModel);
				
				PaymentModel paymentModel = new PaymentModel();
				
				paymentModel.setPaidAmount(rs.getInt(16));
				paymentModel.setMonth(rs.getString(17));
				paymentModel.setYear(rs.getString(18));
				
				memberFeesModel.setPaymentModel(paymentModel);
				arrayList.add(memberFeesModel);
			}
			
			rs.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		array.setFeesModels(arrayList);
		return array;
	}

	public static MemberFeesArray getTreasuryPaidMembers(String month, String year, Connection conn) {
		MemberFeesArray array = new MemberFeesArray();
		String storedProcedure = "USE RAC_SARAYAT; EXEC usp_Treasury_GetMembersPaid ?,?";
		ArrayList<MemberFeesModel> arrayList = new ArrayList<MemberFeesModel>();
		ResultSet rs;
		try {
			CallableStatement cstmt = conn.prepareCall(storedProcedure);
			cstmt.setString(1, month);
			cstmt.setString(2, year);
			rs = cstmt.executeQuery();
			while(rs.next()) {
				
				MemberFeesModel memberFeesModel = new MemberFeesModel();
				MemberModel memberModel = new MemberModel();
				
				memberModel.setMemberID(rs.getInt(1));
				memberModel.setNationalID(rs.getString(2));
				memberModel.setFirstName(rs.getString(3));
				memberModel.setLastName(rs.getString(4));
				memberModel.setAge(rs.getInt(6));
				memberModel.setEmail(rs.getString(7));
				memberModel.setRotaryID(rs.getString(13));
				
				memberFeesModel.setMemberModel(memberModel);
				
				PaymentModel paymentModel = new PaymentModel();
				
				paymentModel.setPaidAmount(rs.getInt(16));
				paymentModel.setMonth(rs.getString(17));
				paymentModel.setYear(rs.getString(18));
				
				memberFeesModel.setPaymentModel(paymentModel);
				arrayList.add(memberFeesModel);
			}
			
			rs.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		array.setFeesModels(arrayList);
		return array;
	}
	

	//----------------------------------------------------------------------------\\
}

