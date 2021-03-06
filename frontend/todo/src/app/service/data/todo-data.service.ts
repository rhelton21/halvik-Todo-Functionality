import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Todo } from '../../list-todos/list-todos.component';

@Injectable({
  providedIn: 'root'
})
export class TodoDataService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllTodos(username) {
	  console.log('retrieveAllTodos===>');
	  console.log('username===>'+username);
	  
    return this.http.get<Todo[]>(`http://localhost:8080/users/todos`);
    //console.log("Execute Hello World Bean Service")
  }

  deleteTodo(username, id){
    return this.http.delete(`http://localhost:8080/users/${id}`);
  }

  retrieveTodo(username, id){
    return this.http.get<Todo>(`http://localhost:8080/users/todos/${id}`);
  }

  updateTodo(username, id, todo){
    return this.http.put(
                `http://localhost:8080/users/todos/${id}`
                , todo);
  }

  createTodo(username, todo){
	  console.log('todo ===>'+todo);
	  
    return this.http.post(
                `http://localhost:8080/users/todos`
                , todo);
  }

}
