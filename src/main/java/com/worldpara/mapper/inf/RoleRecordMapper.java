package com.worldpara.mapper.inf;

import com.worldpara.domain.RoleRecord;
import com.worldpara.domain.RoleRecordExample;

import javax.management.relation.Role;
import java.util.List;

public interface RoleRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_record
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_record
     *
     * @mbg.generated
     */
    int insert(RoleRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_record
     *
     * @mbg.generated
     */
    int insertSelective(RoleRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_record
     *
     * @mbg.generated
     */
    List<RoleRecord> selectByExample(RoleRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_record
     *
     * @mbg.generated
     */
    RoleRecord selectByPrimaryKey(Integer roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_record
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RoleRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_record
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RoleRecord record);

    List<RoleRecord> selectByRoleType(String type);
}