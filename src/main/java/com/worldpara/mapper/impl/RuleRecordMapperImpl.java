package com.worldpara.mapper.impl;

import com.worldpara.domain.RuleRecord;
import com.worldpara.domain.RuleRecordExample;
import com.worldpara.mapper.inf.RuleRecordMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chin39 on 2017/7/3.
 */

@Repository
public class RuleRecordMapperImpl implements RuleRecordMapper {


	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public RuleRecordMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}


	@Override
	public int deleteByPrimaryKey(Integer ruleId) {
		return sqlSessionTemplate.delete("com.worldpara.mapper.inf.RuleRecordMapper.deleteByPrimaryKey", ruleId);
	}

	@Override
	public int insert(RuleRecord record) {
		return 0;
	}

	@Override
	public int insertSelective(RuleRecord record) {
		return sqlSessionTemplate.insert("com.worldpara.mapper.inf.RuleRecordMapper.insertSelective", record);
	}

	@Override
	public List<RuleRecord> selectByExample(RuleRecordExample example) {
		return null;
	}

	@Override
	public RuleRecord selectByPrimaryKey(Integer ruleId) {
		return sqlSessionTemplate.selectOne("com.worldpara.mapper.inf.RuleRecordMapper.selectByPrimaryKey", ruleId);
	}

	@Override
	public int updateByPrimaryKeySelective(RuleRecord record) {
		return sqlSessionTemplate.update("com.worldpara.mapper.inf.RuleRecordMapper.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(RuleRecord record) {
		return 0;
	}
}
