package com.glorial.business;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	//@Autowired
	//private UserInfo userInfo;

	@PostMapping("signin")
	public String signin(HttpServletRequest request,  @RequestParam(required = true) String username, @RequestParam(required = true) String password) {

		HttpSession session = request.getSession(true);

		Map<String, String> user = new HashMap<>();
		user.put("id", username);
		user.put("password", password);
		user.put("sessionId", session.getId());

		session.setAttribute("userInfo", user);

		return "afterlogin";
	}

	@RequestMapping(value = "/getsession")
	@ResponseBody
	public Map<String, String> getsession(HttpSession session) {
		Map<String, String> user = (Map<String, String>)session.getAttribute("userInfo");
		return user;
	}

	@GetMapping("afterlogin2")
	public String afterlogin2() {
		return "afterlogin";
	}
}