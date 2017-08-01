package com.worldpara.mapper.impl;

import com.worldpara.domain.BookInventory;
import com.worldpara.domain.BorrowRecord;
import com.worldpara.domain.BorrowRecordExample;
import com.worldpara.domain.User;
import com.worldpara.mapper.inf.BorrowRecordMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chin39 on 2017/7/3.
 */
@Repository
public class BorrowRecordMapperImpl implements BorrowRecordMapper {


	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public BorrowRecordMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}


	@Override
	public int deleteByPrimaryKey(Integer borrowId) {
		return sqlSessionTemplate.delete("com.worldpara.mapper.inf.BorrowRecordMapper.deleteByPrimaryKey", borrowId);

	}

	@Override
	public int insert(BorrowRecord record) {
		return 0;
	}

	@Override
	public int insertSelective(BorrowRecord record) {
		return sqlSessionTemplate.insert("com.worldpara.mapper.inf.BorrowRecordMapper.insertSelective", record);
	}

	@Override
	public List<BorrowRecord> selectByExample(BorrowRecordExample example) {
		return null;
	}

	@Override
	public BorrowRecord selectByPrimaryKey(Integer borrowId) {
		return sqlSessionTemplate.selectOne("com.worldpara.mapper.inf.BorrowRecordMapper.selectByPrimaryKey", borrowId);
	}

	@Override
	public int updateByPrimaryKeySelective(BorrowRecord record) {
		return sqlSessionTemplate.update("com.worldpara.mapper.inf.BorrowRecordMapper.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(BorrowRecord record) {
		return 0;
	}

	@Override
	public List<BorrowRecord> searchByBookInventoryID(BookInventory bookInventory) {
		BorrowRecordExample example = new BorrowRecordExample();
		example.createCriteria().andBookitemIdEqualTo(bookInventory.getBookitemId());
		BorrowRecordMapper borrowRecordMapper = sqlSessionTemplate.getMapper(BorrowRecordMapper.class);
		return borrowRecordMapper.selectByExample(example);
	}

	@Override
	public List<BorrowRecord> searchByUserID(User user) {
		BorrowRecordExample example = new BorrowRecordExample();
		example.createCriteria().andUserIdEqualTo(user.getUserId());
		BorrowRecordMapper borrowRecordMapper = sqlSessionTemplate.getMapper(BorrowRecordMapper.class);
		return borrowRecordMapper.selectByExample(example);
	}


}
