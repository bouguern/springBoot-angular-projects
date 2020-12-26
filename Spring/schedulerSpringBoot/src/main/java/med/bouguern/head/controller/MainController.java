package med.bouguern.head.controller;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import med.bouguern.head.entity.Event;
import med.bouguern.head.entity.Resource;
import med.bouguern.head.helper.EventCreateParams;
import med.bouguern.head.helper.EventDeleteParams;
import med.bouguern.head.helper.EventMoveParams;
import med.bouguern.head.repository.EventRepository;
import med.bouguern.head.repository.ResourceRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MainController {
	
	
	@Autowired
    private EventRepository eventRepo; 
	
    @Autowired
    private ResourceRepository resourceRepo; 
    
    @GetMapping("/api/resources")
    public Iterable<Resource> resources() {
        return resourceRepo.findAll();
    }
    
    /**
     * This method will be mapped to /api/events endpoint. 
     * It requires the data range to be specified using from
     * and to query string parameters (/api/events?from=2021-10-01T00:00:00&to=2021-11-01T00:00:00).
     * @param from
     * @param to
     * @return Iterable<Event>
     */
    @GetMapping("/api/events")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public Iterable<Event> events(@RequestParam("from") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime from, @RequestParam("to") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime to) {
    	return eventRepo.findBetween(from, to);    	
    }
    
    @PostMapping("/api/events/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    public Event createEvent(@RequestBody EventCreateParams params) {
    	
    	Resource resource = resourceRepo.findById(params.resource).get();   	
    	
        Event event = new Event();
        event.setStart(params.start);
        event.setEnd(params.end);
        event.setText(params.text);
        event.setResource(resource);
    	
    	eventRepo.save(event);
    	
    	return event;
    }
    
    @PostMapping("/api/events/move")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event moveEvent(@RequestBody EventMoveParams params) {
    	
    	Event event = eventRepo.findById(params.id).get();   	
    	Resource resource = resourceRepo.findById(params.resource).get();
    	    	
    	event.setStart(params.start);
    	event.setEnd(params.end);
    	event.setResource(resource);
    	
    	eventRepo.save(event);
    	
    	return event;
    }
    
    @PostMapping("/api/events/delete")
    @Transactional
    void deleteEvent(@RequestBody EventDeleteParams params) {
    	eventRepo.deleteById(params.id);
    }

}
