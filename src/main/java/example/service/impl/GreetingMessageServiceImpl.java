package example.service.impl;

import java.util.List;
//import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.service.GreetingMessageService;
import example.springdata.jpa.entities.GreetingMessage;
import example.springdata.jpa.repositories.GreetingMessageRepository;

@Service
public class GreetingMessageServiceImpl implements GreetingMessageService {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(GreetingMessageServiceImpl.class);

	@Autowired
	private GreetingMessageRepository greetingMessageRepository;

	public List<GreetingMessage> findAll() {
		return greetingMessageRepository.findAllByOrderByIdDesc();
	}

	public void add(GreetingMessage greetingMessage) {
		logger.debug("the greetingMessage is {}", greetingMessage);
		greetingMessageRepository.save(greetingMessage);
	}
}
