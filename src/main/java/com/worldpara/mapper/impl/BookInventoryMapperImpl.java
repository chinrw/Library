package com.worldpara.mapper.impl;

import com.worldpara.domain.BookInformation;
import com.worldpara.domain.BookInventory;
import com.worldpara.domain.BookInventoryExample;
import com.worldpara.mapper.inf.BookInventoryMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chin39 on 2017/6/30.
 */

@Repository
public class BookInventoryMapperImpl implements BookInventoryMapper {


	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public BookInventoryMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public int deleteByPrimaryKey(Integer bookitemId) {
		return sqlSessionTemplate.delete("com.worldpara.mapper.inf.BookInventoryMapper.deleteByPrimaryKey", bookitemId);
	}

	@Override
	public int insert(BookInventory record) {
		return 0;
	}

	@Override
	public int insertSelective(BookInventory record) {
		return sqlSessionTemplate.insert("com.worldpara.mapper.inf.BookInventoryMapper.insertSelective", record);
	}

	@Override
	public List<BookInventory> selectByExample(BookInventoryExample example) {
		return null;
	}

	@Override
	public BookInventory selectByPrimaryKey(Integer bookitemId) {
		return sqlSessionTemplate.selectOne("com.worldpara.mapper.inf.BookInventoryMapper.selectByPrimaryKey", bookitemId);
	}

	@Override
	public int updateByPrimaryKeySelective(BookInventory record) {
		return sqlSessionTemplate.update("com.worldpara.mapper.inf.BookInventoryMapper.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(BookInventory record) {
		return 0;
	}

	@Override
	public List<BookInventory> searchByBookID(BookInformation bookInformation) {
		BookInventoryExample example = new BookInventoryExample();
		example.createCriteria().andBookIdEqualTo(bookInformation.getBookId());
		BookInventoryMapper bookInventoryMapper = sqlSessionTemplate.getMapper(BookInventoryMapper.class);
		return bookInventoryMapper.selectByExample(example);

	}
}
