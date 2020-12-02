/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.encoding.encoder.algorithm;

import console.encoding.encoder.Algorithm;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.function.Function;

/**
 *
 * @author Абс0лютный Н0ль
 */
public abstract class CaesarAlgorithm implements Algorithm {

    /**
     * Ключ шифрования.
     */
    protected int key = 0;
    /**
     * Буфер для хранения порции данных, нужен чтобы избежать переполнения
     * оперативной памяти.
     */
    protected final char[] buffer = new char[1024];

    /**
     * Создаёт экземпляр алгоритма шифра Цезаря.
     *
     * @param key ключ, при помощи которого будет производиться шифрование
     */
    public CaesarAlgorithm(int key) {
        this.key = key;
    }

    @Override
    public void encode(Reader in, Writer out) throws IOException {
        apply(in, out, this::encrypt);
    }

    @Override
    public void decode(Reader in, Writer out) throws IOException {
        apply(in, out, this::decipher);
    }

    /**
     * Изменение ключа для шифрования.
     *
     * @param key
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * Шифрует часть данных, помещённых в data.
     *
     * @param length число символов, которое необходимо зашифровать в буфере
     * @return зашифрованная порция исходных данных
     */
    protected abstract char[] encrypt(int length);

    /**
     * Расшифровывает часть данных, помещённых в data.
     *
     * @param length число символов, которое необходимо расшифровать в буфере
     * @return расшифрованная порция исходных данных
     */
    protected abstract char[] decipher(int length);

    /**
     * Основной цикл по разбиению и обработке исходных данных.
     *
     * @param in входной поток данных
     * @param out выходной поток данных
     * @param mode команда управления
     * @throws IOException если есть ошибки хотя бы в одном из потоков
     */
    private void apply(Reader in, Writer out, Function<Integer, char[]> mode) throws IOException {
        while (true) {
            clearBuffer();
            int length = in.read(this.buffer);

            if (length == 0) {
                break;
            }

            out.write(mode.apply(length));
            out.flush();
        }
    }

    /**
     * Метод очищения буфера от предыдущей порции данных.
     */
    private void clearBuffer() {
        for (int i = 0; i < this.buffer.length; i++) {
            this.buffer[i] = 0;
        }
    }
}
