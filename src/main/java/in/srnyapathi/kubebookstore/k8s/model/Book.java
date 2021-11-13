package in.srnyapathi.kubebookstore.k8s.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Book {
    private String id;
    private String bookName;
    private String author;
    private String price;
    private String isbn;
}
