/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tx.model;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Classe de test de HistoFreq.
 * @author vincetn
 */
public class HistoFreqTest {

    public HistoFreqTest() {
    }

    /**
     * Executé avant la série de tests.
     */
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     * Exectué à la fin de la série de tests.
     */
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Executé avant chaque test.
     */
    @Before
    public void setUp() {
    }

    /**
     * Executé après chaque test.
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class HistoFreq.
     */
    @Test
    public final void testAdd() {
        System.out.println("add");
        final Character car = 'A';
        final HistoFreq instance = new HistoFreq();
        assertEquals("Frequence should be 0",
                instance.getFreq(car), new Double(0));
        instance.add(car);
        assertEquals("Frequence should be 1",
                instance.getFreq(car), new Double(1));
        instance.add('B');
        assertEquals("Frequence should be 0.5",
                instance.getFreq(car), new Double(0.5));
    }

    /**
     * Test of getFreq method, of class HistoFreq.
     */
    @Test
    public final void testGetFreq() {
        // useless : le test de add test également getFreq
        assertEquals("Always true", 0, 0);
    }

    /**
     * Test of getIndice method, of class HistoFreq.
     */
    @Test
    public void testGetIndice() {
        System.out.println("getIndice");
        HistoFreq instance = new HistoFreq();
        Double expResult = 0.0;

        assertEquals("Indice should be 0",
                expResult, instance.getIndice());

        instance.add('A');
        expResult = 1.0;
        assertEquals("Indice should be 1",
                expResult, instance.getIndice());

        instance.add('B');
        expResult = 0.5;
        assertEquals("Indice should be 0.5",
                expResult, instance.getIndice());

        instance.add('C');
        expResult = (1.0/3)*(1.0/3)*3;
        assertEquals("Indice should be 0.333333...",
                expResult, instance.getIndice());
    }

}
