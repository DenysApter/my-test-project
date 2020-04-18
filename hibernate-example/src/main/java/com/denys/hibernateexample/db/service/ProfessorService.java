package com.denys.hibernateexample.db.service;

import com.denys.hibernateexample.db.entity.Professor;
import com.denys.hibernateexample.db.repo.ProfessorRepo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@RequiredArgsConstructor
public class ProfessorService {
    @NonNull
    private ProfessorRepo professorRepo;

    @Transactional
    public List<Professor> test() {
        List<Professor> engProfessors = professorRepo.findAllEnglishTeachers();
        return engProfessors;
    }
}
