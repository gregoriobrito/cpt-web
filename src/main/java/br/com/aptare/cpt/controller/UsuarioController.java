package br.com.aptare.cpt.controller;

import br.com.aptare.cpt.entity.RachaUsuario;
import br.com.aptare.cpt.entity.Usuario;
import br.com.aptare.cpt.repository.RachaUsuarioRepository;
import br.com.aptare.cpt.repository.UsuarioRepository;
import br.com.aptare.cpt.request.*;
import br.com.aptare.cpt.security.UserPrincipal;
import br.com.aptare.cpt.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final RachaUsuarioRepository rachaUsuarioRepository;
    private final String pathImagem = "/cpt/imagens/";

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

            retorno.setFlagImagem("N");
            Path path = Paths.get(pathImagem + retorno.getCodigo() + ".jpg");
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
                retorno.setFlagImagem("S");
            }

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

    @PostMapping("alterar")
    public ResponseEntity<?> alterar(@RequestBody UsuarioAlterarRequest request) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserPrincipal user = (UserPrincipal) auth.getPrincipal();

            request.setCodigo(user.getId());
            usuarioService.alterar(request);
            return ResponseEntity.ok("Perfil atualizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("imagemPerfil/{id}")
    public ResponseEntity<Resource> imagemPerfil(@PathVariable Long id) {

        try {
            Path path = Paths.get(pathImagem + id + ".jpg");

            Resource resource = new UrlResource(path.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);

        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("imagemPerfil")
    public ResponseEntity<?> uploadFoto(@RequestParam("foto") MultipartFile arquivo) {

        try {
            if (arquivo.isEmpty()) {
                return ResponseEntity.badRequest().body("Arquivo vazio");
            }

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserPrincipal user = (UserPrincipal) auth.getPrincipal();

            String nomeArquivo = user.getId() + ".jpg";

            Path caminho = Paths.get(pathImagem, nomeArquivo);
            Files.write(caminho, arquivo.getBytes());

            return ResponseEntity.ok("Foto salva com sucesso");
        }
        catch (IOException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}
