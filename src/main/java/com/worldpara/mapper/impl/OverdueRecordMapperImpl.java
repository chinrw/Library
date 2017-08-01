package com.worldpara.mapper.impl;

import com.worldpara.domain.OverdueRecord;
import com.worldpara.domain.OverdueRecordExample;
import com.worldpara.mapper.inf.OverdueRecordMapper;
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
public class OverdueRecordMapperImpl implements OverdueRecordMapper {


	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public OverdueRecordMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public int deleteByPrimaryKey(Integer overdueId) {
		return sqlSessionTemplate.delete("com.worldpara.mapper.inf.OverdueRecordMapper.deleteByPrimaryKey", overdueId);
	}

	@Override
	public int insert(OverdueRecord record) {
		return 0;
	}

	@Override
	public int insertSelective(OverdueRecord record) {
		return sqlSessionTemplate.insert("com.worldpara.mapper.inf.OverdueRecordMapper.insertSelective", record);
	}

	@Override
	public List<OverdueRecord> selectByExample(OverdueRecordExample example) {
		return null;
	}

	@Override
	public OverdueRecord selectByPrimaryKey(Integer overdueId) {
		return sqlSessionTemplate.selectOne("com.worldpara.mapper.inf.OverdueRecordMapper.selectByPrimaryKey", overdueId);

	}

	@Override
	public int updateByPrimaryKeySelective(OverdueRecord record) {
		return sqlSessionTemplate.update("com.worldpara.mapper.inf.OverdueRecordMapper.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(OverdueRecord record) {
		return 0;
	}

	@Override
	public List<OverdueRecord> searchByUserID(int userID) {
		OverdueRecordExample example = new OverdueRecordExample();
		example.createCriteria().andUserIdEqualTo(userID);
		OverdueRecordMapper overdueRecordMapper = sqlSessionTemplate.getMapper(OverdueRecordMapper.class);
		return overdueRecordMapper.selectByExample(example);
	}

	@Override
	public List<OverdueRecord> searchByBorrowID(int borrowID) {
		OverdueRecordExample example = new OverdueRecordExample();
		example.createCriteria().andBorrowIdEqualTo(borrowID);
		OverdueRecordMapper overdueRecordMapper = sqlSessionTemplate.getMapper(OverdueRecordMapper.class);
		return overdueRecordMapper.selectByExample(example);

	}

	@Override
	public List<OverdueRecord> getcurrentActiveOverdueByUserID(int userID) {
		OverdueRecordExample example = new OverdueRecordExample();
		example.createCriteria().andBorrowIdEqualTo(userID).andStatusEqualTo("1");
		OverdueRecordMapper overdueRecordMapper = sqlSessionTemplate.getMapper(OverdueRecordMapper.class);
		return overdueRecordMapper.selectByExample(example);

	}
}
