// Write your code here

package com.example.todo.repository;

import java.util.*;
import com.example.todo.model.Todo;

public interface TodoRepository {
    ArrayList<Todo> getAllTodo();

    Todo addTodo(Todo todo);

    Todo getById(int id);

    Todo updateTodo(int id, Todo todo);

    void deleteTodo(int id);

}