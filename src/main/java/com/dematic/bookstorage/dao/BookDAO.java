package com.dematic.bookstorage.dao;

import com.dematic.bookstorage.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookDAO {

	List getBooks();

	void saveBook(Book book);

	Book getBookByBarcode(String barcode);

	int updateBook(Map<String, String> values, String barcode);

    List<Map<String, Double>> getTotalPrices(List<String> barcodeList);
}
