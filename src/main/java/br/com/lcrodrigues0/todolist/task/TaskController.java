package br.com.lcrodrigues0.todolist.task;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private ITaskRepository taskRepository;

    @GetMapping("/")
    public String working() {
        return "Task controller is working!";
    }   

    @PostMapping("/")
    public TaskModel create(@RequestBody TaskModel task, HttpServletRequest request) {
        task.setUserId((UUID)request.getAttribute("userId"));

        var taskCreated = taskRepository.save(task);
        return taskCreated;
    }   
}