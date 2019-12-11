package com.example.findjob.repo;

import com.example.findjob.domain.Resume;
import org.springframework.data.repository.CrudRepository;

public interface ResumeRepo extends CrudRepository<Resume, Long> {
}
