package com.worldpara.mapper.impl;

import com.worldpara.domain.LibraryRecord;
import com.worldpara.domain.LibraryRecordExample;
import com.worldpara.mapper.inf.LibraryRecordMapper;
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
public class LibraryRecordMapperImpl implements LibraryRecordMapper {

	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public LibraryRecordMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}


	@Override
	public int deleteByPrimaryKey(Integer library) {
		return sqlSessionTemplate.delete("com.worldpara.mapper.inf.LibraryRecordMapper.deleteByPrimaryKey", library);
	}

	@Override
	public int insert(LibraryRecord record) {
		return 0;
	}

	@Override
	public int insertSelective(LibraryRecord record) {
		return sqlSessionTemplate.insert("com.worldpara.mapper.inf.LibraryRecordMapper.insertSelective", record);
	}

	@Override
	public List<LibraryRecord> selectByExample(LibraryRecordExample example) {
		return null;
	}

	@Override
	public LibraryRecord selectByPrimaryKey(Integer library) {
		return sqlSessionTemplate.selectOne("com.worldpara.mapper.inf.LibraryRecordMapper.selectByPrimaryKey", library);

	}

	@Override
	public int updateByPrimaryKeySelective(LibraryRecord record) {
		return sqlSessionTemplate.update("com.worldpara.mapper.inf.LibraryRecordMapper.updateByPrimaryKeySelective", record);

	}

	@Override
	public int updateByPrimaryKey(LibraryRecord record) {
		return 0;
	}

	@Override
	public List<LibraryRecord> allRecords() {
		LibraryRecordExample example = new LibraryRecordExample();
		LibraryRecordMapper libraryRecordMapper;
		example.createCriteria().andLibraryGreaterThanOrEqualTo(0);
		libraryRecordMapper = sqlSessionTemplate.getMapper(LibraryRecordMapper.class);
		return libraryRecordMapper.selectByExample(example);

	}
}
