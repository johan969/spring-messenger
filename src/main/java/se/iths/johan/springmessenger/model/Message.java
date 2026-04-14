package se.iths.johan.springmessenger.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Message {
    private String recipient;
    private String message;

    public abstract String getType();



}
