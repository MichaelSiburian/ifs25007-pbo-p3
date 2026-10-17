package usecase;

import domain.entity.Activity;
import domain.entity.Day;
import domain.entity.SortOption;
import domain.exception.EntityNotFoundException;
import domain.exception.ValidationException;
import domain.repository.IActivityRepository;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Use case logika bisnis jadwal kegiatan.
 * Tidak melakukan I/O — hanya memproses data dan melempar checked exception domain.
 */
public class ActivityUseCase {
    private final IActivityRepository activityRepository;

    public ActivityUseCase(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public Activity addActivity(String title, String day, String time) throws ValidationException {
        String cleanTitle = validateTitle(title);
        Day parsedDay = parseDay(day);
        LocalTime parsedTime = parseTime(time);
        return activityRepository.save(cleanTitle, parsedDay, parsedTime);
    }

    /**
     * Mengubah judul/hari/waktu secara parsial.
     * Parameter {@code null} atau kosong berarti field tersebut tidak diubah.
     * Semua input divalidasi dahulu sehingga perubahan bersifat atomik.
     */
    public void updateActivity(int id, String title, String day, String time)
            throws EntityNotFoundException, ValidationException {
        Optional<Activity> found = activityRepository.findById(id);
        if (found.isEmpty()) {
            throw new EntityNotFoundException(id);
        }

        String newTitle = isBlank(title) ? null : validateTitle(title);
        Day newDay = isBlank(day) ? null : parseDay(day);
        LocalTime newTime = isBlank(time) ? null : parseTime(time);

        Activity activity = found.get();
        if (newTitle != null) {
            activity.changeTitle(newTitle);
        }
        if (newDay != null) {
            activity.changeDay(newDay);
        }
        if (newTime != null) {
            activity.changeTime(newTime);
        }
        activityRepository.update(activity);
    }

    public void removeActivity(int id) throws EntityNotFoundException {
        if (!activityRepository.deleteById(id)) {
            throw new EntityNotFoundException(id);
        }
    }

    /** Mencari kegiatan yang judulnya mengandung kata kunci (case-insensitive). */
    public List<Activity> searchActivities(String keyword) {
        String lower = keyword.toLowerCase();
        return activityRepository.findBy(a -> a.getTitle().toLowerCase().contains(lower));
    }

    public List<Activity> sortActivities(SortOption option) {
        return activityRepository.findAll().stream()
                .sorted(option.comparator())
                .toList();
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    private String validateTitle(String title) throws ValidationException {
        if (isBlank(title)) {
            throw new ValidationException("Judul kegiatan tidak boleh kosong");
        }
        return title.trim();
    }

    private Day parseDay(String day) throws ValidationException {
        return Day.fromLabel(day)
                .orElseThrow(() -> new ValidationException("Hari tidak valid"));
    }

    private LocalTime parseTime(String time) throws ValidationException {
        if (isBlank(time) || !time.trim().matches("([01]\\d|2[0-3]):[0-5]\\d")) {
            throw new ValidationException("Format waktu tidak valid (gunakan HH:mm)");
        }
        return LocalTime.parse(time.trim());
    }
}
