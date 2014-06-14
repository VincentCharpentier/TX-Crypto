/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tx.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import static tx.models.Constantes.ALPHABET_SIZE;

/**
 *
 * @author vincetn
 */
public class HistoFreq {

    /**
     * Nombre d'apparition pour chaque caractères.
     * Ils sont ordonnés par ordre alphabétique (a:0 , b:1 ...)
     */
    private final transient List<Integer> nbAppar = new ArrayList<>();

    /**
     * Nombre de caractères comptabilisés.
     */
    private Integer count = 0;

    /**
     * permet de savoir si les fréquences et l'indice est à jour.
     */
    private Boolean upToDate = false;

    /**
     * Liste des fréquences par caractères.
     * (a:0 , b:1 ...)
     */
    private final List<Double> freq = new ArrayList<>();

    /**
     * indice de coincidence de l'histogramme
     */
    private Double indice;

    /**
     * Constructeur.
     */
    public HistoFreq() {
        // les listes doivent être initialisées
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            nbAppar.add(0);
            //freq.add((float) 0);
        }
    }

    /**
     * Comptabilise le caractère donnée dans l'histogramme.
     * @param car caractère à comptabiliser
     */
    public final void add(final Character car) {
        upToDate = false;
        // calcul de l'index
        final int index = indexOf(car);
        // on incrémente
        nbAppar.set(index, nbAppar.get(index) + 1);
        count++;
    }

    /**
     * obtenir la frequence d'un caractère.
     * @param car caractère dont on souhaite la fréquence.
     * @return fréquence du caractère donné.
     */
    public final Double getFreq(final Character car) {
        if (!upToDate) {
            maj();
        }
        return freq.get(indexOf(car));
    }

    /**
     * Connaitre l'indice de coïncidence des caractères
     * contenues dans l'histogramme.
     * @return indice de coincidence
     */
    public final Double getIndice() {
        if (!upToDate) {
            maj();
        }
        return indice;
    }

    /**
     * mettre à jour les frequences et l'indice de coincidence.
     */
    private void maj() {
        freq.clear();
        indice = 0.0;
        if (count != 0) {
            final Iterator<Integer> iter = nbAppar.iterator();
            while (iter.hasNext()) {
                final Double currentF = (double) iter.next() / (double) count;
                freq.add(currentF);
                indice += currentF * currentF;
            }
        } else {
            indice = 0.0;
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                freq.add(new Double(0.0));
            }
        }
        upToDate = true;
    }

    /**
     * return the index in List of the given character.
     * @param car character
     * @return index of c
     */
    private int indexOf(final Character car) {
        return car.charValue() - 'A';
    }

    /**
     *
     * @return
     */
    public Character getMostFreqChar() {
        if (!upToDate) {
            maj();
        }
        int index = freq.indexOf(Collections.max(freq));
        return Character.valueOf((char)('A' + index));
    }
}
