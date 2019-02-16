package com.halvik.rest.webservices.restfulwebservices.repository;

import com.halvik.rest.webservices.restfulwebservices.model.Employee;
import com.halvik.rest.webservices.restfulwebservices.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{

}
