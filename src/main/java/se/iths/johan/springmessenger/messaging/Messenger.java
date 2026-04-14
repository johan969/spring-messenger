package se.iths.johan.springmessenger.messaging;

import se.iths.johan.springmessenger.model.Message;

public interface Messenger {
    public void send(Message message);

}
