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
    private int roleId;

    private String roleName;
    @OneToMany(mappedBy = "roles")
    private Set<Person> persons = new HashSet<>();

    public Roles() {
        // JPA needs this
    }

    public Roles(String roleName) {
        this.roleName = roleName;
    }

}


