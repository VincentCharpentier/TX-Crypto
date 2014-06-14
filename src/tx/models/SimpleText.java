/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tx.models;

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

    private LinkedList<Character> list = new LinkedList<>();

    /**
     * Contructeur.
     * @param textArg le texte qui sera contenu et traité
     */
    public SimpleText(final String textArg) {
        text = KeyHandler.filterText(textArg);
        list = stringToList(text);
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
