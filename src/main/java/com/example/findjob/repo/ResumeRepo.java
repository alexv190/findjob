package com.example.findjob.repo;

import com.example.findjob.domain.Resume;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepo extends CrudRepository<Resume, Long> {
}
