package DAL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import Models.MemberArray;
import Models.MemberModel;
import Models.ServerResponse;

public class UserDAL {

	public static ServerResponse login(String email, String password, Connection conn) {
		
		String storedProcedure = "USE RAC_SARAYAT; EXEC usp_User_login ?,?,?,?,?,?";
		ServerResponse response = new ServerResponse();
		
		try {
			CallableStatement cstmt = conn.prepareCall(storedProcedure);
			cstmt.setString(1, email);
			cstmt.setString(2, password);
			cstmt.registerOutParameter(3, Types.NVARCHAR); //HexCode
			cstmt.registerOutParameter(4, Types.NVARCHAR); //HexMsg
			cstmt.registerOutParameter(5, Types.INTEGER); //ID
			cstmt.registerOutParameter(6, Types.INTEGER); //Privilege
			
			cstmt.executeUpdate();
			response.setResponseHexCode(cstmt.getString(3));
			response.setResponseMsg(cstmt.getString(4));
			
			if(response.getResponseHexCode().equals("00"))
			{
				response.setId(cstmt.getInt(5));
				response.setPrivilege(cstmt.getInt(6));
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return response;
	}

	public static ServerResponse logout(String email, Connection conn) {
		String storedProcedure = "USE RAC_SARAYAT; EXEC usp_User_Logout ?,?,?";
		ServerResponse response = new ServerResponse();
		
		try {
			CallableStatement cstmt = conn.prepareCall(storedProcedure);
			cstmt.setString(1, email);
			cstmt.registerOutParameter(2, Types.NVARCHAR); //HexCode
			cstmt.registerOutParameter(3, Types.NVARCHAR); //HexMsgivilege
			
			cstmt.executeUpdate();
			
			response.setResponseHexCode(cstmt.getString(2));
			response.setResponseMsg(cstmt.getString(3));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return response;
	}

	public static ServerResponse updateMemberStatus(Integer memberID, String memberStatusCode, Connection conn) {
		
		String storedProcedure = "USE RAC_SARAYAT; EXEC usp_User_ChangeStatus ?,?,?,?";
		ServerResponse response = new ServerResponse();
		
		try {
			CallableStatement cstmt = conn.prepareCall(storedProcedure);
			cstmt.setInt(1, memberID);
			cstmt.setString(2, memberStatusCode);
			cstmt.registerOutParameter(3, Types.NVARCHAR); //HexCode
			cstmt.registerOutParameter(4, Types.NVARCHAR); //HexMsg
			
			cstmt.executeUpdate();
			response.setResponseHexCode(cstmt.getString(3));
			response.setResponseMsg(cstmt.getString(4));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return response;
	}

	public static ServerResponse assignMemberToPosition(Integer memberID, Integer position, String rotarianYear, Connection conn) {

		String storedProcedure = "USE RAC_SARAYAT; EXEC usp_User_AssignPosition ?,?,?,?,?";
		ServerResponse response = new ServerResponse();
		
		try {
			CallableStatement cstmt = conn.prepareCall(storedProcedure);
			cstmt.setInt(1, memberID);
			cstmt.setInt(2, position);
			cstmt.setString(3, rotarianYear);
			cstmt.registerOutParameter(4, Types.NVARCHAR); //HexCode
			cstmt.registerOutParameter(5, Types.NVARCHAR); //HexMsg
			
			cstmt.executeUpdate();
			response.setResponseHexCode(cstmt.getString(4));
			response.setResponseMsg(cstmt.getString(5));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return response;
	}

	public static MemberArray getMemberByPosition(Integer position, String rotarianYear,Connection conn) {
		
		MemberArray memberArray = new MemberArray();
		ArrayList<MemberModel> memberModels = new ArrayList<MemberModel>();
		String storedProcedure = "USE RAC_SARAYAT; EXEC usp_User_GetByPosition ?,?";
		ResultSet rs;
		
		try {
			CallableStatement cstmt = conn.prepareCall(storedProcedure);
			cstmt.setInt(1, position);
			cstmt.setString(2, rotarianYear);
			rs = cstmt.executeQuery();
			while(rs.next()) {
				MemberModel model = new MemberModel();
				model.setPositionName(rs.getString(1));
				model.setMemberID(rs.getInt(2));
				model.setNationalID(rs.getString(3));
				model.setFirstName(rs.getString(4));
				model.setLastName(rs.getString(5));
				model.setBirthDate(rs.getString(6));
			//	model.setAge(rs.getInt(7));
				model.setEmail(rs.getString(8));
				model.setPosition(position);
				model.setYearsInClub(rs.getString(10));
				model.setMemberStatusCode(rs.getString(12));
				model.setRotaryID(rs.getString(14));
					
				memberModels.add(model);
			}
			rs.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		memberArray.setArrayOfMembers(memberModels);
		return memberArray;
		
		
	}

}
