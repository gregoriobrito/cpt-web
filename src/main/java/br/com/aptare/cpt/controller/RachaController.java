package br.com.aptare.cpt.controller;

import br.com.aptare.cpt.dto.RachaDTO;
import br.com.aptare.cpt.entity.Racha;
import br.com.aptare.cpt.entity.RachaUsuario;
import br.com.aptare.cpt.entity.Usuario;
import br.com.aptare.cpt.repository.RachaRepository;
import br.com.aptare.cpt.repository.RachaUsuarioRepository;
import br.com.aptare.cpt.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/racha")
@RequiredArgsConstructor
public class RachaController {

    private final RachaRepository rachaRepository;
    private final RachaUsuarioRepository rachaUsuarioRepository;

    @GetMapping
    public List<Racha> listarRacha() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal) auth.getPrincipal();

        return rachaRepository.listarPorUsuario(user.getId());
    }

    @GetMapping("v2")
    public List<RachaDTO> listarRachaV2() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal) auth.getPrincipal();

        return rachaUsuarioRepository.listarPorUsuario(user.getId());
    }

    @GetMapping("{id}")
    public RachaDTO get(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal) auth.getPrincipal();
        return rachaRepository.get(id, user.getId());
    }

    @GetMapping("usuario/{id}")
    public List<Usuario> listarUsuario(@PathVariable Long id) {
        // TODO validar se ta vinculado aquele racha

        // listar os rachas
        List<Usuario> listaUsuario = rachaRepository.listarUsuario(id);
        if (listaUsuario != null && !listaUsuario.isEmpty()) {
            for(Usuario elemento : listaUsuario) {
                elemento.setSenha(null);
            }
        }
        return listaUsuario;
    }
}
