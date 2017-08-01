package com.worldpara.controller;

import com.worldpara.domain.*;
import com.worldpara.service.BookService;
import com.worldpara.service.BorrowService;
import com.worldpara.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by chin39 on 2017/7/12.
 */

@SessionAttributes("allLibraries")
@Controller
public class BookController {
	private final Logger log = Logger.getLogger(MainController.class);

	private UserService userService;
	private BookService bookService;
	private BorrowService borrowService;

	@Autowired
	public BookController(UserService userService, BookService bookService, BorrowService borrowService) {
		this.userService = userService;
		this.bookService = bookService;
		this.borrowService = borrowService;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/bookadd", method = RequestMethod.GET)
	public String bookAdd(HttpSession session, ModelMap modelMap) throws RuntimeException {
		log.info("添加书籍信息");
		User user = (User) session.getAttribute("currentUser");
		List<String> roleLists = userService.getRoleStringByUser(user);

		if (roleLists.contains("bookadmin")) {
			boolean temp = false;
			modelMap.addAttribute("check", temp);
			modelMap.addAttribute("bookinfo", new BookInformation());
			return "bookservice/bookinfoadd";
		}
		return "redirect:/index";
	}

	@RequestMapping(value = "/bookadd", params = {"addbook"})
	public String bookAddAction(final BookInformation bookInformation, BindingResult bindingResult,
	                            final boolean temp, ModelMap modelMap) {
		log.info("将信息写入数据库");
		if (bindingResult.hasErrors()) {
			return "bookservice/bookinfoadd";
		}
		if (temp) {
			bookInformation.setCd((byte) 1);
		}
		bookService.bookAdd(bookInformation);
		return "redirect:/index";
	}

	@RequestMapping(value = "/bookmod", params = {"modify"})
	public String bookMod(ModelMap modelMap, RedirectAttributes attributes,
	                      final HttpServletRequest req) {
		log.info("进入更新书籍网页");

		final int bookID = Integer.parseInt(req.getParameter("modify"));
		BookInformation bookInformation = bookService.searchByPrimaryKey(bookID);
		modelMap.addAttribute("bookinfo", bookInformation);
		return "bookservice/bookmod";
	}

	@RequestMapping(value = "/bookmod", params = {"submitModify"})
	public String bookModAction(BookInformation bookInformation, ModelMap modelMap,
	                            RedirectAttributes attributes, HttpSession session) {
		log.info("更新书籍数据库");
		User user = (User) session.getAttribute("currentUser");
		List<String> roleRecords = userService.getRoleStringByUser(user);
		if (!roleRecords.contains("bookadmin")) {
			modelMap.addAttribute("message", "没有权限");
			return "index";
		}
		bookService.bookMod(bookInformation);
		return "bookservice/bookmod";
	}

	@RequestMapping(value = "/bookmod", params = {"detail"})
	public String bookDetail(ModelMap modelMap, final HttpServletRequest req) {
		log.info("进入书籍详细信息");
		final int bookID = Integer.parseInt(req.getParameter("detail"));
		BookInformation bookInformation = bookService.searchByPrimaryKey(bookID);
		modelMap.addAttribute("bookinfo", bookInformation);
		return "bookservice/bookdetail";
	}

	@RequestMapping(value = "/bookmod", params = {"itemMod"})
	public String bookItemAdd(@RequestParam("itemMod") Integer bookID,
	                          ModelMap modelMap, final HttpServletRequest req) {
		log.info("进入书册修改页面");
		BookInformation bookInformation = bookService.searchByPrimaryKey(bookID);
		List<LibraryRecord> libraryRecords = bookService.allLibrarys();
		List<BookInventory> bookInventories = bookService.bookItemSearch(bookInformation);

		modelMap.addAttribute("bookItem", bookInventories);
		modelMap.addAttribute("book", bookInformation);
		modelMap.addAttribute("libraryRecords", libraryRecords);

		return "bookservice/bookitemmod";
	}

	@RequestMapping(value = "/itemMod", params = {"lol"})
	public String bookItemAddAction(@RequestParam("lol") Integer itemID,
	                                ModelMap modelMap, final HttpServletRequest req) {
		log.info("书册添加");
		//	bookService.bookItemAdd(bookInventory, bookInformation, libraryRecord);
		BookInformation bookInformation = bookService.findBookInfoByItemID(itemID);
		List<BookInventory> bookInventories = bookService.bookItemSearch(bookInformation);
		BookInventory bookInventory = bookService.findBookItemByItemID(itemID);
		List<LibraryRecord> libraryRecords = bookService.allLibrarys();

		modelMap.addAttribute("bookItem", bookInventories);
		modelMap.addAttribute("selectItem", bookInventory);
		modelMap.addAttribute("book", bookInformation);
		modelMap.addAttribute("libraryRecords", libraryRecords);
		modelMap.addAttribute("itemID", itemID);
		return "bookservice/bookitemmod";
	}

	@RequestMapping(value = "/itemMod", params = {"update"})
	public String bookItemUpdate(@RequestParam("update") Integer itemID, BookInventory bookInventory, ModelMap modelMap) {
		log.info("更新书册信息");

		bookInventory.setBookitemId(itemID);
		bookService.bookItemMod(bookInventory);

		modelMap.addAttribute("messages", "修改成功");

		BookInformation bookInformation = bookService.findBookInfoByItemID(itemID);
		List<LibraryRecord> libraryRecords = bookService.allLibrarys();
		List<BookInventory> bookInventories = bookService.bookItemSearch(bookInformation);

		modelMap.addAttribute("bookItem", bookInventories);
		modelMap.addAttribute("book", bookInformation);
		modelMap.addAttribute("libraryRecords", libraryRecords);

		return "bookservice/bookitemmod";
	}

	@RequestMapping(value = "/itemMod", params = {"disable"})
	public String bookItemDisable(@RequestParam("disable") Integer itemID, ModelMap modelMap, RedirectAttributes attr) {
		log.info("注销指定书籍");

		BookInformation bookInformation = bookService.findBookInfoByItemID(itemID);
		BookInventory bookInventory = bookService.findBookItemByItemID(itemID);

		try {

			List<BorrowRecord> borrowRecords = borrowService.searchAllActiveRecordByBookItem(bookInventory);

			if (borrowRecords.isEmpty()) {
				bookService.disableBookItem(bookInventory);
			} else {
				throw new RuntimeException("书册还存在活动的借阅记录 无法注销");
			}

		} catch (RuntimeException e) {
			modelMap.addAttribute("message", e.getMessage());
			List<LibraryRecord> libraryRecords = bookService.allLibrarys();
			List<BookInventory> bookInventories = bookService.bookItemSearch(bookInformation);

			modelMap.addAttribute("bookItem", bookInventories);
			modelMap.addAttribute("book", bookInformation);
			modelMap.addAttribute("libraryRecords", libraryRecords);

			return "bookservice/bookitemmod";
		}

		attr.addAttribute("itemMod", bookInformation.getBookId());
		return "redirect:/bookmod";
	}


	@RequestMapping(value = "/itemAdd", params = {"Add"})
	public String bookAdd(@RequestParam("Add") Integer bookID, ModelMap modelMap) {
		log.info("添加书册信息");

		List<LibraryRecord> libraryRecords = bookService.allLibrarys();

		BookInformation bookInformation = bookService.searchByPrimaryKey(bookID);
		modelMap.addAttribute("book", bookInformation);
		modelMap.addAttribute("libraryRecords", libraryRecords);


		modelMap.addAttribute("addTrue", true);
		modelMap.addAttribute("bookItemAdd", new BookInventory());

		return "bookservice/bookitemmod";
	}

	@RequestMapping(value = "/itemAdd", params = {"submit"})
	public String bookAddAction(@RequestParam("submit") Integer bookID, BookInventory bookInventory, ModelMap modelMap) {
		log.info("添加书册进数据库");

		BookInformation bookInformation = bookService.searchByPrimaryKey(bookID);
		LibraryRecord libraryRecord = bookService.findLibraryByID(bookInventory.getLibraryId());

		bookService.bookItemAdd(bookInventory, bookInformation, libraryRecord);

		List<LibraryRecord> libraryRecords = bookService.allLibrarys();
		List<BookInventory> bookInventories = bookService.bookItemSearch(bookInformation);

		modelMap.addAttribute("bookItem", bookInventories);
		modelMap.addAttribute("book", bookInformation);
		modelMap.addAttribute("libraryRecords", libraryRecords);

		return "bookservice/bookitemmod";
	}


}
