package com.worldpara.mapper.inf;

import com.worldpara.domain.BookInventory;
import com.worldpara.domain.BorrowRecord;
import com.worldpara.domain.BorrowRecordExample;
import com.worldpara.domain.User;

import java.util.List;

public interface BorrowRecordMapper {
	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table borrow_record
	 *
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer borrowId);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table borrow_record
	 *
	 * @mbg.generated
	 */
	int insert(BorrowRecord record);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table borrow_record
	 *
	 * @mbg.generated
	 */
	int insertSelective(BorrowRecord record);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table borrow_record
	 *
	 * @mbg.generated
	 */
	List<BorrowRecord> selectByExample(BorrowRecordExample example);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table borrow_record
	 *
	 * @mbg.generated
	 */
	BorrowRecord selectByPrimaryKey(Integer borrowId);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table borrow_record
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(BorrowRecord record);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table borrow_record
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKey(BorrowRecord record);

	List<BorrowRecord> searchByBookInventoryID(BookInventory bookInventory);

	List<BorrowRecord> searchByUserID(User user);
}