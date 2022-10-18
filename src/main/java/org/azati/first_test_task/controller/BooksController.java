package org.azati.first_test_task.controller;

import org.azati.first_test_task.entity.Book;
import org.azati.first_test_task.entity.Person;
import org.azati.first_test_task.service.BookService;
import org.azati.first_test_task.service.PersonService;
import org.azati.first_test_task.validator.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value={"/home/books", "/admin/books"})
public class BooksController {
    private final BookService bookService;
    private final PersonService personService;
    private final BookValidator bookValidator;

    @Autowired
    public BooksController(BookService bookService, PersonService personService, BookValidator bookValidator) {
        this.bookService = bookService;
        this.personService = personService;
        this.bookValidator = bookValidator;
    }

    @GetMapping
    public String index(Model model) {
        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        return "books/bookList";
    }

    @GetMapping("/{id}")
    public String bookInfo(@PathVariable String id, Model model) {
        long bookId = Long.parseLong(id);
        Book book = bookService.getById(bookId);
        model.addAttribute("book", book);
        if (book.getBorrower() == null) {
            List<Person> personList = personService.getAll();
            model.addAttribute("people", personList);
            model.addAttribute("newBorrower", new Person());
        } else {
            model.addAttribute("borrower", book.getBorrower());
        }
        return "books/bookInfo";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable String id, Model model) {
        long bookId = Long.parseLong(id);
        Book book = bookService.getById(bookId);
        model.addAttribute("book", book);
        model.addAttribute("title", "Edit of " + book.getTitle());
        return "books/bookEdit";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("title", "New book");
        return "books/bookEdit";
    }

    @PostMapping
    public String updateBookList(@Valid @ModelAttribute("book") Book book,
                                 BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/bookEdit";
        }
        bookService.insert(book);
        if (book.getId() != null) {
            return "redirect:/books/" + book.getId();
        }
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable String id) {
        long bookId = Long.parseLong(id);
        bookService.delete(bookId);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assignBook(@PathVariable String id,
                             @ModelAttribute("newBorrower") Person person) {
        long bookId = Long.parseLong(id);
        long borrowerId = person.getId();
        if (borrowerId != 0) {
            Book book = bookService.getById(bookId);
            Person borrower = personService.getById(borrowerId);
            book.setBorrower(borrower);
            bookService.insert(book);
        }
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/free")
    public String freeBook(@PathVariable String id) {
        Long bookId = Long.parseLong(id);
        Book book = bookService.getById(bookId);
        book.setBorrower(null);
        bookService.insert(book);
        return "redirect:/books/{id}";
    }
}
