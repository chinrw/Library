package com.worldpara.domain;

import java.util.Date;

public class CDInventory {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cd_inventory.cd_id
     *
     * @mbg.generated
     */
    private Integer cdId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cd_inventory.book_id
     *
     * @mbg.generated
     */
    private Integer bookId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cd_inventory.status
     *
     * @mbg.generated
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cd_inventory.in_date
     *
     * @mbg.generated
     */
    private Date inDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cd_inventory.location
     *
     * @mbg.generated
     */
    private String location;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cd_inventory.cd_id
     *
     * @return the value of cd_inventory.cd_id
     *
     * @mbg.generated
     */
    public Integer getCdId() {
        return cdId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cd_inventory.cd_id
     *
     * @param cdId the value for cd_inventory.cd_id
     *
     * @mbg.generated
     */
    public void setCdId(Integer cdId) {
        this.cdId = cdId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cd_inventory.book_id
     *
     * @return the value of cd_inventory.book_id
     *
     * @mbg.generated
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cd_inventory.book_id
     *
     * @param bookId the value for cd_inventory.book_id
     *
     * @mbg.generated
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cd_inventory.status
     *
     * @return the value of cd_inventory.status
     *
     * @mbg.generated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cd_inventory.status
     *
     * @param status the value for cd_inventory.status
     *
     * @mbg.generated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cd_inventory.in_date
     *
     * @return the value of cd_inventory.in_date
     *
     * @mbg.generated
     */
    public Date getInDate() {
        return inDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cd_inventory.in_date
     *
     * @param inDate the value for cd_inventory.in_date
     *
     * @mbg.generated
     */
    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cd_inventory.location
     *
     * @return the value of cd_inventory.location
     *
     * @mbg.generated
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cd_inventory.location
     *
     * @param location the value for cd_inventory.location
     *
     * @mbg.generated
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }
}