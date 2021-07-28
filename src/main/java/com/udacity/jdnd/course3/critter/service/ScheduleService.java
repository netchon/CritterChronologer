package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.schedule.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    Schedule createSchedule(Schedule schedule);
    List<Schedule> getAllSchedule();
    List<Schedule> getScheduleForEmployee(Long employeeId);
    List<Schedule> getScheduleForPet(Long petId);
    List<Schedule> getScheduleForCustomer(Long customerId);
}
