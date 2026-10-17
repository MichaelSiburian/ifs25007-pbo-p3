package domain.repository;

import domain.entity.Activity;
import domain.entity.Day;
import java.time.LocalTime;

/**
 * Port spesifik Activity yang memperluas generic {@link IRepository}.
 * Repository bertanggung jawab membuat ID unik saat menyimpan.
 */
public interface IActivityRepository extends IRepository<Activity, Integer> {
    Activity save(String title, Day day, LocalTime time);
}
