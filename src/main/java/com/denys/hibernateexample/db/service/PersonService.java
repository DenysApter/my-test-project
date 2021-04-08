package com.denys.hibernateexample.db.service;


import com.denys.hibernateexample.db.repo.PersonRepo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class PersonService {

    @NonNull
    private PersonRepo personRepo;


}
