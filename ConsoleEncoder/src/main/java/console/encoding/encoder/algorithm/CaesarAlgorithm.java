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
 * @author ���0������ �0��
 */
public abstract class CaesarAlgorithm implements Algorithm {

    /**
     * ���� ����������.
     */
    protected int key = 0;
    /**
     * ����� ��� �������� ������ ������, ����� ����� �������� ������������
     * ����������� ������.
     */
    protected final char[] buffer = new char[1024];

    /**
     * ������ ��������� ��������� ����� ������.
     *
     * @param key ����, ��� ������ �������� ����� ������������� ����������
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
     * ��������� ����� ��� ����������.
     *
     * @param key
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * �������/�������������� ����� ������, ���������� � buffer
     *
     * @param length ����� ��������, ������� ���������� �����������/������������
     * � ������
     * @param key ����, �� �������� ���������� ����������/���������������
     * @return �������������/�������������� ������ ������
     */
    protected abstract char[] encrypt(int length, int key);

    /**
     * �������� ���� �� ��������� � ��������� �������� ������.
     *
     * @param in ������� ����� ������
     * @param out �������� ����� ������
     * @param mode ������� ����������
     * @throws IOException ���� ���� ������ ���� �� � ����� �� �������
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
     * ����� �������� ������ �� ���������� ������ ������.
     */
    private void clearBuffer() {
        for (int i = 0; i < this.buffer.length; i++) {
            this.buffer[i] = 0;
        }
    }
}
