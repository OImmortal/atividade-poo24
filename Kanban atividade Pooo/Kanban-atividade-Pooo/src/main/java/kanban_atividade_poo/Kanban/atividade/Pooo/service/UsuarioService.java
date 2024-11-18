package kanban_atividade_poo.Kanban.atividade.Pooo.service;

import kanban_atividade_poo.Kanban.atividade.Pooo.model.Usuario;
import kanban_atividade_poo.Kanban.atividade.Pooo.model.dto.CreateUsuarioDTO;
import kanban_atividade_poo.Kanban.atividade.Pooo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario createUser(CreateUsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setToken(usuario.generateToken());

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findByCreadentials(CreateUsuarioDTO usuarioDTO) {
        List<Usuario> usuarios = findAll();
        for (Usuario usuario : usuarios) {
            if (usuarioDTO.getUsername().equals(usuario.getUsername()) && usuarioDTO.getPassword().equals(usuario.getPassword())) {
                return usuario;
            }
        }

        return null;
    }

    public Usuario findByToken(String token) {
        List<Usuario> usuarios = findAll();
        for (Usuario usuario : usuarios) {
            if (usuario.getToken().equals(token)) {
                return usuario;
            }
        }

        return null;
    }

}
