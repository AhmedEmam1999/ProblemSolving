public class MergeSort {
    public static int[] merge_sort(int[] array)
    {
        if (array.length == 1) {
            return array;
        }
        int mid = array.length/2;
        int[] right_array =new int[array.length-mid],left_array=new int[mid];

        System.arraycopy(array,0,left_array,0 ,mid);
        System.arraycopy(array,mid,right_array,0,array.length-mid);

        left_array=merge_sort(left_array);
        right_array=merge_sort(right_array);

        return merge(left_array,right_array);
    }
    public static int[] merge(int[] left_arr,int[] right_arr)
    {
        int[] Merge_array=new int[left_arr.length+right_arr.length];
        int left_index=0,right_index=0,merge_index=0;
        while (left_index<left_arr.length && right_index<right_arr.length )
        {
            if (left_arr[left_index] < right_arr[right_index]) {
                Merge_array[merge_index]= left_arr[left_index];
                merge_index++;
                left_index++;
            }
            else {
                Merge_array[merge_index]=right_arr[right_index];
                merge_index++;
                right_index++;
            }
        }
        while (left_index!=left_arr.length)
        {
            Merge_array[merge_index]=left_arr[left_index];
            merge_index++;
            left_index++;
        }
        while (right_index!=right_arr.length)
        {
            Merge_array[merge_index]=right_arr[right_index];
            right_index++;
            merge_index++;
        }

        return Merge_array;
    }
    public static void main(String[] args)
    {
        int[ ] arr={30,20,40,5,90,80,10};
        System.out.println("Original Array:");
        for (int num:arr)
        {
            System.out.print(num+" ");
        }
        System.out.println();
        int[] new_arr=merge_sort(arr);
        System.out.println("Sorted Array:");
        for (int num:new_arr)
        {
            System.out.print(num+" ");
        }

    }
}
