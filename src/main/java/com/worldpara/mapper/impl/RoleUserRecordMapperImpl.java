package com.worldpara.mapper.impl;

import com.worldpara.domain.RoleUserRecord;
import com.worldpara.domain.RoleUserRecordExample;
import com.worldpara.mapper.inf.RoleUserRecordMapper;
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
public class RoleUserRecordMapperImpl implements RoleUserRecordMapper {


	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public RoleUserRecordMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}


	@Override
	public int deleteByPrimaryKey(Integer relationId) {
		return sqlSessionTemplate.delete("com.worldpara.mapper.inf.RoleUserRecordMapper.deleteByPrimaryKey", relationId);
	}

	@Override
	public int insert(RoleUserRecord record) {
		return 0;
	}

	@Override
	public int insertSelective(RoleUserRecord record) {
		return sqlSessionTemplate.insert("com.worldpara.mapper.inf.RoleUserRecordMapper.insertSelective", record);
	}

	@Override
	public List<RoleUserRecord> selectByExample(RoleUserRecordExample example) {
		return null;
	}

	@Override
	public RoleUserRecord selectByPrimaryKey(Integer relationId) {
		return sqlSessionTemplate.selectOne("com.worldpara.mapper.inf.RoleUserRecordMapper.selectByPrimaryKey", relationId);
	}

	@Override
	public int updateByPrimaryKeySelective(RoleUserRecord record) {
		return sqlSessionTemplate.update("com.worldpara.mapper.inf.RoleUserRecordMapper.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(RoleUserRecord record) {
		return 0;
	}

	@Override
	public List<RoleUserRecord> selectByUserID(int userID) {
		RoleUserRecordExample example = new RoleUserRecordExample();
		example.createCriteria().andUserIdEqualTo(userID);
		RoleUserRecordMapper roleUserRecordMapper = sqlSessionTemplate.getMapper(RoleUserRecordMapper.class);
		return roleUserRecordMapper.selectByExample(example);
	}
}
