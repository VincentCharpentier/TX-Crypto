/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tx.model;

import java.util.LinkedList;
import java.util.List;
import static tx.model.Constantes.ALPHABET_SIZE;

/**
 *
 * @author vincetn
 */
public final class Vigenere {

    /**
     * Constructeur privé pour empecher toute instanciation.
     */
    private Vigenere() { }

    /**
     * Chiffrer un texte selon la méthode de Vigenere.
     * @param origin texte à chiffrer
     * @param key clé à utiliser
     * @return texte chiffré
     */
    public static String encode(final String origin, final String key) {
        final StringBuilder resultBuf = new StringBuilder();
        final LinkedList<Character> src = (LinkedList) preprocess(origin);

        final int mod = key.length();
        int index = -1;

        while (!src.isEmpty()) {
            index = (index + 1) % mod;
            final Character current = src.pop();
            // 1 - on retire la valeur 'A' a chaque caractere (source et cle)
            // 2 - On additione les caractère
            // 3 - on calcul le resultat modulo 26 (nb lettres dans l'alphabet)
            // 4 - On rajoute 'A' pour obtenir une valeur de caractère valide
            final int alphaIndex = (current.charValue() + key.charAt(index)
                    - (2 * 'A')) % ALPHABET_SIZE;
            resultBuf.append((char) (alphaIndex + 'A'));
        }
        return resultBuf.toString();
    }


    /**
     * Décodage d'une chaine donnée avec une clé donnée.
     * @param origin texte à décoder
     * @param key clé à utiliser
     * @return texte décodé
     */
    public static String decode(final String origin, final String key) {
        final StringBuilder resultBuf = new StringBuilder();
        final LinkedList<Character> src = (LinkedList) preprocess(origin);

        final int mod = key.length();
        int index = -1;

        while (!src.isEmpty()) {
            index = (index + 1) % mod;
            final Character current = src.pop();
            // 1 - on retire la valeur 'A' a chaque caractere (source et cle)
            // 2 - On soustrait la valeur du caratère clé
            // 3 - on calcul le resultat modulo 26 (nb lettres dans l'alphabet)
            // 4 - On rajoute 'A' pour obtenir une valeur de caractère valide
            int alphaIndex = (current.charValue() - key.charAt(index))
                    % ALPHABET_SIZE;
            if (alphaIndex < 0) {
                alphaIndex += ALPHABET_SIZE;
            }
            resultBuf.append((char) (alphaIndex + 'A'));
        }
        return resultBuf.toString();
    }

    /**
     * Pré traitement de la chaine de caractère donnée
     * pour le codage/décodage.
     * @param src String donnée en entrée
     * @return Liste des caractères de la chaine traitée.
     */
    private static List<Character> preprocess(final String src) {
        // traiter caractères speciaux
        final SimpleText txt = new SimpleText(src);
        return txt.getList();
    }

}
