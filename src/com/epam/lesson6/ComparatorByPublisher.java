package com.epam.lesson6;

import java.util.Comparator;

public class ComparatorByPublisher implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        String publisher1 = ((Book)o1).getPublisher();
        String publisher2 = ((Book)o2).getPublisher();
        return publisher1.compareTo(publisher2);
    }
}
