package com.glorial.boot.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "security.url")
public class SecurityUrlData {

	private String login;

	private String logout;

	private String mypage;

    private String accessdenied;

    private String refreshToken;

}