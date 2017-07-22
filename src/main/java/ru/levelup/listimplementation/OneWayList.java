package ru.levelup.listimplementation;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by George on 15.07.2017.
 */
public class OneWayList<T> implements List<T> {

    //Fields*************************************
    private int size = 0;
    private Element first;
    private Element last;

    //Methods************************************
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] toArray() {

        Object[] arr = new Object[size];
        Element element = first;

        for (int i = 0; i < size; i++) {
            arr[i] = element;
            element = element.getNext();
        }

        return arr;
    }

    public boolean add(T t) {

        Element element = new Element(t);
        if (size == 0) {
            first = element;
            last = element;
//            first.setNext(last);      //лишнее
//            size++;
        } else {
            last.setNext(element);
            last = element;
//            size++;
        }

        size++;

        return true;
    }

    public void add(int index, T t) {

        if (index <= size) {
            Element elementToAdd = new Element(t);
            Element currElement = first;
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

        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("Index can not be lass then zero " +
                "or more than count of elements. Index = " + index);

        Element element = first;

        for (int i = 0; i < index; i++) {
            element = element.getNext();
        }

        return element.getValue();
    }

    public T set(int index, T t) {

        if (index > size - 1) throw new IndexOutOfBoundsException();

        Element el = first;

        for (int i = 0; i < index; i++) {
            el = el.getNext();
        }

        el.setValue(t);

        return null;
    }

    //remove method
    public T remove(int index) {
        if (index > size - 1) throw new IndexOutOfBoundsException();

        Element element = first;

        //добавить для  size == 1

        if (index == 0) {
            T t = first.getValue();
            first = element.getNext();
            size--;
            return t;
        }

        if (size == 1) {
            T t = first.getValue();
            first = null;
            last = null;
            size--;
            return t;
        }

        Element prevEl = null;
        for (int i = 0; i < index; i++) {
            if (i == index - 1) {
                prevEl = element;
            }
            element = element.getNext();
        }
        prevEl.setNext(element.getNext());
        size--;

        return element.getValue();
    }

    public boolean remove(Object o) {

        Element element = first;
        boolean isFound = false;

        int index;

        for (int i = 0; i < size; i++) {
            if (element.getValue().equals(o)) {
                index = i;
                isFound = true;
                remove(index);
                break;
            }
            element = element.getNext();
        }

        return isFound;
    }

    public void clear() {
        if (size != 0) {
            Element element = first;
            Element temp = element;

            for (int i = 0; i < size; i++) {
                element = element.getNext();
                temp.setNext(null);
                temp = element;
            }
        }
        last = null;
        size = 0;
    }

    public int indexOf(Object o) {

        int index = 0;
        Element element = first;

        while (!element.getValue().equals(o)) {
            element = element.getNext();
            index++;
        }

        return index;
    }

    public int lastIndexOf(Object o) {
        int indexToReturn = -1;

        if (size != 0) {
            Element element = first;
            for (int i = 0; i < size; i++) {
                if (element.getValue().equals(o)) {
                    indexToReturn = i;
                }
                element = element.getNext();
            }
        }

        return indexToReturn;
    }

    public boolean contains(Object o) {

        boolean isHere = false;

        if (size != 0) {
            Element element = first;
            for (int i = 0; i < size; i++) {
                if (element.getValue().equals(o)) {
                    isHere = true;
                    break;
                }
                element = element.getNext();
            }
        }

        return isHere;
    }

    public List<T> subList(int fromIndex, int toIndex) {

        if (fromIndex < 0 || fromIndex >= size || toIndex <= 0 || toIndex > size)
            throw new IndexOutOfBoundsException();

        List<T> list = new OneWayList<>();

        if (size != 0) {
            Element element = first;
            for (int i = 0; i < toIndex + 1; i++) {
                if (i >= fromIndex) {
                    list.add(element.getValue());
                }
                element = element.getNext();
            }
        }

        return list;
    }

    private void checkClassCastCollection(Collection<?> c) {
        Element element;
        Object[] arrOfC = c.toArray();
        for (int i = 0; i < c.size(); i++) {
            element = first;
            for (int j = 0; j < size; j++) {
                if (!element.getValue().getClass().equals(arrOfC[i].getClass())) throw new ClassCastException();
                element = element.getNext();
            }
        }
    }

    private void checkCollectionOnNull(Collection<?> c) {
        if (c == null) throw new NullPointerException();
    }

    public boolean containsAll(Collection<?> c) {
        checkCollectionOnNull(c);

        if (size != 0) {
            checkClassCastCollection(c);

            Object[] arrOfC = c.toArray();
            int counter = 0;
            for (int i = 0; i < c.size(); i++) {
                Element element = first;
                for (int j = 0; j < size; j++) {
                    if (element.getValue().equals(arrOfC[i])) {
                        counter++;
                        break;
                    }
                    element = element.getNext();
                }
            }
            if (counter == c.size()) return true;
        }

        return false;
    }

    public boolean addAll(Collection<? extends T> c) {
        checkCollectionOnNull(c);
        checkClassCastCollection(c);

        if (c.size() != 0) {
            Object[] arrOfC = c.toArray();
            for (int i = 0; i < arrOfC.length; i++) {
                T valueToAdd = (T) arrOfC[i];
                this.add(valueToAdd);
            }
            return true;
        }

        return false;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        checkCollectionOnNull(c);
        checkClassCastCollection(c);
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        Object[] arrOfC = c.toArray();
        for (int i = 0; i < c.size(); i++) {
            T toAdd = (T) arrOfC[i];
            this.add(index + i, toAdd);
        }

        return true;
    }

    public boolean removeAll(Collection<?> c) {
        checkCollectionOnNull(c);
        checkClassCastCollection(c);

        Object[] arrOfC = c.toArray();
        for (int i = 0; i < arrOfC.length; i++) {
            Element element = first;
            T elementToDelete = (T) arrOfC[i];
            for (int j = 0; j < size; j++) {
                if (elementToDelete.equals(element.getValue())) {
                    this.remove(elementToDelete);
                }
                element = element.getNext();
            }
        }

        return false;
    }

    public boolean retainAll(Collection<?> c) {
        checkCollectionOnNull(c);
        checkClassCastCollection(c);

        this.clear();
        Object[] arrOfC = c.toArray();

        for (int i = 0; i < c.size(); i++) {
            this.add((T) arrOfC[i]);
        }

        return false;
    }

    public <T> T[] toArray(T[] a) {
        if (a == null) throw new NullPointerException();

        if (a.length >= size) {
            Element element = first;
            for (int i = 0; i < a.length; i++) {
                if(i < size) {
                    a[i] = (T) element.getValue();
                    element = element.getNext();
                } else a[i] = null;
            }
        } else {
//            a = (T[]) this.toArray();
//            a = (T[]) new Object[size];
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);

            Element element = first;
            for (int i = 0; i < size; i++) {
                a[i] = (T) element.getValue();
                element = element.getNext();
            }
        }

        return a;
    }

    //Unfinished methods**************************************

    public Iterator<T> iterator() {
        return null;
    }

    public ListIterator<T> listIterator() {
        return null;
    }

    public ListIterator<T> listIterator(int index) {
        return null;
    }

    public void printList() {
        Element element = first;
        for (int i = 0; i < size; i++) {
            System.out.println(element.getValue() + " index: " + i);
            element = element.getNext();
        }
    }

    //***************Inner class of Element of the List*************
    private class Element {

        private T value;
        private Element next;

        private Element() {
            this.value = null;
            this.next = null;
        }

        private Element(T value) {
            this.value = value;
            this.next = null;
        }

        private Element getNext() {
            return next;
        }

        private void setNext(Element next) {
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

//        public int hashCode() {
//            return super.hashCode();
//        }

//        public boolean equals(Object o) {
////            if (this == o) return true;
//            if (o == null) return false;
//            if (this.getValue().getClass() != o.getClass()) return false;
////            Element element = (Element) o;
//            if (this.getValue().equals(o)) {
//                return true;
//            }
//            return false;
//        }

    }
}
