package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;
    private final PetRepository petRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, EmployeeRepository employeeRepository, PetRepository petRepository) {
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
        this.petRepository = petRepository;
    }

    @Override
    public Schedule createSchedule(Schedule schedule) {
        Schedule ScheduleSaved = scheduleRepository.save(schedule);

        return ScheduleSaved;
    }



    @Override
    public List<Schedule> getAllSchedule() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules;
    }

    @Override
    public List<Schedule> getScheduleForEmployee(Long employeeId) {

        List<Schedule> schedules = scheduleRepository.findByEmployees_Id(employeeId);

        return schedules;
    }

    @Override
    public List<Schedule> getScheduleForPet(Long petId) {
        List<Schedule> schedules = scheduleRepository.findByPets_Id(petId);
        return schedules;
    }

    @Override
    public List<Schedule> getScheduleForCustomer(Long customerId) {
        List<Schedule> schedules = scheduleRepository.findByPets_Customer_Id(customerId);
        return schedules;
    }

}
