package kanban_atividade_poo.Kanban.atividade.Pooo.controller;

import kanban_atividade_poo.Kanban.atividade.Pooo.model.Usuario;
import kanban_atividade_poo.Kanban.atividade.Pooo.model.dto.CreateUsuarioDTO;
import kanban_atividade_poo.Kanban.atividade.Pooo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // POST
    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody CreateUsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.createUser(usuarioDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody CreateUsuarioDTO usuarioDTO) {
        Usuario user = usuarioService.findByCreadentials(usuarioDTO);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(Map.of("token", user.getToken()));
    }
}
