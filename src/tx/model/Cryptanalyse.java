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
public class Cryptanalyse {

    private static int MAX_HISTO = 10;
    private static double INDICE_FR = 0.0778;

    public static int findBetterInd() {

        return 0;
    }

    /**
     * pour auto
     * @param source
     * @return
     */
    public static int AutoFindKeyLength(String source){
        List<List<HistoFreq>> all = new LinkedList<>();

        int best = -1;
        double best_value = Double.MAX_VALUE;
        for (int i = 1; i <= MAX_HISTO; i++) {
            List<HistoFreq> list = makeAllHisto(source, i);
            all.add(list);
            double value = Math.abs(indiceMoyen(list) - INDICE_FR);
            if (value < best_value) {
                best = i;
                best_value = value;
            }
        }
        return best;
    }

    /**
     * pour un nb de sous-alphabet donné, retourne la liste des histofreq.
     * @param src
     * @param nbAlpha
     * @return
     */
    public static List<HistoFreq> makeAllHisto(String src, int nbAlpha) {
        List<HistoFreq> result = new LinkedList<HistoFreq>();

        for (int i = 0; i < nbAlpha; i++) {
            result.add(new HistoFreq());
        }

        int length = src.length();
        for (int i = 0; i < length; i++) {
            result.get(i % nbAlpha).add(src.charAt(i));
        }

        return result;
    }

    public static double indiceMoyen(List<HistoFreq> list) {
        double result = 0;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            result += list.get(i).getIndice();
        }
        return result / size;
    }


    /**
     *
     * @param histo
     * @return
     */
    public static int shiftNumber(HistoFreq histo) {
        Character mostFreq = histo.getMostFreqChar();

        int shift = mostFreq.charValue() - 'E';

        if (shift < 0) {
            shift += ALPHABET_SIZE;
        }

        return shift;
    }

    public static String guessKey(String src) {
        int keyLength = AutoFindKeyLength(src);
        List<HistoFreq> histos = makeAllHisto(src, keyLength);
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < keyLength; i++) {
            key.append((char)('A' + shiftNumber(histos.get(i))));
        }

        return key.toString();
    }

}
