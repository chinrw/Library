package com.worldpara.mapper.impl;

import com.worldpara.domain.BookInformation;
import com.worldpara.domain.BookInformationExample;
import com.worldpara.mapper.inf.BookInformationMapper;
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
public class BookInformationMapperImpl implements BookInformationMapper {


	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public BookInformationMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}


	@Override
	public int deleteByPrimaryKey(Integer bookId) {
		return sqlSessionTemplate.delete("com.worldpara.mapper.inf.BookInformationMapper.deleteByPrimaryKey", bookId);
	}

	@Override
	public int insert(BookInformation record) {
		return 0;
	}

	@Override
	public int insertSelective(BookInformation record) {
		return sqlSessionTemplate.insert("com.worldpara.mapper.inf.BookInformationMapper.insertSelective", record);
	}

	@Override
	public List<BookInformation> selectByExample(BookInformationExample example) {
		return null;
	}

	@Override
	public BookInformation selectByPrimaryKey(Integer bookId) {
		return sqlSessionTemplate.selectOne("com.worldpara.mapper.inf.BookInformationMapper.selectByPrimaryKey", bookId);
	}

	@Override
	public int updateByPrimaryKeySelective(BookInformation record) {
		return sqlSessionTemplate.update("com.worldpara.mapper.inf.BookInformationMapper.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(BookInformation record) {
		return 0;
	}

	@Override
	public List<BookInformation> selectByBookname(String bookName) {
		BookInformationExample example = new BookInformationExample();
		BookInformationMapper bookInformationMapper;
		example.createCriteria().andTitleEqualTo(bookName);
		bookInformationMapper = sqlSessionTemplate.getMapper(BookInformationMapper.class);
		return bookInformationMapper.selectByExample(example);
	}

	@Override
	public List<BookInformation> selectByISBN(String isbn) {
		BookInformationExample example = new BookInformationExample();
		BookInformationMapper bookInformationMapper;
		example.createCriteria().andIsbnEqualTo(isbn);
		bookInformationMapper = sqlSessionTemplate.getMapper(BookInformationMapper.class);
		return bookInformationMapper.selectByExample(example);
	}

	@Override
	public List<BookInformation> likeByBookname(String bookName) {
		BookInformationExample example = new BookInformationExample();
		bookName = "%" + bookName + "%";
		BookInformationMapper bookInformationMapper;
		example.createCriteria().andTitleLike(bookName);
		bookInformationMapper = sqlSessionTemplate.getMapper(BookInformationMapper.class);
		return bookInformationMapper.selectByExample(example);
	}
}
