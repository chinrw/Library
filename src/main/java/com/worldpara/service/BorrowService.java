package com.worldpara.service;

import com.worldpara.domain.*;
import com.worldpara.mapper.inf.BookInformationMapper;
import com.worldpara.mapper.inf.BookInventoryMapper;
import com.worldpara.mapper.inf.BorrowRecordMapper;
import com.worldpara.mapper.inf.RuleRecordMapper;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chin39 on 2017/7/4.
 */


@Service
public class BorrowService {


	private final Logger log = Logger.getLogger(BookService.class);

	private BookService bookService;
	private UserService userService;
	private PenaltyService penaltyService;
	private BorrowRecordMapper borrowRecordMapper;
	private BookInformationMapper bookInformationMapper;
	private RuleRecordMapper ruleRecordMapper;
	private BookInventoryMapper bookInventoryMapper;

	@Autowired
	public BorrowService(BookService bookService, UserService userService, BorrowRecordMapper borrowRecordMapper,
	                     BookInformationMapper bookInformationMapper, RuleRecordMapper ruleRecordMapper,
	                     PenaltyService penaltyService, BookInventoryMapper bookInventoryMapper) {
		this.bookService = bookService;
		this.userService = userService;
		this.borrowRecordMapper = borrowRecordMapper;
		this.bookInformationMapper = bookInformationMapper;
		this.ruleRecordMapper = ruleRecordMapper;
		this.penaltyService = penaltyService;
		this.bookInventoryMapper = bookInventoryMapper;
	}

	public List<BorrowRecord> searchByBookInventory(BookInventory bookInventory) {
		log.info("通过书籍库存查找其库存相关的所有借阅记录");
		return borrowRecordMapper.searchByBookInventoryID(bookInventory);
	}

	public List<BorrowRecord> searchByUserID(User user) {
		log.info("通过用户查找其相关的书籍借阅记录");
		return borrowRecordMapper.searchByUserID(user);
	}

	public BorrowRecord searchByBorrowID(int borrowID) {
		return borrowRecordMapper.selectByPrimaryKey(borrowID);
	}

	public BookInformation searchBookInfoByBorrowID(int borrowID) throws RuntimeException {
		log.info("通过借阅记录查找书籍信息");
		BorrowRecord borrowRecord = borrowRecordMapper.selectByPrimaryKey(borrowID);
		return bookInformationMapper.selectByPrimaryKey(borrowRecord.getBookId());
	}

	public BookInventory searchBookItemByBorrowID(int borrowID) {
		return bookInventoryMapper.selectByPrimaryKey(searchByBorrowID(borrowID).getBookitemId());
	}

	public List<BorrowRecord> searchAllActiveRecordByUser(User user) throws RuntimeException {
		log.info("查找用户所有活跃借阅记录");
		List<BorrowRecord> borrowRecordList = searchByUserID(user);
		if (!borrowRecordList.isEmpty()) {

			borrowRecordList.removeIf(temp -> temp.getStatus().equals("0"));
			return borrowRecordList;
		} else {
			throw new RuntimeException("用户没有借阅记录");
		}
	}

	public List<BorrowRecord> searchAllActiveRecordByBookItem(BookInventory bookInventory) throws RuntimeException {
		log.info("查找书册所有活跃借阅记录");
		List<BorrowRecord> borrowRecordList = searchByBookInventory(bookInventory);
		if (!borrowRecordList.isEmpty()) {

			borrowRecordList.removeIf(temp -> temp.getStatus().equals("0"));
			return borrowRecordList;
		} else {
			throw new RuntimeException("书册没有借阅记录");
		}
	}


	public List<BookInformation> findCorBookInfoByBorrowLists(List<BorrowRecord> borrowRecords) {
		return borrowRecords.stream().map(borrowRecord -> searchBookInfoByBorrowID(borrowRecord.getBorrowId())).collect(Collectors.toList());

	}

	public BorrowRecord searchActiveRecordByBookAndUser(BookInventory bookInventory, User user) throws RuntimeException {
		log.info("查找用户与书籍对应的活跃借阅记录");
		List<BorrowRecord> borrowRecordList = searchByUserID(user);
		if (!borrowRecordList.isEmpty()) {

			borrowRecordList.removeIf(temp -> temp.getStatus().equals("0"));
			borrowRecordList.removeIf(borrowRecord -> !borrowRecord.getBookId().equals(bookInventory.getBookId()));

			if (borrowRecordList.size() == 1) {
				return borrowRecordList.get(0);

			} else if (borrowRecordList.isEmpty()) {
				throw new RuntimeException("用户没有对应书册的借阅记录");

			} else {
				throw new RuntimeException("有超过一个的借阅记录");

			}
		} else {
			throw new RuntimeException("用户没有借阅记录");
		}
	}

	public int borrowRecordAdd(User user, User outUser, BookInventory bookInventory) throws RuntimeException {
		log.info("添加借阅记录开始");
		log.info("查看借阅书籍是否可以借阅");
		if (!bookInventory.getStatus().equals("1")) {
			throw new RuntimeException("书册无法借阅，状态为不可用");
		}
		int totalPrice = 0;
		int value_limited = ruleRecordMapper.selectByPrimaryKey(1).getBorrowValueLimited();
		List<BorrowRecord> borrowRecordList = searchByUserID(user);
		for (BorrowRecord item : borrowRecordList) {
			if (item.getStatus().equals("1")) {
				totalPrice += bookInformationMapper.selectByPrimaryKey(item.getBookId()).getPrice();
				continue;
			}
			//可以借 为200块钱+借阅记录*5倍的书籍
			value_limited += 5;
		}
		if (value_limited > totalPrice) {
			log.info("检查是否超过借阅价格限制");
			List<OverdueRecord> overdueRecords = penaltyService.getcurrentActiveOverdueByUserID(user.getUserId());
			if (overdueRecords.isEmpty()) {
				log.info("没有逾期记录");
				List<PenaltyRecord> penaltyRecords = penaltyService.getcurrentActivePenaltyByUserID(user.getUserId());
				if (penaltyRecords.isEmpty()) {
					log.info("没有罚款记录, 开始写入借阅记录");
					bookInventory.setStatus("0"); // 设置书册状态为已借出
					BorrowRecord borrowRecord = new BorrowRecord();
					borrowRecord.setBookId(bookInventory.getBookId());
					borrowRecord.setOutuserId(outUser.getUserId());
					borrowRecord.setUserId(user.getUserId());
					borrowRecord.setBookitemId(bookInventory.getBookitemId());

					//导入日期记录
					Date nowTime = new Date();
					Calendar rightNow = Calendar.getInstance();
					borrowRecord.setOutDate(nowTime);
					rightNow.add(Calendar.DATE, 15);
					nowTime = rightNow.getTime();
					borrowRecord.setBackDate(nowTime);

					//更新书册记录
					bookInventoryMapper.updateByPrimaryKeySelective(bookInventory);
					return borrowRecordMapper.insertSelective(borrowRecord);
				}
			}
		} else {
			log.info("不符合借阅要求");
			throw new RuntimeException("不符合借阅要求");
		}
		throw new RuntimeException("借阅成功");
	}

	public int bookReturn(User user, User backUser, BookInventory bookInventory) throws RuntimeException {
		log.info("准备开始书籍归还过程");
		BorrowRecord borrowRecord;
		try {
			borrowRecord = searchActiveRecordByBookAndUser(bookInventory, user);
		} catch (RuntimeException e) {
			log.info("借阅记录查询出错");
			throw e;
		}

		//查看是否要添加罚款
		penaltyService.checkOverduePenalty(borrowRecord, user, bookInventory);
		//是否要手动添加罚款
		log.info("书册状态设为可用");
		bookInventory.setStatus("1");
		borrowRecord.setBackuserId(backUser.getUserId());
		borrowRecord.setActualBackDate(new Date());
		borrowRecord.setStatus("0");

		if (borrowRecord.getWhetherExt().equals("1")) {
			BorrowRecord prevBorrowRecord = borrowRecordMapper.selectByPrimaryKey(borrowRecord.getPrevBorrowId());
			prevBorrowRecord.setActualBackDate(new Date());
			prevBorrowRecord.setBackuserId(backUser.getUserId());
			borrowRecordMapper.updateByPrimaryKeySelective(prevBorrowRecord);
		}
		borrowRecordMapper.updateByPrimaryKeySelective(borrowRecord);
		bookInventoryMapper.updateByPrimaryKeySelective(bookInventory);
		return 0;
	}

	public BorrowRecord bookExt(User user, User extUsder, BorrowRecord prevBorrowRecord)
			throws RuntimeException {
		log.info("开始准备延期");

		BookInventory bookInventory = searchBookItemByBorrowID(prevBorrowRecord.getBorrowId());
		//判断是否已经逾期
		List<PenaltyRecord> penaltyRecordList = penaltyService.getcurrentActivePenaltyByUserID(user.getUserId());

		if (!penaltyRecordList.isEmpty()) {
			throw new RuntimeException("有罚单未缴纳");
		}

		if (!prevBorrowRecord.getWhetherExt().equals("1")) {

			log.info("曾经没有进行过延期，添加延期记录");
			DateTime dateTime = new DateTime();
			BorrowRecord extBorrowRecord = new BorrowRecord();
			extBorrowRecord.setUserId(user.getUserId());
			extBorrowRecord.setOutuserId(extUsder.getUserId());
			extBorrowRecord.setOutDate(prevBorrowRecord.getOutDate());
			extBorrowRecord.setWhetherExt("1");
			extBorrowRecord.setBookId(bookInventory.getBookId());
			extBorrowRecord.setBookitemId(bookInventory.getBookitemId());
			extBorrowRecord.setPrevBorrowId(prevBorrowRecord.getBorrowId());
			extBorrowRecord.setBackDate(dateTime.plusDays(15).toDate());

			borrowRecordMapper.insertSelective(extBorrowRecord);

			// 修改曾经的借阅记录
			prevBorrowRecord.setExtDate(dateTime.toDate());
			prevBorrowRecord.setStatus("0");
			prevBorrowRecord.setBackuserId(extBorrowRecord.getBackuserId());

			borrowRecordMapper.updateByPrimaryKeySelective(prevBorrowRecord);

		} else {
			throw new RuntimeException("曾经进行过延期");
		}
		return prevBorrowRecord;
	}

}
