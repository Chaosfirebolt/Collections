package test.util;

/**
 * Created by ChaosFire on 29.8.2017 г.
 */
public class Helper {

    public static <T> String printCollection(Iterable<T> iterable) {
        StringBuilder result = new StringBuilder();
        for (T element : iterable) {
            result.append(element).append(" ");
        }
        return result.toString();
    }
}