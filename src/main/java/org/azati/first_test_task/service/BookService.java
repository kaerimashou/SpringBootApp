package org.azati.first_test_task.service;

import org.azati.first_test_task.entity.Book;
import org.azati.first_test_task.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void insert(Book book) {
        bookRepository.saveAndFlush(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public Optional<Book> findCommon(Book book) {
        return bookRepository.findFirstByTitleAndAuthorAndBookYear(
          book.getTitle(),
          book.getAuthor(),
          book.getBookYear()
        );
    }
}
