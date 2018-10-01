package com.sample.tinrf.calendarapp;

import com.sample.tinrf.calendarapp.entity.Calendar;
import com.sample.tinrf.calendarapp.entity.CalendarEvents;
import com.sample.tinrf.calendarapp.repository.CalendarEventsRepository;
import com.sample.tinrf.calendarapp.repository.CalendarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class CalendarApp implements CommandLineRunner {

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    CalendarEventsRepository calendarEventsRepository;

    private static final Logger logger = LoggerFactory.getLogger(CalendarApp.class);


    public static void main(String[] args) {
        SpringApplication.run(CalendarApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        calendarEventsRepository.deleteAllInBatch();
        calendarRepository.deleteAllInBatch();

        List<Calendar> calendars = populateCalendars();
        calendars.forEach(calendar -> calendarRepository.save(calendar));

        logger.info("Calendar found with findAll()");
        calendarRepository.findAll().forEach(calendar ->
                logger.info(calendar.toString() + " \t " + calendar.getCalendarEvents())
        );
    }

    private List<Calendar> populateCalendars() {

        List<Calendar> calendars = new ArrayList<>();

        Calendar calendar = new Calendar("Birthday", "Suresh");
        CalendarEvents calendarEvents = new CalendarEvents(calendar, "Birthday event", LocalDateTime.of(2018, Month.OCTOBER, 4, 10, 10, 10), "atlanta", 2, LocalTime.of(23, 59), true);
        calendar.setCalendarEvents(calendarEvents);
        calendars.add(calendar);

        Calendar calendar1 = new Calendar("Birthday2", "Suresh");
        CalendarEvents calendarEvents1 = new CalendarEvents(calendar1, "Birthday event 2", LocalDateTime.of(2018, Month.OCTOBER, 7, 10, 10, 10), "atlanta", 2, LocalTime.of(23, 59), true);
        calendar1.setCalendarEvents(calendarEvents1);
        calendars.add(calendar1);

        Calendar calendar2 = new Calendar("Birthday3", "Suresh");
        CalendarEvents calendarEvents2 = new CalendarEvents(calendar2, "Birthday event 3", LocalDateTime.of(2018, Month.OCTOBER, 25, 10, 10, 10), "atlanta", 2, LocalTime.of(23, 59), true);
        calendar2.setCalendarEvents(calendarEvents2);
        calendars.add(calendar2);

        Calendar calendar3 = new Calendar("Birthday", "Kumar");
        CalendarEvents calendarEvents3 = new CalendarEvents(calendar3, "Birthday event", LocalDateTime.of(2018, Month.OCTOBER, 4, 10, 10, 10), "atlanta", 2, LocalTime.of(23, 59), true);
        calendar3.setCalendarEvents(calendarEvents3);
        calendars.add(calendar3);

        Calendar calendar4 = new Calendar("Birthday2", "Kumar");
        CalendarEvents calendarEvents4 = new CalendarEvents(calendar4, "Birthday event 2", LocalDateTime.of(2018, Month.OCTOBER, 7, 10, 10, 10), "atlanta", 2, LocalTime.of(23, 59), true);
        calendar4.setCalendarEvents(calendarEvents4);
        calendars.add(calendar4);

        Calendar calendar5 = new Calendar("Birthday3", "Kumar");
        CalendarEvents calendarEvents5 = new CalendarEvents(calendar5, "Birthday event 3", LocalDateTime.of(2018, Month.OCTOBER, 25, 10, 10, 10), "atlanta", 2, LocalTime.of(23, 59), true);
        calendar5.setCalendarEvents(calendarEvents5);
        calendars.add(calendar5);

        return calendars;
    }

}
