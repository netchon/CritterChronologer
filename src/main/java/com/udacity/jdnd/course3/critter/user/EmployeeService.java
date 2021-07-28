package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    Employee getEmployee(long id);
    Employee setAvailability(Set<DayOfWeek> dayOfWeekSet, long employeeId);
    List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, LocalDate localDate);
    List<Employee> findEmployeeByIdIn(List<Long> ids);
}
