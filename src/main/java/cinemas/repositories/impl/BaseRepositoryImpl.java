package cinemas.repositories.impl;

import cinemas.repositories.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepositoryImpl<T, ID> implements BaseRepository<T, ID> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected abstract Class<T> getPersistentClass();

    @Override
    @Transactional
    public <S extends T> S save(S entity) {
        if (entity == null) {
            throw new IllegalArgumentException("The given entity must not be null!");
        }
        return entityManager.merge(entity);
    }

    @Override
    @Transactional
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        if (entities == null) {
            throw new IllegalArgumentException("The given entities must not be null!");
        }
        List<S> result = new ArrayList<>();
        for (S entity : entities) {
            if (entity == null) {
                throw new IllegalArgumentException("The given entity must not be null!");
            }
            result.add(entityManager.merge(entity));
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> findById(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null!");
        }
        return Optional.ofNullable(entityManager.find(getPersistentClass(), id));
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<T> findAll() {
        String hql = "FROM " + getPersistentClass().getName();
        return entityManager.createQuery(hql, getPersistentClass()).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<T> findAllById(Iterable<ID> ids) {
        if (ids == null) {
            throw new IllegalArgumentException("The given ids must not be null!");
        }
        for (ID id : ids) {
            if (id == null) {
                throw new IllegalArgumentException("The given ids must not contain null values!");
            }
        }
        List<ID> idList = new ArrayList<>();
        ids.forEach(idList::add);
        String hql = "FROM " + getPersistentClass().getName() + " WHERE id IN :ids";
        return entityManager.createQuery(hql, getPersistentClass())
                .setParameter("ids", idList)
                .getResultList();
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null!");
        }
        T entity = entityManager.find(getPersistentClass(), id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    @Transactional
    public void delete(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("The given entity must not be null!");
        }
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Override
    @Transactional
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

        String hql = "DELETE FROM " + getPersistentClass().getName() + " WHERE id IN :ids";
        entityManager.createQuery(hql).setParameter("ids", idList).executeUpdate();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<? extends T> entities) {
        if (entities == null) {
            throw new IllegalArgumentException("The given entities must not be null!");
        }
        for (T entity : entities) {
            if (entity == null) {
                throw new IllegalArgumentException("The given entities must not contain null values!");
            }
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
        }
    }

    @Override
    @Transactional
    public void deleteAll() {
        String hql = "DELETE FROM " + getPersistentClass().getName();
        entityManager.createQuery(hql).executeUpdate();
    }
}
