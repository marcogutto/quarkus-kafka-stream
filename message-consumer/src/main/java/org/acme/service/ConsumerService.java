package org.acme.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.domain.Message;
import org.acme.domain.Trigger;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import org.jboss.logging.Logger;

@ApplicationScoped
public class ConsumerService {

    @Inject
    Logger logger;

    @Incoming("messages")
    public void newMessage(Message message) {
        logger.infov("New message: {0}", message);
    }

    @Incoming("triggers")
    public void newTrigger(Trigger trigger) {
        logger.infov("New trigger: {0}", trigger);
    }
}
