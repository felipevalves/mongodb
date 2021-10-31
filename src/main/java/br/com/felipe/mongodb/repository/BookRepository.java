package br.com.felipe.mongodb.repository;

import br.com.felipe.mongodb.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

    Book findFirstByTitle(String title);

}
