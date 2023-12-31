package org.nic.pmusha.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired private JwtConfig jwtConfig;
	
	@Autowired RestTemplate restTemplate;
	@Autowired Environment environment;
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new WithUserHandlerMethodArgumentResolver(jwtConfig,restTemplate,environment));
	}
}
