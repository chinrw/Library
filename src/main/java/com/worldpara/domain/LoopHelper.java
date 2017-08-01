package com.worldpara.domain;

/**
 * Created by chin39 on 2017/7/21.
 */
public class LoopHelper {
	private BorrowRecord borrowRecord;
	private BookInformation bookInformation;

	public LoopHelper(BorrowRecord borrowRecord, BookInformation bookInformation) {
		this.borrowRecord = borrowRecord;
		this.bookInformation = bookInformation;
	}

	public BorrowRecord getBorrowRecord() {
		return borrowRecord;
	}

	public void setBorrowRecord(BorrowRecord borrowRecord) {
		this.borrowRecord = borrowRecord;
	}

	public BookInformation getBookInformation() {
		return bookInformation;
	}

	public void setBookInformation(BookInformation bookInformation) {
		this.bookInformation = bookInformation;
	}
}

