package com.halvik.rest.webservices.restfulwebservices.todo;

import com.halvik.rest.webservices.restfulwebservices.model.Todo;
import com.halvik.rest.webservices.restfulwebservices.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class TodoResource {

	@Autowired
	private TodoRepository todoService;

	@GetMapping("/users/todos")
	public List<Todo> getAllTodos(){
		return todoService.findAll();
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
		
}
