package com.worldpara.controller;

import com.worldpara.domain.*;
import com.worldpara.service.BookService;
import com.worldpara.service.BorrowService;
import com.worldpara.service.PenaltyService;
import com.worldpara.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chin39 on 2017/7/17.
 */

@Controller
@SessionAttributes({"book", "item"})
public class BorrowController {


	private final Logger log = Logger.getLogger(MainController.class);

	private BorrowService borrowService;
	private BookService bookService;
	private UserService userService;
	private PenaltyService penaltyService;

	@Autowired
	public BorrowController(BorrowService borrowService, BookService bookService, UserService userService, PenaltyService penaltyService) {
		this.borrowService = borrowService;
		this.bookService = bookService;
		this.userService = userService;
		this.penaltyService = penaltyService;
	}


	@RequestMapping(value = "/borrowAdd", params = {"borrow"})
	public String borrowAdd(@RequestParam(value = "borrow") Integer itemID,
	                        ModelMap modelMap, HttpServletRequest request) {
		log.info("进入借阅查询用户页面");

		BookInformation bookInformation = bookService.findBookInfoByItemID(itemID);
		BookInventory bookInventory = bookService.findBookItemByItemID(itemID);

		modelMap.addAttribute("book", bookInformation);
		modelMap.addAttribute("item", bookInventory);

		return "borrowservice/borrowadd";
	}

	@RequestMapping(value = "/borrowAdd", params = {"search"})
	public String borrowAddSearchUser(@RequestParam(value = "search") String search,
	                                  ModelMap modelMap) {
		log.info("查询用户信息");


		List<User> userList = userService.getUserByUsername(search);
		if (userList.isEmpty()) {
			modelMap.addAttribute("message", "未能找到用户信息");
		}

		userList.removeIf(user -> !userService.getRoleStringByUser(user).contains("reader"));
		modelMap.addAttribute("userList", userList);

		return "borrowservice/borrowadd";
	}

	@RequestMapping(value = "/borrowAdd", params = {"userID"})
	public String borrowAddAction(@RequestParam("userID") Integer userID, ModelMap modelMap, HttpSession session) {
		log.info("创建借阅记录用户信息");
		String message = "借阅成功";

		BookInformation bookInformation = (BookInformation) modelMap.get("book");
		BookInventory bookInventory = (BookInventory) modelMap.get("item");
		User currentUser = (User) session.getAttribute("currentUser");
		User user = userService.getUserByID(userID);

		try {
			borrowService.borrowRecordAdd(user, currentUser, bookInventory);
		} catch (RuntimeException e) {
			message = e.getMessage();
		}

		List<LibraryRecord> libraryRecords = bookService.allLibrarys();
		List<BookInventory> bookInventories = bookService.bookItemSearch(bookInformation);

		modelMap.addAttribute("message", message);
		modelMap.addAttribute("bookItem", bookInventories);
		modelMap.addAttribute("libraryRecords", libraryRecords);
		modelMap.addAttribute("book", bookInformation);

		return "bookservice/bookitemmod";
	}

	@RequestMapping(value = "/borrowback")
	public String borrowBack(@RequestParam(value = "search", required = false) String search,
	                         ModelMap modelMap, HttpSession session) {
		log.info("进入借阅归还用户搜索页");

		User user = (User) session.getAttribute("currentUser");
		List<String> roleRecords = userService.getRoleStringByUser(user);

		if (!roleRecords.contains("borrowadmin")) {
			log.info("没有权限");
			modelMap.addAttribute("message", "没有权限");
			return "index";
		}


		modelMap.addAttribute("actionUrl", "borrowback");
		modelMap.addAttribute("searchPlaceHolder", "查找指定用户");


		if (search != null) {
			List<User> userList = userService.getUserByUsername(search);

			if (userList.isEmpty()) {
				modelMap.addAttribute("message", "没有找到用户");
			}
			modelMap.addAttribute("userList", userList);
		}

		return "borrowservice/borrowback";
	}


	@RequestMapping(value = "/borrowback", params = {"user"})
	public String borrowBackAction(@RequestParam(value = "user") int userID,
	                               ModelMap modelMap, HttpSession session) {

		log.info("进入借阅归还用户页");

		User user = userService.getUserByID(userID);
		modelMap.addAttribute("borrowUser", user);

		modelMap.addAttribute("actionUrl", "borrowback");
		modelMap.addAttribute("searchPlaceHolder", "查找指定用户");

		try {
			List<BorrowRecord> borrowRecordList = borrowService.searchByUserID(user);
			List<BookInformation> bookInformationList = borrowService.findCorBookInfoByBorrowLists(borrowRecordList);
			List<LoopHelper> loopHelperList = new ArrayList<>();
			for (int i = borrowRecordList.size() - 1; i >= 0; i--) {
				loopHelperList.add(new LoopHelper(borrowRecordList.get(i), bookInformationList.get(i)));
			}
			List<BorrowRecord> activeRecords = borrowService.searchAllActiveRecordByUser(user);
			for (BorrowRecord borrowRecord : activeRecords) {
				BookInventory bookInventory = bookService.findBookItemByItemID(borrowRecord.getBookitemId());
				penaltyService.checkOverduePenalty(borrowRecord, user, bookInventory);
			}
			List<OverdueRecord> overdueRecordList = penaltyService.getcurrentActiveOverdueByUserID(user.getUserId());
			List<Integer> overdueRecordBorrowIDs = overdueRecordList.stream().map(OverdueRecord::getBorrowId).collect(Collectors.toList());

			modelMap.addAttribute("borrowRecords", borrowRecordList);
			modelMap.addAttribute("bookinfoList", bookInformationList);
			modelMap.addAttribute("loopHelper", loopHelperList);
			modelMap.addAttribute("overdueList", overdueRecordBorrowIDs);
			modelMap.addAttribute("userService", userService);
			modelMap.addAttribute("bookService", bookService);

		} catch (RuntimeException e) {
			modelMap.addAttribute("message", e.getMessage());
		}

		return "borrowservice/userBorrowRecord";
	}

	@RequestMapping(value = "/borrowback", params = {"borrowRecord", "userID"})
	public String borrowBackRecordID(@RequestParam("borrowRecord") int borrowID,
	                                 @RequestParam("userID") int userID, HttpSession session,
	                                 RedirectAttributes attr, ModelMap modelMap) {
		log.info("进行借阅归还数据库操作");

		User user = userService.getUserByID(userID);
		//modelMap.addAttribute("borrowUser", user);

		User backUser = (User) session.getAttribute("currentUser");
		List<String> roleRecords = userService.getRoleStringByUser(backUser);

		if (!roleRecords.contains("borrowadmin")) {
			log.info("没有权限");
			modelMap.addAttribute("message", "没有权限");
			return "index";
		}
		BorrowRecord borrowRecord = borrowService.searchByBorrowID(borrowID);
		BookInventory bookInventory = bookService.findBookItemByItemID(borrowRecord.getBookitemId());

		try {
			borrowService.bookReturn(user, backUser, bookInventory);
		} catch (RuntimeException e) {
			modelMap.addAttribute("message", e.getMessage());
		}

		//回到归还用户页面
		attr.addAttribute("user", userID);
		return "redirect:borrowback";
	}

	@RequestMapping(value = "/borrowExt", params = {"borrowRecord", "userID"})
	public String borrowExt(@RequestParam("borrowRecord") int borrowID, ModelMap modelMap, HttpSession session,
	                        RedirectAttributes attr) {
		log.info("开始书册借阅延期操作");

		BorrowRecord borrowRecord = borrowService.searchByBorrowID(borrowID);
		User user = userService.getUserByID(borrowRecord.getUserId());
		User currentUser = (User) session.getAttribute("currentUser");

		try {
			borrowService.bookExt(user, currentUser, borrowRecord);
			attr.addAttribute("message", "续借成功");
		} catch (RuntimeException e) {
			attr.addAttribute("message", e.getMessage());
		}

		attr.addAttribute("user", borrowRecord.getUserId());
		return "redirect:borrowback";
	}


}
