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
    private static final int a_z = (int) 'z' - (int) 'a' + 1;
    private static final int A = (int) 'A';
    private static final int Z = (int) 'Z';
    private static final int A_Z = (int) 'Z' - (int) 'A' + 1;

    public ShiftCaesarAlgorithm(int key) {
        super(key);
    }

    @Override
    protected char[] encrypt(int length, int key) {
        final char[] result = new char[length];

        for (int i = 0; i < length; i++) {
            int letter = (int) this.buffer[i];
            if (isEnglishLowerLetter(letter)) {
                letter += key;

                if (letter > z) {
                    letter -= a_z;
                }
                if (letter < a) {
                    letter += a_z;
                }

                result[i] = (char) letter;
            }

            if (isEnglishUpperLetter(letter)) {
                letter += key;

                if (letter > Z) {
                    letter -= A_Z;
                }
                if (letter < A) {
                    letter += A_Z;
                }

                result[i] = (char) letter;
            }
        }

        return result;
    }

    /**
     * Проверка на принадлежность символа английскому алфавиту в нижнем
     * регистре.
     *
     * @param letter символ для проверки
     * @return true - если символ английский и в нижнем регистре, инача - false
     */
    private boolean isEnglishUpperLetter(int letter) {
        return letter >= a && letter <= z;
    }

    /**
     * Проверка на принадлежность символа английскому алфавиту в верхнем
     * регистре.
     *
     * @param letter символ для проверки
     * @return true - если символ английский и в верхнем регистре, инача - false
     */
    private boolean isEnglishLowerLetter(int letter) {
        return letter >= A && letter <= Z;
    }
}
