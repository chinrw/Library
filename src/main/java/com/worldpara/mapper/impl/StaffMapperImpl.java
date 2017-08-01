package com.worldpara.mapper.impl;

import com.worldpara.domain.Staff;
import com.worldpara.domain.StaffExample;
import com.worldpara.mapper.inf.StaffMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chin39 on 2017/6/30.
 */
@Repository
public class StaffMapperImpl implements StaffMapper {


	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public StaffMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}


	@Override
	public int deleteByPrimaryKey(Integer staffId) {
		return sqlSessionTemplate.delete("com.worldpara.mapper.inf.StaffMapper.deleteByPrimaryKey", staffId);
	}

	@Override
	public int insert(Staff record) {
		return 0;
	}

	@Override
	public int insertSelective(Staff record) {
		return sqlSessionTemplate.insert("com.worldpara.mapper.inf.StaffMapper.insertSelective", record);

	}

	@Override
	public List<Staff> selectByExample(StaffExample example) {
		return null;
	}

	@Override
	public Staff selectByPrimaryKey(Integer staffId) {
		return sqlSessionTemplate.selectOne("com.worldpara.mapper.inf.StaffMapper.selectByPrimaryKey", staffId);
	}

	@Override
	public int updateByPrimaryKeySelective(Staff record) {
		return sqlSessionTemplate.update("com.worldpara.mapper.inf.StaffMapper.selectByPrimaryKey", record);

	}

	@Override
	public int updateByPrimaryKey(Staff record) {
		return 0;
	}
}
