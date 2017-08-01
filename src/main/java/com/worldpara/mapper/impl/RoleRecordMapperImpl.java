package com.worldpara.mapper.impl;

import com.worldpara.domain.RoleRecord;
import com.worldpara.domain.RoleRecordExample;
import com.worldpara.mapper.inf.RoleRecordMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chin39 on 2017/7/3.
 */
@Repository
public class RoleRecordMapperImpl implements RoleRecordMapper {


	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public RoleRecordMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}


	@Override
	public int deleteByPrimaryKey(Integer roleId) {
		return sqlSessionTemplate.delete("com.worldpara.mapper.inf.RoleRecordMapper.deleteByPrimaryKey", roleId);
	}

	@Override
	public int insert(RoleRecord record) {
		return 0;
	}

	@Override
	public int insertSelective(RoleRecord record) {
		return sqlSessionTemplate.insert("com.worldpara.mapper.inf.RoleRecordMapper.insertSelective", record);

	}

	@Override
	public List<RoleRecord> selectByExample(RoleRecordExample example) {
		return null;
	}

	@Override
	public RoleRecord selectByPrimaryKey(Integer roleId) {
		return sqlSessionTemplate.selectOne("com.worldpara.mapper.inf.RoleRecordMapper.selectByPrimaryKey", roleId);
	}

	@Override
	public int updateByPrimaryKeySelective(RoleRecord record) {
		return sqlSessionTemplate.update("com.worldpara.mapper.inf.RoleRecordMapper.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(RoleRecord record) {
		return 0;
	}

	@Override
	public List<RoleRecord> selectByRoleType(String type) {
		RoleRecordExample example = new RoleRecordExample();
		example.createCriteria().andRoleTypeEqualTo(type).andStatusEqualTo("1");
		RoleRecordMapper roleRecordMapper = sqlSessionTemplate.getMapper(RoleRecordMapper.class);
		return roleRecordMapper.selectByExample(example);
	}
}

