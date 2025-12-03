package br.com.aptare.cpt.service;

import br.com.aptare.cpt.entity.Partida;
import br.com.aptare.cpt.entity.Time;
import br.com.aptare.cpt.entity.TimeUsuario;
import br.com.aptare.cpt.entity.Usuario;
import br.com.aptare.cpt.repository.PartidaRepository;
import br.com.aptare.cpt.repository.TimeRepository;
import br.com.aptare.cpt.repository.TimeUsuarioRepository;
import br.com.aptare.cpt.repository.UsuarioRepository;
import br.com.aptare.cpt.request.PartidaRequest;
import br.com.aptare.cpt.request.TimeRequest;
import br.com.aptare.cpt.request.UsuarioRequest;
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
        partida.setSituacao(1);

        partida = partidaRepository.save(partida);

        // cadastrar times da partida
        List<Long> codigoUsuarioIn = null;
        String nomeTime = "";
        String conector = "";
        TimeUsuario timeUsuarioAdd = null;

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
                time.setNome(nomeTime);
                time.setSituacao(1);

                time = timeRepository.save(time);

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

        return partida;
    }
}
