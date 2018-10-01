package com.sample.tinrf.calendarapp;


import com.sample.tinrf.calendarapp.entity.Calendar;
import com.sample.tinrf.calendarapp.entity.CalendarEvents;
import com.sample.tinrf.calendarapp.repository.CalendarEventsRepository;
import com.sample.tinrf.calendarapp.repository.CalendarRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CalendarControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    CalendarEventsRepository calendarEventsRepository;

    @Test
    public void greetingMessageTest() throws Exception {

        mvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Calendar App is up and running")));
    }

    @Test
    public void lastNameTest()
            throws Exception {

        mvc.perform(get("/listAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", Matchers.is("Birthday")));
    }

    @Test
    public void firstNameTest()
            throws Exception {

        mvc.perform(get("/listEvents"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title", Matchers.is("Birthday event")));
    }

    @Before
    public void setUp() throws Exception {

        calendarEventsRepository.deleteAllInBatch();
        calendarRepository.deleteAllInBatch();

        List<Calendar> calendars = populateCalendars();
        calendars.forEach(calendar -> calendarRepository.save(calendar));

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
