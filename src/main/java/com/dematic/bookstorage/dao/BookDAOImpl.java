package com.dematic.bookstorage.dao;

import com.dematic.bookstorage.entity.AntiqueBook;
import com.dematic.bookstorage.entity.Book;
import com.dematic.bookstorage.entity.Journal;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDAOImpl implements BookDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Book> getBooks() {

        List<Book> result = new ArrayList<>();
	    Query query = (Query) entityManager.createNativeQuery("SELECT* FROM book");
        List<Object[]> rows = query.list();

        for (Object[] row : rows) {
            result.add(getBookInstance(row));
        }

	    return result;
	}

	@Override
	public void saveBook(Book book) {

	    String sqlQuery = "INSERT INTO book (name, author, barcode, quantity, price, year, scienceIndex) VALUES (?,?,?,?,?,?,?)";
		Query query = (Query) entityManager.createNativeQuery(sqlQuery);

		query.setParameter(1, book.getName());
        query.setParameter(2, book.getAuthor());
        query.setParameter(3, book.getBarcode());
        query.setParameter(4, book.getQuantity());
        query.setParameter(5, book.getPrice());
        query.setParameter(6, null);
        query.setParameter(7, null);

        if (book instanceof AntiqueBook) {
            query.setParameter(6, ((AntiqueBook) book).getYear());
        } else if (book instanceof Journal){
            query.setParameter(7, ((Journal) book).getScienceIndex());
        }

		query.executeUpdate();
	}

    @Override
	public Book getBookByBarcode(String barcode) {

        Query query = (Query) entityManager.createNativeQuery("SELECT * FROM book where barcode=?");
		query.setParameter(1, barcode);

        List<Object[]> books = query.getResultList();

		if (books.isEmpty()) {
			return null;
		}

		return getBookInstance(books.get(0));
	}

    @Override
    public int updateBook(Map<String, String> values, String barcode) {

	    String updateQuery = "update book set ";

	    int columnCount = values.size();

	    // constructing where condition with provided column names
	    for (Map.Entry<String, String> entry : values.entrySet()) {
            updateQuery += entry.getKey() + "='" + entry.getValue() + "'";

            if (columnCount != 1) {
                updateQuery += ",";
            }

            columnCount--;
        }

	    updateQuery += " where barcode = " + barcode;

        Query query = (Query) entityManager.createNativeQuery(updateQuery);
        return query.executeUpdate();
    }

    @Override
    public List<Map<String, Double>> getTotalPrices(List<String> barcodeList) {

        List<Map<String, Double>> result = new ArrayList<>();

        String sqlQuery = "SELECT* FROM book where barcode in (";
        int columnCount = barcodeList.size();

        // constructing where condition with provided column names
        for (String barcode : barcodeList) {
            sqlQuery += "'" + barcode + "'";

            if (columnCount != 1) {
                sqlQuery += ",";
            }

            columnCount--;
        }
        sqlQuery += ")";

        Query query = (Query) entityManager.createNativeQuery(sqlQuery);
        List<Object[]> rows = query.list();

        for (Object[] row : rows) {

            Map<String, Double> map = new HashMap<>();
            Book book = getBookInstance(row);

            map.put(book.getBarcode(), book.calculatePrice());
            result.add(map);
        }

	    return result;
    }

	private Book getBookInstance(Object[] fields) {

        Book book;

        String name = (String) fields[1];
        String author = (String) fields[2];
        String barcode = (String) fields[3];
        Integer quantity = Integer.valueOf(fields[4].toString());
        Double price = Double.valueOf(fields[5].toString());

        // we create certain type of book based on containing year or science index
        if (fields[6] != null) {
            book = new AntiqueBook();
            ((AntiqueBook) book).setYear(Integer.valueOf(fields[6].toString()));
        } else if (fields[7] != null) {
            book = new Journal();
            ((Journal) book).setScienceIndex(Integer.valueOf(fields[7].toString()));
        } else {
            book = new Book();
        }

        book.setName(name);
        book.setAuthor(author);
        book.setBarcode(barcode);
        book.setQuantity(quantity);
        book.setPrice(price);

        return book;
    }
}
