package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final EmployeeService employeeService;
    private final PetService petService;

    public ScheduleController(ScheduleService scheduleService, EmployeeService employeeService, PetService petService) {
        this.scheduleService = scheduleService;
        this.employeeService = employeeService;
        this.petService = petService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule scheduleConverted = convertScheduleDtoToEntity(scheduleDTO);
        Schedule scheduleSaved = scheduleService.createSchedule(scheduleConverted);
        return convertScheduleEntityToDto(scheduleSaved);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {

        List<Schedule> schedules = scheduleService.getAllSchedule();
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();

        for(Schedule schedule : schedules){
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            scheduleDTO = convertScheduleEntityToDto(schedule);
            scheduleDTOS.add(scheduleDTO);
        }

        return scheduleDTOS;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.getScheduleForPet(petId);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();

        for(Schedule schedule : schedules){
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            scheduleDTO = convertScheduleEntityToDto(schedule);
            scheduleDTOS.add(scheduleDTO);
        }

        return scheduleDTOS;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.getScheduleForEmployee(employeeId);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();

        for(Schedule schedule : schedules){
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            scheduleDTO = convertScheduleEntityToDto(schedule);
            scheduleDTOS.add(scheduleDTO);
        }

        return scheduleDTOS;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.getScheduleForCustomer(customerId);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();

        for(Schedule schedule : schedules){
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            scheduleDTO = convertScheduleEntityToDto(schedule);
            scheduleDTOS.add(scheduleDTO);
        }

        return scheduleDTOS;
    }

    private ScheduleDTO convertScheduleEntityToDto(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setActivities(schedule.getActivities());
        scheduleDTO.setDate(schedule.getScheduledDate());
        scheduleDTO.setEmployeeIds(schedule.getEmployees().stream()
                .map(employee -> employee.getId())
                .collect(Collectors.toList()));
        scheduleDTO.setPetIds(schedule.getPets().stream()
                .map(pet -> pet.getId())
                .collect(Collectors.toList()));
        return scheduleDTO;
    }

    private Schedule convertScheduleDtoToEntity(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        schedule.setActivities(scheduleDTO.getActivities());
        schedule.setScheduledDate(scheduleDTO.getDate());
        schedule.setEmployees(employeeService.findEmployeeByIdIn(scheduleDTO.getEmployeeIds()));
        schedule.setPets(petService.findPetByIdIn(scheduleDTO.getPetIds()));
        return schedule;
    }


}
