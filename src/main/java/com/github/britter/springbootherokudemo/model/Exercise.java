package com.github.britter.springbootherokudemo.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by rygwelski on 9/22/16.
 */
@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @ManyToOne
    private Day day;
    @OneToMany
    private Collection<Set> sets;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Collection<Set> getSets() {
        return sets;
    }

    public void setSets(Collection<Set> sets) {
        this.sets = sets;
    }
}
