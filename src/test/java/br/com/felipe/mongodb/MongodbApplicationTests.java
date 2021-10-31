package br.com.felipe.mongodb;

import br.com.felipe.mongodb.entity.Attachment;
import br.com.felipe.mongodb.entity.Author;
import br.com.felipe.mongodb.entity.Book;
import br.com.felipe.mongodb.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@SpringBootTest
class MongodbApplicationTests {

	@Autowired
	private BookRepository repository;

	@Test
	void contextLoads() {
	}

	@Test
	void test_save_book() throws IOException {

		var img = getAttachment();

		var book = new Book(null,"Clean Code", null, 50.99,
				new Author("Robert", "C. Martin"),
				new Attachment("book", "image/jpeg", img.length, img));

		Book bookOk = repository.save(book);

		Assertions.assertNotNull(bookOk);
	}

	@Test
	void test_save_list_book() throws IOException {

		var img = getAttachment();

		var book1 = new Book(null,"Clean Code", "A Handbook of Agile Software Craftsmanship", 50.99,
				new Author("Robert", "C. Martin"),
				new Attachment("book", "image/jpeg", img.length, img));

		var book2 = new Book(null,"Clean Architecture", "A Craftsman's Guide to Software Structure and Design", 55.99,
				new Author("Robert", "C. Martin"),
				new Attachment("book", "image/jpeg", img.length, img));

		var book3 = new Book(null,"The Clean Coder", "A Code of Conduct for Professional Programmers", 45.99,
				new Author("Robert", "C. Martin"),
				new Attachment("book", "image/jpeg", img.length, img));

		List<Book> books = repository.saveAll(List.of(book1, book2, book3));

		Assertions.assertNotNull(books);
	}

	private byte[] getAttachment() throws IOException {
		Path path = Paths.get("C:\\temp\\clean-code.jpg");
		return Files.readAllBytes(path);
	}

	@Test
	void test_find_all() {
		List<Book> books = repository.findAll();

		Assertions.assertNotNull(books);
		System.out.println(books);
	}

	@Test
	void test_find_by_title() throws IOException {
		Book book = repository.findFirstByTitle("Clean Code");

		Assertions.assertNotNull(book);

		Files.write(Path.of("C:\\temp\\clean-code-" + book.id().toString() + ".jpg"), book.attachment().image());
		System.out.println(book);
	}

	@Test
	void test_delete_by_id() {
		Book book = repository.findFirstByTitle("Clean Code");

		repository.deleteById(book.id().toString());

		Assertions.assertNotNull(book);
		System.out.println(book);
	}

	@Test
	void test_update_book() {
		var book = repository.findFirstByTitle("Clean Code");

		var newBook = new Book(book.id(),"Clean Code 2", null, 10.90,
				book.author(),
				book.attachment());

		var updated = repository.save(newBook);

		Assertions.assertNotNull(updated);
		System.out.println(updated);
	}

}
