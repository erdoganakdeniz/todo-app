package com.techer.todoapp;

import com.techer.todoapp.entity.ToDoItem;
import com.techer.todoapp.entity.User;
import com.techer.todoapp.payload.request.ToDoDTO;
import com.techer.todoapp.repository.ToDoRepository;
import com.techer.todoapp.service.ToDoService;
import com.techer.todoapp.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class ToDoServiceTest {
    @Mock
    private UserService userService;

    @Mock
    private ToDoRepository todoRepository;

    @InjectMocks
    private ToDoService todoService;




    @Test
    public void deleteTodoById_should_throw_exception_if_todo_not_found() {
        //given
        User user = new User();
        user.setId("123");
        user.setEmail("erdogan@gmail.com");

        String todoId = "1";

        BDDMockito
                .given(userService.getAuthenticatedUser())
                .willReturn(user);


        //when
        Throwable throwable = catchThrowable(() -> todoService.deleteToDoItemById(todoId));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Todo not found!");
    }



    @Test
    public void updateTodo_should_throw_exception_if_founded_todo_not_belong_to_authenticated_user() {
        //given
        User user = new User();
        user.setId("123456");
        user.setEmail("erdoganakdeniz@gmail.com");

        String todoId = "1";

        ToDoItem todo = new ToDoItem();
        todo.setItemId("1");
        todo.setUserId("123456");
        todo.setDescription("Spor Yap");

        ToDoDTO todoRequest = new ToDoDTO();
        todoRequest.setTodo("Spor Yap");

        BDDMockito
                .given(userService.getAuthenticatedUser())
                .willReturn(user);

        BDDMockito
                .given(todoRepository.findById(todoId))
                .willReturn(Optional.of(todo));

        //when
        Throwable throwable = catchThrowable(() -> todoService.updateToDoItem(todoId, todoRequest));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("This todo is not belong to this user.");
    }


    @Test
    public void checkTodo_should_throw_exception_if_todo_not_found() {
        //given
        User user = new User();
        user.setId("123");
        user.setEmail("erdoganakdeniz@gmail.com");

        String todoId = "1";

        BDDMockito
                .given(userService.getAuthenticatedUser())
                .willReturn(user);

        BDDMockito
                .given(todoRepository.findById(todoId))
                .willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> todoService.checkToDoItem(todoId));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Todo not found!");
    }

    @Test
    public void checkTodo_should_throw_exception_if_founded_todo_not_belong_to_authenticated_user() {
        //given
        User user = new User();
        user.setId("123456");
        user.setEmail("erdoganakdeniz@gmail.com");

        String todoId = "1";

        ToDoItem todo = new ToDoItem();
        todo.setItemId("1");
        todo.setUserId("123456");
        todo.setDescription("Spor Yap");

        BDDMockito
                .given(userService.getAuthenticatedUser())
                .willReturn(user);

        BDDMockito
                .given(todoRepository.findById(todoId))
                .willReturn(Optional.of(todo));

        //when
        Throwable throwable = catchThrowable(() -> todoService.checkToDoItem(todoId));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("This todo is not belong to this user.");
    }
}
