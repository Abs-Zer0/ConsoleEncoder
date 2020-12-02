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
public class UnicodeCaesarAlgorithm extends CaesarAlgorithm {

    public UnicodeCaesarAlgorithm(int key) {
        super(key);
    }

    @Override
    protected char[] encrypt(int length, int key) {
        final char[] result = new char[length];

        for (int i = 0; i < length; i++) {
            int letter = ((int) this.buffer[i]) + key;

            if (letter < 0) {
                letter += (int) Character.MAX_VALUE + 1;
            }
            if (letter > Character.MAX_VALUE) {
                letter -= (int) Character.MAX_VALUE + 1;
            }

            result[i] = (char) letter;
        }

        return result;
    }

}
