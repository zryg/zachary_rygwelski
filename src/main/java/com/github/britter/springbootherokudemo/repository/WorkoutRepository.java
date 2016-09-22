package com.github.britter.springbootherokudemo.repository;

import com.github.britter.springbootherokudemo.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by rygwelski on 9/21/16.
 */

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long>{

    List<Workout> findByAccountId(Long accountId);
}
