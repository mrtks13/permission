package com.yapikredi.permission.repository;

import com.yapikredi.permission.domain.entity.PublicHoliday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<PublicHoliday, Long> {

    List<PublicHoliday> findHolidaysByDateBetween(LocalDate startDate, LocalDate EndDate);
}
