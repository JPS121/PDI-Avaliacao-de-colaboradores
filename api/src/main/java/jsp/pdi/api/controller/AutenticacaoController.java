package jsp.pdi.api.controller;

import jakarta.validation.Valid;
import jsp.pdi.api.domain.usuario.DadosAutencacao;
import jsp.pdi.api.domain.usuario.Usuario;
import jsp.pdi.api.infra.security.DadosTokenJWT;
import jsp.pdi.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager auth;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosAutencacao dados) {

        var authToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authResponse = auth.authenticate(authToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authResponse.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
