package example.springdata.jpa.repositories;

import java.util.List;

import example.springdata.jpa.entities.GreetingMessage;

public interface GreetingMessageRepository extends BaseRepository<GreetingMessage, Integer> {

	List<GreetingMessage> findAllByOrderByIdDesc();
}