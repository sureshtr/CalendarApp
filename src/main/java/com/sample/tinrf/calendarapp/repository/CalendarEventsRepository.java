package com.sample.tinrf.calendarapp.repository;

import com.sample.tinrf.calendarapp.entity.CalendarEvents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarEventsRepository extends JpaRepository<CalendarEvents, Long> {
}
