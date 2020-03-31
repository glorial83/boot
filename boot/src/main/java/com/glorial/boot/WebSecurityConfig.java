package com.glorial.boot;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
	        .csrf().disable()
	        .authorizeRequests()
	        .antMatchers("/", "/index").permitAll()
	        .anyRequest().authenticated()
	        .and()
	        .httpBasic();
    }
/*
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> users= new ArrayList<UserDetails>();
        users.add(User.withDefaultPasswordEncoder().username("glorial").password("glorial").roles("USER","ADMIN").build());
        return new InMemoryUserDetailsManager(users);
    }*/
}
