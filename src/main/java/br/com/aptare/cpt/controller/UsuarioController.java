package br.com.aptare.cpt.controller;

import br.com.aptare.cpt.entity.RachaUsuario;
import br.com.aptare.cpt.entity.Usuario;
import br.com.aptare.cpt.repository.RachaUsuarioRepository;
import br.com.aptare.cpt.repository.UsuarioRepository;
import br.com.aptare.cpt.request.*;
import br.com.aptare.cpt.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import br.com.aptare.cpt.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final RachaUsuarioRepository rachaUsuarioRepository;

    @PostMapping("cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody UsuarioCadastroRequest request) {
        try {
            Usuario novoUsuario = usuarioService.cadastrar(request);
            novoUsuario.setSenha(null);
            return ResponseEntity.ok(novoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> get() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserPrincipal user = (UserPrincipal) auth.getPrincipal();

            Usuario retorno = usuarioRepository.findById(user.getId()).orElseThrow(() -> new Exception("Usuário não encontrada"));
            retorno.setSenha(null);
            return ResponseEntity.ok(retorno);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping({"buscarLogin/{idRacha}/{login}"})
    public ResponseEntity<?> buscarLogin(@PathVariable Long idRacha, @PathVariable String login) {
        try {
            Usuario retorno = usuarioRepository.findByLogin(login.toUpperCase()).orElseThrow(() -> new Exception("Usuário não encontrado"));
            RachaUsuario rachaUsuario = rachaUsuarioRepository.findByRachaUsuario(idRacha, retorno.getCodigo());

            if (rachaUsuario != null) {
                throw new Exception("Este usuário já está vinculado a esse racha");
            }
            retorno.setSenha(null);
            return ResponseEntity.ok(retorno);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("vincularRacha")
    public ResponseEntity<?> vincularRacha(@RequestBody VincularRachaRequest request) {
        try {
            return ResponseEntity.ok(usuarioService.vincularRachar(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/recuperarSenha")
    public ResponseEntity<?> esqueciSenha(@RequestBody EsqueciSenhaRequest request) {
        try {
            String mensagem = usuarioService.recuperarSenha(request);
            return ResponseEntity.ok(mensagem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
      
    @PostMapping("desvincularRacha")
    public ResponseEntity<?> desvincularRacha(@RequestBody VincularRachaRequest request) {

        // TODO validar se o usuario que esta logado tem acesso adm do racha que vem do request
        try {
            rachaUsuarioRepository.desvincularRacha(request.getCodigoRacha(), request.getCodigoUsuario());
            return ResponseEntity.ok(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("tornarRetirarAdministrador")
    public ResponseEntity<?> tornarRetirarAdministrador(@RequestBody UsuarioAdministradorRequest request) {

        // TODO validar se o usuario que esta logado tem acesso adm do racha que vem do request
        try {
            rachaUsuarioRepository.tornarRetirarAdministrador(request.getCodigoRacha(), request.getCodigoUsuario(), request.getFlagAdministrador());
            return ResponseEntity.ok(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/alterarSenha")
    public ResponseEntity<?> alterarSenha(@RequestBody AlterarSenhaRequest request) {

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserPrincipal user = (UserPrincipal) auth.getPrincipal();

            usuarioService.alterarSenha(user.getId(), request);
            return ResponseEntity.ok("Senha alterada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}
