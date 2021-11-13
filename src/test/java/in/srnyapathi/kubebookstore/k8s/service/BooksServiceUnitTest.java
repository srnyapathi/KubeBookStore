package in.srnyapathi.kubebookstore.k8s.service;

import in.srnyapathi.kubebookstore.k8s.entity.Book;
import in.srnyapathi.kubebookstore.k8s.repository.BooksRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class BooksServiceUnitTest {
    @Mock
    BooksRepository booksRepository;
    @InjectMocks
    BooksService booksService;

    @Test
    void getBooks() {
        Book book = new Book().setBookName("Let us C").setAuthor("Yashvanth karnetkar").setIsbn("dkljfskljslfsf").setId("987989797987");
        Mockito.when(booksRepository.findById("987989797987")).thenReturn(Optional.of(book));
        Book book1 = booksService.get("987989797987");
        assert book1.equals(book);
    }

    @Test
    void getBooksNotFound() {
        Mockito.when(booksRepository.findById("987989797987")).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class,()->{
            Book book1 = booksService.get("987989797987");
        });
        Assertions.assertEquals("Book Does not exist",exception.getMessage());
    }

    @Test
    void save() {
        Book book = new Book().setBookName("Let us C")
                              .setAuthor("Yashvanth karnetkar")
                              .setIsbn("dkljfskljslfsf").setId("987989797987");
        Mockito.when(booksRepository.save(book)).thenReturn(book);
        Book book1 = booksService.create(book);
        assert book1.equals(book);
    }

    @Test
    void delete() {
        Book book = new Book().setBookName("Let us C")
                .setAuthor("Yashvanth karnetkar")
                .setIsbn("dkljfskljslfsf").setId("987989797987");
        Mockito.doNothing().when(booksRepository).deleteById("987989797987");

    }



}
