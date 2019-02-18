package com.halvik.rest.webservices.restfulwebservices.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "scheduler_date")
public class SchedulerDate {
    @Id
    private long id;
    @Column(name = "target")
    private Date targetDate;
}
