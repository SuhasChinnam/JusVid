package com.jusvid.JusVid.repository;



import com.jusvid.JusVid.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {

    Roles getByRoleName(String roleName);

    Optional<Roles> findByRoleName(String admin);
}

