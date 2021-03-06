package com.worldpara.mapper.inf;

import com.worldpara.domain.PenaltyRecord;
import com.worldpara.domain.PenaltyRecordExample;
import java.util.List;

public interface PenaltyRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table penalty_record
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer penaltyRecord);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table penalty_record
     *
     * @mbg.generated
     */
    int insert(PenaltyRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table penalty_record
     *
     * @mbg.generated
     */
    int insertSelective(PenaltyRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table penalty_record
     *
     * @mbg.generated
     */
    List<PenaltyRecord> selectByExample(PenaltyRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table penalty_record
     *
     * @mbg.generated
     */
    PenaltyRecord selectByPrimaryKey(Integer penaltyRecord);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table penalty_record
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PenaltyRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table penalty_record
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PenaltyRecord record);

    List<PenaltyRecord> searchByUserID(int userID);

    List<PenaltyRecord> searchByBorrowID(int borrowID);

    List<PenaltyRecord> getcurrentActivePenaltyByUserID(int userID);
}