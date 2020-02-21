package com.epam.lesson6;

import java.util.Comparator;

public class ComparatorByCost implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        float cost1 = ((Book) o1).getCost();
        float cost2 = ((Book) o2).getCost();
        if (cost1 == cost2) {
            return 0;
        } else {
            return cost1 < cost2 ? 1 : -1;
        }
    }
}
