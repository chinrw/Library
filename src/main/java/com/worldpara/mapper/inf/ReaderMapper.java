package com.worldpara.mapper.inf;

import com.worldpara.domain.Reader;
import com.worldpara.domain.ReaderExample;
import java.util.List;

public interface ReaderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reader
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer readerId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reader
     *
     * @mbg.generated
     */
    int insert(Reader record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reader
     *
     * @mbg.generated
     */
    int insertSelective(Reader record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reader
     *
     * @mbg.generated
     */
    List<Reader> selectByExample(ReaderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reader
     *
     * @mbg.generated
     */
    Reader selectByPrimaryKey(Integer readerId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reader
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Reader record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reader
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Reader record);
}