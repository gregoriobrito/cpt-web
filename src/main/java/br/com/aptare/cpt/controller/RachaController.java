package br.com.aptare.cpt.controller;

import br.com.aptare.cpt.entity.Racha;
import br.com.aptare.cpt.repository.RachaRepository;
import br.com.aptare.cpt.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/racha")
@RequiredArgsConstructor
public class RachaController {

    private final RachaRepository rachaRepository;

    @GetMapping
    public List<Racha> listarRacha() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal) auth.getPrincipal();

        List<Racha> listaRacha = rachaRepository.listarPorUsuario(user.getId());
        return listaRacha;
    }
}
