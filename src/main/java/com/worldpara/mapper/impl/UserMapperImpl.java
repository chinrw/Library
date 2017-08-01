package com.worldpara.mapper.impl;

import com.worldpara.domain.User;
import com.worldpara.domain.UserExample;
import com.worldpara.mapper.inf.UserMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chin39 on 2017/6/27.
 * User类的补全 提供高级查询功能
 */

@Repository
public class UserMapperImpl implements UserMapper {

	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public UserMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}


	@Override
	public int deleteByPrimaryKey(Integer userId) {
		return sqlSessionTemplate.delete("com.worldpara.mapper.inf.UserMapper.deleteByPrimaryKey", userId);
	}

	@Override
	public int insert(User record) {
		//返回user id
		return sqlSessionTemplate.insert("com.worldpara.mapper.inf.UserMapper.insert", record);
	}

	@Override
	public int insertSelective(User record) {
		return sqlSessionTemplate.insert("com.worldpara.mapper.inf.UserMapper.insertSelective", record);
	}

	@Override
	public List<User> selectByExample(UserExample example) {
		return null;
	}

	@Override
	public User selectByPrimaryKey(Integer userId) {
		return (User) sqlSessionTemplate.selectOne("com.worldpara.mapper.inf.UserMapper.selectByPrimaryKey", userId);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return sqlSessionTemplate.update("com.worldpara.mapper.inf.UserMapper.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(User record) {
		return 0;
	}

	@Override
	public List<User> selectByUsername(String username) {
		UserExample example;
		UserMapper userMapper;
		username = "%" + username + "%";
		example = new UserExample();
		example.createCriteria().andUsernameLike(username);
		userMapper = sqlSessionTemplate.getMapper(UserMapper.class);
		return userMapper.selectByExample(example);

	}

	@Override
	public List<User> searchByUsername(String username) {
		UserExample example;
		UserMapper userMapper;
		example = new UserExample();
		example.createCriteria().andUsernameEqualTo(username);
		userMapper = sqlSessionTemplate.getMapper(UserMapper.class);
		return userMapper.selectByExample(example);
	}
}
