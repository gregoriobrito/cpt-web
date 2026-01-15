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
        // ... (seu código de cadastrar existente continua aqui igual) ...
        if (nomeRacha == null || nomeRacha.trim().isEmpty()) {
            // validações
        }

        Racha racha = new Racha();
        racha.setNome(nomeRacha);
        racha.setSituacao(1); // ativo
        racha = rachaRepository.save(racha);

        RachaUsuario rachaUsuario = new RachaUsuario();
        rachaUsuario.setCodigoUsuario(codigoUsuario);
        rachaUsuario.setCodigoRacha(racha.getCodigo());
        rachaUsuario.setSituacao(1);
        rachaUsuario.setFlagAdministrador("S");
        rachaUsuario = rachaUsuarioRepository.save(rachaUsuario);

        return rachaUsuario;
    }

    @Transactional
    public void excluir(Long codigoRacha, Long codigoUsuarioSolicitante) {
        RachaUsuario vinculo = rachaUsuarioRepository.findByRachaUsuario(codigoRacha, codigoUsuarioSolicitante);

        if (vinculo == null) {
            throw new RuntimeException("Você não faz parte deste grupo.");
        }

        if (!"S".equalsIgnoreCase(vinculo.getFlagAdministrador())) {
            throw new RuntimeException("Apenas o administrador pode excluir o grupo.");
        }

        Racha racha = rachaRepository.findById(codigoRacha)
                .orElseThrow(() -> new RuntimeException("Racha não encontrado."));

        racha.setSituacao(2);
        rachaRepository.save(racha);
    }
}