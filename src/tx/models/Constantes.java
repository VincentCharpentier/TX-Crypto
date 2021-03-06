/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tx.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classes regroupant des constantes d'ordre général.
 * @author vincetn
 */
public final class Constantes {

    /**
     * Nombre de lettres dans l'alphabet. (26)
     */
    public static final Integer ALPHABET_SIZE = 26;


    public static final int KEY_MAX_LENGTH = 10;

    /**
     * Diagramme de frequence d'apparition des lettre en français
     */
    public static final List<Double> diagFR =
            new ArrayList<>(Arrays.asList(
                    7.636, 0.901, 3.260, 3.669,
                    14.715, 1.066, 0.866, 0.737,
                    7.529, 0.545, 0.049, 5.456,
                    2.968, 7.095, 5.378, 3.021,
                    1.362, 6.553, 7.948, 7.244,
                    6.311, 1.628, 0.114, 0.387,
                    0.308, 0.136));

    /**
     * Diagramme de frequence d'apparition des lettre en anglais.
     */
    public static final List<Double> diagEN =
            new ArrayList<>(Arrays.asList(
                    8.08, 7.38, 1.67, 7.47,
                    3.18, 1.91, 3.99, 0.09,
                    12.56, 6.42, 2.17, 6.59,
                    1.80, 9.15, 5.27, 2.79,
                    7.24, 1.00, 0.14, 1.89,
                    0.63, 0.21, 4.04, 1.65,
                    2.60, 0.07));

    /**
     * Constructeur privée pour empécher l'instanciation.
     */
    private Constantes() { }
}
