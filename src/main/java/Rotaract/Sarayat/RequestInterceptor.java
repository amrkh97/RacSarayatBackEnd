package Rotaract.Sarayat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.message.internal.ReaderWriter;
import org.glassfish.jersey.server.ContainerException;


@Provider
@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class RequestInterceptor implements ContainerRequestFilter {

	@Context
    private HttpServletRequest sr;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		System.out.println("---- General Request Interceptor ----");
		// Get the Authorization header from the request
		//String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		
		System.out.println("IP Address: "+ sr.getRemoteAddr());
		System.out.println("URI PATH: "+ requestContext.getUriInfo().getAbsolutePath());
		System.out.println("REQUEST METHOD: " + requestContext.getRequest().getMethod().toString());
		/////////////////////////////////////////////
		
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
	        InputStream in = requestContext.getEntityStream();
	        final StringBuilder b = new StringBuilder();
	        try {
	            if (in.available() > 0) {
	                ReaderWriter.writeTo(in, out);

	                byte[] requestEntity = out.toByteArray();
	                printEntity(b, requestEntity);

	                requestContext.setEntityStream(new ByteArrayInputStream(requestEntity));
	            }
	        } catch (IOException ex) {
	            throw new ContainerException(ex);
	        }

	    }

	    private void printEntity(StringBuilder b, byte[] entity) throws IOException {
	        if (entity.length == 0)
	            return;
	        b.append(new String(entity)).append("\n");
	        System.out.println("REQUEST BODY:");
	        System.out.println(b.toString());
	    }
		
		/////////////////////////////////////////////
}

