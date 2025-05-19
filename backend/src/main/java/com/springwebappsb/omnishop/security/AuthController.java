package com.springwebappsb.omnishop.security;

import com.springwebappsb.omnishop.entity.Usuario;
import com.springwebappsb.omnishop.enums.Rol;
import com.springwebappsb.omnishop.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getRol().name());

        return ResponseEntity.ok(new AuthResponse(token, usuario.getRol().name()));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        Usuario usuario = Usuario.builder()
                .email(request.getEmail())
                .contraseña(passwordEncoder.encode(request.getPassword()))
                .nombre(request.getNombre())
                .rol(Rol.CLIENTE)
                .build();

        usuarioRepository.save(usuario);
        String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getRol().name());

        return ResponseEntity.ok(new AuthResponse(token, usuario.getRol().name()));
    }
    @GetMapping("/me")
    public ResponseEntity<MeResponse> me(Authentication auth) {
        Usuario usuario = usuarioRepository.findByEmail(auth.getName()).orElseThrow();
        return ResponseEntity.ok(new MeResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol().name()
        ));
    }
}

