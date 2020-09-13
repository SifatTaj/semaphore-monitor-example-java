/**
 *
 * The producer–consumer problem with a monitor
 * This is the preferred approach
 *
 * @author Sifat Ut Taki
 * BRAC University
 */

class Producer2 extends Thread {
  @Override
  public void run() {
    for (int i = 0 ; i < 100 ; i++)
      BufferWithMonitor.criticalSection("increment");
  }
}

class Consumer2 extends Thread {
  @Override
  public void run() {
    for (int i = 0 ; i < 100 ; i++)
      BufferWithMonitor.criticalSection("decrement");
  }
}

class BufferWithMonitor {

  static int count = 0;

  public static synchronized void criticalSection(String operation) {
    if (operation.equals("increment"))
      count++; // count = count + 1;
    else if (operation.equals("decrement"))
      count--; // count = count - 1;
  }
}

public class MutexDemoWithMonitor {

  public static void main(String[] args) throws InterruptedException {
    Producer2 producer = new Producer2();
    Consumer2 consumer = new Consumer2();

    producer.start();
    consumer.start();

    producer.join();
    consumer.join();

    System.out.println("Buffer count: " + BufferWithMonitor.count);
  }
}
