package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.commons.RecordNotFoundException;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{


    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
        return employee;
    }

    @Override
    public Employee setAvailability(Set<DayOfWeek> dayOfWeekSet, long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(RecordNotFoundException::new);
        employee.setDaysAvailable(dayOfWeekSet);

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, LocalDate localDate) {

        List <Employee> employeesAvailable = employeeRepository.findAll().stream()
                .filter(employee -> employee.getSkills().containsAll(skills)
                        && employee.getDaysAvailable().contains(localDate.getDayOfWeek()))
                .collect(Collectors.toList());

        return employeesAvailable;
    }

    @Override
    public List<Employee> findEmployeeByIdIn(List<Long> ids) {
        return employeeRepository.findEmployeeByIdIn(ids);
    }
}
