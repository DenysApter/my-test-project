package com.denys.hibernateexample.db.service;

import com.denys.hibernateexample.db.repo.ExamsResultRepo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamsResultService {
    @NonNull
    private ExamsResultRepo examsResultRepo;
}
