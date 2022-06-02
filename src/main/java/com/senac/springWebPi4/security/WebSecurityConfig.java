package com.senac.springWebPi4.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ImplementsUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/home").permitAll()
                .antMatchers(HttpMethod.GET, "/home/{nomepesquisa}").permitAll()
                .antMatchers(HttpMethod.POST, "/cadastroCliente").permitAll()
                .antMatchers(HttpMethod.GET, "/cliente/cadastro").permitAll()
                .antMatchers(HttpMethod.GET, "/produto/detalheHome/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/cliente/cadastroEnderecoEntrega").permitAll()
                .antMatchers(HttpMethod.GET, "/cliente/carrinho").permitAll()
                .antMatchers(HttpMethod.GET, "/adicionar/carrinho/{id}/{qtd}/{status}").permitAll()
                .antMatchers(HttpMethod.GET, "/carrinho/dropItem/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/teste").permitAll()
                
                .antMatchers(HttpMethod.GET, "/list").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/form").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/list").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/criarUsuario").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/userEdit/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/editUser/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/findByName").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/usuario/alterarStatus").hasRole("ADMIN")
                
                .antMatchers(HttpMethod.GET, "/produto/form").hasAnyRole("ADMIN","ESTOQUE")
                .antMatchers(HttpMethod.GET, "/produto/list/{page}").hasAnyRole("ADMIN","ESTOQUE")
                .antMatchers(HttpMethod.GET, "/produto/findByName/{page}/{nomepesquisa}").hasAnyRole("ADMIN","ESTOQUE")
                .antMatchers(HttpMethod.GET, "/produto/detalhe/{id}").hasAnyRole("ADMIN","ESTOQUE")                
                .antMatchers(HttpMethod.GET, "/salvarImagem").hasAnyRole("ADMIN","ESTOQUE")
                .antMatchers(HttpMethod.GET, "/produto/edit/{id}").hasAnyRole("ADMIN","ESTOQUE")
                .antMatchers(HttpMethod.GET, "/produto/alterarStatus").hasAnyRole("ADMIN","ESTOQUE")
                
                .antMatchers(HttpMethod.GET, "/cliente/dados").hasRole("CLIENTE")
                .antMatchers(HttpMethod.GET, "/cliente/checkout").hasRole("CLIENTE")
                .antMatchers(HttpMethod.GET, "/detalheDoPedido/{id}").hasAnyRole("CLIENTE","ESTOQUE")
                

                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login")
                .permitAll()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/imagem/**", "/css/**");
    }
}
