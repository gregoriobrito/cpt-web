package br.com.aptare.cpt.controller;

import br.com.aptare.cpt.dto.RachaDTO;
import br.com.aptare.cpt.dto.UsuarioDTO;
import br.com.aptare.cpt.entity.Racha;
import br.com.aptare.cpt.entity.RachaUsuario;
import br.com.aptare.cpt.entity.Usuario;
import br.com.aptare.cpt.repository.RachaRepository;
import br.com.aptare.cpt.repository.RachaUsuarioRepository;
import br.com.aptare.cpt.repository.TimeUsuarioRepository;
import br.com.aptare.cpt.request.RachaCadastrarRequest;
import br.com.aptare.cpt.security.UserPrincipal;
import br.com.aptare.cpt.service.RachaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/racha")
@RequiredArgsConstructor
public class RachaController {

    private final RachaRepository rachaRepository;
    private final RachaUsuarioRepository rachaUsuarioRepository;
    private final RachaService rachaService;
    private final TimeUsuarioRepository timeUsuarioRepository;

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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal) auth.getPrincipal();

        RachaUsuario rachaUsuario = rachaUsuarioRepository.findByRachaUsuario(id, user.getId());

        if (rachaUsuario == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Este usuário não tem acesso a este racha");
        }

        // listar os rachas
        List<Usuario> listaUsuario = rachaRepository.listarUsuario(id);
        if (listaUsuario != null && !listaUsuario.isEmpty()) {
            for(Usuario elemento : listaUsuario) {
                elemento.setSenha(null);
            }
        }
        return listaUsuario;
    }

    @GetMapping("usuario/v2/{id}")
    public List<UsuarioDTO> listarUsuarioV2(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal) auth.getPrincipal();

        RachaUsuario rachaUsuario = rachaUsuarioRepository.findByRachaUsuario(id, user.getId());

        if (rachaUsuario == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Este usuário não tem acesso a este racha");
        }

        return rachaRepository.listarUsuarioV2(id);
    }

    @PostMapping
    public void cadastrar(@RequestBody RachaCadastrarRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal) auth.getPrincipal();

        rachaService.cadastrar(request.getNome(), user.getId());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal) auth.getPrincipal();

        rachaService.excluir(id, user.getId());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("informacoesUsuario/{id}")
    public ResponseEntity<?> informacoesUsuario(@PathVariable Long id) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserPrincipal user = (UserPrincipal) auth.getPrincipal();
            return ResponseEntity.ok(timeUsuarioRepository.informacoesUsuario(id, user.getId()));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}
