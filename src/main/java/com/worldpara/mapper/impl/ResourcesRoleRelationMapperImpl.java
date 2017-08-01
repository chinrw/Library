package com.worldpara.mapper.impl;

import com.worldpara.domain.ResourcesRoleRelation;
import com.worldpara.domain.ResourcesRoleRelationExample;
import com.worldpara.mapper.inf.ResourcesRoleRelationMapper;
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
public class ResourcesRoleRelationMapperImpl implements ResourcesRoleRelationMapper {

	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public ResourcesRoleRelationMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}


	@Override
	public int deleteByPrimaryKey(Integer rToRId) {
		return sqlSessionTemplate.delete("com.worldpara.mapper.inf.ResourcesRoleRelationMapper.deleteByPrimaryKey", rToRId);

	}

	@Override
	public int insert(ResourcesRoleRelation record) {
		return 0;
	}

	@Override
	public int insertSelective(ResourcesRoleRelation record) {
		return sqlSessionTemplate.insert("com.worldpara.mapper.inf.ResourcesRoleRelationMapper.insertSelective", record);
	}

	@Override
	public List<ResourcesRoleRelation> selectByExample(ResourcesRoleRelationExample example) {
		return null;
	}

	@Override
	public ResourcesRoleRelation selectByPrimaryKey(Integer rToRId) {
		return sqlSessionTemplate.selectOne("com.worldpara.mapper.inf.ResourcesRoleRelationMapper.selectByPrimaryKey", rToRId);
	}

	@Override
	public int updateByPrimaryKeySelective(ResourcesRoleRelation record) {
		return sqlSessionTemplate.update("com.worldpara.mapper.inf.ResourcesRoleRelationMapper.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(ResourcesRoleRelation record) {
		return 0;
	}
}
