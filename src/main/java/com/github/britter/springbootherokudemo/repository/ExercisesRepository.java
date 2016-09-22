package com.github.britter.springbootherokudemo.repository;

import com.github.britter.springbootherokudemo.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by rygwelski on 9/22/16.
 */
@Repository
public interface ExercisesRepository extends JpaRepository<Exercise, Long>{
    List<Exercise> findByWorkoutId(Long workoutId);
}
