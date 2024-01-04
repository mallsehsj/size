/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 *
 */

// Write your code here
package com.example.todo.service;

import com.example.todo.model.*;
import com.example.todo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class TodoH2Service implements TodoRepository {
    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Todo> getAllTodo() {
        List<Todo> list = db.query("select * from todolist", new TodoRowMapper());
        ArrayList<Todo> all = new ArrayList<>(list);
        return all;

    }

    @Override

    public Todo getById(int id) {

        try {
            Todo todo1 = db.queryForObject("select * from todolist where id=?", new TodoRowMapper(), id);
            return todo1;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Todo addTodo(Todo todo) {
        db.update("insert into todolist(todo, status, priority) values(?,?,?)", todo.getTodo(), todo.getStatus(),
                todo.getPriority());
        return db.queryForObject("select * from todolist where id=?", new TodoRowMapper(), todo.getId());
    }

    @Override

    public void deleteTodo(int id) {
        db.update("delete from todolist where id=?", id);

    }

    @Override
    public Todo updateTodo(int id, Todo todo) {
        if (todo.getTodo() != null) {
            db.update("update todolist set todo=? where id=?", todo.getTodo(), id);
        }
        if (todo.getStatus() != null) {
            db.update("update todolist set status=? where id=?", todo.getStatus(), id);
        }
        if (todo.getPriority() != null) {
            db.update("update todolist set priority=? where id=?", todo.getPriority(), id);
        }
        try {
            return db.queryForObject("select * from todolist where id=?", new TodoRowMapper(), id);

        } catch (Exception a) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

}
