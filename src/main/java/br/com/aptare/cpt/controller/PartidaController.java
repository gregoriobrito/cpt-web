package br.com.aptare.cpt.controller;

import br.com.aptare.cpt.dto.PartidaDTO;
import br.com.aptare.cpt.entity.Partida;
import br.com.aptare.cpt.repository.PartidaRepository;
import br.com.aptare.cpt.request.PartidaRequest;
import br.com.aptare.cpt.service.PartidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/partida")
@RequiredArgsConstructor
public class PartidaController {

    private final PartidaRepository partidaRepository;
    private final PartidaService partidaService;

    @GetMapping({"racha/{idRacha}", "racha/{idRacha}/{idPartida}"})
    public List<PartidaDTO> listarPartidas(
            @PathVariable Long idRacha,
            @PathVariable(name = "idPartida", required = false) Long idPartida) {

        return partidaRepository.listarPartidaRacha(idRacha, idPartida)
                .stream()
                .map(PartidaDTO::fromEntity)
                .toList();
    }

    @PostMapping
    public Partida cadastrar(@RequestBody PartidaRequest request) {
        return partidaService.cadastrar(request);
    }

    @GetMapping("{id}")
    public Partida get(@PathVariable Long id) {
        return partidaRepository.findWithListaTimeByCodigo(id)
                .orElseThrow(() -> new RuntimeException("Partida não encontrada"));
    }

}
