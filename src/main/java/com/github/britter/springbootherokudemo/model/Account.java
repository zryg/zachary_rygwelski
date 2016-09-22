package com.github.britter.springbootherokudemo.model;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Collection;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    private String name;

    private Integer maxBench;

    private Integer maxSquat;

    private Integer maxDeadlift;

    @OneToMany
    private Collection<Workout> workouts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxBench() {
        return maxBench;
    }

    public void setMaxBench(Integer maxBench) {
        this.maxBench = maxBench;
    }

    public Integer getMaxSquat() {
        return maxSquat;
    }

    public void setMaxSquat(Integer maxSquat) {
        this.maxSquat = maxSquat;
    }

    public Integer getMaxDeadlift() {
        return maxDeadlift;
    }

    public void setMaxDeadlift(Integer maxDeadlift) {
        this.maxDeadlift = maxDeadlift;
    }

    public Collection<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(Collection<Workout> workouts) {
        this.workouts = workouts;
    }
}
