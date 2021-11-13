package in.srnyapathi.kubebookstore.k8s.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
@Accessors(chain = true)
public class Book {
    @Id
    private String id;
    private String bookName;
    private String author;
    private String price;
    private String isbn;
}
