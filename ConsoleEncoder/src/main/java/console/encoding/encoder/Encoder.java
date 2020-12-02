/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.encoding.encoder;

import console.encoding.encoder.algorithm.ShiftCaesarAlgorithm;
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
public class Encoder {

    private Reader in = new StringReader("");
    private Writer out = new OutputStreamWriter(System.out);
    private Algorithm alg = new ShiftCaesarAlgorithm(0);

    public Encoder setInputData(String txt) throws NullPointerException {
        if (txt == null) {
            throw new NullPointerException("txt cannot be null");
        }

        this.in = new StringReader(txt);

        return this;
    }

    public Encoder setInputData(File inFile) throws NullPointerException, FileNotFoundException {
        if (inFile == null) {
            throw new NullPointerException("txtFile cannot be null");
        }

        this.in = new InputStreamReader(new FileInputStream(inFile));

        return this;
    }

    public Encoder setOutputData(OutputStream outStream) throws NullPointerException {
        if (outStream == null) {
            throw new NullPointerException("outStream cannot be null");
        }

        this.out = new OutputStreamWriter(outStream);

        return this;
    }

    public Encoder setOutputData(File outFile) throws NullPointerException, FileNotFoundException, IOException {
        if (outFile == null) {
            throw new NullPointerException("outFile cannot be null");
        }

        outFile.createNewFile();
        this.out = new OutputStreamWriter(new FileOutputStream(outFile));

        return this;
    }

    public Encoder setAlgorithm(Algorithm alg) throws NullPointerException {
        if (alg == null) {
            throw new NullPointerException("alg cannot be null");
        }

        this.alg = alg;

        return this;
    }

    public Writer encode() throws IOException {
        this.alg.encode(in, out);

        return out;
    }

    public Writer decode() throws IOException {
        this.alg.decode(in, out);

        return out;
    }
}
