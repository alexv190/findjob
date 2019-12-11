package com.example.findjob.controller;

import com.example.findjob.domain.Vacancy;
import com.example.findjob.repo.VacancyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class VacancyController {

    @Autowired
    private VacancyRepo vacancyRepo;

    @GetMapping("/vacancies")
    public String vacanciesList(Map<String, Object> model) {
        Iterable<Vacancy> vacancies = vacancyRepo.findAll();

        model.put("vacanciesList", vacancies);
        return "vacancy/vacancies";
    }

    @GetMapping("/addVacancy")
    public String addResume() {
        return "vacancy/addvacancy";
    }

    @PostMapping("/addVacancy")
    public String addVacancy(@RequestParam String company, @RequestParam String position, @RequestParam String vacancyText, Map<String, Object> model) {
        Vacancy vacancy = new Vacancy(company, position, vacancyText);
        vacancyRepo.save(vacancy);

        Iterable<Vacancy> vacancies = vacancyRepo.findAll();
        model.put("vacanciesList", vacancies);
        return "redirect:/vacancies";
    }
}
