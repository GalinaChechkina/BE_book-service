package ait.cohort34.book.dao;

import ait.cohort34.book.model.Publisher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class PublisherReposititoryImpl implements PublisherRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public List<String> findByPublishersAuthor(String authorName) {
        List<String> publishers = em.createQuery("select b.publisher.publisherName from Book b JOIN b.authors a where a.name=:authorName", String.class)
                .setParameter("authorName",authorName)
                .getResultList();
        return publishers;
    }

    @Override
    public Stream<Publisher> findDistinctByBooksAuthorsName(String authorName) {
//не разобралась, как делать
        return Stream.of();
    }

    @Override
    public Optional<Publisher> findById(String publisher) {
        return Optional.ofNullable(em.find(Publisher.class, publisher));
    }

    @Override
    public Publisher save(Publisher publisher) {
        em.persist(publisher);
        return publisher;
    }
}
