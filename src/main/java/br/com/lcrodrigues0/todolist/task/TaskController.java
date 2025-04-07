package br.com.lcrodrigues0.todolist.task;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import utils.Utils;





@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private ITaskRepository taskRepository;

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
    public ResponseEntity<TaskModel[]> list(HttpServletRequest request) {
        var taskList = taskRepository.findAllByUserId((UUID) request.getAttribute("userId"));

        return ResponseEntity.status(HttpStatus.OK).body(taskList);
    }

    @SuppressWarnings("rawtypes")
    @PatchMapping("/{id}")
    public ResponseEntity putMethodName(@PathVariable UUID id, @RequestBody TaskModel task, HttpServletRequest request) {
        Optional<TaskModel> previousTaskOpt = taskRepository.findById((UUID) id);

        if (previousTaskOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no task with the provided id.");

        } 

        TaskModel updatedTask = previousTaskOpt.get();

        Utils.copyNonNullProperties(task, updatedTask);

        taskRepository.save(updatedTask);
        
        return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
    }
    
}