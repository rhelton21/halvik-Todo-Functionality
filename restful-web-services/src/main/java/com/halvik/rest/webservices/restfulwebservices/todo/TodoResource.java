package com.halvik.rest.webservices.restfulwebservices.todo;

import com.halvik.rest.webservices.restfulwebservices.configuration.JobConfiguration;
import com.halvik.rest.webservices.restfulwebservices.configuration.job.PrintJob;
import com.halvik.rest.webservices.restfulwebservices.model.SchedulerDate;
import com.halvik.rest.webservices.restfulwebservices.model.Todo;
import com.halvik.rest.webservices.restfulwebservices.repository.SchedulerRepository;
import com.halvik.rest.webservices.restfulwebservices.repository.TodoRepository;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@CrossOrigin(origins="http://localhost:4200")
@RestController
public class TodoResource {

	@Autowired
	private TodoRepository todoService;

	@Autowired
    private SchedulerRepository schedulerRepository;

    @Autowired
    private Scheduler scheduler;

    @GetMapping("/users/todos")
	public List<Todo> getAllTodos(){
		return todoService.findAll();
	}

    @Autowired
    JobConfiguration jobConfiguration;


    @RequestMapping("/job")
    public void handleJob() throws Exception{
        jobConfiguration.job();
    }

    @RequestMapping("/scheduler")
    public void handleSchedule() throws Exception{

        SchedulerDate schedulerDate;
        schedulerDate = schedulerRepository.getOne(1l);
        LocalDateTime ldt = LocalDateTime.of(2016, Month.AUGUST, 22, 14, 30);
        JobDetail jobDetail = buildJobDetail();
        ZoneId zoneId = ZoneId.of( "America/New_York" );        //Zone information
        ZonedDateTime zdtNewYork = ldt.atZone( zoneId );     //Local time in Asia timezone
        Trigger trigger = buildJobTrigger(jobDetail, zdtNewYork);
        scheduler.scheduleJob(jobDetail, trigger);
        jobConfiguration.job();
    }


	@GetMapping("/users/todos/{id}")
	public Optional<Todo> getTodo(@PathVariable long id){
		return todoService.findById(id);
	}

	//DELETE /users/{username}/todos/{id}
	@DeleteMapping("/users/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable long id){
        /* Optional<Todo> todo = todoService.findById(id); */
        todoService.deleteById(id);

        return ResponseEntity.noContent().build();
	}
	

	//Edit/Update a Todo
	//PUT /users/{user_name}/todos/{todo_id}
	@PutMapping("/users/todos/{id}")
	public ResponseEntity<Todo> updateTodo(
			@PathVariable long id, @RequestBody Todo todo){
        System.out.println("todo ===>"+todo);

		Todo todoUpdated = todoService.save(todo);
		
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}

	// Create Todo
	@PostMapping("/users/todos")
	public ResponseEntity<Void> updateTodo(@RequestBody Todo todo){
		System.out.println("todo ===>"+todo);
		Todo createdTodo = todoService.save(todo);
		
		//Location
		//Get current resource url
		///{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

    private JobDetail buildJobDetail() {
        JobDataMap jobDataMap = new JobDataMap();
      return JobBuilder.newJob(PrintJob.class)
                .withIdentity(UUID.randomUUID().toString(), "email-jobs")
                .withDescription("Send Email Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }
    private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "job")
                .withDescription("Print Job")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }

}
