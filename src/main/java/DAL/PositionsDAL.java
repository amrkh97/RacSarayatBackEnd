package DAL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import Models.PositionsArray;
import Models.PositionsModel;
import Models.ServerResponse;

public class PositionsDAL {

	public static ServerResponse addBoardPosition(String positionName, String positionDescription, Connection conn) {
		String storedProcedure = "USE RAC_SARAYAT; EXEC usp_Positions_Add ?,?,?,?";
		ServerResponse response = new ServerResponse();
		
		try {
			CallableStatement cstmt = conn.prepareCall(storedProcedure);
			cstmt.setString(1, positionName);
			cstmt.setString(2, positionDescription);
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

	public static PositionsArray getAllBoardPosition(Connection conn) {
		PositionsArray memberArray = new PositionsArray();
		ArrayList<PositionsModel> memberModels = new ArrayList<>();
		String storedProcedure = "USE RAC_SARAYAT; EXEC usp_Positions_GetAll";
		ResultSet rs;
		
		try {
			CallableStatement cstmt = conn.prepareCall(storedProcedure);
			rs = cstmt.executeQuery();
			while(rs.next()) {
				PositionsModel model = new PositionsModel();
				
				model.setPositionID(rs.getInt(1));
				model.setPositionName(rs.getString(2));
				model.setPositionDescription(rs.getString(3));
				
				memberModels.add(model);
			}
			rs.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		memberArray.setPositionsModels(memberModels);
		return memberArray;
		
		
	}

	public static ServerResponse editBoardPosition(Integer positionID, String positionDescription, Connection conn) {
		String storedProcedure = "USE RAC_SARAYAT; EXEC usp_Positions_EditDescription ?,?,?,?";
		ServerResponse response = new ServerResponse();
		
		try {
			CallableStatement cstmt = conn.prepareCall(storedProcedure);
			cstmt.setInt(1, positionID);
			cstmt.setString(2, positionDescription);
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
