package package1122;

public class Test {
    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.offer(93);
        priorityQueue.offer(84);
        priorityQueue.offer(72);
        priorityQueue.offer(99);
        priorityQueue.offer(100);
        priorityQueue.offer(90);
        priorityQueue.display();
        int result = priorityQueue.poll();
        System.out.println(result);
        int result2 = priorityQueue.peek();
        System.out.println(result2);
    }
}
