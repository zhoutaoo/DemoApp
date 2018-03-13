package kata;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactor {

    private ArrayList<Integer> result;

    public List<Integer> factor(int number) {
        result = new ArrayList<Integer>();
        int candidate = 2;
        while (candidate <= number) {
            while (number % candidate == 0) {
                result.add(candidate);
                number = number / candidate;
            }
            candidate++;
        }
        return result;
    }
}
