package in.srnyapathi.kubebookstore.k8s.controller;

import in.srnyapathi.kubebookstore.k8s.model.Book;
import in.srnyapathi.kubebookstore.k8s.service.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/book/")
@RequiredArgsConstructor
public class BooksController {
    private final BooksService booksService;

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        var array = getModel(booksService.getAll());
        return ResponseEntity.ok(array);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") String id) {
        return ResponseEntity.ok(domainToModel(booksService.get(id)));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") String id) {
        booksService.delete(id);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        var createResponse = booksService.create(modelToDomain(book));
        return ResponseEntity.ok(domainToModel(createResponse));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> editBook(@PathVariable("id") String id, @RequestBody Book book) {
        var createResponse = booksService.edit(id, modelToDomain(book));
        return ResponseEntity.ok(domainToModel(createResponse));
    }

    private in.srnyapathi.kubebookstore.k8s.entity.Book modelToDomain(Book book) {
        return new in.srnyapathi.kubebookstore.k8s.entity.Book()
                .setBookName(book.getBookName())
                .setAuthor(book.getAuthor())
                .setIsbn(book.getIsbn())
                .setPrice(book.getPrice());
    }

    private List<Book> getModel(List<in.srnyapathi.kubebookstore.k8s.entity.Book> all) {
        return all.stream().map(bookEntity -> getBook(bookEntity)).collect(Collectors.toList());
    }

    private Book domainToModel(in.srnyapathi.kubebookstore.k8s.entity.Book all) {
        return getBook(all);
    }

    private Book getBook(in.srnyapathi.kubebookstore.k8s.entity.Book bookEntity) {
        return new Book().setBookName(bookEntity.getBookName())
                .setAuthor(bookEntity.getAuthor())
                .setPrice(bookEntity.getPrice())
                .setIsbn(bookEntity.getIsbn())
                .setId(bookEntity.getId());
    }
}
