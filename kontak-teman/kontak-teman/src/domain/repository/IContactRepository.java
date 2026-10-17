package domain.repository;

import domain.entity.Contact;

/**
 * Port spesifik Contact yang memperluas generic {@link IRepository}.
 * Menambahkan method pembuatan Contact baru dengan penomoran ID otomatis.
 */
public interface IContactRepository extends IRepository<Contact, Integer> {
    /**
     * Menyimpan kontak baru.
     *
     * @param name  nama kontak
     * @param phone nomor telepon kontak
     * @param email email kontak
     * @return kontak yang tersimpan (lengkap dengan ID)
     */
    Contact save(String name, String phone, String email);
}
