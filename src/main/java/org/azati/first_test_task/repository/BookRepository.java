package org.azati.first_test_task.repository;

import org.azati.first_test_task.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findFirstByTitleAndAuthorAndBookYear(String title, String author, Integer bookYear);
}
