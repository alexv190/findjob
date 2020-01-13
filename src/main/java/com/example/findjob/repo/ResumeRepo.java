package com.example.findjob.repo;

import com.example.findjob.domain.Resume;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ResumeRepo extends CrudRepository<Resume, Long> {
    @Query("from Resume r where lower(r.resumeText) like lower(concat('%', :search, '%'))")
    Collection<Resume> searchInResumeList(@Param("search") String search);
}
