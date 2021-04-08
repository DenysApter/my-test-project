package com.denys.hibernateexample.db.service;

import com.denys.hibernateexample.db.repo.GroupOfLessonRepo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupOfLessonService {
    @NonNull
    private GroupOfLessonRepo groupOfLessonRepo;
}
