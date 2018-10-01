package com.sample.tinrf.calendarapp.controller;

import com.sample.tinrf.calendarapp.entity.Calendar;
import com.sample.tinrf.calendarapp.entity.CalendarEvents;
import com.sample.tinrf.calendarapp.repository.CalendarRepository;
import com.sample.tinrf.calendarapp.service.CalendarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CalendarController {
    private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);


    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    CalendarService calendarService;

    @GetMapping("/")
    public String getWelcomeMessage() {
        return "Calendar App is up and running";
    }


    @GetMapping("/listAll")
    public List<Calendar> getAllCalendar() {
        return calendarService.getAllCalendar();
    }
    @GetMapping("/listEvents")
    public List<CalendarEvents> getAllEvents() {
        return calendarService.getAllEvents();
    }

    @GetMapping("/listEvents/{user}")
    public List<CalendarEvents> getAllEventsforUser(@PathVariable(value = "user") String  user) {
        return calendarService.getAllEventsforUser(user);

    }
    @GetMapping("/listEvents/{user}/{date}")
    public List<CalendarEvents> getAllEventsforUserByDate(@PathVariable(value = "user") String  user,
                                                          @PathVariable(value = "date") String  date) {
        return calendarService.getAllEventsforUserByDate(user, date);
    }

    @PostMapping("/addEvent")
    public ResponseEntity<Calendar> createEvent(@Valid @RequestBody Calendar calendar) {
        return calendarService.save(calendar);
    }

    @PutMapping("/updateEvent/{user}/{name}")
    public ResponseEntity<Calendar> updateEvent(@PathVariable(value = "user") String  user,
                                @PathVariable(value = "name") String  name,
                                @Valid @RequestBody Calendar calendar) {
        return calendarService.updateEvent(user, name, calendar);
    }

    @DeleteMapping("/deleteEvent/{user}/{name}")
    public ResponseEntity<?> deleteEvent(@PathVariable(value = "user") String  user,
                                         @PathVariable(value = "name") String  name) {
        return calendarService.deleteEvent(user, name);
    }
}
