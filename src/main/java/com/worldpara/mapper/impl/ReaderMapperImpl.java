package com.worldpara.mapper.impl;

import com.worldpara.domain.Reader;
import com.worldpara.domain.ReaderExample;
import com.worldpara.mapper.inf.ReaderMapper;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chin39 on 2017/6/30.
 */

@Repository
public class ReaderMapperImpl implements ReaderMapper {

	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public ReaderMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public int deleteByPrimaryKey(Integer readerId) {
		return sqlSessionTemplate.delete("com.worldpara.mapper.inf.ReaderMapper.deleteByPrimaryKey", readerId);
	}

	@Override
	public int insert(Reader record) {
		return 0;
	}

	@Override
	public int insertSelective(Reader record) {
		return sqlSessionTemplate.insert("com.worldpara.mapper.inf.ReaderMapper.insertSelective", record);
	}

	@Override
	public List<Reader> selectByExample(ReaderExample example) {
		return null;
	}

	@Override
	public Reader selectByPrimaryKey(Integer readerId) {
		return sqlSessionTemplate.selectOne("com.worldpara.mapper.inf.ReaderMapper.selectByPrimaryKey", readerId);
	}

	@Override
	public int updateByPrimaryKeySelective(Reader record) {
		return sqlSessionTemplate.update("com.worldpara.mapper.inf.ReaderMapper.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(Reader record) {
		return 0;
	}
}
