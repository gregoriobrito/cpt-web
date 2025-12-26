package br.com.aptare.cpt.controller;

import br.com.aptare.cpt.entity.Usuario;
import br.com.aptare.cpt.request.UsuarioCadastroRequest;
import br.com.aptare.cpt.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public Usuario cadastrar(@RequestBody UsuarioCadastroRequest request) {
        Usuario novoUsuario = usuarioService.cadastrar(request);

        novoUsuario.setSenha(null);
        return novoUsuario;
    }
}