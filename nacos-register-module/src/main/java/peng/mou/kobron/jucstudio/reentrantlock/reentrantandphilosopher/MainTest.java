package peng.mou.kobron.jucstudio.reentrantlock.reentrantandphilosopher;



public class MainTest {
    public static void main(String[] args) {
        Chopsticks chopsticks1 = new Chopsticks("1号筷子");
        Chopsticks chopsticks2 = new Chopsticks("2号筷子");
        Chopsticks chopsticks3 = new Chopsticks("3号筷子");
        Chopsticks chopsticks4 = new Chopsticks("4号筷子");
        Chopsticks chopsticks5 = new Chopsticks("5号筷子");

        new Philosopher("PengMou",chopsticks1,chopsticks5).start();
        new Philosopher("SheDanny",chopsticks2,chopsticks1).start();
        new Philosopher("LinPiao",chopsticks3,chopsticks2).start();
        new Philosopher("HuangMan",chopsticks4,chopsticks3).start();
        new Philosopher("GaoYuan",chopsticks5,chopsticks4).start();
    }
}
