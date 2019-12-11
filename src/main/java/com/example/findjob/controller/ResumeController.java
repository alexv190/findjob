package com.example.findjob.controller;

import com.example.findjob.domain.Resume;
import com.example.findjob.repo.ResumeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResumeController {

    @Autowired
    private ResumeRepo resumeRepo;

    @GetMapping("/resumes")
    public String resumesList(Model model) {
        Iterable<Resume> resumes = resumeRepo.findAll();
        model.addAttribute("resumesList", resumes);
        return "resume/resumes";
    }

    @GetMapping("/resume/{resume_id}")
    public String resume(@PathVariable(name="resume_id") Resume resume, Model model) {
        model.addAttribute("resume", resume);
        return "resume/resume";
    }

    @GetMapping("/addResume")
    public String addResume() {
        return "resume/addresume";
    }

    @PostMapping("/addResume")
    public String addResume(@RequestParam String position, @RequestParam String fio, @RequestParam String resumeText, @RequestParam Integer salary, Model model) {
        Resume resume1 = new Resume(position, fio, resumeText, salary);
        resumeRepo.save(resume1);
        return "redirect:resumes";
    }
}
