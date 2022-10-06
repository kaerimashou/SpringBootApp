package org.azati.first_test_task.repository;

import org.azati.first_test_task.entity.Book;
import org.azati.first_test_task.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByBorrower(Person borrower);

    Optional<Book> findFirstByTitleAndAuthorAndBookYear(String title, String author, Integer bookYear);
}
