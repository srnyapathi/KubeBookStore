package in.srnyapathi.kubebookstore.k8s.repository;

import in.srnyapathi.kubebookstore.k8s.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends MongoRepository<Book,String> {
}
