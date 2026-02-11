package br.com.aptare.cpt.service;

import br.com.aptare.cpt.entity.Grupo;
import br.com.aptare.cpt.entity.GrupoUsuario;
import br.com.aptare.cpt.repository.GrupoRepository;
import br.com.aptare.cpt.repository.GrupoUsuarioRepository;
import br.com.aptare.cpt.request.GrupoAtualizarRequest;
import br.com.aptare.cpt.request.GrupoUsuarioRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class GrupoService {

    private final GrupoRepository grupoRepository;
    private final GrupoUsuarioRepository grupoUsuarioRepository;

    @Transactional
    public void atualizarGrupo(GrupoAtualizarRequest request) {

        Grupo grupo = new Grupo();

        if (request.getCodigoGrupo() != null) {
            grupo = grupoRepository.findById(request.getCodigoGrupo()).orElseThrow(() -> new RuntimeException("Grupo não encontrado"));
        }
        grupo.setDescricao(request.getDescricao());
        grupo.setSituacao(1);
        grupo.setDataInclusao(new Date());

        grupoRepository.save(grupo);

        // atualizar os usuarios
        if (request.getListaUsuario() != null
                && !request.getListaUsuario().isEmpty()) {
            GrupoUsuario grupoUsuario = null;

            for (GrupoUsuarioRequest usuarioRequest : request.getListaUsuario()) {
                if (usuarioRequest.getCodigo() != null) {
                    grupoUsuario = grupoUsuarioRepository.findById(usuarioRequest.getCodigo()).orElseThrow(
                            () -> new RuntimeException("Usuario grupo não encontrado."));
                }
            }
        }
    }

}
