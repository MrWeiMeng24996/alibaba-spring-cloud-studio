package peng.mou.kobron.trycatch;

public class MainTestTry {
    public static void main(String[] args) {
        System.out.println(test());
        System.out.println(test12());
    }
    static int test(){

        try{return 8;}finally {
            return 24;
        }
    }
    static int test12(){
        int i = 8;
        try{
            return i;
        }finally {
            i = 99;
        }
    }
}
