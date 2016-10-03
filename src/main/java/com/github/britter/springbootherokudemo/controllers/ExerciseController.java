package com.github.britter.springbootherokudemo.controllers;

import com.github.britter.springbootherokudemo.model.Day;
import com.github.britter.springbootherokudemo.model.Exercise;
import com.github.britter.springbootherokudemo.model.Set;
import com.github.britter.springbootherokudemo.repository.DayRepository;
import com.github.britter.springbootherokudemo.repository.ExerciseRepository;
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
 * Created by rygwelski on 9/26/16.
 */
@Controller
@RequestMapping("/exercises")

public class ExerciseController {

    private ExerciseRepository exerciseRepository;
    private DayRepository dayRepository;

    @Autowired
    public ExerciseController(ExerciseRepository exerciseRepository, DayRepository dayRepository) {
        this.exerciseRepository = exerciseRepository;
        this.dayRepository = dayRepository;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String exercise(ModelMap model, @RequestParam Integer id) {
        List<Exercise> exercises = exerciseRepository.findByDayId(new Long(id));
//        List<Exercise> exercises = exerciseRepository.findAll();
        Day day = dayRepository.findOne(new Long(id));
        model.addAttribute("exercises", exercises);
        model.addAttribute("day", day);
        model.addAttribute("insertExercise", new Exercise());
        return "exercise";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String insertData(ModelMap model,
                             @ModelAttribute("insertExercise") @Valid Exercise exercise,
                             @RequestParam Integer id,
                             BindingResult result) {
        exercise.setId(null);
        Day day = dayRepository.findOne(new Long(id));
        exercise.setDay(day);
        if (!result.hasErrors()) {
            exerciseRepository.save(exercise);
        }
        return exercise(model, new Long(exercise.getDay().getId()).intValue());
    }

    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public String insertSet(ModelMap model,
                             @ModelAttribute("insertSet") @Valid Set set,
                             @RequestParam Integer id,
                             BindingResult result) {
        System.out.println("saving set");
        return "x";
    }
}
