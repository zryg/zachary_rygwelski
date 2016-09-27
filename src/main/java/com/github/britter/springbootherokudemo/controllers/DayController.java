package com.github.britter.springbootherokudemo.controllers;

import com.github.britter.springbootherokudemo.model.Day;
import com.github.britter.springbootherokudemo.model.Workout;
import com.github.britter.springbootherokudemo.repository.DayRepository;
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
@RequestMapping("/days")
public class DayController {

    private WorkoutRepository workoutRepository;
    private DayRepository dayRepository;

    @Autowired
    public DayController(WorkoutRepository workoutRepository, DayRepository dayRepository) {
        this.workoutRepository = workoutRepository;
        this.dayRepository = dayRepository;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String day(ModelMap model, @RequestParam Integer id) {
        List<Day> days = dayRepository.findByWorkoutId(new Long(id));
        Workout workout = workoutRepository.findOne(new Long(id));
        model.addAttribute("days", days);
        model.addAttribute("workout", workout);
        model.addAttribute("insertDay", new Day());
        return "day";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String insertData(ModelMap model,
                             @ModelAttribute("insertDay") @Valid Day day,
                             @RequestParam Integer id,
                             BindingResult result) {
        day.setId(null);
        Workout workout = workoutRepository.findOne(new Long(id));
        day.setWorkout(workout);
        if (!result.hasErrors()) {
            dayRepository.save(day);
        }
        return day(model, new Long(day.getWorkout().getId()).intValue());
    }
}
