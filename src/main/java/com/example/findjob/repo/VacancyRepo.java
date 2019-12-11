package com.example.findjob.repo;

import com.example.findjob.domain.Vacancy;
import org.springframework.data.repository.CrudRepository;

public interface VacancyRepo extends CrudRepository<Vacancy, Long> {
}
