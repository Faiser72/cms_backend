package com.vetologic.cms.controller.home;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vetologic.cms.dto.response.CmsResponse;
import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.model.service.user.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
public class HomeController {

	private static Logger log = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private UserService userService;

//	@GetMapping(path = "/home", produces = MediaType.APPLICATION_JSON_VALUE)
//	public Map<String, Object> home(Authentication authentication) {
//		System.out.println("Welcome to CMS.");
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//		Map<String, Object> mapObj = new HashMap<String, Object>();
//		mapObj.put("username", userDetails.getUsername());
//		mapObj.put("userRole", userDetails.getAuthorities().iterator().next().getAuthority());
//		return mapObj;
//	}

	@GetMapping(path = "/home", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse home(Principal principal, CmsResponse cmsResponse) {
		if (principal != null) {
			log.info("Hi, " + principal.getName() + "! Welcome, to CMS Home Controller..");
			try {
				UserDto user = userService.getUserByName(principal.getName());
				cmsResponse.setObject(user);
				cmsResponse.setSuccess(true);
			} catch (Exception e) {
				cmsResponse.setSuccess(false);
				e.printStackTrace();
			}
		} else {
			cmsResponse.setSuccess(false);
			log.error("java.security.Principal data is empty.");
			log.error("Fails to Load VHR Home.");
		}
		return cmsResponse;
	}
}
