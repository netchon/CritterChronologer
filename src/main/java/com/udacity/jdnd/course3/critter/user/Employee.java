package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.commons.CommonAttributes;
import com.udacity.jdnd.course3.critter.schedule.Schedule;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee extends User {

    @Column(name = "skills")
    @Enumerated(value = EnumType.STRING)
    @ElementCollection
    private Set<EmployeeSkill> skills;

    @Column(name = "days_available")
    @Enumerated(value = EnumType.STRING)
    @ElementCollection
    private Set<DayOfWeek> daysAvailable;


    public Employee(LocalDateTime createdDate, LocalDateTime modifiedDate, Long id, String nome, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
        super(createdDate, modifiedDate, id, nome);
        this.skills = skills;
        this.daysAvailable = daysAvailable;

    }

    public Employee() {
    }


    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
