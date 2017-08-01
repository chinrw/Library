package com.worldpara.controller;


import com.worldpara.domain.User;
import com.worldpara.domain.UserRoles;
import com.worldpara.service.BookService;
import com.worldpara.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AccountController {
	private final Logger log = Logger.getLogger(MainController.class);


	private UserService userService;
	private BookService bookService;

	@Autowired
	public AccountController(UserService userService, BookService bookService) {
		this.userService = userService;
		this.bookService = bookService;
	}

	@RequestMapping(value = "/login")
	public String login(ModelMap modelMap, HttpSession session) {
		log.info("用户登录");
		modelMap.addAttribute("user", new User());
		return "account/login";
	}

	@RequestMapping(value = "/login", params = {"login"})
	public String login(final User user, ModelMap modelMap, HttpSession session, RedirectAttributes attr) {
		log.info("用户输入登录信息");
		User login = userService.userLogin(user.getUsername(), user.getPassword());
		if (login != null) {
			session.setAttribute("currentUser", login);
			attr.addAttribute("message", "登录成功");
			List<String> roleLists = userService.getRoleStringByUser(login);
			session.setAttribute("roleList", roleLists);
		}
		return "redirect:/index";
	}

	@RequestMapping(value = "/logout")
	public String logout(ModelMap modelMap, HttpSession session) {
		log.info("用户登出");
		Enumeration<String> em = session.getAttributeNames();
		log.info("清除session");
		while (em.hasMoreElements()) session.removeAttribute(em.nextElement());
		return "redirect:/index";
	}


	@RequestMapping(value = "/signup")
	public String signUp(Model model) {
		log.info("用户注册");
		model.addAttribute("user", new User());
		return "account/signup";
	}

	@RequestMapping(value = "/signup", params = {"save"})
	public String indexPost(@ModelAttribute User input, final Model model,
	                        BindingResult bindingResult, HttpSession session) {
		log.info("提交表单");
		if (bindingResult.hasErrors()) {
			model.addAttribute("message", "输入信息错误");
			return "account/signup";
		}
		try {
			userService.userAdd(input);
		} catch (RuntimeException e) {
			model.addAttribute("message", e.getMessage());
			return "index";
		}
		userService.setroleUserRelationByID(input.getUserId(), 4);

		model.addAttribute("message", "注册成功");
		session.setAttribute("currentUser", input);
		model.addAttribute("user", input);
		return "index";
	}

	@RequestMapping(value = "/profile")
	public String profile(Model model, HttpSession session) {
		log.info("用户个人信息页面");
		User user = (User) session.getAttribute("currentUser");
		model.addAttribute("user", user);
		model.addAttribute("action", "profile");
		return "account/profile";
	}

	@RequestMapping(value = "/profile", params = {"submit"})
	public String profileAction(@ModelAttribute User user, @RequestParam("submit") int userID,
	                            Model model, RedirectAttributes attr) {
		log.info("提交个人信息修改");
		user.setUserId(userID);
		userService.userMod(user);
		attr.addFlashAttribute("message", "个人信息更新成功");
		return "redirect:index";
	}

	@RequestMapping(value = "/rolemod")
	public String roleMod(ModelMap modelMap, HttpSession session) {
		User user = (User) session.getAttribute("currentUser");
		List<String> roleRecords = userService.getRoleStringByUser(user);
		if (!roleRecords.contains("admin")) {
			modelMap.addAttribute("message", "没有权限");
			return "index";
		}
		return "account/rolemod";
	}

	@RequestMapping(value = "/rolemod", params = {"search"})
	public String roleModSearch(@RequestParam("search") final String search, ModelMap modelMap) {
		log.info("查询用户已有权限");


		List<User> userList = userService.getUserByUsername(search);
		List<String> roleStrings = userService.getAllRoleStrings();
		List<UserRoles> userRoles = userService.createUserRoleLists(userList);

		Map<Integer, UserRoles> userRolesMap = new HashMap<>();

		modelMap.addAttribute("userList", userList);
		modelMap.addAttribute("roleStrings", roleStrings);
		modelMap.addAttribute("userRoleList", userRoles);
		modelMap.addAttribute("newRoles", new UserRoles());

		return "account/rolemod";
	}

	@RequestMapping(value = "/rolemod", params = {"modify"})
	public String roleModAction(@RequestParam("modify") Integer userID, UserRoles userRoles, ModelMap modelMap) {
		log.info("提交用户权限修改");

		userService.updateUserRoleRecords(userID, userRoles.getRoleLists());

		return "account/rolemod";
	}

}
