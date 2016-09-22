package com.github.britter.springbootherokudemo.controllers;

import com.github.britter.springbootherokudemo.model.Exercise;
import com.github.britter.springbootherokudemo.model.Workout;
import com.github.britter.springbootherokudemo.repository.ExercisesRepository;
import com.github.britter.springbootherokudemo.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rygwelski on 9/22/16.
 */
@Controller
@RequestMapping("/exercises")
public class ExerciseController {

    private WorkoutRepository workoutRepository;
    private ExercisesRepository exercisesRepository;

    @Autowired
    public ExerciseController(WorkoutRepository workoutRepository, ExercisesRepository exercisesRepository) {
        this.workoutRepository = workoutRepository;
        this.exercisesRepository = exercisesRepository;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String exercise(ModelMap model, @RequestParam Integer id) {
        List<Exercise> exercises = exercisesRepository.findByWorkoutId(new Long(id));
        Workout workout = workoutRepository.findOne(new Long(id));
        model.addAttribute("exercises", exercises);
        model.addAttribute("workout", workout);
        model.addAttribute("insertExercise", new Exercise());
        return "exercise";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String insertData(ModelMap model,
                             @ModelAttribute("insertWorkout") @Valid Exercise exercise,
                             @RequestParam Integer id,
                             BindingResult result) {
        Workout workout = workoutRepository.findOne(new Long(id));
        exercise.setWorkout(workout);
        if (!result.hasErrors()) {
            exercisesRepository.save(exercise);
        }
        return exercise(model, new Long(exercise.getWorkout().getId()).intValue());
    }
}
