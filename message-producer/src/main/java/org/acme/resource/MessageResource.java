package org.acme.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.mutiny.Uni;

import org.acme.domain.Message;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Path("/messages")
public class MessageResource {

    private static final Logger logger = LoggerFactory.getLogger(MessageResource.class);

    @Channel("messages")
    Emitter<Message> emitter;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
	public Uni<Response> send(Message message) {
		
		logger.info(message.toString());

        emitter.send(message);
		
		return Uni.createFrom().item(Response.status(Response.Status.OK).build());
		
	}
}