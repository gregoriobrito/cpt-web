package br.com.aptare.cpt.controller;

import br.com.aptare.cpt.dto.PartidaDTO;
import br.com.aptare.cpt.entity.Partida;
import br.com.aptare.cpt.entity.VwPartida;
import br.com.aptare.cpt.repository.PartidaRepository;
import br.com.aptare.cpt.repository.VwPartidaRepository;
import br.com.aptare.cpt.request.AtualizarPontoRequest;
import br.com.aptare.cpt.request.PartidaRequest;
import br.com.aptare.cpt.service.PartidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partida")
@RequiredArgsConstructor
public class PartidaController {

    private final PartidaRepository partidaRepository;
    private final PartidaService partidaService;
    private final VwPartidaRepository vwPartidaRepository;

    @GetMapping({"racha/{idRacha}", "racha/{idRacha}/{idPartida}"})
    public List<PartidaDTO> listarPartidas(
            @PathVariable Long idRacha,
            @PathVariable(name = "idPartida", required = false) Long idPartida) {

        return partidaRepository.listarPartidaRacha(idRacha, idPartida)
                .stream()
                .map(PartidaDTO::fromEntity)
                .toList();
    }

    @GetMapping({"v2/racha/{idRacha}", "v2/racha/{idRacha}/{idPartida}"})
    public List<VwPartida> listarPartidasVw(
            @PathVariable Long idRacha,
            @PathVariable(name = "idPartida", required = false) Long idPartida) {

        return vwPartidaRepository.listarPartidaRacha(idRacha, idPartida);
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

    @PostMapping("atualizarPonto")
    public Partida atualizarPonto(@RequestBody AtualizarPontoRequest request) {
        return partidaService.atualizarPonto(request);
    }

    @PostMapping("excluir")
    public void excluir(@RequestBody PartidaRequest request) {
        int afetadas = partidaRepository.excluir(request.getCodigo());
        if (afetadas == 0) {
            throw new RuntimeException("Partida não encontrada para excluir: " + request.getCodigo());
        }
    }

}
