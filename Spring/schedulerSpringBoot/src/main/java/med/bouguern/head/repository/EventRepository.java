package med.bouguern.head.repository;

import java.time.LocalDateTime;

import org.springframework.data.repository.CrudRepository;

import med.bouguern.head.entity.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

	Iterable<Event> findBetween(LocalDateTime from, LocalDateTime to);

}
