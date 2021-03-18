package priorityQueueLab;
public class test_queue{
    public static void main(String[] args) {
        priority_queue<Integer> queue = new priority_queue<>(2);
        for (int i = 1; i < 11; i++){
            int x = (i*9 +i^2+7)%31;
            queue.add(x);
        }
        queue.remove();
        queue.remove();
        for (int i = 11; i < 14; i++){
            int x = (i*9 +i^2+7)%31;
            queue.add(x);
        }
        System.out.println(queue.toString());
        queue.add(18);
        System.out.println(queue.toString());
        queue.remove();
        System.out.println(queue.toString());
        
    }
}