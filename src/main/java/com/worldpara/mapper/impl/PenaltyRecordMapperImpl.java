package com.worldpara.mapper.impl;

import com.worldpara.domain.PenaltyRecord;
import com.worldpara.domain.PenaltyRecordExample;
import com.worldpara.mapper.inf.PenaltyRecordMapper;
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
public class PenaltyRecordMapperImpl implements PenaltyRecordMapper {


	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public PenaltyRecordMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}


	@Override
	public int deleteByPrimaryKey(Integer penaltyRecord) {
		return sqlSessionTemplate.delete("com.worldpara.mapper.inf.PenaltyRecordMapper.deleteByPrimaryKey", penaltyRecord);
	}

	@Override
	public int insert(PenaltyRecord record) {
		return 0;
	}

	@Override
	public int insertSelective(PenaltyRecord record) {
		return sqlSessionTemplate.insert("com.worldpara.mapper.inf.PenaltyRecordMapper.insertSelective", record);
	}

	@Override
	public List<PenaltyRecord> selectByExample(PenaltyRecordExample example) {
		return null;
	}

	@Override
	public PenaltyRecord selectByPrimaryKey(Integer penaltyRecord) {
		return sqlSessionTemplate.selectOne("com.worldpara.mapper.inf.PenaltyRecordMapper.selectByPrimaryKey", penaltyRecord);
	}

	@Override
	public int updateByPrimaryKeySelective(PenaltyRecord record) {
		return sqlSessionTemplate.update("com.worldpara.mapper.inf.PenaltyRecordMapper.updateByPrimaryKeySelective", record);

	}

	@Override
	public int updateByPrimaryKey(PenaltyRecord record) {
		return 0;
	}

	@Override
	public List<PenaltyRecord> searchByUserID(int userID) {
		PenaltyRecordExample example = new PenaltyRecordExample();
		example.createCriteria().andUserIdEqualTo(userID);
		PenaltyRecordMapper penaltyRecordMapper = sqlSessionTemplate.getMapper(PenaltyRecordMapper.class);
		return penaltyRecordMapper.selectByExample(example);

	}

	@Override
	public List<PenaltyRecord> searchByBorrowID(int borrowID) {
		PenaltyRecordExample example = new PenaltyRecordExample();
		example.createCriteria().andBorrowIdEqualTo(borrowID);
		PenaltyRecordMapper penaltyRecordMapper = sqlSessionTemplate.getMapper(PenaltyRecordMapper.class);
		return penaltyRecordMapper.selectByExample(example);

	}

	@Override
	public List<PenaltyRecord> getcurrentActivePenaltyByUserID(int userID) {
		PenaltyRecordExample example = new PenaltyRecordExample();
		example.createCriteria().andUserIdEqualTo(userID).andStatusEqualTo("1");
		PenaltyRecordMapper penaltyRecordMapper = sqlSessionTemplate.getMapper(PenaltyRecordMapper.class);
		return penaltyRecordMapper.selectByExample(example);

	}
}
