package domain.exception;

/**
 * Exception yang dilempar saat terjadi kegagalan validasi aturan bisnis atau input domain.
 * Pesannya ditulis tanpa tanda seru karena presenter yang menyusun format akhirnya.
 */
public class ValidationException extends DomainException {
    public ValidationException(String message) {
        super(message);
    }
}
