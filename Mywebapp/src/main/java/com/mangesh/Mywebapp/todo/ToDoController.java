package com.mangesh.Mywebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class ToDoController {
	private TodoService todoservice;

	public ToDoController(TodoService todoservice) {
		super();
		this.todoservice = todoservice;
	}

	@RequestMapping("list-todos")
	public String listofToDos(ModelMap model) {
		String username = getUsername(model);
		List<ToDo> todos = todoservice.findbyUsername(username);
		model.addAttribute("todos", todos);
		return "listTodos";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String ShowNewTodoPage(ModelMap model) {
		String username = getUsername(model);
		ToDo AddTodo = new ToDo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("AddTodo", AddTodo);
		return "AddTodo";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String ADDNewTodo(ModelMap model, @Valid ToDo AddTodo, BindingResult a) {

		if (a.hasErrors()) {
			return "redirect:add-todo";
		}
		String username = getUsername(model);
		todoservice.AddTodo(username, AddTodo.getDescription(), AddTodo.getTargetDate(), false);
		return "redirect:list-todos";
	}

	@RequestMapping("delete-todo")
	public String DeleteTodo(@RequestParam int id) {
		todoservice.deletebyID(id);
		return "redirect:list-todos";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String showUpdateTodo(@RequestParam int id, ModelMap model) {
		ToDo AddTodo = todoservice.findByID(id);
		model.addAttribute("AddTodo", AddTodo);
		return "AddTodo";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid ToDo AddTodo, BindingResult a) {

		if (a.hasErrors()) {
			return "redirect:list-todos";
		}
		String username = getUsername(model);
		AddTodo.setUsername(username);
		todoservice.updateTodo(AddTodo);
		return "redirect:list-todos";
	}

	private String getUsername(ModelMap model) {
		Authentication A = SecurityContextHolder.getContext().getAuthentication();
		return A.getName();
	}

}
