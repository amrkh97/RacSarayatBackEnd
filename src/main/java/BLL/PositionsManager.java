package BLL;

import java.sql.Connection;
import java.sql.SQLException;

import DAL.PositionsDAL;
import DB.DBManager;
import Models.PositionsArray;
import Models.PositionsModel;
import Models.ServerResponse;

public class PositionsManager {

	public static ServerResponse addBoardPosition(PositionsModel model) {
		Connection intermediateConnection = DBManager.getDBConn();
		ServerResponse response = new ServerResponse();
		try {
			response = PositionsDAL.addBoardPosition(model.getPositionName(),model.getPositionDescription(),intermediateConnection);
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

	public static PositionsArray getAllPositions() {
		Connection intermediateConnection = DBManager.getDBConn();
		PositionsArray response = new PositionsArray();
		try {
			response = PositionsDAL.getAllBoardPosition(intermediateConnection);
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

	public static ServerResponse editBoardPosition(PositionsModel model) {
		Connection intermediateConnection = DBManager.getDBConn();
		ServerResponse response = new ServerResponse();
		try {
			response = PositionsDAL.editBoardPosition(model.getPositionID(),model.getPositionDescription(),intermediateConnection);
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

}
