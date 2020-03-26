package com.dematic.bookstorage.service;

import com.dematic.bookstorage.dao.BookDAO;
import com.dematic.bookstorage.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookDAO bookDAO;

	@Override
	@Transactional
	public List<Book> getBooks() {
		return bookDAO.getBooks();
	}

	@Override
	@Transactional
	public void saveBook(Book book) {
		bookDAO.saveBook(book);
	}

	@Override
	@Transactional
	public Book getBookByBarcode(String barcode) {
		return bookDAO.getBookByBarcode(barcode);
	}

    @Override
    @Transactional
    public List<Map<String, Double>> getTotalPrices(List<String> barcodeList) {
        return bookDAO.getTotalPrices(barcodeList);
    }

    @Override
    @Transactional
    public int updateBook(Map<String, String> values, String barcode) {
        return bookDAO.updateBook(values, barcode);
    }
}
