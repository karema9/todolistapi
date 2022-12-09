package com.example.todolistapi.controllers;


import com.example.todolistapi.models.Todo;
import com.example.todolistapi.models.TodoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos/")
public class TodoController {

    private final TodoRepository repository;

    public TodoController(TodoRepository todorepository){
         this.repository = todorepository;
    }
    @PostMapping
    public Todo createTodo(@RequestBody Todo todo){
        return this.repository.save(todo);
    }

    @GetMapping
    public List<Todo> getTodos(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Todo> getTodo(@PathVariable("id") Long id){
        var todo = repository.findById(id);
        return todo;
    }

    @PutMapping("/{id}")
    public Optional<Todo> updateTodo(@PathVariable("id") Long id, @RequestBody Todo updatedTodo){
        return this.repository.findById(id).map(oldTodo -> this.repository.save(updatedTodo));
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable("id") Long id){
        this.repository.deleteById(id);
    }
}
