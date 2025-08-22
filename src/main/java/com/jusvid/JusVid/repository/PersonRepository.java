package com.jusvid.JusVid.repository;



import com.jusvid.JusVid.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person readByEmail(String email);


    Optional<Person> findByEmail(String email);



}

