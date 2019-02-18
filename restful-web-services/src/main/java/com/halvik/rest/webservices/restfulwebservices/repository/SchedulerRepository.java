package com.halvik.rest.webservices.restfulwebservices.repository;

import com.halvik.rest.webservices.restfulwebservices.model.SchedulerDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerRepository extends JpaRepository<SchedulerDate, Long>{

}
