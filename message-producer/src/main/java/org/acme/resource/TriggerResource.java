package org.acme.resource;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.mutiny.Uni;

import org.acme.domain.Message;
import org.acme.domain.Trigger;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Path("/triggers")
public class TriggerResource {

    private static final Logger logger = LoggerFactory.getLogger(MessageResource.class);

    @Inject
    @Channel("triggers")
    Emitter<Trigger> emitter;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
	public Uni<Response> send(Trigger trigger) {
		
		logger.info(trigger.toString());

        CompletionStage<Void> ack = emitter.send(trigger);
		
		return Uni.createFrom().item(Response.status(Response.Status.OK).build());
		
	}
}