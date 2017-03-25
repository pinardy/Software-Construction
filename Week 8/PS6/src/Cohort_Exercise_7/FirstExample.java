package Cohort_Exercise_7;

import java.util.Vector; // Vector is thread-safe

public class FirstExample {
    //@pre: list is not null
    //@post: return last item in list
    public static Object getLast(Vector list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            return list.get(lastIndex);
        }
    }

    //@pre: list is not null
    //@post: remove last item from list
    public static void deleteLast(Vector list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            list.remove(lastIndex);
        }
    }

    //@pre: list is not null, object is not null
    //@post: return true if item is in list
    public static boolean contains(Vector list, Object obj) {
        synchronized (list) {
            Object clone = obj;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(clone)) {
                    return true;
                }
            }
            return false;
        }
    }
}
