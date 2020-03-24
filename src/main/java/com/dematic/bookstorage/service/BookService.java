package com.dematic.bookstorage.service;

import com.dematic.bookstorage.entity.Book;

import java.util.List;

public interface BookService {

	List<Book> getBooks();

	void saveBook(Book book);

	Book getBookByBarcode(String barcode);
}
