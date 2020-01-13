package com.example.findjob.repo;

import com.example.findjob.domain.Vacancy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface VacancyRepo extends CrudRepository<Vacancy, Long> {
    @Query("from Vacancy v where lower(v.vacancyText) like lower(concat('%', :search, '%'))")
    Collection<Vacancy> searchInVacancyList(@Param("search") String search);
}
