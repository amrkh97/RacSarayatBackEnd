package BLL;

import java.sql.Connection;
import java.sql.SQLException;

import DAL.UserDAL;
import DB.DBManager;
import Models.LoginModel;
import Models.ServerResponse;

public class UserManager {

	public static ServerResponse login(LoginModel model) {
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

}
