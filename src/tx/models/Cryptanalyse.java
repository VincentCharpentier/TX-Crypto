/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tx.models;

import java.util.LinkedList;
import java.util.List;
import static tx.models.Constantes.ALPHABET_SIZE;
import static tx.models.Constantes.KEY_MAX_LENGTH;

/**
 *
 * @author vincetn
 */
public class Cryptanalyse {

    public static final double INDICE_FR = 0.0778;
    public static final double INDICE_EN = 0.0667;

    public static int findBetterInd() {

        return 0;
    }

    /**
     * pour auto
     * @param source
     * @return
     */
    public static int AutoFindKeyLength(String source){
        //List<List<HistoFreq>> all = new LinkedList<>();

        int best = -1;
        double best_value = Double.MAX_VALUE;
        for (int i = 1; i <= KEY_MAX_LENGTH; i++) {
            List<HistoFreq> list = makeAllHisto(source, i);
            //all.add(list);
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
        return guessKey(src, keyLength);
    }

    public static String guessKey(String src, int keyLength) {
        List<HistoFreq> histos = makeAllHisto(src, keyLength);
        final StringBuilder key = new StringBuilder();
        for (int i = 0; i < keyLength; i++) {
            key.append((char) ('A' + shiftNumber(histos.get(i))));
        }
        return key.toString();
    }

    public static String guessKey(List<HistoFreq> histos, int keyLength) {
        final StringBuilder key = new StringBuilder();
        for (int i = 0; i < keyLength; i++) {
            key.append((char) ('A' + shiftNumber(histos.get(i))));
        }
        return key.toString();
    }
}
