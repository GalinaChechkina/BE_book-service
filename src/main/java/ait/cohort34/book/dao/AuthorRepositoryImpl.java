package ait.cohort34.book.dao;

import ait.cohort34.book.model.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Optional<Author> findById(String name) {
        return Optional.ofNullable(em.find(Author.class, name));
    }

    @Override
    public Author save(Author author) {
        em.persist(author);
        return author;
    }

    @Override
    public void deleteById(String authorName) {
        Author author = em.find(Author.class, authorName);
        if (author!=null){
            em.remove(author);
        }
    }
}
