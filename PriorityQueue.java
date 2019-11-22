package package1122;


import java.util.Arrays;

public class PriorityQueue {
    //这个数组是队列本体，基于数组建堆
   private int[] array = new int[100];
   //队列中元素的个数，堆的大小
   private int size;

   public  void offer(int x) {
       if (size > array.length) {
           return;
       }
       array[size] = x;
       size++;
       shifUp(array,size - 1);
   }

    private static void shifUp(int[] array, int index) {
       int child = index;
       int parent = (index - 1) / 2;
       while (child > 0) {
           if (array[child] > array[parent]) {
               swap(array,child,parent);
           } else {//调整完全后，跳出
               break;
           }
           child = parent;
           parent = (child - 1) / 2;
       }
    }

    // 堆顶元素就是返回值
    // 删除堆顶元素并不是直接删除.
    // 用最后一个元素来覆盖堆顶元素
    // 再从堆顶位置向下调整即可
    public  Integer poll() {
       if (size == 0) {
           return null;
       }
       int ret = array[0];
       array[0] = array[size - 1];
       size--;
       ShiftDown(array,size - 1,0);
       return ret;
    }

    private static void ShiftDown(int[] array, int size, int index) {
       int parent = index;
       int child = 2 * parent + 1;
       while (child < size) {
           if (child + 1 < size
                   && array[child + 1] > array[child]) {
                child = child + 1;
           }
           if (array[child] > array[parent]) {
               swap(array,child,parent);
           } else {
               break;
           }
           parent = child;
           child = 2 * parent + 1;
       }
    }

    private static void swap(int[] array, int child, int parent) {
       int tmp = array[child];
       array[child] = array[parent];
       array[parent] = tmp;
    }

    public  Integer peek() {
       if (size == 0) {
           return null;
       }
       return array[0];
    }

    public void display() {

        System.out.println(Arrays.toString(array));
    }


}
