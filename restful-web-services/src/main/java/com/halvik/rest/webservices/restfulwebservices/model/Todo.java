package com.halvik.rest.webservices.restfulwebservices.model;



import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "todo")
public class Todo {

    @Id
 	private long id;
	@Column(name = "username")
	private String username;
	@Column(name = "description")
	private String description;
	@Column(name = "target")
	private Date targetDate;
	@Column(name = "done")
	private boolean isDone;
}
