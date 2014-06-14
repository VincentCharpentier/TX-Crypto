/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tx.model;

import tx.models.Vigenere;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author vincetn
 */
public class VigenereTest {

    /**
     * Alphabet en clair.
     */
    private static final String CLAIR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Resultat attendu pour l'alphabet encodé avec une clé 'Z'.
     */
    private static final String CHIFFRE_Z = "ZABCDEFGHIJKLMNOPQRSTUVWXY";

    /**
     * Resultat attendu pour l'alphabet encodé avec une clé 'ZA'.
     */
    private static final String CHIFFRE_AZ = "AACCEEGGIIKKMMOOQQSSUUWWYY";

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
     * Test of encode method, of class Vigenere.
     */
    @Test
    public final void testEncode() {
        System.out.println("encode");
        String key = "A";
        String result = Vigenere.encode(CLAIR, key);
        assertEquals("Should be the same as clear text (A-KEY)", CLAIR, result);

        key = "Z";
        result = Vigenere.encode(CLAIR, key);
        assertEquals("Should be the same as z-encoded text (Z-KEY)",
                CHIFFRE_Z, result);

        key = "AZ";
        result = Vigenere.encode(CLAIR, key);
        assertEquals("Should be the same as az-encoded text (AZ-KEY)",
                CHIFFRE_AZ, result);
    }

    /**
     * Test of decode method, of class Vigenere.
     */
    @Test
    public final void testDecode() {
        System.out.println("decode");

        String key = "A";
        String result = Vigenere.decode(CLAIR, key);
        assertEquals("Should be the same as encoded (A-KEY)", CLAIR, result);

        key = "Z";
        result = Vigenere.decode(CHIFFRE_Z, key);
        assertEquals("Error, should be the alphabet (Z-KEY)", CLAIR, result);

        key = "AZ";
        result = Vigenere.decode(CHIFFRE_AZ, key);
        assertEquals("Error, should be the alphabet (AZ-KEY)", CLAIR, result);
    }

}
