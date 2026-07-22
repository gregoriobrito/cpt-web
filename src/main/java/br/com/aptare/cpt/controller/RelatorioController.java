package br.com.aptare.cpt.controller;

import br.com.aptare.cpt.dto.RelatorioDTO;
import br.com.aptare.cpt.dto.ResultadoAgrupadoDTO;
import br.com.aptare.cpt.dto.ResultadoDTO;
import br.com.aptare.cpt.entity.Racha;
import br.com.aptare.cpt.repository.RachaRepository;
import br.com.aptare.cpt.repository.RelatorioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/relatorio")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioRepository relatorioRepository;
    private final RachaRepository rachaRepository;
    private final String pathImagem = "/cpt/imagens/";

    @GetMapping("geral/{idRacha}")
    public RelatorioDTO relatorioGeral(@PathVariable Long idRacha) {
        RelatorioDTO retorno = null;

        List<ResultadoDTO> listaBanco = relatorioRepository.listarRelatorio(idRacha);

        if (listaBanco != null
                && !listaBanco.isEmpty()) {
            retorno = new RelatorioDTO();
            retorno.setNomeRelatorio("Estatística Geral");

            Racha racha = rachaRepository.findById(idRacha).orElseThrow(() -> new RuntimeException("Racha não encontrado"));

            ResultadoAgrupadoDTO add = new ResultadoAgrupadoDTO();
            add.setAgrupador(racha.getNome());
            add.setListaResultado(listaBanco);

            retorno.setLista(new ArrayList<ResultadoAgrupadoDTO>());
            retorno.getLista().add(add);
        }

        return carregarImagemUsuario(retorno);
    }

    @GetMapping("data/{idRacha}")
    public RelatorioDTO relatorioData(@PathVariable Long idRacha) {
        List<ResultadoDTO> listaBanco = relatorioRepository.listarRelatorioData(idRacha);
        return agrupar(listaBanco, "Estatística por Data");
    }

    @GetMapping("mes/{idRacha}")
    public RelatorioDTO relatorioMes(@PathVariable Long idRacha) {
        List<ResultadoDTO> listaBanco = relatorioRepository.listarRelatorioMes(idRacha);
        return agrupar(listaBanco, "Estatística por Mês");
    }

    @GetMapping("ano/{idRacha}")
    public RelatorioDTO relatorioAno(@PathVariable Long idRacha) {
        List<ResultadoDTO> listaBanco = relatorioRepository.listarRelatorioAno(idRacha);
        return agrupar(listaBanco, "Estatística por Ano");
    }

    private RelatorioDTO agrupar(List<ResultadoDTO> listaBanco, String nomeRelatorio) {
        RelatorioDTO retorno = null;

        String agrupador = "";
        if (listaBanco != null
                && !listaBanco.isEmpty()) {
            retorno = new RelatorioDTO();
            retorno.setNomeRelatorio(nomeRelatorio);
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

        return carregarImagemUsuario(retorno);
    }

    private RelatorioDTO carregarImagemUsuario(RelatorioDTO relatorio) {
        Resource resource = null;
        Path path = null;

        if (relatorio != null
                && relatorio.getLista() != null
                && !relatorio.getLista().isEmpty()) {
            for (ResultadoAgrupadoDTO eAgrupado : relatorio.getLista()) {

                if (eAgrupado != null
                        && !eAgrupado.getListaResultado().isEmpty()) {
                    for (ResultadoDTO usuario : eAgrupado.getListaResultado()) {
                        path = Paths.get(pathImagem + usuario.getCodigo() + ".jpg");
                        try {
                            resource = new UrlResource(path.toUri());
                            if (resource.exists()) usuario.setFlagImagem("S");
                        } catch (Exception e) {}
                    }
                }

            }
        }

        return relatorio;
    }

}
