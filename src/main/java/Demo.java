import reduceExample.ElemwntList;
import reduceExample.IndexValuePair_1;
import reduceExample.TestTools;
import searchOnInternet.Example01;
import searchOnInternet.Example15;
import searchOnInternet.Example31;
import searchOnInternet.TwoTuple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scan = new Scanner(System.in);
        System.out.println("please input the name of file: ");
        String filename = scan.next();
        TestTools testTools = new TestTools();
        List<ElemwntList> list = new ArrayList<>();
        ElemwntList elist ;
        elist = testTools.readTestCase("TestCase/case31.txt");
        Example31 e = new Example31();
        System.out.println("elist: "+elist);
        e.reduce(elist);
        List<TwoTuple> tt1  = e.getOutput();
        System.out.println(tt1);
    }

}
