/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tx.Model;

import java.util.LinkedList;

/**
 *
 * @author vincetn
 */
public class SimpleText {

    /**
     * Texte en String.
     */
    private String text = "";

    private LinkedList<Character> list = new LinkedList<Character>();

    /**
     * Contructeur.
     * @param textArg le texte qui sera contenu et traité
     */
    public SimpleText(final String textArg) {
        text = format(textArg);
        list = stringToList(text);
    }

    /**
     * Formatter une chaine de caractère.
     * remplacement des caractères spéciaux en leur équivalents [A-Z]
     * quand ils existent (exemple : é => E).
     * Passage des lettre en majuscules.
     * Suppression des autres caractères.
     * @param src chaine à transformer
     * @return chaine transformée
     */
    private String format(String src) {
        // il reste des caractères spéciaux mais bon, les principaux y sont.
        src = src.replaceAll("[ÈËÊÉëéèê]", "E");
        src = src.replaceAll("[âäàåáÄÅ]", "A");
        src = src.replaceAll("[Çç]", "C");
        src = src.replaceAll("[Üûùú]", "U");
        src = src.replaceAll("[ïîìí]", "I");
        src = src.replaceAll("[Ññ]", "N");
        src = src.replaceAll("[Öóôöò]", "O");
        src = src.replaceAll("[ÿ]", "Y");
        src = src.replaceAll("[ü]", "U");

        // mise en majuscules
        src = src.toUpperCase();
        // retrait de tout ce qui n'est pas une lettre
        src = src.replaceAll("[^ABCDEFGHIJKLMNOPQRSTUVWXYZ]", "");
        return src;
    }

    /**
     * Créer une liste de caractères à partir d'une String.
     * @param src String d'origine
     * @return Liste des caractères de la chaine
     */
    private LinkedList<Character> stringToList(String src) {
        final LinkedList<Character> result = new LinkedList<>();
        int i = src.length() - 1;
        while (i >= 0) {
            result.push(src.charAt(i));
            i--;
        }
        return result;
    }

    public LinkedList<Character> getList() {
        return list;
    }

    public String getText() {
        return text;
    }
}
