package br.com.aptare.cpt.service;

import br.com.aptare.cpt.entity.Racha;
import br.com.aptare.cpt.entity.RachaUsuario;
import br.com.aptare.cpt.repository.RachaRepository;
import br.com.aptare.cpt.repository.RachaUsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RachaService {

    private final RachaRepository rachaRepository;
    private final RachaUsuarioRepository rachaUsuarioRepository;

    @Transactional
    public RachaUsuario cadastrar(String nomeRacha, Long codigoUsuario) {

        if (nomeRacha == null || nomeRacha.trim().isEmpty()) {
            //TODO validar que o nome do racha nao pode vir vazio
        }

        if (codigoUsuario == null) {
            //TODO validar que o codigo do usuario nao pode vir vazio
        }

        Racha racha = new Racha();
        racha.setNome(nomeRacha);
        racha.setSituacao(1); // ativo

        racha = rachaRepository.save(racha);

        RachaUsuario rachaUsuario = new RachaUsuario();
        rachaUsuario.setCodigoUsuario(codigoUsuario);
        rachaUsuario.setCodigoRacha(racha.getCodigo());
        rachaUsuario.setSituacao(1); // ativo
        rachaUsuario.setFlagAdministrador("S");

        rachaUsuario = rachaUsuarioRepository.save(rachaUsuario);

        return rachaUsuario;
    }
}
