package cinemas.repositories.impl;

import cinemas.dtos.Pageable;
import cinemas.enums.MovieStatus;
import cinemas.models.Movie;
import cinemas.repositories.MoviesRepository;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("moviesRepository")
public class MoviesRepositoryImpl extends BaseRepositoryImpl<Movie, Integer> implements MoviesRepository {
    public MoviesRepositoryImpl() {
        super(Movie.class);
    }
    @Override
    public List<Movie> getMoviesByStatus(MovieStatus status) {
        String hql = "FROM Movie m WHERE m.status = :status";
        TypedQuery<Movie> query = entityManager.createQuery(hql, Movie.class);
        query.setParameter("status", status);
        return query.getResultList();
    }

    public List<Movie> getMoviesByTitleAndStatus(String title, Pageable pageable, MovieStatus status) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> criteriaQuery = criteriaBuilder.createQuery(Movie.class);
        Root<Movie> root = criteriaQuery.from(Movie.class);
        // Create predicates for the LIKE operation on title_vn and title_en
        Predicate titleVnPredicate = criteriaBuilder.like(root.get("titleVn"), "%" + title + "%");
        Predicate titleEnPredicate = criteriaBuilder.like(root.get("titleEn"), "%" + title + "%");
        // Combine predicates with OR
        Predicate titlePredicate = criteriaBuilder.or(titleVnPredicate, titleEnPredicate);
        // Create a list to hold all predicates
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(titlePredicate);
        // Add the status predicate if it's not null
        if (status != null) {
            Predicate statusPredicate = criteriaBuilder.equal(root.get("status"), status);
            predicates.add(statusPredicate);
        }
        // Combine all predicates with AND
        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        // Apply the predicate to the query
        criteriaQuery.where(finalPredicate);
        // Create the query
        TypedQuery<Movie> query = entityManager.createQuery(criteriaQuery);
        // Set pagination
        query.setFirstResult((int) pageable.getOffset()); // Convert to int if necessary
        query.setMaxResults(pageable.getSize()); // Use pageable.getPageSize() for max results
        // Execute the query and get the results
        return query.getResultList();
    }

    @Override
    public Integer countMoviesByTitleAndStatus(String keyword, MovieStatus status) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Movie> root = criteriaQuery.from(Movie.class);
        criteriaQuery.select(criteriaBuilder.count(root));
        Predicate titleVnPredicate = criteriaBuilder.like(root.get("titleVn"), "%" + keyword + "%");
        Predicate titleEnPredicate = criteriaBuilder.like(root.get("titleEn"), "%" + keyword + "%");
        // Combine predicates with OR
        Predicate titlePredicate = criteriaBuilder.or(titleVnPredicate, titleEnPredicate);
        // Create a list to hold all predicates
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(titlePredicate);
        // Add the status predicate if it's not null
        if (status != null) {
            Predicate statusPredicate = criteriaBuilder.equal(root.get("status"), status);
            predicates.add(statusPredicate);
        }
        // Combine all predicates with AND
        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        // Apply the predicate to the query
        criteriaQuery.where(finalPredicate);
        // Execute the query and get the result
        Long count = entityManager.createQuery(criteriaQuery).getSingleResult();
        return count.intValue(); // Return as Integer
    }
}
