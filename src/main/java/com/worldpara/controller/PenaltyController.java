package com.worldpara.controller;

import com.worldpara.domain.BorrowRecord;
import com.worldpara.domain.PenaltyRecord;
import com.worldpara.domain.Reader;
import com.worldpara.domain.User;
import com.worldpara.service.BookService;
import com.worldpara.service.BorrowService;
import com.worldpara.service.PenaltyService;
import com.worldpara.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by chin39 on 2017/7/21.
 */

@Controller
@SessionAttributes({"penaltyUser"})
public class PenaltyController {
	private final Logger log = Logger.getLogger(MainController.class);

	private BorrowService borrowService;
	private BookService bookService;
	private UserService userService;
	private PenaltyService penaltyService;

	@Autowired
	public PenaltyController(BorrowService borrowService, BookService bookService, UserService userService, PenaltyService penaltyService) {
		this.borrowService = borrowService;
		this.bookService = bookService;
		this.userService = userService;
		this.penaltyService = penaltyService;
	}

	@RequestMapping(value = "/penalty")
	public String searchPenalty(@RequestParam(value = "search", required = false) String search,
	                            ModelMap modelMap, HttpSession session) {

		log.info("查询用户信息");

		User user = (User) session.getAttribute("currentUser");
		List<String> roleRecords = userService.getRoleStringByUser(user);

		if (!roleRecords.contains("borrowadmin") || !roleRecords.contains("borrowadmin")) {
			log.info("没有权限");
			modelMap.addAttribute("message", "没有权限");
			return "index";
		}

		modelMap.addAttribute("actionUrl", "penalty");
		modelMap.addAttribute("searchPlaceHolder", "查找指定用户罚单");

		if (search != null) {
			List<User> userList = userService.getUserByUsername(search);

			if (userList.isEmpty()) {
				modelMap.addAttribute("message", "没有找到指定用户");
			}
			modelMap.addAttribute("userList", userList);
		}

		return "penaltyservice/penaltySearch";
	}

	@RequestMapping(value = "/penalty", params = {"user"})
	public String userSelect(@RequestParam("user") int userID, ModelMap modelMap) {

		log.info("显示搜索到的用户");

		User user = userService.getUserByID(userID);
		Reader reader = userService.findReaderByUserID(userID);

		modelMap.addAttribute("actionUrl", "penalty");
		modelMap.addAttribute("searchPlaceHolder", "查找指定用户罚单");
		modelMap.addAttribute("penaltyUser", user);

		List<PenaltyRecord> penaltyRecordList = penaltyService.findallPenaltyRecordByUserID(userID);

		modelMap.addAttribute("penaltyRecords", penaltyRecordList);
		modelMap.addAttribute("bookService", bookService);
		modelMap.addAttribute("borrowService", borrowService);
		modelMap.addAttribute("penaltyService", penaltyService);
		modelMap.addAttribute("userService", userService);
		modelMap.addAttribute("reader", reader);

		return "penaltyservice/penaltyRecord";
	}

	@RequestMapping(value = "/penaltyAdd", params = {"user", "borrowRecord"})
	public String penaltyAdd(@RequestParam("user") int userID, @RequestParam("borrowRecord") int borrowID,
	                         ModelMap modelMap) {
		log.info("添加罚单");

		User user = userService.getUserByID(userID);
		BorrowRecord borrowRecord = borrowService.searchByBorrowID(borrowID);

		modelMap.addAttribute("actionUrl", "penalty");
		modelMap.addAttribute("searchPlaceHolder", "查找指定用户罚单");
		modelMap.addAttribute("penaltyUser", user);

		modelMap.addAttribute("borrowRecord", borrowRecord);
		modelMap.addAttribute("newPenalty", new PenaltyRecord());
		return "penaltyservice/penaltyRecordAdd";
	}

	@RequestMapping(value = "/penaltyAdd", params = {"addPenalty"})
	public String penaltyAddAction(@RequestParam("addPenalty") int borrowID, PenaltyRecord penaltyRecord,
	                               @ModelAttribute("penaltyUser") User penaltyUser, ModelMap modelMap,
	                               RedirectAttributes attr, HttpSession session) {
		log.info("罚单写入数据库");

		User user = (User) session.getAttribute("currentUser");
		BorrowRecord borrowRecord = borrowService.searchByBorrowID(borrowID);

		try {
			penaltyService.addPenalty(penaltyUser, user, borrowRecord, penaltyRecord.getPenalty(), penaltyRecord.getPenaltyType());
		} catch (RuntimeException e) {
			modelMap.addAttribute("message", e.getMessage());
		}

		attr.addAttribute("user", penaltyUser.getUserId());
		return "redirect:/penalty";
	}

	@RequestMapping(value = "/penaltyPay", params = {"pay"})
	public String penaltyPay(@ModelAttribute("penaltyUser") User penaltyUser, Double pay, HttpSession session,
	                         RedirectAttributes attr) {
		log.info("罚单缴纳");

		User user = (User) session.getAttribute("currentUser");
		List<String> roleRecordList = userService.getRoleStringByUser(user);

		if (roleRecordList.contains("borrowadmin")) {
			penaltyService.payPenalty(pay, penaltyUser.getUserId());
		}

		attr.addAttribute("user", penaltyUser.getUserId());

		return "redirect:/penalty";
	}


}
