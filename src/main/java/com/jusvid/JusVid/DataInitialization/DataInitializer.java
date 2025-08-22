package com.jusvid.JusVid.DataInitialization;

import com.jusvid.JusVid.model.Person;
import com.jusvid.JusVid.model.Roles;
import com.jusvid.JusVid.repository.PersonRepository;
import com.jusvid.JusVid.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.Timestamp;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RolesRepository rolesRepo;

    @Autowired
    private PersonRepository personRepo;

    @Override
    public void run(String... args) throws Exception {

        // Ensure ADMIN Role (id=1)
        Roles adminRole = rolesRepo.findByRoleName("ADMIN")
                .orElseGet(() -> {
                    Roles r = new Roles();
                    r.setRoleId(1);  // force id=1
                    r.setRoleName("ADMIN");
                    r.setCreatedBy("SYSTEM");
                    return rolesRepo.save(r);
                });

        // Ensure USER Role (id=2)
        Roles userRole = rolesRepo.findByRoleName("USER")
                .orElseGet(() -> {
                    Roles r = new Roles();
                    r.setRoleId(2);  // force id=2
                    r.setRoleName("USER");
                    r.setCreatedBy("SYSTEM");
                    return rolesRepo.save(r);
                });

        // Insert ADMIN if not exists
        if (personRepo.findByEmail("admin@jusvid.com").isEmpty()) {
            Person admin = new Person();
            admin.setName("Admin");
            admin.setEmail("any mail u want");
            admin.setMobileNumber("9999999999");
            admin.setPwd("your own password");
            admin.setRoles(adminRole);
            admin.setCreatedBy("SYSTEM");
            personRepo.save(admin);
        }
    }
}

