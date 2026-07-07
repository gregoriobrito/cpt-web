package br.com.aptare.cpt.controller;

import br.com.aptare.cpt.dto.GrupoDTO;
import br.com.aptare.cpt.entity.RachaUsuario;
import br.com.aptare.cpt.repository.GrupoRepository;
import br.com.aptare.cpt.repository.RachaUsuarioRepository;
import br.com.aptare.cpt.request.GrupoAtualizarRequest;
import br.com.aptare.cpt.request.GrupoUsuarioRequest;
import br.com.aptare.cpt.security.UserPrincipal;
import br.com.aptare.cpt.service.GrupoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grupo")
@RequiredArgsConstructor
public class GrupoController {

    private final GrupoRepository grupoRepository;
    private final RachaUsuarioRepository rachaUsuarioRepository;
    private final GrupoService grupoService;

    @GetMapping("listar/{idRacha}")
    public ResponseEntity<?> listar(@PathVariable Long idRacha) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserPrincipal user = (UserPrincipal) auth.getPrincipal();

            RachaUsuario validar = rachaUsuarioRepository.findByRachaUsuario(idRacha, user.getId());
            if (validar == null) {
                throw new RuntimeException("Este usuário não tem acesso a este racha.");
            }

            return ResponseEntity.ok(grupoRepository.findWithListaTimeByCodigoRacha(idRacha)
                    .stream()
                    .map(GrupoDTO::fromEntity)
                    .toList());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    public ResponseEntity<?> atualizar(@RequestBody GrupoAtualizarRequest request) {
        try {
            grupoService.atualizarGrupo(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
        return null;
    }
}
