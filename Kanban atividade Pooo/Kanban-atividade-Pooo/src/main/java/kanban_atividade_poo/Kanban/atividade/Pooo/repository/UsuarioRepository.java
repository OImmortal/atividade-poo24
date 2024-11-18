package kanban_atividade_poo.Kanban.atividade.Pooo.repository;

import kanban_atividade_poo.Kanban.atividade.Pooo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
