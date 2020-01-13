package com.example.findjob.controller;

import com.example.findjob.domain.Vacancy;
import com.example.findjob.repo.VacancyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class VacancyController {

    @Autowired
    private VacancyRepo vacancyRepo;

    @GetMapping("/vacancy/{vacancy_id}")
    public String vacancy(@PathVariable(name="vacancy_id") Vacancy vacancy, Model model) {
        model.addAttribute("vacancy", vacancy);
        return "vacancy/vacancy";
    }

    @GetMapping("/vacanciesList")
    public String vacanciesList(@RequestParam(name = "search", required = false) String search, Model model) {
        Iterable<Vacancy> vacancies;
        if (search == null)
            vacancies = vacancyRepo.findAll();
        else {
            vacancies = vacancyRepo.searchInVacancyList(search);
            model.addAttribute("search", search);
        }

        model.addAttribute("vacanciesList", vacancies);
        return "vacancy/vacanciesList";
    }

    @GetMapping("/addVacancy")
    public String addResume() {
        return "vacancy/addVacancy";
    }

    @PostMapping("/addVacancy")
    public String addVacancy(@Valid Vacancy vacancy, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(ControllerUtils.getErrors(bindingResult));
            return "vacancy/addVacancy";
        }
        vacancyRepo.save(vacancy);

        return "redirect:/vacanciesList";
    }
}
