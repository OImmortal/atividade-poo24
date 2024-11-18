package kanban_atividade_poo.Kanban.atividade.Pooo.controller;

import kanban_atividade_poo.Kanban.atividade.Pooo.model.dto.GetStatusDTO;
import kanban_atividade_poo.Kanban.atividade.Pooo.model.Task;
import kanban_atividade_poo.Kanban.atividade.Pooo.model.enums.Status;
import kanban_atividade_poo.Kanban.atividade.Pooo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Visualizar todas as Task criadas
    @GetMapping
    public List<Task> getAllTask() {
        return taskService.findAll();
    }

    @GetMapping("/byStatus")
    public List<Task> getTaskByStatus(@RequestBody GetStatusDTO statusDTO) {
        return taskService.listTasksByStatus(statusDTO.getStatus());
    }

    // Criar Task
    @PostMapping
    public ResponseEntity saveTaks(@RequestBody Task task) {
        taskService.save(task);
        return new ResponseEntity(HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@RequestBody Task task, @PathVariable int id) {
        Task taskUpdated = taskService.updateTask(id, task);
        return new ResponseEntity<>(taskUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public int deleteTask(@PathVariable int id) {
        return taskService.deleteTask(id);
    }

    // Atualizar Status da Task
    @PutMapping
    @RequestMapping("/updateStatusTask/{id}/{status}")
    public Task updateStatusTaks(@PathVariable int id, @PathVariable Status status) {
        taskService.updateStatusTask(status, id);
        return taskService.findById(id);
    }

    @PutMapping
    @RequestMapping("/{id}/move")
    public ResponseEntity<String> moveTask(@PathVariable int id) {
        Status status = taskService.moveTask(id);
        return new ResponseEntity("Task movida para: " + status, HttpStatus.OK);
    }

}
