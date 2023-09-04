package com.hps.communication;

/**
 * 案例2: 生产者&消费者生产者(Productor)将产品交给店员(Clerk)，而消费者(Customer)从店员处取走产品，
 * 店员一次只能持有固定数量的产品(比如:20)，如果生产者试图生产更多的产品，店员会叫生产者停一下，
 * 如果店中有空位放产品了再通知生产者继续生产:如果店中没有产品了，店员会告诉消费者等一下，
 * 如果店中有产品了再通知消费者来取走产品。
 * <p>
 * 分析:
 * 1、是否是多线程问题? 是，生产者、消费者
 * 2、是否有共享数据? 有! 共享数据是: 产品
 * 3、是否有线程安全问题? 有!因为有共享数据
 * 4、是否需要处理线程安全问题? 是! 如何处理? 使用同步机制4
 * 5、是否存在线程间的通信 ?在在。
 *
 *
 * 虚假唤醒：if改成while
 */

class Clerk {
    public int productNum = 0;

    public synchronized void addProduct() {
        while (productNum >= 20){
            //等待
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        productNum++;
        System.out.println(Thread.currentThread().getName() + "生产了第 "+ productNum + " 个产品");
        notifyAll();
    }

    public synchronized void minusProduct() {
        while(productNum <= 0){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Thread.currentThread().getName() + "消费了第 "+ productNum + " 个产品");
        productNum--;
        notifyAll();
    }
}

class Producer extends Thread {

    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
//            System.out.println("生产者开始生产产品。。。");

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clerk.addProduct();
        }
    }
}

class Consumer extends Thread {

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
//            System.out.println("消费者开始消费产品。。。");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clerk.minusProduct();
        }

    }
}


public class ProducerConsumer_SyncTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        //利用构造器将产品提升为生产者，消费者的公共变量
        Producer pro1 = new Producer(clerk);
        Consumer con1 = new Consumer(clerk);
        pro1.setName("生产者1");
        con1.setName("消费者1");

        Producer pro2 = new Producer(clerk);
        Consumer con2 = new Consumer(clerk);
        pro2.setName("生产者2");
        con2.setName("消费者2");

        Producer pro3 = new Producer(clerk);
        Consumer con3 = new Consumer(clerk);
        pro3.setName("生产者3");
        con3.setName("消费者3");

        Producer pro4 = new Producer(clerk);
        Consumer con4 = new Consumer(clerk);
        pro4.setName("生产者3");
        con4.setName("消费者3");

        pro1.start();
        con1.start();
//        pro2.start();
        con2.start();
//        pro3.start();
        con3.start();
//        pro4.start();
        con4.start();
    }
}
