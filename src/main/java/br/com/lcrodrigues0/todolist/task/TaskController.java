package br.com.lcrodrigues0.todolist.task;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private ITaskRepository taskRepository;

    // @GetMapping("/")
    // public String working() {
    //     return "Task controller is working!";
    // }   

    @SuppressWarnings("rawtypes")
    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel task, HttpServletRequest request) {
        task.setUserId((UUID)request.getAttribute("userId"));

        var currentDate = LocalDateTime.now();

        if(currentDate.isAfter(task.getStartAt()) || currentDate.isAfter(task.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date.");

        } else if (task.getStartAt().isAfter(task.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date range.");
        }

        var taskCreated = taskRepository.save(task);

        return ResponseEntity.status(HttpStatus.OK).body(taskCreated);
    }   

    @GetMapping("/")
    public ResponseEntity list(HttpServletRequest request) {
        var taskList = taskRepository.findAllByUserId((UUID) request.getAttribute("userId"));

        return ResponseEntity.status(HttpStatus.OK).body(taskList);
    }
    
}