package ru.levelup.listimplementation;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by George on 15.07.2017.
 */
public class OneWayList<T> implements List<T> {

    //Fileds*************************************
    private int size = 0;
    private Element<T> first;
    private Element<T> last;

    //Methods************************************
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] toArray() {

        Object[] arr = new Object[size];
        Element<T> element = first;

        for (int i = 0; i < size; i++) {
            arr[i] = element;
            element = element.getNext();
        }

        return arr;
    }

    public boolean add(T t) {

        Element<T> element = new Element<T>(t);
        if (size == 0) {
            first = element;
            last = element;
            first.setNext(last);
            size++;
        } else {
            last.setNext(element);
            last = element;
            size++;
        }

        return true;
    }

    public void add(int index, T t) {

        if (index <= size) {
            Element<T> elementToAdd = new Element<T>(t);
            Element<T> currElement = first;
            int currIndex = 0;

            while (currIndex < index - 1) {
                currElement = currElement.getNext();
                currIndex++;
            }

            elementToAdd.setNext(currElement.getNext());
            currElement.setNext(elementToAdd);

            if (index == size) last = elementToAdd;

            size++;

        } else throw new IndexOutOfBoundsException();

    }

    public T get(int index) {

        Element<T> element = first;

        for (int i = 0; i < index; i++) {
            element = element.getNext();
        }

        return element.getValue();
    }

    public T set(int index, T t) {

        if (index > size - 1) throw new IndexOutOfBoundsException();

        Element<T> el = first;

        for (int i = 0; i < index; i++) {
            el = el.getNext();
        }

        el.setValue(t);

        return null;
    }

    //remove method Изменение!!!!
    public T remove(int index) {
        if (index > size - 1) throw new IndexOutOfBoundsException();

        Element<T> element = first;

        if (index == 0) {
            first = element.getNext();
            size--;
            return null;
        }

        if (index == size - 1) {
            for (int i = 0; i < index - 1; i++) {
                element = element.getNext();
            }
            last = element;
            last.setNext(null);

            size--;
            //
            return null;
        }

        Element<T> prevEl = null;
        for (int i = 0; i < index; i++) {
            if (i == index - 1) {
                prevEl = element;
            }
            element = element.getNext();
        }
        prevEl.setNext(element.getNext());

        size--;
        return null;
    }

    public boolean remove(Object o) {

        if (first.getValue().getClass() != o.getClass()) return false;

        Element<T> element = first;

        int index = 0;

        while (!element.getValue().equals(o)) {
            element = element.getNext();
            index++;
        }

        remove(index);

        return true;
    }

    //Unfinished methods**************************************
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {

    }

    public boolean contains(Object o) {
        return false;
    }

    public Iterator<T> iterator() {
        return null;
    }

    public int indexOf(Object o) {
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator<T> listIterator() {
        return null;
    }

    public ListIterator<T> listIterator(int index) {
        return null;
    }

    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    public void printList() {
        Element<T> element = new Element<T>();
        element = first;
        for (int i = 0; i < size; i++) {
            System.out.println(element.getValue() + " index: " + i);
            element = element.getNext();
        }
    }

    //***************Inner class of Element of the List*************
    private class Element<T> {

        private T value;
        private Element<T> next;

        private Element() {
            this.value = null;
            this.next = null;
        }

        private Element(T value) {
            this.value = value;
            this.next = null;
        }

        private Element<T> getNext() {
            return next;
        }

        private void setNext(Element<T> next) {
            this.next = next;
        }

        private T getValue() {
            return value;
        }

        private void setValue(T value) {
            this.value = value;
        }

        private boolean hasNextElement() {
            return next != null;
        }

        public int hashCode() {
            return super.hashCode();
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if (this.getClass() != o.getClass()) return false;
            Element<T> element = (Element) o;
            if (this.getValue().equals(element.getValue())) {
                return true;
            }
            return false;
        }

    }
}
