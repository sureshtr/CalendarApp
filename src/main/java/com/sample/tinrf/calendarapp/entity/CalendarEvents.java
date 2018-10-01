package com.sample.tinrf.calendarapp.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "CalendarEvents")
public class CalendarEvents {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "name")
    @JsonBackReference
    private Calendar calendar;

    @Column(name = "title", nullable = true)
    private String title;

    @Column(name = "eventDateTime", nullable = true)
    private LocalDateTime eventDateTime;

    @Column(name = "location", nullable = true)
    private String location;

    @Column(name = "attendees", nullable = true)
    private int attendees;

    @Column(name = "reminderTime", nullable = true)
    private LocalTime reminderTime;

    @Column(name = "isSent", nullable = true)
    private boolean isSent;

    public CalendarEvents(Calendar calendar, String title, LocalDateTime eventDateTime, String location, int attendees, LocalTime reminderTime, boolean isSent) {

        this.calendar = calendar;
        this.title = title;
        this.eventDateTime = eventDateTime;
        this.location = location;
        this.attendees = attendees;
        this.reminderTime = reminderTime;
        this.isSent = isSent;
    }


    public CalendarEvents() {
        super();
    }


    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAttendees() {
        return attendees;
    }

    public void setAttendees(int attendees) {
        this.attendees = attendees;
    }

    public LocalTime getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(LocalTime reminderTime) {
        this.reminderTime = reminderTime;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    @Override
    public String toString() {
        return "CalendarEvents{" +
                "calendar=" + calendar +
                ", title='" + title + '\'' +
                ", eventDateTime=" + eventDateTime +
                ", location='" + location + '\'' +
                ", attendees=" + attendees +
                ", reminderTime=" + reminderTime +
                ", isSent=" + isSent +
                '}';
    }
}
