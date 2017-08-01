package com.worldpara.mapper.inf;

import com.worldpara.domain.RoleUserRecord;
import com.worldpara.domain.RoleUserRecordExample;
import java.util.List;

public interface RoleUserRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_user_record
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer relationId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_user_record
     *
     * @mbg.generated
     */
    int insert(RoleUserRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_user_record
     *
     * @mbg.generated
     */
    int insertSelective(RoleUserRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_user_record
     *
     * @mbg.generated
     */
    List<RoleUserRecord> selectByExample(RoleUserRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_user_record
     *
     * @mbg.generated
     */
    RoleUserRecord selectByPrimaryKey(Integer relationId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_user_record
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RoleUserRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_user_record
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RoleUserRecord record);

    List<RoleUserRecord> selectByUserID(int userID);
}