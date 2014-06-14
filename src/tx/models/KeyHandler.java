/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tx.models;


/**
 *
 * @author vincetn
 */
public class KeyHandler {

    private KeyHandler() {}


    /**
     * Formatter une chaine de caractère.
     * remplacement des caractères spéciaux en leur équivalents [A-Z]
     * quand ils existent (exemple : é => E).
     * Passage des lettre en majuscules.
     * Suppression des autres caractères.
     * @param src chaine à transformer
     * @return chaine transformée
     */
    public static String filterText(String src) {
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
}
