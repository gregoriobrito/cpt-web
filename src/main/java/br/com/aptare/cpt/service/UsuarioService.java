package br.com.aptare.cpt.service;

import br.com.aptare.cpt.entity.RachaUsuario;
import br.com.aptare.cpt.entity.Usuario;
import br.com.aptare.cpt.repository.RachaUsuarioRepository;
import br.com.aptare.cpt.repository.UsuarioRepository;
import br.com.aptare.cpt.request.UsuarioCadastroRequest;
import br.com.aptare.cpt.request.VincularRachaRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RachaUsuarioRepository rachaUsuarioRepository; // Injetamos o novo repository
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario cadastrar(UsuarioCadastroRequest request) {

        if (request.getLogin() != null && usuarioRepository.findByLogin(request.getLogin().toUpperCase().trim()).isPresent()) {
            throw new RuntimeException("Login já existente.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome().toUpperCase().trim());
        usuario.setApelido(request.getApelido().toUpperCase().trim());
        usuario.setLogin(request.getLogin().toUpperCase().trim());

        String senhaParaSalvar = (request.getSenha() != null && !request.getSenha().isEmpty())
                ? request.getSenha()
                : "123456";
        usuario.setSenha(passwordEncoder.encode(senhaParaSalvar));

        usuario.setSituacao(1); // Ativo
        usuario = usuarioRepository.save(usuario);

        if (request.getCodigoRacha() != null) {
            RachaUsuario vinculo = new RachaUsuario();
            vinculo.setCodigoRacha(request.getCodigoRacha());
            vinculo.setCodigoUsuario(usuario.getCodigo());
            vinculo.setSituacao(1);
            vinculo.setFlagAdministrador("N");

            rachaUsuarioRepository.save(vinculo);
        }

        return usuario;
    }

    @Transactional
    public RachaUsuario vincularRachar(VincularRachaRequest request) {

        Usuario retorno = usuarioRepository.findById(request.getCodigoUsuario()).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        RachaUsuario rachaUsuario = rachaUsuarioRepository.findByRachaUsuario(request.getCodigoRacha(), retorno.getCodigo());

        if (rachaUsuario != null) {
            throw new RuntimeException("Este usuário já está vinculado a este racha");
        }

        rachaUsuario = new RachaUsuario();
        rachaUsuario.setCodigoRacha(request.getCodigoRacha());
        rachaUsuario.setCodigoUsuario(request.getCodigoUsuario());
        rachaUsuario.setFlagAdministrador("N");
        rachaUsuario.setSituacao(1); // ativo

        rachaUsuarioRepository.save(rachaUsuario);

        return rachaUsuario;
    }
}