package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.commons.CommonAttributes;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public class User extends CommonAttributes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    @Nationalized
    private String name;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(LocalDateTime createdDate, LocalDateTime modifiedDate, Long id, String name) {
        super(createdDate, modifiedDate);
        this.id = id;
        this.name = name;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }
}
