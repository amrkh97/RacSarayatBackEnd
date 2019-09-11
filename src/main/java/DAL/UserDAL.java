package DAL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

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

}
