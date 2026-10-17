package framework.util;

import java.util.Scanner;

/**
 * Utility untuk membaca input string dari keyboard.
 * Berada di layer framework karena bergantung pada {@link System#in}.
 */
public class InputUtil {
    /** Scanner bersama untuk seluruh aplikasi (satu instance cukup). */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Menampilkan prompt dan membaca satu baris input dari user.
     * Jika input sudah habis (mis. file test case selesai dibaca), mengembalikan "x"
     * agar aplikasi keluar dengan rapi alih-alih melempar exception.
     *
     * @param info label yang ditampilkan sebelum input
     * @return teks yang diketik user
     */
    public static String input(String info) {
        System.out.print(info + " : ");
        if (!scanner.hasNextLine()) {
            return "x";
        }
        return scanner.nextLine();
    }
}
