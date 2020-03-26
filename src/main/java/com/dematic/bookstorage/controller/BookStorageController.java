package com.dematic.bookstorage.controller;

import com.dematic.bookstorage.entity.AntiqueBook;
import com.dematic.bookstorage.entity.Book;
import com.dematic.bookstorage.entity.Journal;
import com.dematic.bookstorage.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class BookStorageController {

	@Autowired
	private BookService bookService;

	private List<String> columns = Arrays.asList("name", "author", "barcode", "quantity", "price", "year", "scienceIndex");
    private static final String BARCODE = "barcode";

	@GetMapping("/list")
	@ResponseBody
	public List<Book> getBookList() {
		return bookService.getBooks();
	}

	@PostMapping("/addBook")
	@ResponseBody
	public ResponseEntity addBook(@Valid @RequestBody Book book, BindingResult bindingResult) {

	    return validateAndAddBook(book, bindingResult);
	}

	@PostMapping("/addAntiqueBook")
	@ResponseBody
	public ResponseEntity addAntique(@Valid @RequestBody AntiqueBook antiqueBook, BindingResult bindingResult) {

        return validateAndAddBook(antiqueBook, bindingResult);
	}

	@PostMapping("/addJournal")
	@ResponseBody
	public ResponseEntity addJournal(@Valid @RequestBody Journal journal, BindingResult bindingResult) {

        return validateAndAddBook(journal, bindingResult);
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

    @PutMapping("/updateBook")
    @ResponseBody
    public ResponseEntity updateBook(@RequestBody Map<String, String> values, @RequestParam("barcode") String barcode) {

       boolean isJSONValid = columns.containsAll(values.keySet());

        if (isJSONValid && values.size() > 0) {
            int result = bookService.updateBook(values, barcode);

            if (result > 0) {
                return ResponseEntity.ok("The book was updated.");
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("JSON or barcode is invalid !!!");
    }

    @GetMapping("/getTotalPrice")
    @ResponseBody
    public ResponseEntity getTotalPrice(@RequestBody List<Map<String, String>> values) {

	    // we collect barcode from JSON list
        List<String> barcodeList = values.stream().filter(entry -> entry.keySet().contains(BARCODE)).map(entry -> entry.get(BARCODE))
                .collect(Collectors.toList());

        if (barcodeList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty barcode list !!!");
        }

        return ResponseEntity.ok().body(bookService.getTotalPrices(barcodeList));
    }

	private ResponseEntity validateAndAddBook(Book book, BindingResult bindingResult) {

	    // check if there are errors in JSON
        if (bindingResult.hasErrors()) {

            // collecting all errors
            String errorMessage = "";
            for(ObjectError error : bindingResult.getAllErrors()) {
                errorMessage += error.getDefaultMessage() + "\n";
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        bookService.saveBook(book);

        return ResponseEntity.status(HttpStatus.CREATED).body("Book is added successfully.");
    }
}
