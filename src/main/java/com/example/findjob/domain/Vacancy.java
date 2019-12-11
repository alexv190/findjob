package com.example.findjob.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String company;
    private String position;
    private String vacancyText;

    public Vacancy() {
    }

    public Vacancy(String company, String position, String vacancytext) {
        this.company = company;
        this.position = position;
        this.vacancyText = vacancytext;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getVacancyText() {
        return vacancyText;
    }

    public void setVacancyText(String vacancyText) {
        this.vacancyText = vacancyText;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
