package com.test.demo.repo;

import com.test.demo.models.Role;
import org.springframework.data.repository.CrudRepository;


public interface RoleRepo extends CrudRepository<Role, Integer> {

}
