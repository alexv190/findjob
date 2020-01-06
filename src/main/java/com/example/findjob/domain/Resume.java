package com.example.findjob.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Укажите должность")
    @Length(max = 255, message = "Длина должна быть меньше 255 символов")
    private String position;

    @NotBlank(message = "Укажите ФИО")
    @Length(max = 255, message = "Длина должна быть меньше 255 символов")
    private String fio;

    @NotBlank(message = "Заполните резюме")
    @Length(max = 2048, message = "Длина должна быть меньше 2048 символов")
    private String resumeText;

    @Min(0)
    private Integer salary;

    public Resume() {
    }

    public Resume(String position, String fio, String resumeText, Integer salary) {
        this.position = position;
        this.fio = fio;
        this.resumeText = resumeText;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getResumeText() {
        return resumeText;
    }

    public void setResumeText(String resumeText) {
        this.resumeText = resumeText;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
