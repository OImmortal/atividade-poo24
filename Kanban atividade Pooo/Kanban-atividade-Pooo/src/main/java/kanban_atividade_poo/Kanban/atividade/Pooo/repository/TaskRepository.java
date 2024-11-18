package kanban_atividade_poo.Kanban.atividade.Pooo.repository;

import kanban_atividade_poo.Kanban.atividade.Pooo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
