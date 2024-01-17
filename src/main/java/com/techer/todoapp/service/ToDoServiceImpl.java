package com.techer.todoapp.service;

import com.techer.todoapp.entity.ToDoItem;
import com.techer.todoapp.entity.User;
import com.techer.todoapp.payload.request.ToDoDTO;
import com.techer.todoapp.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ToDoServiceImpl implements ToDoService{

    private final ToDoRepository toDoRepository;
    private final UserService userService;


    public ToDoServiceImpl(ToDoRepository toDoRepository, UserService userService) {
        this.toDoRepository = toDoRepository;
        this.userService = userService;
    }

    @Override
    public ToDoItem createToDo(ToDoDTO toDoDTO) {
        User user=userService.getAuthenticatedUser();
        ToDoItem toDoItem=new ToDoItem();
        toDoItem.setUserId(user.getId());
        toDoItem.setItemId(UUID.randomUUID().toString());
        toDoItem.setCreatedAt(Instant.now());
        toDoItem.setStatus(false);
        toDoItem.setDescription(toDoDTO.getTodo());
        return toDoRepository.save(toDoItem);

    }


    @Override
    public List<ToDoItem> getAllToDoItem(String userId) {
        return toDoRepository.findByUserId(userId);
    }

    @Override
    public void deleteToDoItemById(String id) {
        toDoRepository.deleteById(id);

    }

    @Override
    public ToDoItem checkToDoItem(String id) {
        ToDoItem toDoItem=toDoRepository.findById(id).get();
        toDoItem.setStatus(true);
        toDoItem.setUpdatedAt(Instant.now());
        return toDoRepository.save(toDoItem);

    }

    @Override
    public ToDoItem updateToDoItem(String id, ToDoDTO toDoDTO) {
        ToDoItem toDoItem=toDoRepository.findById(id).get();
        toDoItem.setDescription(toDoDTO.getTodo());
        return toDoRepository.save(toDoItem);

    }
}
