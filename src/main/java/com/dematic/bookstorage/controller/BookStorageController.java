package com.dematic.bookstorage.controller;

import com.dematic.bookstorage.entity.Book;
import com.dematic.bookstorage.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookStorageController {

	@Autowired
	private BookService bookService;

	@GetMapping("/list")
	@ResponseBody
	public List<Book> getBookList() {
		return bookService.getBooks();
	}

	@PostMapping("/addBook")
	@ResponseBody
	public ResponseEntity addBook(@Valid @RequestBody Book book, BindingResult bindingResult) {

		// check if there are errors in JSON
		if (bindingResult.hasErrors()) {

			String errorMessage = "";
			for(ObjectError error : bindingResult.getAllErrors()) {
				errorMessage += error.getDefaultMessage() + "\n";
			}

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}

		bookService.saveBook(book);

		return ResponseEntity.status(HttpStatus.CREATED).body("Book is added successfully.");
	}

	@GetMapping("/getBook")
	@ResponseBody
	public ResponseEntity getBook(@RequestParam("barcode") String barcode) {

		Book book = bookService.getBookByBarcode(barcode);

		if (book == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no book with barcode: " + barcode);
		} else {
			return ResponseEntity.ok(book);
		}
	}

/*	@PostMapping("/addAntiqueBook")
	@ResponseBody
	public String addAntique(@RequestParam("year") int year,  @RequestBody Book antiqueBook) {

		antiqueBook.setYear(year);
		bookService.saveBook(antiqueBook);

		return "OK";
	}

	@PostMapping("/addJournal")
	@ResponseBody
	public String addJournal(@RequestParam("index") int index,  @RequestBody Book journal) {

		journal.setYear(index);
		bookService.saveBook(journal);

		return "OK";
	}*/
}
