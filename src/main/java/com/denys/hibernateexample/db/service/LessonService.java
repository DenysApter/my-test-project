package com.denys.hibernateexample.db.service;

import com.denys.hibernateexample.db.repo.LessonRepo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {
    @NonNull
    private LessonRepo lessonRepo;
}
