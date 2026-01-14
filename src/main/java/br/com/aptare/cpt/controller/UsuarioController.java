package br.com.aptare.cpt.controller;

import br.com.aptare.cpt.entity.RachaUsuario;
import br.com.aptare.cpt.entity.Usuario;
import br.com.aptare.cpt.repository.RachaUsuarioRepository;
import br.com.aptare.cpt.request.EsqueciSenhaRequest;
import br.com.aptare.cpt.request.UsuarioCadastroRequest;
import br.com.aptare.cpt.request.VincularRachaRequest;
import br.com.aptare.cpt.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import br.com.aptare.cpt.repository.UsuarioRepository;
import br.com.aptare.cpt.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final RachaUsuarioRepository rachaUsuarioRepository;

    @PostMapping("cadastrar")
    public Usuario cadastrar(@RequestBody UsuarioCadastroRequest request) {
        Usuario novoUsuario = usuarioService.cadastrar(request);
        novoUsuario.setSenha(null);
        return novoUsuario;
    }

    @GetMapping
    public Usuario get() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal) auth.getPrincipal();

        Usuario retorno = usuarioRepository.findById(user.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_CONTENT, "Usuário não encontrada"));
        retorno.setSenha(null);
        return retorno;
    }

    @GetMapping({"buscarLogin/{idRacha}/{login}"})
    public Usuario buscarLogin(@PathVariable Long idRacha, @PathVariable String login) {

        Usuario retorno = usuarioRepository.findByLogin(login.toUpperCase()).orElseThrow(() -> new RuntimeException("Usuário não encontrada"));
        RachaUsuario rachaUsuario = rachaUsuarioRepository.findByRachaUsuario(idRacha, retorno.getCodigo());

        if (rachaUsuario != null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_CONTENT,
                    "Este usuário já está vinculado a esse racha");
        }

        retorno.setSenha(null);
        return retorno;
    }

    @PostMapping("vincularRacha")
    public RachaUsuario vincularRacha(@RequestBody VincularRachaRequest request) {
        return usuarioService.vincularRachar(request);
    }

    @PostMapping("/esqueci_senha")
    public ResponseEntity<String> esqueciSenha(@RequestBody EsqueciSenhaRequest request) {
        String mensagem = usuarioService.recuperarSenha(request);
        return ResponseEntity.ok(mensagem);
      
    @PostMapping("desvincularRacha")
    public VincularRachaRequest desvincularRacha(@RequestBody VincularRachaRequest request) {
        rachaUsuarioRepository.desvincularRacha(request.getCodigoRacha(), request.getCodigoUsuario());
        return request;
    }

}
