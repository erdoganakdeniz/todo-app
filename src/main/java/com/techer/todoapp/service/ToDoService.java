package com.techer.todoapp.service;

import com.techer.todoapp.entity.ToDoItem;
import com.techer.todoapp.payload.request.ToDoDTO;

import java.util.List;

public interface ToDoService {

    ToDoItem createToDo(ToDoDTO toDoDTO);
    List<ToDoItem> getAllToDoItem(String userId);

    void deleteToDoItemById(String id);
    ToDoItem checkToDoItem(String id);
    ToDoItem updateToDoItem(String id, ToDoDTO toDoDTO);

}
