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
public class UnicodeCaesarAlgorithm extends CaesarAlgorithm{

    public UnicodeCaesarAlgorithm(int key) {
        super(key);
    }

    @Override
    protected char[] encrypt(int length) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected char[] decipher(int length) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
