package cinemas.repositories.impl;

import cinemas.repositories.CrudRepository;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Repository
public abstract class CrudRepositoryImpl<T, ID> implements CrudRepository<T, ID> {

    protected abstract Class<T> getPersistentClass();
    private static final Logger logger = Logger.getLogger(String.valueOf(CrudRepositoryImpl.class));

    @Autowired
    private SessionFactory sessionFactory;



    @Override
    public <S extends T> S save(S entity) {
        if (entity == null) {
            throw new IllegalArgumentException("The given entity must not be null!");
        }

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (OptimisticLockException e) {
            if (transaction != null) transaction.rollback();
            throw new OptimisticLockingFailureException("Optimistic locking failed", e);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        if (entities == null) {
            throw new IllegalArgumentException("The given entities must not be null!");
        }

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            for (S entity : entities) {
                if (entity == null) {
                    throw new IllegalArgumentException("The given entity must not be null!");
                }
                session.merge(entity);
            }
            transaction.commit();
        } catch (OptimisticLockException e) {
            if (transaction != null) transaction.rollback();
            throw new OptimisticLockingFailureException("Optimistic locking failed", e);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return entities;
    }

    @Override
    public Optional<T> findById(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null!");
        }

        Session session = sessionFactory.openSession();
        T entity = null;
        try {
            entity = session.get(getPersistentClass(), id);
        } finally {
            session.close();
        }

        return Optional.ofNullable(entity);
    }

    @Override
    public boolean existsById(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null!");
        }

        Session session = null;
        boolean exists = false;

        try {
            session = sessionFactory.openSession();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<T> root = criteriaQuery.from(getPersistentClass());

            criteriaQuery.select(criteriaBuilder.count(root))
                    .where(criteriaBuilder.equal(root.get("id"), id));

            Long count = session.createQuery(criteriaQuery).getSingleResult();
            exists = (count > 0);
        } catch (Exception e) {
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return exists;
    }

    @Override
    public Iterable<T> findAll() {
        Session session = null;
        List<T> entities = null;

        try {
            session = sessionFactory.openSession();
            String hql = "FROM " + getPersistentClass().getName();
            entities = session.createQuery(hql, getPersistentClass()).getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return entities;
    }

    @Override
    public Iterable<T> findAllById(Iterable<ID> ids) {
        if (ids == null) {
            throw new IllegalArgumentException("The given ids must not be null!");
        }

        for (ID id : ids) {
            if (id == null) {
                throw new IllegalArgumentException("The given ids must not contain null values!");
            }
        }

        Session session = null;
        List<T> entities = null;

        try {
            session = sessionFactory.openSession();

            List<ID> idList = new ArrayList<>();
            ids.forEach(idList::add);

            String hql = "FROM " + getPersistentClass().getName() + " WHERE id IN :ids";
            entities = session.createQuery(hql, getPersistentClass())
                    .setParameterList("ids", idList)
                    .getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return entities;
    }

    @Override
    public long count() {
        Session session = null;
        Long count = 0L;

        try {
            session = sessionFactory.openSession();

            String hql = "SELECT COUNT(*) FROM " + getPersistentClass().getName();
            count = (Long) session.createQuery(hql).getSingleResult();
        } catch (Exception e) {
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return count;
    }

    @Override
    public void deleteById(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null!");
        }

        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<T> criteriaDelete = criteriaBuilder.createCriteriaDelete(getPersistentClass());
            Root<T> root = criteriaDelete.from(getPersistentClass());

            criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));

            int result = session.createQuery(criteriaDelete).executeUpdate();

            transaction.commit();


            if (result == 0) {
                logger.info("No entity was deleted !!");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("The given entity must not be null!");
        }

        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            if (session.contains(entity)) {
                session.delete(entity);
            } else {
                throw new OptimisticLockingFailureException("The entity does not exist in the persistence context.");
            }

            transaction.commit();
        } catch (OptimisticLockingFailureException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends ID> ids) {
        if (ids == null) {
            throw new IllegalArgumentException("The given ids must not be null!");
        }

        List<ID> idList = new ArrayList<>();
        for (ID id : ids) {
            if (id == null) {
                throw new IllegalArgumentException("The given ids must not contain null values!");
            }
            idList.add(id);
        }

        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<T> criteriaDelete = criteriaBuilder.createCriteriaDelete(getPersistentClass());
            Root<T> root = criteriaDelete.from(getPersistentClass());

            criteriaDelete.where(root.get("id").in(idList));

            int result = session.createQuery(criteriaDelete).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        if (entities == null) {
            throw new IllegalArgumentException("The given entities must not be null!");
        }

        List<T> entityList = new ArrayList<>();
        for (T entity : entities) {
            if (entity == null) {
                throw new IllegalArgumentException("The given entities must not contain null values!");
            }
            entityList.add(entity);
        }

        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            for (T entity : entityList) {
                session.delete(entity);
            }

            transaction.commit();
        } catch (OptimisticLockingFailureException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    @Override
    public void deleteAll() {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            String hql = "DELETE FROM " + getPersistentClass().getName();
            int result = session.createQuery(hql).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


}
