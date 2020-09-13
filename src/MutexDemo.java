import java.util.concurrent.Semaphore;

/**
 *
 * The producer–consumer problem with a semaphore
 * However, this is not a preferred approach
 *
 * @author Sifat Ut Taki
 * BRAC University
 */

class Producer extends Thread {
  @Override
  public void run() {
    for (int i = 0 ; i < 100 ; i++) {
      try {
        MutexDemo.mutex.acquire(); // down(mutex)
        Buffer.criticalSection("increment");
        MutexDemo.mutex.release(); // up(mutex)
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

class Consumer extends Thread {
  @Override
  public void run() {
    for (int i = 0 ; i < 100 ; i++) {
      try {
        MutexDemo.mutex.acquire(); // down(mutex)
        Buffer.criticalSection("decrement");
        MutexDemo.mutex.release(); // up(mutex)
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

class Buffer {

  static int count = 0;

  public static synchronized void criticalSection(String operation) {
    if (operation.equals("increment"))
      count++; // count = count + 1;
    else if (operation.equals("decrement"))
      count--; // count = count - 1;
  }
}

public class MutexDemo {

  public static Semaphore mutex = new Semaphore(1);

  public static void main(String[] args) throws InterruptedException {
    Producer producer = new Producer();
    Consumer consumer = new Consumer();

    producer.start();
    consumer.start();

    producer.join();
    consumer.join();

    System.out.println("Buffer count: " + Buffer.count);
  }
}
