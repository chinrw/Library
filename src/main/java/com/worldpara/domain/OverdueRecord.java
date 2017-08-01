package com.worldpara.domain;

public class OverdueRecord {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column overdue_record.overdue_id
     *
     * @mbg.generated
     */
    private Integer overdueId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column overdue_record.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column overdue_record.borrow_id
     *
     * @mbg.generated
     */
    private Integer borrowId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column overdue_record.bookitem_id
     *
     * @mbg.generated
     */
    private Integer bookitemId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column overdue_record.pass_days
     *
     * @mbg.generated
     */
    private Integer passDays;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column overdue_record.status
     *
     * @mbg.generated
     */
    private String status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column overdue_record.overdue_id
     *
     * @return the value of overdue_record.overdue_id
     *
     * @mbg.generated
     */
    public Integer getOverdueId() {
        return overdueId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column overdue_record.overdue_id
     *
     * @param overdueId the value for overdue_record.overdue_id
     *
     * @mbg.generated
     */
    public void setOverdueId(Integer overdueId) {
        this.overdueId = overdueId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column overdue_record.user_id
     *
     * @return the value of overdue_record.user_id
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column overdue_record.user_id
     *
     * @param userId the value for overdue_record.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column overdue_record.borrow_id
     *
     * @return the value of overdue_record.borrow_id
     *
     * @mbg.generated
     */
    public Integer getBorrowId() {
        return borrowId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column overdue_record.borrow_id
     *
     * @param borrowId the value for overdue_record.borrow_id
     *
     * @mbg.generated
     */
    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column overdue_record.bookitem_id
     *
     * @return the value of overdue_record.bookitem_id
     *
     * @mbg.generated
     */
    public Integer getBookitemId() {
        return bookitemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column overdue_record.bookitem_id
     *
     * @param bookitemId the value for overdue_record.bookitem_id
     *
     * @mbg.generated
     */
    public void setBookitemId(Integer bookitemId) {
        this.bookitemId = bookitemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column overdue_record.pass_days
     *
     * @return the value of overdue_record.pass_days
     *
     * @mbg.generated
     */
    public Integer getPassDays() {
        return passDays;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column overdue_record.pass_days
     *
     * @param passDays the value for overdue_record.pass_days
     *
     * @mbg.generated
     */
    public void setPassDays(Integer passDays) {
        this.passDays = passDays;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column overdue_record.status
     *
     * @return the value of overdue_record.status
     *
     * @mbg.generated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column overdue_record.status
     *
     * @param status the value for overdue_record.status
     *
     * @mbg.generated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}