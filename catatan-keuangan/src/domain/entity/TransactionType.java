package domain.entity;

/**
 * Jenis transaksi keuangan.
 * Urutan deklarasi dipakai oleh {@link SortOption} (INCOME lebih dulu dari EXPENSE).
 */
public enum TransactionType {
    /** Uang masuk. */
    INCOME,
    /** Uang keluar. */
    EXPENSE
}
