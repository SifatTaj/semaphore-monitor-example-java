class Producer extends Thread {
  @Override
  public void run() {
    for (int i = 0 ; i < 100 ; i++)
      Buffer2.criticalSection("increment");
  }
}

class Consumer extends Thread {
  @Override
  public void run() {
    for (int i = 0 ; i < 100 ; i++)
      Buffer2.criticalSection("decrement");
  }
}

class Buffer2 {
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
    Producer2 producer2 = new Producer2();
    Consumer2 consumer2 = new Consumer2();

    producer2.start();
    consumer2.start();

    producer2.join();
    consumer2.join();

    System.out.println("Buffer count: " + Buffer2.count);
  }
}
