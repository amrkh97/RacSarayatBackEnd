package Rotaract.Sarayat;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import BLL.UserManager;
import Models.MemberModel;
import Models.ServerResponse;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("api")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
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
    
    
}
