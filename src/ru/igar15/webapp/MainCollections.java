package ru.igar15.webapp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainCollections {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        removeWithForCycle(new ArrayList<>(list), "3");
        removeWithIterator(new ArrayList<>(list), "3");
        removeWithForEachOperator(new ArrayList<>(list), "3");
    }

    public static <T> void removeWithForCycle(List<T> list, T element) {
        for (int i = 0; i < list.size(); ) {
            if (list.get(i).equals(element)) {
                list.remove(i);
            } else {
                i++;
            }
        }
        System.out.println(list);
    }

    public static <T> void removeWithIterator(List<T> list, T element) {
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            T temp = iterator.next();
            if (temp.equals(element)) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }

    public static <T> void removeWithForEachOperator(List<T> list, T element) {
        for (T temp : list) {
            if (temp.equals(element)) {
                list.remove(temp);
            }
        }
        System.out.println(list);
    }
}
