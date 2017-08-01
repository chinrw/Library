package com.worldpara.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BookInformation {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book_infofmation.book_id
     *
     * @mbg.generated
     */
    private Integer bookId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book_infofmation.title
     *
     * @mbg.generated
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book_infofmation.authors
     *
     * @mbg.generated
     */
    private String authors;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book_infofmation.publish
     *
     * @mbg.generated
     */
    private String publish;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book_infofmation.tags
     *
     * @mbg.generated
     */
    private String tags;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book_infofmation.pages
     *
     * @mbg.generated
     */
    private Integer pages;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book_infofmation.isbn
     *
     * @mbg.generated
     */
    private String isbn;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book_infofmation.version
     *
     * @mbg.generated
     */
    private String version;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book_infofmation.price
     *
     * @mbg.generated
     */
    private Double price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book_infofmation.content
     *
     * @mbg.generated
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book_infofmation.sum_authors
     *
     * @mbg.generated
     */
    private String sumAuthors;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book_infofmation.publish_date
     *
     * @mbg.generated
     */
    private Date publishDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book_infofmation.cover
     *
     * @mbg.generated
     */
    private String cover;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book_infofmation.CD
     *
     * @mbg.generated
     */
    private Byte cd;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book_infofmation.book_id
     *
     * @return the value of book_infofmation.book_id
     *
     * @mbg.generated
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column book_infofmation.book_id
     *
     * @param bookId the value for book_infofmation.book_id
     *
     * @mbg.generated
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book_infofmation.title
     *
     * @return the value of book_infofmation.title
     *
     * @mbg.generated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column book_infofmation.title
     *
     * @param title the value for book_infofmation.title
     *
     * @mbg.generated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book_infofmation.authors
     *
     * @return the value of book_infofmation.authors
     *
     * @mbg.generated
     */
    public String getAuthors() {
        return authors;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column book_infofmation.authors
     *
     * @param authors the value for book_infofmation.authors
     *
     * @mbg.generated
     */
    public void setAuthors(String authors) {
        this.authors = authors == null ? null : authors.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book_infofmation.publish
     *
     * @return the value of book_infofmation.publish
     *
     * @mbg.generated
     */
    public String getPublish() {
        return publish;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column book_infofmation.publish
     *
     * @param publish the value for book_infofmation.publish
     *
     * @mbg.generated
     */
    public void setPublish(String publish) {
        this.publish = publish == null ? null : publish.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book_infofmation.tags
     *
     * @return the value of book_infofmation.tags
     *
     * @mbg.generated
     */
    public String getTags() {
        return tags;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column book_infofmation.tags
     *
     * @param tags the value for book_infofmation.tags
     *
     * @mbg.generated
     */
    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book_infofmation.pages
     *
     * @return the value of book_infofmation.pages
     *
     * @mbg.generated
     */
    public Integer getPages() {
        return pages;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column book_infofmation.pages
     *
     * @param pages the value for book_infofmation.pages
     *
     * @mbg.generated
     */
    public void setPages(Integer pages) {
        this.pages = pages;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book_infofmation.isbn
     *
     * @return the value of book_infofmation.isbn
     *
     * @mbg.generated
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column book_infofmation.isbn
     *
     * @param isbn the value for book_infofmation.isbn
     *
     * @mbg.generated
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book_infofmation.version
     *
     * @return the value of book_infofmation.version
     *
     * @mbg.generated
     */
    public String getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column book_infofmation.version
     *
     * @param version the value for book_infofmation.version
     *
     * @mbg.generated
     */
    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book_infofmation.price
     *
     * @return the value of book_infofmation.price
     *
     * @mbg.generated
     */
    public Double getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column book_infofmation.price
     *
     * @param price the value for book_infofmation.price
     *
     * @mbg.generated
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book_infofmation.content
     *
     * @return the value of book_infofmation.content
     *
     * @mbg.generated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column book_infofmation.content
     *
     * @param content the value for book_infofmation.content
     *
     * @mbg.generated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book_infofmation.sum_authors
     *
     * @return the value of book_infofmation.sum_authors
     *
     * @mbg.generated
     */
    public String getSumAuthors() {
        return sumAuthors;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column book_infofmation.sum_authors
     *
     * @param sumAuthors the value for book_infofmation.sum_authors
     *
     * @mbg.generated
     */
    public void setSumAuthors(String sumAuthors) {
        this.sumAuthors = sumAuthors == null ? null : sumAuthors.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book_infofmation.publish_date
     *
     * @return the value of book_infofmation.publish_date
     *
     * @mbg.generated
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    public Date getPublishDate() {
        return publishDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column book_infofmation.publish_date
     *
     * @param publishDate the value for book_infofmation.publish_date
     *
     * @mbg.generated
     */
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book_infofmation.cover
     *
     * @return the value of book_infofmation.cover
     *
     * @mbg.generated
     */
    public String getCover() {
        return cover;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column book_infofmation.cover
     *
     * @param cover the value for book_infofmation.cover
     *
     * @mbg.generated
     */
    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book_infofmation.CD
     *
     * @return the value of book_infofmation.CD
     *
     * @mbg.generated
     */
    public Byte getCd() {
        return cd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column book_infofmation.CD
     *
     * @param cd the value for book_infofmation.CD
     *
     * @mbg.generated
     */
    public void setCd(Byte cd) {
        this.cd = cd;
    }
}