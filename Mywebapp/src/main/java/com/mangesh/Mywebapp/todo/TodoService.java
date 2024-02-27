package com.mangesh.Mywebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
@Service
public class TodoService {
	private static List<ToDo> todos= new ArrayList<>();
	private static int Count=0;
	static {
		todos.add(new ToDo(++Count,"Mangesh","Learn SpringBoot",
				LocalDate.now().plusYears(1), false));
		
		todos.add(new ToDo(++Count,"Mangesh","Learn JAVA",
				LocalDate.now().plusYears(1), false));
		
		todos.add(new ToDo(++Count,"Mangesh","Learn ReactJS",
				LocalDate.now().plusYears(1), false));
		
		todos.add(new ToDo(++Count,"Mangesh","Learn Microservices",
				LocalDate.now().plusYears(2), false));
	}
	public List<ToDo> findbyUsername(String username){
		Predicate<? super ToDo> predicate =AddTodo -> AddTodo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}
	public void AddTodo(String username, String description, LocalDate targetDate, boolean completionstatus ) {
		ToDo AddTodo=new ToDo(++Count,username,description,targetDate,completionstatus);
		todos.add(AddTodo);
	}

	public void deletebyID(int id) {
		Predicate<? super ToDo> predicate =AddTodo -> AddTodo.getId()== id;
		todos.removeIf(predicate);
	}
	public ToDo findByID(int id) {
		Predicate<? super ToDo> predicate =AddTodo -> AddTodo.getId()== id;
	    ToDo AddTodo = todos.stream().filter(predicate).findFirst().get();
	    return AddTodo;
	}
	public void updateTodo(@Valid ToDo AddTodo) {
		deletebyID(AddTodo.getId());
		todos.add(AddTodo);
		
	}
}
