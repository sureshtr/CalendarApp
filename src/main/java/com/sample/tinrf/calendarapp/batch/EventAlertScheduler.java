package com.sample.tinrf.calendarapp.batch;


import com.sample.tinrf.calendarapp.entity.Calendar;
import com.sample.tinrf.calendarapp.repository.CalendarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventAlertScheduler {

    private static final Logger logger = LoggerFactory.getLogger(EventAlertScheduler.class);

    @Autowired
    CalendarRepository calendarRepository;

    @Scheduled(fixedRate = 2000 * 60)
    public void alertEvents() {
        List<Calendar> calendars = calendarRepository.findAll();
        calendars.forEach(calendar -> logger.info("Dear " + calendar.getUser() + " You have an event" + calendar.getCalendarEvents().getTitle() + " on " + calendar.getCalendarEvents().getEventDateTime()));
    }

}
