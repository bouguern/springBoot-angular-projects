package com.example.demo.Repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AppRole;
import com.example.demo.model.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<AppRole, Long>{
	
	Optional<AppRole> findByName(RoleName roleName);

}
