package se.iths.johan.springmessenger.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Email extends Message {
    private String subject;

    public String getType() {
        return "email";
    }

}
