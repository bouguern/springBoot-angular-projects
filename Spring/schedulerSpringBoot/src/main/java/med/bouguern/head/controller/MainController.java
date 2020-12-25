package med.bouguern.head.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import med.bouguern.head.entity.Event;
import med.bouguern.head.entity.Resource;
import med.bouguern.head.repository.EventRepository;
import med.bouguern.head.repository.ResourceRepository;

@RestController
public class MainController {
	
	
	@Autowired
    private EventRepository eventRepo; //er
	
    @Autowired
    private ResourceRepository resourceRepo; // rr
	
    @GetMapping("/")
    String home() {
        return "Welcome!";
    }
    
    @GetMapping("/api/resources")
    Iterable<Resource> resources() {
        return resourceRepo.findAll();
    }
    
    @GetMapping("/api/events")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<Event> events(@RequestParam("from") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime from, @RequestParam("to") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime to) {
    	return eventRepo.findBetween(from, to);    	
    }

}
