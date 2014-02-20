/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tx;

import java.util.LinkedList;
import tx.Model.SimpleText;

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

    public static String encode(String origin, String key) {
        StringBuilder resultBuf = new StringBuilder();
        LinkedList<Character> src = preprocess(origin);

        // TODO
        int mod = key.length();
        int index = -1;

        while (!src.isEmpty()) {
            index = (index + 1) % mod;
            Character current = src.pop();
            // 1 - on retire la valeur 'A' a chaque caractere (source et cle)
            // 2 - On additione les caractère
            // 3 - on calcul le resultat modulo 26 (nb de lettres dans l'alphabet)
            // 4 - On rajoute 'A' pour obtenir une valeur de caractère valide
            int alphaIndex = (current.charValue()+key.charAt(index)-(2*'A'))%26;
            resultBuf.append((char)(alphaIndex+'A'));
        }
        return resultBuf.toString();
    }


    /**
     * Décodage d'une chaine donnée avec une clé donnée.
     * @param origin texte à décoder
     * @param key clé à utiliser
     * @return texte décodé
     */
    public static String decode(String origin, String key) {
        StringBuilder resultBuf = new StringBuilder();
        LinkedList<Character> src = preprocess(origin);

        // TODO
        int mod = key.length();
        int index = -1;

        while (!src.isEmpty()) {
            index = (index + 1) % mod;
            Character current = src.pop();
            // 1 - on retire la valeur 'A' a chaque caractere (source et cle)
            // 2 - On soustrait la valeur du caratère clé
            // 3 - on calcul le resultat modulo 26 (nb de lettres dans l'alphabet)
            // 4 - On rajoute 'A' pour obtenir une valeur de caractère valide
            int alphaIndex = (current.charValue()-key.charAt(index))%26;
            if (alphaIndex < 0) {
                alphaIndex += 26;
            }
            resultBuf.append((char)(alphaIndex+'A'));
        }
        return resultBuf.toString();
    }

    /**
     * Pré traitement de la chaine de caractère donnée pour le codage/décodage.
     * @param src String donnée en entrée
     * @return Liste des caractères de la chaine traitée.
     */
    public static LinkedList<Character> preprocess(String src) {
        // traiter caractères speciaux
        SimpleText txt = new SimpleText(src);
        return txt.getList();
    }

}
