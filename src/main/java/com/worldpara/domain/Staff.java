package com.worldpara.domain;

public class Staff {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column staff.staff_id
     *
     * @mbg.generated
     */
    private Integer staffId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column staff.staff_id_card
     *
     * @mbg.generated
     */
    private Integer staffIdCard;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column staff.staff_id
     *
     * @return the value of staff.staff_id
     *
     * @mbg.generated
     */
    public Integer getStaffId() {
        return staffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column staff.staff_id
     *
     * @param staffId the value for staff.staff_id
     *
     * @mbg.generated
     */
    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column staff.staff_id_card
     *
     * @return the value of staff.staff_id_card
     *
     * @mbg.generated
     */
    public Integer getStaffIdCard() {
        return staffIdCard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column staff.staff_id_card
     *
     * @param staffIdCard the value for staff.staff_id_card
     *
     * @mbg.generated
     */
    public void setStaffIdCard(Integer staffIdCard) {
        this.staffIdCard = staffIdCard;
    }
}