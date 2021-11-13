package in.srnyapathi.kubebookstore.k8s.service;

import in.srnyapathi.kubebookstore.k8s.entity.Book;
import in.srnyapathi.kubebookstore.k8s.repository.BooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BooksService {

    private final BooksRepository booksRepository;

    public List<Book> getAll() {
        return booksRepository.findAll();
    }

    public Book create(Book book) {
        return booksRepository.save(book);
    }

    public Book get(String id) {
        return booksRepository.findById(id).orElseThrow(() -> new RuntimeException("Book Does not exist"));
    }

    public Book edit(String id, Book modelToDomain) {
        var bookOpt = booksRepository.findById(id).orElseThrow(() -> new RuntimeException("Book does not exist"));
        modelToDomain.setId(bookOpt.getId());
        return booksRepository.save(modelToDomain);

    }

    public void delete(String id) {
        booksRepository.deleteById(id);
    }
}
