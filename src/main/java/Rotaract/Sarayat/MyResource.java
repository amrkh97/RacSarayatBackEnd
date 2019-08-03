package Rotaract.Sarayat;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import BLL.UserManager;
import Models.LoginModel;
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
        return "Hello, Heroku!";
    }
    
    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginModel model) {
    	
    	ServerResponse response = new ServerResponse();
    	response = UserManager.login(model);
    	switch (response.getResponseHexCode()) {
		case "00":
			
			break;
			
		default:
			break;
		}
    	
    	return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }
    
    
    @Path("logout")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout() {
    	
    	ServerResponse response = new ServerResponse();
    	
    	switch (response.getResponseHexCode()) {
		case "00":
			
			break;
			
		default:
			break;
		}
    	
    	return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }
}
