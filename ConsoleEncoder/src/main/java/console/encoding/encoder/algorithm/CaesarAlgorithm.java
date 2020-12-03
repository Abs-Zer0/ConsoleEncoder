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
        apply(in, out, this.key);
    }

    @Override
    public void decode(Reader in, Writer out) throws IOException {
        apply(in, out, -this.key);
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
     * Шифрует/расшифровывает часть данных, помещённых в buffer
     *
     * @param length число символов, которое необходимо зашифровать/расшифровать
     * в буфере
     * @param key ключ, по которому происходит шифрование/расшифровывание
     * @return зашифрованная/расшифрованная порция данных
     */
    protected abstract char[] encrypt(int length, int key);

    /**
     * Основной цикл по разбиению и обработке исходных данных.
     *
     * @param in входной поток данных
     * @param out выходной поток данных
     * @param mode команда управления
     * @throws IOException если есть ошибки хотя бы в одном из потоков
     */
    private void apply(Reader in, Writer out, int key) throws IOException {
        while (true) {
            clearBuffer();
            int length = in.read(this.buffer);

            if (length <= 0) {
                break;
            }

            out.write(encrypt(length, key));
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
