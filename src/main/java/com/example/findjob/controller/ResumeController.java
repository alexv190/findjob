package com.example.findjob.controller;

import com.example.findjob.domain.Resume;
import com.example.findjob.repo.ResumeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ResumeController {

    @Autowired
    private ResumeRepo resumeRepo;

    @GetMapping("/resumesList")
    public String resumesList(Model model) {
        Iterable<Resume> resumes = resumeRepo.findAll();
        model.addAttribute("resumesList", resumes);
        return "resume/resumesList";
    }

    @GetMapping("/resume/{resume_id}")
    public String resume(@PathVariable(name="resume_id") Resume resume, Model model) {
        model.addAttribute("resume", resume);
        return "resume/resume";
    }

    @GetMapping("/addResume")
    public String addResume() {
        return "resume/addResume";
    }

    @PostMapping("/addResume")
    public String addResume(@Valid Resume resume, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.mergeAttributes(ControllerUtils.getErrors(bindingResult));
            return "resume/addResume";
        }

        resumeRepo.save(resume);
        return "redirect:resumesList";
    }
}
