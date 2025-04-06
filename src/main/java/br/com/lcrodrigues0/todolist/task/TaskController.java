package br.com.lcrodrigues0.todolist.task;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/task")
public class TaskController {

    @GetMapping("/")
    public String taskWorking() {
        return "Task controller is working!";
    }   
}