package example.service;

import java.util.List;

import example.springdata.jpa.entities.GreetingMessage;

public interface GreetingMessageService {
	public List<GreetingMessage> findAll();

	public void add(GreetingMessage greetingMessage);
}
