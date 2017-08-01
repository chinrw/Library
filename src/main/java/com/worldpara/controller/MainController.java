

package com.worldpara.controller;


import com.worldpara.domain.BookInformation;
import com.worldpara.domain.User;
import com.worldpara.service.BookService;
import com.worldpara.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {

	private final Logger log = Logger.getLogger(MainController.class);

	private UserService userService;
	private BookService bookService;

	@Autowired
	public MainController(UserService userService, BookService bookService) {
		this.userService = userService;
		this.bookService = bookService;
	}


	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(@ModelAttribute("message") String message, ModelMap model, HttpSession session) {
		log.info("进入主页");
		User user = (User) session.getAttribute("currentUser");
		session.setAttribute("message", null);
		if (user != null) {
			model.addAttribute("user", user);
		} else {
			model.addAttribute("user", new User());
		}
		List<BookInformation> informationList = new ArrayList<>();
		BookInformation bookInformation1 = bookService.searchByPrimaryKey(4);
		informationList.add(bookInformation1);
		informationList.add(bookService.searchByPrimaryKey(5));
		informationList.add(bookService.searchByPrimaryKey(6));

		model.addAttribute("bookList", informationList);
		model.addAttribute("message", message);
		
		return "index";
	}


	@RequestMapping(value = "/index", params = {"search"})
	public String searchBar(@RequestParam("search") final String input, final ModelMap model) {
		log.info("开始搜索");
		List<BookInformation> bookInformationList = this.bookService.fuzzySearchBookname(input);
		System.out.println(input);
		model.addAttribute("books", bookInformationList);
		model.addAttribute("user", new User());

		return "search-results";
	}
}
