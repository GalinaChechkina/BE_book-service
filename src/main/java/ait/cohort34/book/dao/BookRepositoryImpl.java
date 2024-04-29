package ait.cohort34.book.dao;

import ait.cohort34.book.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Stream<Book> findByAuthorsName(String authorName) {
        return em.createQuery("select b from Book b join b.authors a where a.name=?1", Book.class)
                .setParameter(1, authorName)//в запросе м. использовать номер параметра (1)
                .getResultStream();
    }

    @Override
    public Stream<Book> findByPublisherPublisherName(String name) {
        return em.createQuery("select b from Book b join b.publisher p where p.publisherName=:name", Book.class)
                .setParameter("name",name)//а м. использовать название параметра (name)
                .getResultStream();
    }

    @Override
    public void deleteByAuthorsName(String name) {
        em.createQuery("delete from Book b where :name member of b.authors")
                 .setParameter("name", name)
                 .executeUpdate();
    }

    @Override
    public boolean existsById(String isbn) {
        return em.find(Book.class, isbn) != null;
    }

    @Override
    public Book save(Book book) {
        em.persist(book);
        return book;
    }

    @Override
    public Optional<Book> findById(String isbn) {
        Book book = em.find(Book.class,isbn);
        return Optional.ofNullable(book);
    }

    @Override
    public void deleteById(String isbn) {
        Book book = em.find(Book.class,isbn);
            em.remove(book);//не надо проверять- обработаем ошибку в сервисе
    }
}
