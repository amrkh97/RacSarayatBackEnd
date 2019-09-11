package BLL;

import java.sql.Connection;
import java.sql.SQLException;

import DAL.UserDAL;
import DB.DBManager;
import Models.MemberModel;
import Models.ServerResponse;

public class UserManager {

	public static ServerResponse login(MemberModel model) {
		Connection intermediateConnection = DBManager.getDBConn();
		ServerResponse response = new ServerResponse();
		try {
			response = UserDAL.login(model.getEmail(),model.getPassword(),intermediateConnection);
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

	public static ServerResponse logout(MemberModel model) {
		Connection intermediateConnection = DBManager.getDBConn();
		ServerResponse response = new ServerResponse();
		try {
			response = UserDAL.logout(model.getEmail(),intermediateConnection);
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

	public static ServerResponse updateMemberStatus(MemberModel model) {
		Connection intermediateConnection = DBManager.getDBConn();
		ServerResponse response = new ServerResponse();
		try {
			response = UserDAL.updateMemberStatus(model.getMemberID(),model.getMemberStatusCode(),intermediateConnection);
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
