import java.util.List;
import java.util.ArrayList;
import java.util.Random;
public class Sorting {
    public static void main(String args[]) {
        Sorting s = new Sorting();
        System.exit(s.run());
    }
    public int run() {
        List<Integer> arr = new ArrayList<>();
        int m = 0;
        for(int i = 0; i < 100; ++i) {
            int add = (new Random()).nextInt() % 100;
            add *= add < 0 ? -1 : 1;
            arr.add(new Integer(add));
            if(add > m) {
                m = add;
            }
        }
        System.out.println("Counting sorting: " + arr.toString());
        arr = countingSort(arr, m);
        System.out.println("Sorted to: " + arr.toString());
        return 0;
    }
    /**
     * Perform a counting sort
     * @param arr A list of integers
     * @param m the biggest number in the list
     * @return The list that was input but sorted
     */
    public List<Integer> countingSort(List<Integer> arr, Integer m) {
        List<Integer> count = new ArrayList<>(m + 1);
        for(int i = 0; i < m + 1; ++i) {
            count.add(i, 0);
        }
        for(Integer e : arr) {
            count.set(e, count.get(e) + 1);
        }
        List<Integer> result_arr = new ArrayList<Integer>(arr.size());
        for(int i = 0; i < m + 1; ++i) {
            for(int j = 0; j < count.get(i); ++j) {
                result_arr.add(i);
            }
        }
        return result_arr;
    }
}
