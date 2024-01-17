package com.techer.todoapp.controller;

import com.techer.todoapp.entity.ToDoItem;
import com.techer.todoapp.payload.request.ToDoDTO;
import com.techer.todoapp.service.ToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
public class ToDoController {

    private final ToDoService toDoService;


    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<ToDoItem>> getAllToDoByUserId(@PathVariable("userId") String userId){
        return ResponseEntity.ok(toDoService.getAllToDoItem(userId));
    }

    @PostMapping
    public ResponseEntity<ToDoItem> save(@RequestBody ToDoDTO toDoDTO){
        return ResponseEntity.ok(toDoService.createToDo(toDoDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        toDoService.deleteToDoItemById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDoItem> update(@PathVariable("id") String id, @RequestBody ToDoDTO toDoDTO){
        return ResponseEntity.ok(toDoService.updateToDoItem(id,toDoDTO));
    }

    @PostMapping("/todo/{id}/check")
    public ResponseEntity<ToDoItem> check(@PathVariable String id){
        return ResponseEntity.ok(toDoService.checkToDoItem(id));
    }
}
