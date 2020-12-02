/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.encoding.encoder.algorithm;

/**
 *
 * @author Абс0лютный Н0ль
 */
public class ShiftCaesarAlgorithm extends CaesarAlgorithm {

    private static final int a = (int) 'a';
    private static final int z = (int) 'z';
    private static final int A = (int) 'A';
    private static final int Z = (int) 'Z';

    public ShiftCaesarAlgorithm(int key) {
        super(key);
    }

    @Override
    protected char[] encrypt(int length) {
        final char[] result = new char[length];

        for (int i = 0; i < length; i++) {
            final char letter = this.buffer[i];
            if (isEnglishLowerLetter(letter)) {
                int tmp = ((int) letter) + this.key;

                if (tmp > z) {
                    tmp = a + (tmp - z);
                }
                if (tmp < a) {
                    tmp = z - (a - tmp);
                }

                result[i] = (char) tmp;
            }

            if (isEnglishUpperLetter(letter)) {
                int tmp = ((int) letter) + this.key;

                if (tmp > Z) {
                    tmp = A + (tmp - Z);
                }
                if (tmp < A) {
                    tmp = Z - (A - tmp);
                }

                result[i] = (char) tmp;
            }
        }

        return result;
    }

    @Override
    protected char[] decipher(int length) {
        final int actualKey = -this.key;
        final char[] result = new char[length];

        for (int i = 0; i < length; i++) {
            final char letter = this.buffer[i];
            if (isEnglishLowerLetter(letter)) {
                int tmp = ((int) letter) + actualKey;

                if (tmp > z) {
                    tmp = a + (tmp - z);
                }
                if (tmp < a) {
                    tmp = z - (a - tmp);
                }

                result[i] = (char) tmp;
            }

            if (isEnglishUpperLetter(letter)) {
                int tmp = ((int) letter) + actualKey;

                if (tmp > Z) {
                    tmp = A + (tmp - Z);
                }
                if (tmp < A) {
                    tmp = Z - (A - tmp);
                }

                result[i] = (char) tmp;
            }
        }

        return result;
    }

    /**
     * Проверка на принадлежность символа английскому алфавиту в нижнем регистре.
     * @param letter символ для проверки
     * @return true - если символ английский и в нижнем регистре, инача - false
     */
    private boolean isEnglishUpperLetter(char letter) {
        return letter >= a && letter <= z;
    }

    /**
     * Проверка на принадлежность символа английскому алфавиту в верхнем регистре.
     * @param letter символ для проверки
     * @return true - если символ английский и в верхнем регистре, инача - false
     */
    private boolean isEnglishLowerLetter(char letter) {
        return letter >= A && letter <= Z;
    }
}
