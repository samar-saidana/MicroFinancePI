package com.example.pidev_finance.controllers;

import com.example.pidev_finance.entities.Event;
import com.example.pidev_finance.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Event")
public class EventRestController {
    private EventService eventService;

    @PostMapping("/add")
    Event addEvent(@RequestBody Event event){
        return eventService.AddEvent(event);
    }
    @GetMapping("/all")
    List<Event> retrieveAllEvents(){

        return eventService.retrieveAllEvents();
    }
    @GetMapping("/get/{id}")
    Event retrieveEvent(@PathVariable("id") Integer IdEvent){
        return eventService.retrieveEvent(IdEvent);
    }
    @DeleteMapping("/delete/{id}")
    void RemoveEvent(@PathVariable("id") Integer IdEvent){
        eventService.removeEvent(IdEvent);
    }
    @PutMapping ("/update")
    Event updateEvent(@RequestBody Event event){
        return eventService.updateEvent(event);
    }
    @GetMapping("/getEventByShareholder/{nom}/{prenom}")
    List<Event> getEventByShareholder(@PathVariable("nom") String Firstname,@PathVariable("prenom") String Lastname) {
        return eventService.findByShareHolders_LastNameShareholderAndShareHolders_FirstNameShareholder(Lastname, Firstname);
    }

        @GetMapping("/{eventId}/totalInvestment")
        public ResponseEntity<Double> getTotalInvestmentInEvent(@PathVariable int eventId) {
            Double totalInvestment = eventService.getTotalInvestmentInEvent(eventId);
            return ResponseEntity.ok(totalInvestment);
        }
    @PostMapping("/{eventId}/send-reminder-emails")
    public ResponseEntity<?> sendEventReminderEmails(@PathVariable int eventId) {
        eventService.sendEventReminderEmail(eventId);
        return ResponseEntity.ok().build();
    }
}



