package com.sample.tinrf.calendarapp.service;

import com.sample.tinrf.calendarapp.entity.Calendar;
import com.sample.tinrf.calendarapp.entity.CalendarEvents;
import com.sample.tinrf.calendarapp.repository.CalendarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarService {

    private static final Logger logger = LoggerFactory.getLogger(CalendarService.class);


    @Autowired
    CalendarRepository calendarRepository;

    public List<Calendar> getAllCalendar() {
        return calendarRepository.findAll();
    }


    public List<CalendarEvents> getAllEvents() {
        List<CalendarEvents> calendarEvents = new ArrayList<>();
        List<Calendar> calendars = calendarRepository.findAll();
        for (Calendar calendar : calendars) {
            calendarEvents.add(calendar.getCalendarEvents());
        }
        return calendarEvents;
    }

    public List<CalendarEvents> getAllEventsforUser(String user) {
        List<CalendarEvents> calendarEvents = new ArrayList<>();
        List<Calendar> calendars = calendarRepository.findByUser(user);

        for (Calendar calendar : calendars) {
            calendarEvents.add(calendar.getCalendarEvents());
        }
        return calendarEvents;
    }

    public List<CalendarEvents> getAllEventsforUserByDate(String user, String date) {
        List<CalendarEvents> calendarEvents = new ArrayList<>();
        List<Calendar> calendars = calendarRepository.findByUser(user);
        DateTimeFormatter formatter = null;
        if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        } else if (date.matches("\\d{4}-\\d{2}")) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        }
        for (Calendar calendar : calendars) {
            LocalDateTime ldt = calendar.getCalendarEvents().getEventDateTime();

            if (ldt.format(formatter).equals(date)) {
                calendarEvents.add(calendar.getCalendarEvents());
            }
        }
        return calendarEvents;
    }


    public ResponseEntity<Calendar> save(Calendar calendarInfo) {
        try {
            Calendar calendar = calendarRepository.save(calendarInfo);
            return ResponseEntity.accepted().body(calendar);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    public ResponseEntity<Calendar> updateEvent(String user, String name, Calendar calendarInfo) {
        Calendar calendar = calendarRepository.findByUserEvent(user, name);
        if (calendar != null) {

            CalendarEvents calendarEvents = calendar.getCalendarEvents();
            calendarEvents.setAttendees(calendarInfo.getCalendarEvents().getAttendees());
            calendarEvents.setEventDateTime(calendarInfo.getCalendarEvents().getEventDateTime());
            calendarEvents.setLocation(calendarInfo.getCalendarEvents().getLocation());
            calendarEvents.setReminderTime(calendarInfo.getCalendarEvents().getReminderTime());
            calendarEvents.setTitle(calendarInfo.getCalendarEvents().getTitle());
            calendarEvents.setSent(calendarInfo.getCalendarEvents().isSent());
            return ResponseEntity.accepted().body(calendar);
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> deleteEvent(String user, String name) {
        Calendar calendar = calendarRepository.findByUserEvent(user, name);
        if (calendar != null) {
            calendarRepository.delete(calendar);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }
}
