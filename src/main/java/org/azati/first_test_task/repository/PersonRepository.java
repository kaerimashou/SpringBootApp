package org.azati.first_test_task.repository;

import org.azati.first_test_task.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findFirstByEmail(String email);

    Optional<Person> findFirstByUserName(String userName);
}
