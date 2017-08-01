package com.worldpara.service;

import com.worldpara.domain.BookInformation;
import com.worldpara.domain.BookInventory;
import com.worldpara.domain.BorrowRecord;
import com.worldpara.domain.LibraryRecord;
import com.worldpara.mapper.inf.BookInformationMapper;
import com.worldpara.mapper.inf.BookInventoryMapper;
import com.worldpara.mapper.inf.CDInventoryMapper;
import com.worldpara.mapper.inf.LibraryRecordMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chin39 on 2017/7/3.
 */

@Service
public class BookService {

	private BookInventoryMapper bookInventoryMapper;
	private BookInformationMapper bookInformationMapper;
	private LibraryRecordMapper libraryRecordMapper;
	private CDInventoryMapper cdInventoryMapper;

	private final Logger log = Logger.getLogger(BookService.class);

	@Autowired
	public BookService(BookInventoryMapper bookInventoryMapper, BookInformationMapper bookInformationMapper,
	                   LibraryRecordMapper libraryRecordMapper, CDInventoryMapper cdInventoryMapper) {
		this.bookInventoryMapper = bookInventoryMapper;
		this.bookInformationMapper = bookInformationMapper;
		this.libraryRecordMapper = libraryRecordMapper;
		this.cdInventoryMapper = cdInventoryMapper;
	}

	public BookInformation isbnSearch(String isbn) {
		log.info("通过ISBN查询书籍信息");
		List<BookInformation> bookInformationList = bookInformationMapper.selectByISBN(isbn);
		if (bookInformationList.isEmpty()) {
			log.info("未能查找到书籍");
			return null;
		} else {
			return bookInformationList.get(0);
		}
	}

	public BookInformation booknameSearch(String bookName) {
		log.info("通过书名查找书籍信息");
		List<BookInformation> bookInformationList = bookInformationMapper.selectByBookname(bookName);
		if (bookInformationList.isEmpty()) {
			log.info("未能找到书籍信息");
			return null;
		} else if (bookInformationList.size() == 1) {
			return bookInformationList.get(0);
		} else {
			log.info("有超过一本的同名书籍, 需要查询数据库");
			return null;
		}
	}

	public List<BookInformation> fuzzySearchBookname(String bookName) {
		log.info("通过书名模糊查询");
		return bookInformationMapper.likeByBookname(bookName);
	}

	public BookInformation searchByPrimaryKey(int bookID) {
		return bookInformationMapper.selectByPrimaryKey(bookID);
	}

	public List<LibraryRecord> allLibrarys() {
		return libraryRecordMapper.allRecords();
	}

	public List<String> findLibraryByItem(List<BookInventory> bookInventories) {
		log.info("查找书册记录对应的图书馆地址 返回一个 location的list");
		return bookInventories.stream().map(bookInventory -> libraryRecordMapper.selectByPrimaryKey(bookInventory.
				getLibraryId()).getLocation()).collect(Collectors.toList());

	}

	public BookInventory findBookItemByItemID(int itemID) {
		return bookInventoryMapper.selectByPrimaryKey(itemID);
	}


	public int bookAdd(BookInformation bookInformation) {
		log.info("添加书籍信息");
		if (isbnSearch(bookInformation.getIsbn()) == null) {
			log.info("没有查询到重复的书籍信息");
			return bookInformationMapper.insertSelective(bookInformation);
		}
		log.info("查询到重复的书籍信息");
		return 0;
	}

	public int bookMod(BookInformation bookInformation) {
		log.info("修改书籍信息");
		if (isbnSearch(bookInformation.getIsbn()) != null) {
			log.info("查询到已存在书籍，进行修改");
			return bookInformationMapper.updateByPrimaryKeySelective(bookInformation);
		}
		return 0;
	}

	public int bookItemAdd(BookInventory bookInventory, BookInformation bookInformation, LibraryRecord libraryRecord) {
		log.info("添加书籍库存");
		bookInventory.setBookId(bookInformation.getBookId());
		bookInventory.setLibraryId(libraryRecord.getLibrary());
		return bookInventoryMapper.insertSelective(bookInventory);
	}

	public List<BookInventory> bookItemSearch(BookInformation bookInformation) {
		log.info("通过书籍信息 搜索库存");
		return bookInventoryMapper.searchByBookID(bookInformation);
	}

	public LibraryRecord findLibraryByID(int libraryID){
		return libraryRecordMapper.selectByPrimaryKey(libraryID);
	}

	public int bookItemMod(BookInventory bookInventory) {
		log.info("修改库存信息");
		return bookInventoryMapper.updateByPrimaryKeySelective(bookInventory);
	}

	public BookInformation findBookInfoByItemID(int bookInventoryID) {
		BookInventory bookInventory = bookInventoryMapper.selectByPrimaryKey(bookInventoryID);
		return bookInformationMapper.selectByPrimaryKey(bookInventory.getBookId());
	}

	public int disableBookItem(BookInventory bookInventory) throws RuntimeException{
		bookInventory.setStatus("2");
		return bookItemMod(bookInventory);
	}
}
