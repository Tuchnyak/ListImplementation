package ru.levelup.listimplementation;

import java.util.LinkedList;

/**
 * Created by go_schennikov on 12.07.2017.
 */
public class L1HWOneWayList {

    public static void main(String[] args) {

        OneWayList<String> owl = new OneWayList<String>();

        owl.add("owl 0");
        owl.add("owl 1");
        owl.add("owl 2");
        owl.add("owl 3");
        owl.add("owl 4");
        owl.add("owl 5");
        owl.add("owl 6");

        OneWayList<String> owl2 = (OneWayList<String>) owl.subList(2, 6);

        owl2.printList();

        System.out.println("**********");

        owl.printList();
        System.out.println("**********");

        owl.add(7, "OWL X");

        owl.remove("owl 3");

        owl.clear();

        owl.printList();

        System.out.println(owl);

        System.out.println(owl.getClass());

        LinkedList<String> listToAdd = new LinkedList<>();
        listToAdd.add("owl X1");
        listToAdd.add("owl X2");

        owl.addAll(listToAdd);
        owl.printList();

    }

}
