package com.dematic.bookstorage.dao;

import com.dematic.bookstorage.entity.Book;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Book> getBooks() {

		Session session = entityManager.unwrap(Session.class);
		Query<Book> query = session.createQuery("from Book order by author", Book.class);

		return query.getResultList();
	}

	@Override
	public void saveBook(Book book) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(book);
	}

	@Override
	public Book getBookByBarcode(String barcode) {
		Session session = entityManager.unwrap(Session.class);

		Query<Book> query = session.createQuery("from Book where barcode=:barcode", Book.class);
		query.setParameter("barcode", barcode);

		List<Book> books = query.getResultList();

		if (books.isEmpty()) {
			return null;
		}

		return books.get(0);
	}
}
