package br.com.aptare.cpt.controller;

import br.com.aptare.cpt.entity.Usuario;
import br.com.aptare.cpt.repository.UsuarioRepository;
import br.com.aptare.cpt.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @GetMapping
    public Usuario get() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal) auth.getPrincipal();

        Usuario retorno = usuarioRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("Usuário não encontrada"));
        retorno.setSenha(null);

        return retorno;
    }

}
