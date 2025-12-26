package br.com.aptare.cpt.service;

import br.com.aptare.cpt.entity.RachaUsuario;
import br.com.aptare.cpt.entity.Usuario;
import br.com.aptare.cpt.repository.RachaUsuarioRepository;
import br.com.aptare.cpt.repository.UsuarioRepository;
import br.com.aptare.cpt.request.UsuarioCadastroRequest;
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

        if (request.getLogin() != null && usuarioRepository.findByLogin(request.getLogin()).isPresent()) {
            throw new RuntimeException("Login já existente.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setApelido(request.getApelido());
        usuario.setLogin(request.getLogin());

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
}