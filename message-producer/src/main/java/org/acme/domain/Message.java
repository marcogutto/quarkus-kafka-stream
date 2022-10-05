package org.acme.domain;

import lombok.Data;

@Data
public class Message {

    private String id;
    
    private String from;

    private String to;

    private String text;
}
