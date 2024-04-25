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
        List<Book> books = em.createQuery("select b from Book b join b.authors a where a.name=:authorName", Book.class)
                .setParameter("authorName", authorName)
                .getResultList();
        return books.stream();
    }

    @Override
    public Stream<Book> findByPublisherPublisherName(String name) {
        List<Book>books = em.createQuery("select b from Book b where b.publisher.publisherName=:name", Book.class)
                .setParameter("name",name)
                .getResultList();
        return books.stream();
    }

    @Override
    public void deleteByAuthorsName(String name) {
// не разобралась
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
        if(book!=null){
            em.remove(book);
        }
    }
}
