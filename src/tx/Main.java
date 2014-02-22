/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tx;

import static tx.model.Vigenere.decode;
import static tx.model.Vigenere.encode;

/**
 *
 * @author vincetn
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String test = "abcdé fghî$j";
        //String src = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String cle = "ZACLDE";
        String code = encode(test, cle);
        String deco = decode(code, cle);
        System.out.println("src  : '" + test + "'");
        System.out.println("codé : '" + code + "'");
        System.out.println("deco : '" + deco + "'");
    }


}
