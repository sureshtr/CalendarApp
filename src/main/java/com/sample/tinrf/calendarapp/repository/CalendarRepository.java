package com.sample.tinrf.calendarapp.repository;

import com.sample.tinrf.calendarapp.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalendarRepository  extends JpaRepository<Calendar, Long> {

    public List<Calendar> findByUser(String user);

    @Query("select c from Calendar c where c.user=?1 AND c.name=?2")
    public Calendar findByUserEvent(String user, String name);


}
