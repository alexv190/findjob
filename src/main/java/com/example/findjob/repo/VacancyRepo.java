package com.example.findjob.repo;

import com.example.findjob.domain.Vacancy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepo extends CrudRepository<Vacancy, Long> {
}
