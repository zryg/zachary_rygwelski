package com.github.britter.springbootherokudemo.repository;

import com.github.britter.springbootherokudemo.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by rygwelski on 9/26/16.
 */
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByDayId(Long dayId);
}
