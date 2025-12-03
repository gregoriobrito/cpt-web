package br.com.aptare.cpt.controller;

import br.com.aptare.cpt.entity.Partida;
import br.com.aptare.cpt.entity.Racha;
import br.com.aptare.cpt.repository.PartidaRepository;
import br.com.aptare.cpt.request.PartidaRequest;
import br.com.aptare.cpt.security.UserPrincipal;
import br.com.aptare.cpt.service.PartidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partida")
@RequiredArgsConstructor
public class PartidaController {

    private final PartidaRepository partidaRepository;
    private final PartidaService partidaService;

    @GetMapping("{id}")
    public List<Partida> listarPartidas(@PathVariable Long id) {
        return partidaRepository.listarPartidaRacha(id);
    }

    @PostMapping
    public Partida cadastrar(@RequestBody PartidaRequest request) {
        Partida retorno = partidaService.cadastrar(request);
        return retorno;
    }
}
