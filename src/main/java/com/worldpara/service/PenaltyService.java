package com.worldpara.service;

import com.worldpara.domain.*;
import com.worldpara.mapper.inf.*;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chin39 on 2017/7/5.
 */


@Service
public class PenaltyService {

	private final Logger log = Logger.getLogger(BookService.class);

	private PenaltyRecordMapper penaltyRecordMapper;
	private OverdueRecordMapper overdueRecordMapper;
	private BookInformationMapper bookInformationMapper;
	private UserService userService;
	private StaffMapper staffMapper;
	private ReaderMapper readerMapper;


	@Autowired
	public PenaltyService(PenaltyRecordMapper penaltyRecordMapper, OverdueRecordMapper overdueRecordMapper,
	                      UserService userService, BookInformationMapper bookInformationMapper, StaffMapper staffMapper, ReaderMapper readerMapper) {
		this.penaltyRecordMapper = penaltyRecordMapper;
		this.overdueRecordMapper = overdueRecordMapper;
		this.bookInformationMapper = bookInformationMapper;
		this.staffMapper = staffMapper;
		this.userService = userService;
		this.readerMapper = readerMapper;
	}

	List<PenaltyRecord> getcurrentActivePenaltyByUserID(int userID) {
		return penaltyRecordMapper.getcurrentActivePenaltyByUserID(userID);
	}

	List<PenaltyRecord> getcurrentActivePenaltyByBorrowID(int borrowID) {
		List<PenaltyRecord> penaltyRecordList = penaltyRecordMapper.searchByBorrowID(borrowID);
		penaltyRecordList.removeIf(temp -> temp.getStatus().equals("0"));
		return penaltyRecordList;
	}


	public List<OverdueRecord> getcurrentActiveOverdueByUserID(int userID) {
		return overdueRecordMapper.getcurrentActiveOverdueByUserID(userID);
	}

	public List<OverdueRecord> getcurrentActiveOverdueByBorrowID(int borrowID) {
		List<OverdueRecord> overdueRecordList = overdueRecordMapper.searchByBorrowID(borrowID);
		overdueRecordList.removeIf(temp -> temp.getStatus().equals("0"));
		return overdueRecordList;
	}


	public PenaltyRecord addPenalty(User user, User admin, BorrowRecord borrowRecord, double penaltyAmount, String penaltyType)
			throws RuntimeException {
		log.info("添加罚款");
		List<String> roleList = userService.getRoleStringByUser(admin);
		Staff staff = staffMapper.selectByPrimaryKey(admin.getUserId());
		PenaltyRecord penaltyRecord = new PenaltyRecord();
		if (!roleList.contains("reader")) {
			log.info("确认管理员身份 开始添加罚款");
			penaltyRecord.setPenalty(penaltyAmount);
			penaltyRecord.setUserId(user.getUserId());
			penaltyRecord.setBorrowId(borrowRecord.getBorrowId());
			penaltyRecord.setPenaltyType(penaltyType);
			penaltyRecord.setAdminId(admin.getUserId());
			penaltyRecordMapper.insertSelective(penaltyRecord);
		} else {
			throw new RuntimeException("不是管理员在 添加罚款");
		}
		return penaltyRecord;
	}

	public OverdueRecord addOverdueRecord(User user, BorrowRecord borrowRecord, BookInventory bookInventory, int passDays)
			throws RuntimeException {
		log.info("添加逾期记录");

		List<OverdueRecord> overdueRecords = this.getcurrentActiveOverdueByBorrowID(borrowRecord.getBorrowId());

		if (overdueRecords.isEmpty()) {
			log.info("逾期记录不存在 添加记录");
			OverdueRecord overdueRecord = new OverdueRecord();
			overdueRecord.setPassDays(passDays);
			overdueRecord.setBookitemId(bookInventory.getBookitemId());
			overdueRecord.setBorrowId(borrowRecord.getBorrowId());
			overdueRecord.setUserId(user.getUserId());
			overdueRecord.setStatus("1");
			overdueRecordMapper.insertSelective(overdueRecord);
			return overdueRecord;
		} else if (overdueRecords.size() == 1) {
			log.info("已存在逾期记录 更新记录");
			return updateOverdueRecord(overdueRecords.get(0), passDays);
		} else {
			throw new RuntimeException("数据库出错");
		}
	}

	public OverdueRecord updateOverdueRecord(OverdueRecord overdueRecord, int passDays) {
		overdueRecord.setPassDays(passDays);
		overdueRecordMapper.updateByPrimaryKeySelective(overdueRecord);
		return overdueRecord;
	}


	public int checkOverduePenalty(BorrowRecord borrowRecord, User user, BookInventory bookInventory) throws RuntimeException {
		//每逾期一天 缴纳5元违约金 违约金最高不超过书的价格
		// 罚款类别    1 为逾期罚款 2 为损坏等罚款
		log.info("判断是否添加逾期罚款");
		DateTime startTime = new DateTime(borrowRecord.getOutDate());
		DateTime currentTime = new DateTime();
		Days days = Days.daysBetween(startTime, currentTime);
		if (days.getDays() > 15) {
			log.info("已逾期 检查是否有已经存在记录");

			addOverdueRecord(user, borrowRecord, bookInventory, days.getDays() - 15);

			List<PenaltyRecord> penaltyRecords = penaltyRecordMapper.searchByBorrowID(borrowRecord.getBorrowId());

			penaltyRecords.removeIf(temp -> temp.getPenaltyType().equals("2")); // 删除非逾期罚款

			double penalty = (days.getDays() - 15) * 5.0;
			BookInformation bookInformation = bookInformationMapper.selectByPrimaryKey(bookInventory.getBookId());

			//判断违约金是否超过书本价格
			if (penalty > bookInformation.getPrice()) penalty = bookInformation.getPrice();

			if (penaltyRecords.size() == 1) {

				log.info("发现已经存在的记录，更新罚款记录");

				PenaltyRecord penaltyRecord = penaltyRecords.get(0);
				penaltyRecord.setPenalty(penalty);
				return penaltyRecordMapper.updateByPrimaryKeySelective(penaltyRecord);

			} else if (penaltyRecords.isEmpty()) {
				log.info("曾经没有记录，添加记录");
				PenaltyRecord penaltyRecord = new PenaltyRecord();
				penaltyRecord.setPenalty(penalty);
				penaltyRecord.setBorrowId(borrowRecord.getBorrowId());
				penaltyRecord.setUserId(borrowRecord.getUserId());
				penaltyRecord.setPenaltyType("1");
				return penaltyRecordMapper.insertSelective(penaltyRecord);

			} else {
				throw new RuntimeException("有多个同样记录 需要检查数据库");
			}
		}
		log.info("未逾期 跳过");
		return 0;
	}

	public double findAllPenaltyAmountBypenaltyRecords(List<PenaltyRecord> penaltyRecords) {
		return penaltyRecords.stream().mapToDouble(PenaltyRecord::getPenalty).sum();
	}

	public double findAllPenaltyAmountBypenaltyRecords(int userID) throws RuntimeException {
		List<PenaltyRecord> penaltyRecords = getcurrentActivePenaltyByUserID(userID);
		Reader reader = readerMapper.selectByPrimaryKey(userID);
		if (reader == null) {
			throw new RuntimeException("不存在对应reader 请检查数据库");
		}
		double amount = penaltyRecords.stream().mapToDouble(PenaltyRecord::getPenalty).sum();
		if (amount - reader.getDeposit() < 0.0) return 0.0;
		else return amount - reader.getDeposit();
	}

	public List<PenaltyRecord> findallPenaltyRecordByUserID(int userID) {
		return penaltyRecordMapper.searchByUserID(userID);
	}


	public double payPenalty(double payAmount, int userID) {

		log.info("清算已付金额与欠款金额");

		Reader reader = readerMapper.selectByPrimaryKey(userID);
		payAmount += reader.getDeposit();
		List<PenaltyRecord> penaltyRecords = getcurrentActivePenaltyByUserID(userID);
		double amount = findAllPenaltyAmountBypenaltyRecords(penaltyRecords);
		for (PenaltyRecord penaltyRecord : penaltyRecords) {
			if (payAmount > penaltyRecord.getPenalty()) {
				payAmount -= penaltyRecord.getPenalty();
				penaltyRecord.setStatus("0");

				log.info("写入数据库");
				penaltyRecordMapper.updateByPrimaryKeySelective(penaltyRecord);
			}
		}
		reader.setDeposit(payAmount);
		readerMapper.updateByPrimaryKeySelective(reader);
		return payAmount;
	}

}
