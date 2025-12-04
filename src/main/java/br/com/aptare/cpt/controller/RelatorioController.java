package br.com.aptare.cpt.controller;

import br.com.aptare.cpt.dto.RelatorioDTO;
import br.com.aptare.cpt.dto.ResultadoAgrupadoDTO;
import br.com.aptare.cpt.dto.ResultadoDTO;
import br.com.aptare.cpt.repository.RelatorioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/relatorio")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioRepository relatorioRepository;

    @GetMapping("geral/{idRacha}")
    public RelatorioDTO relatorioGeral(@PathVariable Long idRacha) {
        RelatorioDTO retorno = null;

        List<ResultadoDTO> listaBanco = relatorioRepository.listarRelatorio(idRacha);

        if (listaBanco != null
                && !listaBanco.isEmpty()) {
            retorno = new RelatorioDTO();
            retorno.setNomeRelatorio("Estatística Geral");

            ResultadoAgrupadoDTO add = new ResultadoAgrupadoDTO();
            add.setAgrupador("CAPIVARA CARRAPATO");
            add.setListaResultado(listaBanco);

            retorno.setLista(new ArrayList<ResultadoAgrupadoDTO>());
            retorno.getLista().add(add);
        }

        return retorno;
    }

    @GetMapping("data/{idRacha}")
    public RelatorioDTO relatorioData(@PathVariable Long idRacha) {
        RelatorioDTO retorno = null;

        List<ResultadoDTO> listaBanco = relatorioRepository.listarRelatorioData(idRacha);

        String agrupador = "";
        if (listaBanco != null
                && !listaBanco.isEmpty()) {
            retorno = new RelatorioDTO();
            retorno.setNomeRelatorio("Estatística por Data");
            retorno.setLista(new ArrayList<ResultadoAgrupadoDTO>());

            ResultadoAgrupadoDTO add = null;
            for (ResultadoDTO elemento : listaBanco) {
                if (!agrupador.equals(elemento.getAgrupador())) {
                    if (add != null) {
                        retorno.getLista().add(add);
                    }
                    agrupador = elemento.getAgrupador();
                    add = new ResultadoAgrupadoDTO();
                    add.setAgrupador(elemento.getAgrupador());
                    add.setListaResultado(new ArrayList<ResultadoDTO>());
                }
                add.getListaResultado().add(elemento);
            }

            if (add != null) {
                retorno.getLista().add(add);
            }
        }

        return retorno;
    }

}
