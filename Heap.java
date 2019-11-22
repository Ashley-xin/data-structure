package package1121;

import java.util.Arrays;

public class Heap {

    public static void shiftDown(int[] array,int size,int index) {
        int parent = index;
        int Child = 2 * index + 1;
        while (Child < size) {
            if (Child + 1 < size &&array[Child + 1] > array[Child]) {
                Child = Child + 1;//总之Child指向最大的那个数字
            }
            if (array[Child] > array[parent]) {
                swap(array,parent,Child);
            } else {
                break;
            }
            parent = Child;
            Child = 2 *parent + 1;
        }
    }

    private static void swap(int[] array, int parent, int child) {
        int tmp = array[parent];
        array[parent] = array[child];
        array[child] = tmp;
    }


    }public static void creatHeap(int[] array,int size) {
        //从最后一个非叶子节点开始遍历
        for (int i = (size - 1 - 1) / 2; i >= 0; i-- ) {
            shiftDown(array,size,i);
        }


    public static void main(String[] args) {
        int[] array = { 1,8,9,10,2,3,5};
        creatHeap(array,array.length);
        System.out.println(Arrays.toString(array));
    }
}
