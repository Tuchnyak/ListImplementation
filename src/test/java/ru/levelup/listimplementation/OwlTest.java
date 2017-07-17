package ru.levelup.listimplementation;

import org.junit.*;

import static junit.framework.TestCase.*;

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

        owl.remove(3);
        assertEquals("owl 4", owl.get(3));
        owl.remove("owl 0");
        assertEquals("owl 1", owl.get(0));
        owl.remove("owl 6");
        assertEquals("owl 5", owl.get(3));

    }

    @Test
    public void get() {
        owl.add("owl 0");
        owl.add("owl 1");
        owl.add("owl 2");
        owl.add("owl 3");
        owl.add("owl 4");
        owl.add("owl 5");
        owl.add("owl 6");

        assertEquals("owl 0", owl.get(0));
        assertEquals("owl 3", owl.get(3));
        assertEquals("owl 6", owl.get(6));
    }

    @Test
    public void size() {
        owl.add("OWL");
        assertEquals(1, owl.size());
        owl.remove(0);
        assertEquals(0, owl.size());
    }

    @Test
    public void isEmpty() {
        assertEquals(true, owl.isEmpty());
        owl.add("OWL");
        assertEquals(false, owl.isEmpty());
    }

}
