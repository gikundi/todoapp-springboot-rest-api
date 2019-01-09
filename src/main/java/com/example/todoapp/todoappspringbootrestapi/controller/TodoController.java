package com.example.todoapp.todoappspringbootrestapi.controller;


import com.example.todoapp.todoappspringbootrestapi.exception.ResourceNotFoundException;
import com.example.todoapp.todoappspringbootrestapi.model.Todo;
import com.example.todoapp.todoappspringbootrestapi.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Denis Gikundi on 02/01/19.
 */
@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    TodoRepository todoRepository;

    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @PostMapping("/todos")
    public Todo createTodo(@Valid @RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    @GetMapping("/todos/{id}")
    public Todo getTodoById(@PathVariable(value = "id") Long todoId) {
        return todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "id", todoId));
    }

    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable(value = "id") Long todoId,
                           @Valid @RequestBody Todo todoDetails) {

        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "id", todoId));

        todo.setTask(todoDetails.getTask());
        todo.setDescription(todoDetails.getDescription());

        Todo updatedTodo= todoRepository.save(todo);
        return updatedTodo;
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable(value = "id") Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "id", todoId));

        todoRepository.delete(todo);

        return ResponseEntity.ok().build();
    }


}
