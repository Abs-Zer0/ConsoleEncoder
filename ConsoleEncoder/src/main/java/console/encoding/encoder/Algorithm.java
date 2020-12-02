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
 * @author Абс0лютный Н0ль
 */
public interface Algorithm {

    /**
     * Берёт данные из in, шифрует и записывает в out.
     * @param in входной поток данных
     * @param out выходной поток данных
     * @throws IOException если есть ошибки хотя бы в одном из потоков
     */
    void encode(Reader in, Writer out) throws IOException;

    /**
     * Берёт данные из in, расшифровывает и записывает в out.
     * @param in входной поток данных
     * @param out выходной поток данных
     * @throws IOException если есть ошибки хотя бы в одном из потоков
     */
    void decode(Reader in, Writer out) throws IOException;
}
