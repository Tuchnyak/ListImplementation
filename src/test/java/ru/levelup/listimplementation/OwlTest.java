package ru.levelup.listimplementation;

import org.junit.*;

//import static junit.framework.TestCase.*;
import java.util.Collection;
import java.util.LinkedList;

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

    @Test(expected = IndexOutOfBoundsException.class)
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

    @Test
    public void clear() {
        owl.add("owl 0");
        owl.add("owl 1");
        owl.add("owl 2");
        owl.add("owl 3");

        owl.clear();
        assertEquals(0, owl.size());
    }

    @Test
    public void indexOf() {
        owl.add("owl 0");
        owl.add("owl 1");
        owl.add("owl 2");
        owl.add("owl 3");

        assertEquals(0, owl.indexOf("owl 0"));
        assertEquals(2, owl.indexOf("owl 2"));
        assertEquals(3, owl.indexOf("owl 3"));
    }

    @Test
    public void lastIndexOf() {
        assertEquals("empty list", -1, owl.lastIndexOf("owl"));

        owl.add("owl 0");
        owl.add("owl");
        owl.add("owl 2");
        owl.add("owl 3");
        assertEquals("one owl in list", 1, owl.lastIndexOf("owl"));

        owl.add("owl");
        assertEquals("tow owls in list", 4, owl.lastIndexOf("owl"));
    }

    @Test
    public void contains() {
        assertFalse("empty list", owl.contains("owl"));

        owl.add("owl 0");
        owl.add("owl");
        owl.add("owl 2");
        owl.add("owl 3");
        assertTrue("search owl", owl.contains("owl"));
        assertFalse("serach big owl", owl.contains("owl 7"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void subList() {
        owl.subList(0, 1);

        owl.add("owl 0");
        owl.add("owl 1");
        owl.add("owl 2");
        owl.add("owl 3");
        owl.add("owl 4");
        assertEquals(5, owl.subList(0, 4).size());
    }

    @Test(expected = NullPointerException.class)
    public void containsAllNullPointerException() {
        Collection<String> list = new LinkedList<>();
        list = null;
        owl.containsAll(list);
    }

    @Test(expected = ClassCastException.class)
    public void containsAllClassCastException() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);

        owl.add("owl");
        owl.containsAll(list);
//        if(!"".getClass().equals(list.get(0).getClass())) throw new ClassCastException();
    }

    @Test
    public void containsAll() {
        LinkedList<String> list = new LinkedList<>();
        list.add("owl 1");
        list.add("owl 0");

        owl.add("owl 0");
        owl.add("owl 1");
        owl.add("owl 2");
        assertTrue(owl.containsAll(list));
    }

    @Test
    public void addAll() {
        LinkedList<String> listToAdd = new LinkedList<>();
        listToAdd.add("owl 3");
        listToAdd.add("owl 4");

        owl.add("owl 0");
        owl.add("owl 1");
        owl.add("owl 2");
        assertTrue(owl.addAll(listToAdd));

        LinkedList<String> secondListToAdd = new LinkedList<>();
        secondListToAdd.add("owl 5");
        secondListToAdd.add("owl 6");
        owl.addAll(secondListToAdd);
        assertEquals(7, owl.size());
    }

    @Test
    public void addAllByIndex() {
        LinkedList<String> listToAdd = new LinkedList<>();
        listToAdd.add("owl x1");
        listToAdd.add("owl x2");

        owl.add("owl 0");
        owl.add("owl 1");
        owl.add("owl 2");
        owl.addAll(2, listToAdd);

        assertEquals(5, owl.size());
        assertEquals("owl x1", owl.get(2));
        assertEquals("owl x2", owl.get(3));
        assertEquals("owl 2", owl.get(4));
    }

    @Test
    public void removeAll() {
        LinkedList<String> listToDel = new LinkedList<>();
        listToDel.add("owl 0");
        listToDel.add("owl 2");

        owl.add("owl 0");
        owl.add("owl 1");
        owl.add("owl 2");
        owl.removeAll(listToDel);

        assertEquals(1, owl.size());
        assertEquals("owl 1", owl.get(0));
    }

    @Test
    public void retainAll() {
        LinkedList<String> listToDel = new LinkedList<>();
        listToDel.add("owl 0");
        listToDel.add("owl 2");

        owl.add("owl 0");
        owl.add("owl 1");
        owl.add("owl 2");
        owl.retainAll(listToDel);

        assertEquals(2, owl.size());
        assertEquals("owl 0", owl.get(0));
    }

    @Test
    public void toArrayComplex() {
        owl.add("owl 0");
        owl.add("owl 1");
        owl.add("owl 2");

        String[] arrStr = new String[10];
        String[] secondArrStr = owl.toArray(arrStr);

        assertEquals("size",10, secondArrStr.length);
        assertEquals("check arr[i]","owl 0", secondArrStr[0]);
        assertEquals("check arr[i]","owl 1", secondArrStr[1]);
        assertEquals("check arr[i]","owl 2", secondArrStr[2]);
        assertEquals("check arr[i]",null, secondArrStr[8]);

        arrStr = new String[1];
        secondArrStr = owl.toArray(arrStr);

        assertEquals("size",3, secondArrStr.length);
        assertEquals("check arr[i]","owl 0", secondArrStr[0]);
        assertEquals("check arr[i]","owl 1", secondArrStr[1]);
        assertEquals("check arr[i]","owl 2", secondArrStr[2]);
        assertEquals(arrStr.getClass(), owl.toArray(arrStr).getClass());
    }
}
