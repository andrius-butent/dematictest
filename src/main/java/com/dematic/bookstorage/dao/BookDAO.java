package com.dematic.bookstorage.dao;

import com.dematic.bookstorage.entity.Book;

import java.util.List;

public interface BookDAO {

	List<Book> getBooks();

	void saveBook(Book book);

	Book getBookByBarcode(String barcode);
}
