package com.example.interfazPeliculas.config;

import com.example.interfazPeliculas.model.Rol;
import com.example.interfazPeliculas.model.Usuario;
import com.example.interfazPeliculas.service.InterfaceUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private InterfaceUsuarioService usuariosService;

    public CustomAuthenticationProvider() {
        super();
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        final String usuario = authentication.getName();
        String password = authentication.getCredentials().toString();
        Usuario usuarioLogueado = usuariosService.login(usuario, password);

        if (usuarioLogueado != null) {
            final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();

            for (Rol rol : usuarioLogueado.getRoles()) {
                grantedAuths.add(new SimpleGrantedAuthority(rol.getAuthority()));
            }

            final UserDetails principal = new User(usuario, password, grantedAuths);
            final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);

            return auth;
        }else
        {
            return null;
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean supports(final Class authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
