/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 */

// Write your code here
package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.todo.model.*;
import com.example.todo.service.*;

@RestController
public class TodoController {
    @Autowired
    public TodoH2Service tService;

    @GetMapping("/todos")
    public ArrayList<Todo> getAllTodo() {
        return tService.getAllTodo();

    }

    @PostMapping("/todos")
    public Todo addTodo(Todo todo) {
        return tService.addTodo(todo);

    }

    @GetMapping("/todos/{id}")
    public Todo getById(@PathVariable("id") int id) {
        return tService.getById(id);

    }

    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable("id") int id, @RequestBody Todo todo) {
        return tService.updateTodo(id, todo);
    }

    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable("id") int id) {
        tService.deleteTodo(id);

    }

}