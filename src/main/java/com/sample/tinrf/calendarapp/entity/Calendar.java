package com.sample.tinrf.calendarapp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Calendar",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "user"}))
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "user", nullable = false)
    private String user;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "calendar")
    @JsonManagedReference
    private CalendarEvents calendarEvents;


    public Calendar(String name, String user) {
        this.name = name;
        this.user = user;
    }

    public Calendar() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public CalendarEvents getCalendarEvents() {
        return calendarEvents;
    }

    public void setCalendarEvents(CalendarEvents calendarEvents) {
        this.calendarEvents = calendarEvents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calendar calendar = (Calendar) o;
        return Objects.equals(name, calendar.name) &&
                Objects.equals(user, calendar.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, user);
    }


    @Override
    public String toString() {
        return "Calendar{" +
                "name='" + name + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
