package com.worldpara.domain;

public class RoleRecord {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_record.role_id
     *
     * @mbg.generated
     */
    private Integer roleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_record.role_type
     *
     * @mbg.generated
     */
    private String roleType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_record.role_sum
     *
     * @mbg.generated
     */
    private String roleSum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_record.status
     *
     * @mbg.generated
     */
    private String status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_record.role_id
     *
     * @return the value of role_record.role_id
     *
     * @mbg.generated
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_record.role_id
     *
     * @param roleId the value for role_record.role_id
     *
     * @mbg.generated
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_record.role_type
     *
     * @return the value of role_record.role_type
     *
     * @mbg.generated
     */
    public String getRoleType() {
        return roleType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_record.role_type
     *
     * @param roleType the value for role_record.role_type
     *
     * @mbg.generated
     */
    public void setRoleType(String roleType) {
        this.roleType = roleType == null ? null : roleType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_record.role_sum
     *
     * @return the value of role_record.role_sum
     *
     * @mbg.generated
     */
    public String getRoleSum() {
        return roleSum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_record.role_sum
     *
     * @param roleSum the value for role_record.role_sum
     *
     * @mbg.generated
     */
    public void setRoleSum(String roleSum) {
        this.roleSum = roleSum == null ? null : roleSum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_record.status
     *
     * @return the value of role_record.status
     *
     * @mbg.generated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_record.status
     *
     * @param status the value for role_record.status
     *
     * @mbg.generated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}