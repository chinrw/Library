package com.worldpara.mapper.impl;

import com.worldpara.domain.Resources;
import com.worldpara.domain.ResourcesExample;
import com.worldpara.mapper.inf.ResourcesMapper;
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
public class ResourcesMapperImpl implements ResourcesMapper {

	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public ResourcesMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}


	@Override
	public int deleteByPrimaryKey(Integer resourcesId) {
		return sqlSessionTemplate.delete("com.worldpara.mapper.inf.ResourcesMapper.deleteByPrimaryKey", resourcesId);

	}

	@Override
	public int insert(Resources record) {
		return 0;
	}

	@Override
	public int insertSelective(Resources record) {
		return sqlSessionTemplate.insert("com.worldpara.mapper.inf.ResourcesMapper.insertSelective", record);
	}

	@Override
	public List<Resources> selectByExample(ResourcesExample example) {
		return null;
	}

	@Override
	public Resources selectByPrimaryKey(Integer resourcesId) {
		return sqlSessionTemplate.selectOne("com.worldpara.mapper.inf.ResourcesMapper.selectByPrimaryKey", resourcesId);
	}

	@Override
	public int updateByPrimaryKeySelective(Resources record) {
		return sqlSessionTemplate.update("com.worldpara.mapper.inf.ResourcesMapper.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(Resources record) {
		return 0;
	}
}
