import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class MainProblem {
    public static void main(String[] args) {
        int[] arr= {2,5,9,11,23,30,31,8,12};
        int num=20;
        Map<Integer,Integer> ValueMap=new HashMap<>();
        //Problem Solve in O(n^2).
        for (int i=0;i<arr.length-1;i++)
            for (int j = 1; j < arr.length; j++)
                if (arr[i] + arr[j] == num) {
                    ValueMap.put(i, arr[i]);
                    ValueMap.put(j, arr[j]);
                }


        System.out.println("Problem Solve in O(n^2).\n"+ValueMap.values());

        //Problem Solve in O(n log n).
        int  left_index=0, right_index = arr.length-1;
        ValueMap.clear();
        for (int i=0 ; i < arr.length -1; i++) {
            if (arr[i + 1] < arr[i]) {
                Arrays.sort(arr);
                break;
            }
        }

        while ( left_index <=right_index )
        {
            int Sum=arr[left_index]+arr[right_index];

            if (Sum==num)
            {
                ValueMap.put(left_index,arr[left_index]);
                ValueMap.put(right_index,arr[right_index]);
                left_index++;
            }
            else if (Sum<num) {
                left_index++;
            }
            else {
                right_index--;
            }
        }
        System.out.println("Problem Solve in O(n log n).\n"+ValueMap.values());

        //Problem Solve in O(n).
        ValueMap.clear();
        Vector<Integer> Values=new Vector<>();
        for (int i=0;i<arr.length;i++) {
            int reminder=num-arr[i];

            if (ValueMap.containsValue(reminder)){
                ValueMap.put(i,arr[i] );
                Values.add(arr[i] );
                Values.add(reminder);
            }
            ValueMap.put(i,arr[i]);
        }

        System.out.println("Problem Solve in O(n).\n"+Values);

    }
}