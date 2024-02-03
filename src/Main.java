import java.util.Random;

public class Main {
    static int balance = 100;
    public static void main(String[] args) {
        Test test1 = new Test();
        Test2 test2 = new Test2();
        new Thread(test1).start();
        new Thread(test2).start();
    }

    static class Test extends Thread {


        @Override
        public void run() {
            while (true) {
                Random rand = new Random();
                synchronized (this) {
                    balance += 1 + rand.nextInt(100);
                }
//            System.out.println("Balance: " + balance);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    static class Test2 extends Thread {
        @Override
        public void run() {
            while (true) {
                Random rand = new Random();
                synchronized (this) {
                    int n = 1 + rand.nextInt( 110);
                    if (balance < n) {
                        System.out.println("Не хватает!");
                    } else {
                        balance -= n;
                    }
                }
                System.out.println("Balance: " + balance);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}



