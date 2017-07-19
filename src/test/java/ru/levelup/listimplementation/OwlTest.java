package ru.levelup.listimplementation;

import org.junit.*;

//import static junit.framework.TestCase.*;
import static org.junit.Assert.*;

/**
 * Created by George on 16.07.2017.
 */
public class OwlTest {

    private OneWayList<String> owl;

    @Before
    public void setup() {
        owl = new OneWayList<String>();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void add() {
        boolean result = owl.add("owl 0");

        assertTrue(result);
        assertEquals(1, owl.size());
        assertFalse(owl.isEmpty());
    }

    @Test
    public void remove() {
        owl.add("owl 0");
        owl.add("owl 1");
        owl.add("owl 2");
        owl.add("owl 3");
        owl.add("owl 4");
        owl.add("owl 5");
        owl.add("owl 6");

        owl.remove(5);
        assertEquals("owl 6", owl.get(5));
    }

    @Test
    public void removeByValue() {
        owl.add("owl 0");
        owl.add("owl 1");
        owl.add("owl 2");
        owl.add("owl 3");

        assertTrue(owl.remove("owl 1"));
        assertEquals("owl 2", owl.get(1));
        assertFalse(owl.remove(""));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void get() {
        owl.add("owl 0");
        owl.add("owl 1");
        owl.add("owl 2");
        owl.add("owl 3");
        owl.add("owl 4");
        owl.add("owl 5");
        owl.add("owl 6");

        assertEquals("owl 0", owl.get(-3));     //разделять тесты на разные условия
        assertEquals("owl 3", owl.get(3));
        assertEquals("owl 6", owl.get(6));
    }

    @Test
    public void size() {
        assertEquals(0, owl.size());
        owl.add("OWL");
        assertEquals(1, owl.size());
        owl.remove(0);
        assertEquals(0, owl.size());
    }

    @Test
    public void isEmpty() {
        assertTrue(owl.isEmpty());
        assertEquals(true, owl.isEmpty());
        owl.add("OWL");
        assertEquals(false, owl.isEmpty());
    }

}
