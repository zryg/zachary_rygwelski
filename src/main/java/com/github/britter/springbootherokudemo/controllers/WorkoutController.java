package com.github.britter.springbootherokudemo.controllers;

import com.github.britter.springbootherokudemo.model.Account;
import com.github.britter.springbootherokudemo.model.Workout;
import com.github.britter.springbootherokudemo.repository.AccountRepository;
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
 * Created by rygwelski on 9/21/16.
 */
@Controller
@RequestMapping("/workouts")
public class WorkoutController {

    private WorkoutRepository repository;
    private AccountRepository accountRepository;

    @Autowired
    public WorkoutController(WorkoutRepository repository, AccountRepository accountRepository) {
        this.repository = repository;
        this.accountRepository = accountRepository;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String workout(ModelMap model, @RequestParam Integer id) {
        List<Workout> workouts = repository.findByAccountId(new Long(id));
        model.addAttribute("workouts", workouts);
        model.addAttribute("insertWorkout", new Workout());
        return "workout";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String insertData(ModelMap model,
                             @ModelAttribute("insertWorkout") @Valid Workout workout,
                             @RequestParam Integer id,
                             BindingResult result) {
        Account account = accountRepository.findOne(new Long(id));
        workout.setAccount(account);
        if (!result.hasErrors()) {
            repository.save(workout);
        }
        return workout(model, new Long(workout.getAccount().getId()).intValue());
    }
}
