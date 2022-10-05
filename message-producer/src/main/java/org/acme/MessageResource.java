package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/messages")
public class MessageResource {

    @Channel("messages")
    Emitter<String> emitter;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
	public Uni<Response> send(String message) {
		
		logger.info(message);

        emitter.send(message);
		
		return Uni.createFrom().item(Response.status(Response.Status.OK).build());
		
	}
}