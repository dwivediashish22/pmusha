package org.nic.pmusha.security;

import javax.servlet.http.HttpServletRequest;

import org.nic.pmusha.utility.WithUser;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class WithUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	private final JwtConfig jwtConfig;
	private final RestTemplate restTemplate;
	private final Environment environment;
    	
	public WithUserHandlerMethodArgumentResolver( JwtConfig jwtConfig,RestTemplate restTemplate
			,Environment environment) {
		this.jwtConfig = jwtConfig;
		this.restTemplate = restTemplate;
		this.environment = environment;
	}
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(WithUser.class) &&
                parameter.getParameterType().equals(UserInfo.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		   //take the request
        HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();
        //take our Authorization header from request and get access token
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        authorizationHeader = authorizationHeader.replace("Bearer ", "");
        //Using jjwt library parse our token and create Claim object
       
        
        Claims claims = Jwts.parser()
				.setSigningKey(jwtConfig.getSecret().getBytes())
				.parseClaimsJws(authorizationHeader)
				.getBody();
		
		String username = claims.getSubject();
		Integer userid=0;
		String regex = "\\d+";
		if(!username.matches(regex)) {
			userid = restTemplate.getForObject(environment.getProperty("user.url")+"?username="+username, Integer.class);
		}
	    return new UserInfo(username,userid);
	}

}