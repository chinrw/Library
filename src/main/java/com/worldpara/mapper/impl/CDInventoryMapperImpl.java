package com.worldpara.mapper.impl;

import com.worldpara.domain.CDInventory;
import com.worldpara.domain.CDInventoryExample;
import com.worldpara.mapper.inf.CDInventoryMapper;
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
public class CDInventoryMapperImpl implements CDInventoryMapper {


	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public CDInventoryMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}


	@Override
	public int deleteByPrimaryKey(Integer cdId) {
		return sqlSessionTemplate.delete("com.worldpara.mapper.inf.CDInventoryMapper.deleteByPrimaryKey", cdId);
	}

	@Override
	public int insert(CDInventory record) {
		return 0;
	}

	@Override
	public int insertSelective(CDInventory record) {
		return sqlSessionTemplate.insert("com.worldpara.mapper.inf.CDInventoryMapper.insertSelective", record);
	}

	@Override
	public List<CDInventory> selectByExample(CDInventoryExample example) {
		return null;
	}

	@Override
	public CDInventory selectByPrimaryKey(Integer cdId) {
		return sqlSessionTemplate.selectOne("com.worldpara.mapper.inf.CDInventoryMapper.selectByPrimaryKey", cdId);
	}

	@Override
	public int updateByPrimaryKeySelective(CDInventory record) {
		return sqlSessionTemplate.update("com.worldpara.mapper.inf.CDInventoryMapper.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(CDInventory record) {
		return 0;
	}
}
