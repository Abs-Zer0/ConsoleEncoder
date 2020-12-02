/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.encoding.encoder;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 *
 * @author ���0������ �0��
 */
public interface Algorithm {

    /**
     * ���� ������ �� in, ������� � ���������� � out.
     * @param in ������� ����� ������
     * @param out �������� ����� ������
     * @throws IOException ���� ���� ������ ���� �� � ����� �� �������
     */
    void encode(Reader in, Writer out) throws IOException;

    /**
     * ���� ������ �� in, �������������� � ���������� � out.
     * @param in ������� ����� ������
     * @param out �������� ����� ������
     * @throws IOException ���� ���� ������ ���� �� � ����� �� �������
     */
    void decode(Reader in, Writer out) throws IOException;
}
