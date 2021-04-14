package reduceExample;

import java.util.Random;

public class randomCount {
    int count = 0;




    public int generRandom(int max){
        Random random = new Random();
        int c = random.nextInt();
        while (c>max||c<1){
            c=random.nextInt();
        }
        return c;
    }


    public static void main(String[] args) {
        randomCount randomCount = new randomCount();
        for (int i = 0;i<100;i++){
            System.out.println(randomCount.generRandom(1628800));
        }
    }
}
