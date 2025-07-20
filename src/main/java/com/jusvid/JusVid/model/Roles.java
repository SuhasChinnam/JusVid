package com.jusvid.JusVid.model;

import com.jusvid.JusVid.model.Person;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Roles extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int roleId;

    private String roleName;
    @OneToMany(mappedBy = "roles")
    private Set<Person> persons = new HashSet<>();

}


