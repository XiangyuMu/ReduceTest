import TestCasesSetGeneration.GerenateTestCasesSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerenateTestCaseSetTest {
    public static void main(String[] args) throws IOException {
        GerenateTestCasesSet gerenateTestCasesSet = new GerenateTestCasesSet();
        gerenateTestCasesSet.readTestCase("E:\\eclipse\\ReduceTest\\src\\main\\java\\TestCase\\case1.txt");
        System.out.println(gerenateTestCasesSet.SingleTestCase_str);
//        gerenateTestCasesSet.generateWholeSet("case1Whole.txt");
        List<Integer> relevant = new ArrayList<>();
        List<Integer> replacement = new ArrayList<>();
        List<Integer> irrelevant = new ArrayList<>();

        relevant.add(0);
        irrelevant.add(1);
        irrelevant.add(2);
        irrelevant.add(3);
        irrelevant.add(4);
        relevant.add(5);
        irrelevant.add(6);
        relevant.add(7);
        irrelevant.add(8);
        irrelevant.add(9);


        gerenateTestCasesSet.generateFiveSet("case1Whole.txt","IndexValuePair",relevant,replacement,irrelevant);



    }


}
