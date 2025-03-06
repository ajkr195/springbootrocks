package com.spring.boot.rocks.model.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {

		//String userName;

		//Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		//if (principal instanceof UserDetails) {
			//userName = ((UserDetails) principal).getUsername();
		//} else {
			//userName = principal.toString();
		//}

		//if (userName.equals("anonymousUser")) {
			//return Optional.of("WebSite");
		//}

		return Optional.of("System");
	}
}