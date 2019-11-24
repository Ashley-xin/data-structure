import java.util.Arrays;
import java.util.Stack;

public class insertSort {

    public static void insertSort(int []array) {
        //分为待排区和已排区
        //由bound分界
        int bound = 1;
        for (; bound < array.length; bound++) {
            int cur = bound - 1;
            int tmp = array[bound];
            for (; cur >= 0; cur--) {
                if (array[cur] > tmp) {
                    array[cur + 1] = array[cur];
                } else {
                    break;
                }
            }
            array[cur + 1] = tmp;
        }
    }

    public static void shellSort(int[] array) {
        int gap = array.length;
        while (gap > 1) {
            insertSortGap(array,gap);
            gap = gap / 2;
        }
        insertSortGap(array,1);
    }

    private static void insertSortGap(int[] array, int gap) {
            int bound = 0;
            for (; bound < array.length; bound++) {
                int tmp = array[bound];
                int cur = bound -gap;
                for (;cur >= 0;cur -= gap) {
                    if (array[cur] > tmp) {
                        array[cur + gap] = array[cur];
                    } else {
                        break;
                    }
                }
                array[cur + gap] = tmp;
            }
        }


    public static void selectSort(int[] array) {
        //bound表示边界
        //选择未排序的最大放入未排序的末尾
        //[0,bound]已排序
        //[bound,size)未排序
        for (int bound = 0; bound < array.length; bound++) {
            for (int cur = bound + 1 ; cur < array.length; cur++) {
                if (array[bound] > array[cur]) {
                    swap(array,cur,bound);
                }
            }
        }
    }

    private static void swap(int[] array, int x, int y) {
        int tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
    }

    public static void heapSort(int[] array) {
        //先建堆
        creatHeap(array);
        //取出最大的放在数组最后面
        for (int index = 0; index < array.length; index++) {
            //已排序区间[array.length - i, array.length]
            swap(array,0,array.length - index - 1);
            // 第一个参数是数组
            // 第二个参数是数组中的有效元素的个数
            // 第三个参数是从哪个位置进行向下调整
            ShiftDown(array,array.length - index - 1, 0);
        }
    }



    private static void creatHeap(int[] array) {
        //最后一个非叶子节点开始构建
        for (int i = array.length - 1 - 1 / 2; i >= 0; i--) {
            ShiftDown(array,array.length,i);
        }
    }

    private static void ShiftDown(int[] array, int size, int index) {
       int parent = index;
       int child = 2 * parent + 1;
       while (child < size) {
           if (child + 1 < size &&
                   array[child + 1] > array[child]) {
               child = child + 1;
           }
           //child 一定是左右孩子中最大的
           if (array[parent] < array[child]) {
               swap(array,parent,child);
           } else {
               break;
           }
           //传递下标
           parent = child;
           child = 2 * parent + 1;
       }
    }


    public static void bubbleSort(int[] array) {
        //[0,bound)已排序区间
        //[bound,size)未排序区间
       for (int bound = 0; bound < array.length; bound++) {
           for (int index = array.length - 1; index > bound; index--) {
               if (array[index - 1] > array[index]) {
                   swap(array,index - 1,index);
               }
           }
       }
    }


    public static void quickSort(int[] array) {

        quickSortHelper(array,0,array.length - 1);
    }

    private static void quickSortHelper(int[] array, int left, int right) {
       if (left >= right) {
           //数组中只有一个元素，或者没有元素，不需要排序
           return;
       }
       int pos = partition(array,left,right);
       quickSortHelper(array,left,pos - 1);
       quickSortHelper(array,pos + 1,right);
    }

    private static int partition(int[] array, int left, int right) {
        int baseIndex = left;
        int baseValue = array[baseIndex];
        while (left < right) {
            while (left < right && array[right] >= baseValue) {
                right--;
            }
            while (left < right && array[left] <= baseValue) {
                left++;
            }
            swap(array,left,right);
        }
        swap(array,left,baseIndex);
        return left;
    }

    //非递归实现
    public static void quickSortByLoop(int[] array) {
        //在栈中存取待处理的下标
        Stack<Integer> stack = new Stack<>();
        stack.push(array.length - 1);
        stack.push(0);
        while (!stack.isEmpty()) {
            int left = stack.pop();
            int right = stack.pop();
            if (left >= right) {
                continue;
            }
            int pos = partition(array,left,right);
            stack.push(pos - 1);
            stack.push(left);
            stack.push(right);
            stack.push(pos + 1);
        }
    }


    public static void mergeSort(int[] array) {
        // 后两个参数表示要进行归并排序的区间.
        // [0, array.length)
        // new 足够大的数组, 把这个数组作为缓冲区传给
        // 递归函数
        mergeSortHelper(array, 0, array.length);
    }

    private static void mergeSortHelper(int[] array, int left, int right) {
        // [left, right) 构成了要去进行归并排序的区间
        // 如果区间为空区间, 或者只有一个元素, 都不用排序
        if (left >= right || right -  left == 1) {
            // 空区间或者区间只有一个元素, 都不需要进行归并排序
            return;
        }
        // 使用类似后序遍历的方式.
        // 先把当前的待排序区间拆成两半,
        // 递归的对这两个子区间进行归并排序, 保证两个区间有序之后
        // 再进行合并
        int mid = (left + right) / 2;
        // [left, mid)
        // [mid, right)
        mergeSortHelper(array, left, mid);
        mergeSortHelper(array, mid, right);
        merge(array, left, mid, right);
    }

    private static void merge(int[] array, int left,
                              int mid, int right) {
        // 创建一段临时空间辅助进行归并
        // 这个临时空间的长度应该是两个待归并区间的长度之和
        int length = right - left;
        int[] output = new int[length];
        // 这个变量保存着当前 output 中的末尾元素的下标
        int outputIndex = 0;
        // i 和 j 是用来遍历两个区间的辅助变量
        // [left, mid)
        // [mid, right)
        int i = left;
        int j = mid;
        while (i < mid && j < right) {
            // 此处的 if 条件必须要 <= , 否则没法保证稳定性
            if (array[i] <= array[j]) {
                // i 对应的元素比 j 小
                // 就把 i 对应的元素插入到 output 末尾
                output[outputIndex++] = array[i++];
            } else {
                output[outputIndex++] = array[j++];
            }
        }
        // 上面的循环结束之后, 两个区间至少有一个是遍历完了的.
        // 就把剩下的区间的内容直接拷贝到 output 中即可.
        while (i < mid) {
            output[outputIndex++] = array[i++];
        }
        while (j < right) {
            output[outputIndex++] = array[j++];
        }

        // 最后一步, 把 output 中的元素拷贝回原来的区间
        for (int k = 0; k < length; k++) {
            array[left + k] = output[k];
        }
    }

    public static void mergeSortByLoop(int[] array) {
        for (int gap = 1; gap < array.length; gap *= 2) {
            for (int i = 0; i < array.length; i += 2 * gap) {
                // 这个循环负责在 gap 为指定值的情况下
                // 把所有的区间进行归并
                // 针对当前的 i, 也能划分出两个需要进行归并的区间
                // [beg, mid)
                // [mid, end)
                int beg = i;
                int mid = i + gap;
                int end = i + 2 * gap;
                if (mid > array.length) {
                    mid = array.length;
                }
                if (end > array.length) {
                    end = array.length;
                }
                merge(array, beg, mid, end);
            }
        }
    }
    public static void main(String[] args) {
        int[] arr = {9, 5, 2, 7, 3, 6, 8, 1};
         //insertSort(arr);
         //shellSort(arr);
        // selectSort(arr);
        //heapSort(arr);
         bubbleSort(arr);
        // quickSort(arr);
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
