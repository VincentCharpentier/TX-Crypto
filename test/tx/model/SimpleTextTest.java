/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tx.model;

import java.util.Iterator;
import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Classe de test de SimpleText.
 * @author vincetn
 */
public class SimpleTextTest {

    /**
     * Texte servant de source, à reformater.
     */
    private static final String SOURCE = "abcdé fghî$j32ABC";
    /**
     * Texte à obtenir.
     */
    private static final String EXPECTED = "ABCDEFGHIJABC";


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
     * Test of getList method, of class SimpleText.
     */
    @Test
    public final void testGetList() {
        System.out.println("TEST : getList");

        final LinkedList<Character> expResult = new LinkedList<Character>();
        final int length = EXPECTED.length();
        for (int i = 0; i < length; i++) {
            expResult.add(EXPECTED.charAt(i));
        }

        final SimpleText instance = new SimpleText(SOURCE);
        final LinkedList<Character> result = instance.getList();

        if (result.size() == length) {
            final Iterator<Character> itR = result.iterator();
            final Iterator<Character> itE = expResult.iterator();
            while (itR.hasNext()) {
                if (itE.next() != itR.next()) {
                    fail("FAIL (1)");
                }
            }
        } else {
            fail("FAIL (2)");
        }
    }

    /**
     * Test of getText method, of class SimpleText.
     */
    @Test
    public void testGetText() {
        System.out.println("TEST : getText");

        final SimpleText instance = new SimpleText(SOURCE);
        final String result = instance.getText();
        assertEquals("Result different from expected.", EXPECTED, result);
    }

}
