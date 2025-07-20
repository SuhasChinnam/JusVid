package com.jusvid.JusVid.repository;



import com.jusvid.JusVid.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person readByEmail(String email);
    Person findByEmail(String email);


}

