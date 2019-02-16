package com.halvik.rest.webservices.restfulwebservices.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "todo")
public class Todo {

    private long id;
    private String userName;
    private String description;
    private Date target;
    private boolean done;

    public Todo() {

    }

    public Todo(String userName, String description, Date targetDate, boolean isDone) {
        this.userName = userName;
        this.description = description;
        this.target = targetDate;
        this.done = isDone;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "username", nullable = false)
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "description")
    public String getLastName() {
        return description;
    }
    public void setLastName(String description) {
        this.description = description;
    }

    @Column(name = "target")
    public Date getTarget() {
        return target;
    }
    public void setTarget(Date target) {
        this.target = target;
    }

    @Column(name = "done")
    public boolean getDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }

    //  @Override
  //  public String toString() {
   //     return "Employee [id=" + id + ", userName=" + userName + ", description=" + description + ", targetDate=" + targetDate
   //             + "]";
   // }

}
