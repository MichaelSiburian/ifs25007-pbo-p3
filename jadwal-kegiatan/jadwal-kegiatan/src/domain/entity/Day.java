package domain.entity;

import java.util.Optional;

/**
 * Hari dalam seminggu (urutan enum = urutan pengurutan Senin..Minggu).
 */
public enum Day {
    SENIN("Senin"),
    SELASA("Selasa"),
    RABU("Rabu"),
    KAMIS("Kamis"),
    JUMAT("Jumat"),
    SABTU("Sabtu"),
    MINGGU("Minggu");

    private final String label;

    Day(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    /** Mencari hari berdasarkan nama (case-insensitive). */
    public static Optional<Day> fromLabel(String text) {
        if (text == null) {
            return Optional.empty();
        }
        String clean = text.trim();
        for (Day day : values()) {
            if (day.label.equalsIgnoreCase(clean)) {
                return Optional.of(day);
            }
        }
        return Optional.empty();
    }
}
