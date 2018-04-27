package bookstoreApp.service.sale;

import bookstoreApp.dto.SaleBookDto;
import bookstoreApp.entity.Author;
import bookstoreApp.entity.Book;
import bookstoreApp.repository.author.AuthorRepository;
import bookstoreApp.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public SaleServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository= authorRepository;
    }


    public float sellBook(SaleBookDto saleBookDto) throws LimittedStockException {
        Book book = bookRepository.findById(saleBookDto.bookId).orElse(null);
        if (book.getQuantity()<saleBookDto.saleQuantity){
            throw(new LimittedStockException("Limitted stock on book with ISBN =" + book.getIsbn()+"\n"
                    + "in stock = "+ book.getQuantity()+"\n"
                    + "required ="+saleBookDto.saleQuantity+"\n"
            ));
        }else{
            Author author = book.getAuthor();
            author.removeBook(book);
            book.setQuantity(book.getQuantity() - saleBookDto.saleQuantity);
            author.addBook(book);
            authorRepository.save(author);
            return (book.getPrice()*saleBookDto.saleQuantity);
        }
    }
}
