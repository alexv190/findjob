package com.example.findjob.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Укажите название компании")
    @Length(max = 255, message = "Длина должна быть меньше 255 символов")
    private String company;

    @NotBlank(message = "Укажите должность")
    @Length(max = 255, message = "Длина должна быть меньше 255 символов")
    private String position;

    @NotBlank(message = "Заполните текст вакансии")
    @Length(max = 2048, message = "Длина должна быть меньше 2048 символов")
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
