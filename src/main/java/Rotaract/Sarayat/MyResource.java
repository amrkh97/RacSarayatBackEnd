package Rotaract.Sarayat;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import BLL.PaymentManager;
import BLL.PositionsManager;
import BLL.UserManager;
import DB.DBManager;
import Models.MemberArray;
import Models.MemberFeesArray;
import Models.MemberModel;
import Models.PaymentArray;
import Models.PaymentModel;
import Models.PositionsArray;
import Models.PositionsModel;
import Models.ServerResponse;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("api")
public class MyResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {

		/* IMPORTS:
		 * 
		 * import java.util.Properties;
		 * import javax.mail.Message; import javax.mail.MessagingException; import
		 * javax.mail.PasswordAuthentication; import javax.mail.Session; import
		 * javax.mail.Transport; import javax.mail.internet.InternetAddress; import
		 * javax.mail.internet.MimeMessage;
		 * 
		 */

		/*
		 * Properties props = new Properties(); props.put("mail.smtp.host",
		 * "smtp.gmail.com"); props.put("mail.smtp.socketFactory.port", "465");
		 * props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		 * props.put("mail.smtp.auth", "true"); props.put("mail.smtp.port", "465");
		 * 
		 * Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		 * protected PasswordAuthentication getPasswordAuthentication() { return new
		 * PasswordAuthentication("amrkh97@gmail.com", "Familymessi1997New"); } });
		 * 
		 * try {
		 * 
		 * MimeMessage message = new MimeMessage(session); message.setFrom(new
		 * InternetAddress("amrkh97@gmail.com"));
		 * message.addRecipient(Message.RecipientType.TO, new
		 * InternetAddress("amrkh97@gmail.com")); message.setSubject("With Pass");
		 * message.setText("Hello, this is example of sending email With password");
		 * 
		 * // Send message Transport.send(message);
		 * System.out.println("message sent successfully....");
		 * 
		 * } catch (MessagingException mex) {
		 * 
		 * mex.printStackTrace();
		 * 
		 * }
		 */

		try {
			Connection conn = DBManager.newConn();
			System.out.println("NEW CONN: " + conn.toString());
			CallableStatement cstmt = conn.prepareCall("USE RAC_SARAYAT; SELECT * FROM MemberStatus");
			ResultSet rs = cstmt.executeQuery();
			Integer numberRecords = 1;
			while (rs.next()) {
				System.out.println("RETRIEVED RECORD:" + numberRecords);
				numberRecords++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Server is Running";
	}

	@Path("login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(MemberModel model) {

		ServerResponse response = new ServerResponse();
		response = UserManager.login(model);
		switch (response.getResponseHexCode()) {
		case "00":
			return Response.ok(response).header("Access-Control-Allow-Origin", "*").build();

		default:
			return Response.status(400).entity(response).header("Access-Control-Allow-Origin", "*").build();
		}

	}

	@Path("logout")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout(MemberModel model) {
		ServerResponse response = new ServerResponse();
		response = UserManager.logout(model);
		switch (response.getResponseHexCode()) {
		case "00":
			return Response.ok(response).header("Access-Control-Allow-Origin", "*").build();

		default:
			return Response.status(400).entity(response).header("Access-Control-Allow-Origin", "*").build();
		}
	}

	@Path("user/updateMemberStatus")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateMemberStatus(MemberModel model) {

		ServerResponse response = new ServerResponse();
		response = UserManager.updateMemberStatus(model);
		switch (response.getResponseHexCode()) {
		case "00":
			return Response.ok(response).header("Access-Control-Allow-Origin", "*").build();

		default:
			return Response.status(400).entity(response).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	
	
	@Path("user/assignToPosition")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response assignMemberToPosition(MemberModel model) {

		ServerResponse response = new ServerResponse();
		response = UserManager.assignMemberToPosition(model);
		switch (response.getResponseHexCode()) {
		case "00":
			return Response.ok(response).header("Access-Control-Allow-Origin", "*").build();

		default:
			return Response.status(400).entity(response).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	
	
	@Path("user/getByPosition")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMemberByPosition(MemberModel model) {

		MemberArray response = new MemberArray();
		response = UserManager.getMemberByPosition(model);
		return Response.ok(response).header("Access-Control-Allow-Origin", "*").build();
	
	}
	
	
	
	
	//-----------------------------------------------------------------------------------------------------------------------------------//

	@Path("treasury/addMonthlyFees")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response RegisterMonthlyFees(PaymentModel model) {
		ServerResponse response = new ServerResponse();
		response = PaymentManager.RegisterMonthlyFees(model);
		switch (response.getResponseHexCode()) {
		case "00":
			return Response.ok(response).header("Access-Control-Allow-Origin", "*").build();

		default:
			return Response.status(400).entity(response).header("Access-Control-Allow-Origin", "*").build();
		}
	}

	@Path("treasury/pay")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response payFromTreasury(PaymentModel model) {

		ServerResponse response = new ServerResponse();
		response = PaymentManager.payFromTreasury(model);
		switch (response.getResponseHexCode()) {
		case "00":
			return Response.ok(response).header("Access-Control-Allow-Origin", "*").build();

		default:
			return Response.status(400).entity(response).header("Access-Control-Allow-Origin", "*").build();
		}

	}

	@Path("treasury/getByYear")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTreasuryByYear(PaymentModel model) {

		PaymentArray array = new PaymentArray();
		array = PaymentManager.getTreasuryByYear(model);
		return Response.ok(array).header("Access-Control-Allow-Origin", "*").build();

	}

	@Path("treasury/getByMonthAndYear")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTreasuryByMonthAndYear(PaymentModel model) {

		PaymentArray array = new PaymentArray();
		array = PaymentManager.getTreasuryByMonthAndYear(model);
		return Response.ok(array).header("Access-Control-Allow-Origin", "*").build();

	}

	@Path("treasury/getMemberRecord")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTreasuryMemberRecord(PaymentModel model) {

		MemberFeesArray array = new MemberFeesArray();
		array = PaymentManager.getTreasuryMemberRecord(model);
		return Response.ok(array).header("Access-Control-Allow-Origin", "*").build();

	}

	@Path("treasury/getMemberRecordByYear")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTreasuryMemberRecordByYear(PaymentModel model) {

		MemberFeesArray array = new MemberFeesArray();
		array = PaymentManager.getTreasuryMemberRecordByYear(model);
		return Response.ok(array).header("Access-Control-Allow-Origin", "*").build();

	}

	@Path("treasury/getPaidMembers")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTreasuryPaidMembers(PaymentModel model) {

		MemberFeesArray array = new MemberFeesArray();
		array = PaymentManager.getTreasuryPaidMembers(model);
		return Response.ok(array).header("Access-Control-Allow-Origin", "*").build();

	}
	
	//------------------------------------------------------------------------------------------------------------------//

	@Path("position/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBoardPosition(PositionsModel model) {

		ServerResponse response = new ServerResponse();
		response = PositionsManager.addBoardPosition(model);
		switch (response.getResponseHexCode()) {
		case "00":
			return Response.ok(response).header("Access-Control-Allow-Origin", "*").build();

		default:
			return Response.status(400).entity(response).header("Access-Control-Allow-Origin", "*").build();
		}

	}
	
	@Path("position/getAll")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBoardPosition(PositionsModel model) {
		PositionsArray response = new PositionsArray();
		response = PositionsManager.getAllPositions();
		return Response.ok(response).header("Access-Control-Allow-Origin", "*").build();

	}
	
	@Path("position/editDescription")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response editBoardPosition(PositionsModel model) {

		ServerResponse response = new ServerResponse();
		response = PositionsManager.editBoardPosition(model);
		switch (response.getResponseHexCode()) {
		case "00":
			return Response.ok(response).header("Access-Control-Allow-Origin", "*").build();

		default:
			return Response.status(400).entity(response).header("Access-Control-Allow-Origin", "*").build();
		}

	}
}
