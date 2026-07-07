package br.com.aptare.cpt.service;

import br.com.aptare.cpt.entity.Partida;
import br.com.aptare.cpt.entity.Time;
import br.com.aptare.cpt.entity.TimeUsuario;
import br.com.aptare.cpt.entity.Usuario;
import br.com.aptare.cpt.enums.PartidaSituacaoEnum;
import br.com.aptare.cpt.repository.PartidaRepository;
import br.com.aptare.cpt.repository.TimeRepository;
import br.com.aptare.cpt.repository.TimeUsuarioRepository;
import br.com.aptare.cpt.repository.UsuarioRepository;
import br.com.aptare.cpt.request.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartidaService {

    private final UsuarioRepository usuarioRepository;
    private final PartidaRepository partidaRepository;
    private final TimeRepository timeRepository;
    private final TimeUsuarioRepository timeUsuarioRepository;

    @Transactional
    public Partida cadastrar(PartidaRequest request) {

        // quantidade de partidas do dia para descricao
        LocalDate hoje = LocalDate.now();
        LocalDateTime ldtInicio = hoje.atStartOfDay();
        LocalDateTime ldtFim = hoje.atTime(23, 59, 59, 999_999_999);
        ZoneId zone = ZoneId.systemDefault();
        Date dataInicio = Date.from(ldtInicio.atZone(zone).toInstant());
        Date dataFim = Date.from(ldtFim.atZone(zone).toInstant());

        Integer quantidade = partidaRepository.quantidadePartida(request.getCodigoRacha(), dataInicio, dataFim);
        quantidade = quantidade + 1;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = ldtInicio.format(formatter);

        // cadastrar partida
        Partida partida = new Partida();
        partida.setCodigoRacha(request.getCodigoRacha());
        partida.setIdentificador(String.format("%02d", quantidade) + " - " + dataFormatada);
        partida.setData(new Date());
        partida.setSituacao(PartidaSituacaoEnum.CADASTRADO.getValor());

        partida = partidaRepository.save(partida);

        // cadastrar times da partida
        List<Long> codigoUsuarioIn = null;
        String nomeTime = "";
        String conector = "";
        TimeUsuario timeUsuarioAdd = null;
        String nomePartida = "";
        String conectorPartida = "";

        if (request.getListaTime() != null
                && !request.getListaTime().isEmpty()) {
            for (TimeRequest eTimeRequest : request.getListaTime()) {
                codigoUsuarioIn = new ArrayList<Long>();
                if (eTimeRequest.getListaUsuario() != null
                        && !eTimeRequest.getListaUsuario().isEmpty()) {
                    for (UsuarioRequest eUsuarioRequest : eTimeRequest.getListaUsuario()) {
                        codigoUsuarioIn.add(eUsuarioRequest.getCodigo());
                    }
                }

                nomeTime = "";
                conector = "";
                List<Usuario> listaUsuarioTime = usuarioRepository.listarIn(codigoUsuarioIn);
                for(Usuario eUsuario : listaUsuarioTime) {
                    nomeTime += conector + eUsuario.getApelido();
                    conector = "/";
                }

                Time time = new Time();
                time.setCodigoPartida(partida.getCodigo());
                time.setPontuacao(0);
                time.setPontuacaoExtra(0);
                time.setPontuacao2(0);
                time.setPontuacaoExtra2(0);
                time.setPontuacao3(0);
                time.setPontuacaoExtra3(0);
                time.setIdentificador(nomeTime);
                time.setSituacao(1);

                time = timeRepository.save(time);

                nomePartida += conectorPartida + nomeTime;
                conectorPartida = " x ";

                // cadastrar usuario do time
                for(Usuario eUsuario : listaUsuarioTime) {
                    timeUsuarioAdd = new TimeUsuario();
                    timeUsuarioAdd.setCodigoTime(time.getCodigo());
                    timeUsuarioAdd.setCodigoUsuario(eUsuario.getCodigo());
                    timeUsuarioAdd.setSituacao(1);

                    timeUsuarioRepository.save(timeUsuarioAdd);
                }
            }
        }

        partida.setIdentificador(nomePartida);
        partida = partidaRepository.save(partida);

        return partida;
    }

    @Transactional
    public Partida atualizarPonto(AtualizarPontoRequest request) {

        Long codigoPartida = null;

        if (request != null && request.getLista() != null &&  request.getLista().size() > 0) {
            for (DetalheAtualizarPontoRequest elemento : request.getLista()) {
                Time time = timeRepository.findById(elemento.getIdTime())
                        .orElseThrow(() -> new RuntimeException("Time não encontrado com id: " + elemento.getIdTime()));
                time.setPontuacao(elemento.getPontos());
                time.setPontuacaoExtra(elemento.getPontosExtra());
                time.setPontuacao2(elemento.getPontos2());
                time.setPontuacaoExtra2(elemento.getPontosExtra2());
                time.setPontuacao3(elemento.getPontos3());
                time.setPontuacaoExtra3(elemento.getPontosExtra3());

                timeRepository.save(time);
                codigoPartida = time.getCodigoPartida();
            }

        }

        return partidaRepository.findById(codigoPartida).orElseThrow(() -> new RuntimeException("Partida não encontrada"));

    }



}
