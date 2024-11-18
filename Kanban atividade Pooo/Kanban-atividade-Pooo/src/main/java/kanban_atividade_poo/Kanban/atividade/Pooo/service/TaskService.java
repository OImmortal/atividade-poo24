package kanban_atividade_poo.Kanban.atividade.Pooo.service;

import kanban_atividade_poo.Kanban.atividade.Pooo.model.Task;
import kanban_atividade_poo.Kanban.atividade.Pooo.model.enums.Priority;
import kanban_atividade_poo.Kanban.atividade.Pooo.model.enums.Status;
import kanban_atividade_poo.Kanban.atividade.Pooo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // GeT
    public List<Task> findAll() {
        List<Task> allTask = taskRepository.findAll();

        List<Task> finalList = new ArrayList<>();


        Priority[] priorities = Priority.values();
        for (Priority priority : priorities) {
            finalList.addAll(allTask.stream()
                    .filter(task -> task.getPriority() == priority)
                    .collect(Collectors.toList()));
        }

        finalList = allTask.stream()
                .sorted(Comparator.comparing(Task::getLimitDate))
                .collect(Collectors.toList());

        return finalList;
    }

    public Task findById(int id) {
        List<Task> tasks = taskRepository.findAll();
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }

        return null;
    }

    public List<Task> findByStatus(Status status) {
        List<Task> tasks = taskRepository.findAll();
        List<Task> filteredTasks = new ArrayList<>();

        List<Task> finalList = new ArrayList<>();


        for (Task task : tasks) {
            if (task.getStatus() == status) {
                filteredTasks.add(task);
            }
        }

        Priority[] priorities = Priority.values();
        for (Priority priority : priorities) {
            finalList.addAll(filteredTasks.stream()
                    .filter(task -> task.getPriority() == priority)
                    .collect(Collectors.toList()));
        }

        return finalList.reversed();
    }

    public List<Task> listTasksByStatus(Status status) {
        List<Task> tasks = findByStatus(status);

        List<Task> filteredTasks = new ArrayList<>();

        filteredTasks = tasks
                        .stream().filter(task -> {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate today = LocalDate.now();
                            LocalDate taskLimitDate = task.getLimitDate().toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate();
                            boolean date = today.isAfter(taskLimitDate);
                            return date;
                        })
                        .collect(Collectors.toList());

        return filteredTasks;
    }

    // Post
    public void save(Task task) {
        taskRepository.save(task);
    }

    //Update
    public Task updateTask(int id, Task task) {
        Task taskSelected = findById(id);
        taskSelected = task;
        taskSelected.setId(id);
        if (taskSelected != null) {
            taskRepository.save(taskSelected);
            return taskSelected;
        }

        return null;
    }

    public Task updateStatusTask(Status status, int id) {
        Task task = findById(id);
        if (task != null) {
           task.setStatus(status);
           return updateTask(id, task);
        }

        return null;
    }

    public Status moveTask(int id) {
        Task task = findById(id);
        if(task != null) {
            task.moveStatus();
            taskRepository.save(task);
        }

        return task.getStatus();
    }

    // Delete
    public int deleteTask(int id) {
        Task task = findById(id);
        if (task != null) {
            taskRepository.delete(task);
            return id;
        }
        return 0;
    }

}
