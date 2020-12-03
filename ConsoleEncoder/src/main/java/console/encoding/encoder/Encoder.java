/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.encoding.encoder;

import console.encoding.encoder.algorithm.ShiftCaesarAlgorithm;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;

/**
 *
 * @author Абс0лютный Н0ль
 */
public class Encoder implements Closeable {

    private Reader in = new StringReader("");
    private Writer out = new OutputStreamWriter(System.out);
    private Algorithm alg = new ShiftCaesarAlgorithm(0);

    /**
     * Назначение источника данных из строки txt.
     *
     * @param txt строка для шифрования/расшифровывания
     * @return Encoder с изменённым источником данных
     * @throws NullPointerException если в качестве аргумента передаётся null
     */
    public Encoder setInputData(String txt) throws NullPointerException {
        if (txt == null) {
            throw new NullPointerException("txt cannot be null");
        }

        this.in = new StringReader(txt);

        return this;
    }

    /**
     * Назначение источника данных из файла.
     *
     * @param inFile файл, содержащий текст для шифрования/расшифровывания
     * @return Encoder с изменённым источником данных
     * @throws NullPointerException если в качестве аргумента передаётся null
     * @throws FileNotFoundException если файл, описанный в inFile, не
     * существует
     */
    public Encoder setInputData(File inFile) throws NullPointerException, FileNotFoundException {
        if (inFile == null) {
            throw new NullPointerException("txtFile cannot be null");
        }

        this.in = new InputStreamReader(new FileInputStream(inFile));

        return this;
    }

    /**
     * Назначение выхода данных в поток.
     *
     * @param outStream поток, в который будут записываться
     * зашифрованные/расштфрованные данные
     * @return Encoder с изменённым выходом данных
     * @throws NullPointerException если в качестве аргумента передаётся null
     */
    public Encoder setOutputData(OutputStream outStream) throws NullPointerException {
        if (outStream == null) {
            throw new NullPointerException("outStream cannot be null");
        }

        this.out = new OutputStreamWriter(outStream);

        return this;
    }

    /**
     * Назначение выхода данных в файл.
     *
     * @param outFile файл, в который будут записываться
     * зашифрованные/расштфрованные данные
     * @return Encoder с изменённым выходом данных
     * @throws NullPointerException если в качестве аргумента передаётся null
     * @throws IOException если есть ошибки в описании файла
     */
    public Encoder setOutputData(File outFile) throws NullPointerException, IOException {
        if (outFile == null) {
            throw new NullPointerException("outFile cannot be null");
        }

        outFile.createNewFile();
        this.out = new OutputStreamWriter(new FileOutputStream(outFile));

        return this;
    }

    /**
     * Назначение алгоритма, по которому будут шифроваться/расшифровываться
     * данные.
     *
     * @param alg алгоритм шифрования
     * @return Encoder с изменённым алгоритмом шифрования
     * @throws NullPointerException если в качестве аргумента передаётся null
     */
    public Encoder setAlgorithm(Algorithm alg) throws NullPointerException {
        if (alg == null) {
            throw new NullPointerException("alg cannot be null");
        }

        this.alg = alg;

        return this;
    }

    /**
     * Возвращение алгоритма шифрования для изменения его параметров
     *
     * @return алгоритм шифрования
     */
    public Algorithm getAlgorithm() {
        return this.alg;
    }

    /**
     * Установка стандартных настроек: источник данных = "", выход данных =
     * System.out, алгоритм шифрования = shift.
     *
     * @return стандартный Encoder
     */
    public Encoder setDefaultComponents() {
        this.in = new StringReader("");
        this.out = new OutputStreamWriter(System.out);
        this.alg = new ShiftCaesarAlgorithm(0);

        return this;
    }

    /**
     * Функция шифрования данных.
     *
     * @return поток, в котором уже записаны зашифрованные данные
     * @throws IOException если есть ошибки хотя бы в одном из потоков
     */
    public Writer encode() throws IOException {
        this.alg.encode(in, out);

        return out;
    }

    /**
     * Функция расшифровывания данных.
     *
     * @return поток, в котором уже записаны расшифрованные данные
     * @throws IOException елси есть ошибки хотя бы в одном из потоков
     */
    public Writer decode() throws IOException {
        this.alg.decode(in, out);

        return out;
    }

    @Override
    public void close() throws IOException {
        this.in.close();
    }
}
