package domain.entity;

import java.util.Comparator;

/**
 * Kriteria pengurutan kegiatan. Setiap opsi membawa comparator-nya sendiri.
 */
public enum SortOption {
    /** Urutkan berdasarkan hari (Senin -> Minggu), lalu waktu. */
    DAY(Comparator.comparing(Activity::getDay).thenComparing(Activity::getTime)),

    /** Urutkan berdasarkan waktu paling awal, lalu hari. */
    TIME(Comparator.comparing(Activity::getTime).thenComparing(Activity::getDay)),

    /** Urutkan judul dari A ke Z (case-insensitive). */
    TITLE_ASC(Comparator.comparing(Activity::getTitle, String.CASE_INSENSITIVE_ORDER)),

    /** Urutkan judul dari Z ke A (case-insensitive). */
    TITLE_DESC(Comparator.comparing(Activity::getTitle, String.CASE_INSENSITIVE_ORDER).reversed());

    private final Comparator<Activity> comparator;

    SortOption(Comparator<Activity> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Activity> comparator() {
        return comparator;
    }
}
