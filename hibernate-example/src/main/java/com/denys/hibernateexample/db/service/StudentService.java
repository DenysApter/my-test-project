package com.denys.hibernateexample.db.service;

import com.denys.hibernateexample.db.repo.StudentRepo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    @NonNull
    private StudentRepo studentRepo;
}
