package domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Kontrak Generic Repository Port untuk operasi CRUD dan pencarian.
 *
 * @param <T>  tipe entitas domain
 * @param <ID> tipe data identifier entitas
 */
public interface IRepository<T, ID> {
    List<T> findAll();

    Optional<T> findById(ID id);

    T save(T entity);

    boolean deleteById(ID id);

    void update(T entity);

    List<T> findBy(Predicate<T> predicate);
}
