import TestCasesSetGeneration.GerenateTestCasesSet;

import java.util.ArrayList;
import java.util.List;

public class IndexValuePairTestCaseTest {

    public void IndexValuePairTestCase(List<String> list,List<Integer> Indexlist1, List<Integer> Indexlist2){
        List<String> stringList = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        if (Indexlist1.size()<Indexlist2.size()){
            for (int i = 0;i<Indexlist1.size();i++){
                list1.add(list.get(Indexlist1.get(i)));
            }
            for (int j = 0;j<Indexlist2.size();j++){
                list2.add(list.get(Indexlist2.get(j)));
            }
        }else{
            for (int i = 0;i<Indexlist2.size();i++){
                list1.add(list.get(Indexlist2.get(i)));
            }
            for (int j = 0;j<Indexlist1.size();j++){
                list2.add(list.get(Indexlist1.get(j)));
            }
        }




        int list_count1 = 0;
        int list_count2 = 0;
        for (int k = 0;k<Indexlist1.size()+Indexlist2.size();k++){
            list_count1 = 0;
            list_count2 = 0;
            if (k<list1.size()){
                for (int l1 = 0;l1<list1.size()-k;l1++){
                    stringList.add(list1.get(list_count1));
                    list_count1++;
                }
                for (int l2 = 0; l2<k; l2++){
                    stringList.add(list2.get(list_count2));
                    list_count2++;
                    stringList.add(list1.get(list_count1));
                    list_count1++;
                }
                if (list_count1!=list1.size()){
                    for (;list_count1<list1.size();list_count1++){
                        stringList.add(list1.get(list_count1));
                    }
                }
                if (list_count2!=list2.size()){
                    for (;list_count2<list2.size();list_count2++){
                        stringList.add(list2.get(list_count2));
                    }
                }
            }else{
                int j = k-list1.size()+1;
                for (int l2 = 0;l2<j;l2++){
                    stringList.add(list2.get(list_count2));
                    list_count2++;
                }
                while((list_count1!=list1.size())&&(list_count2!=list2.size())){
                    stringList.add(list1.get(list_count1));
                    list_count1++;
                    stringList.add(list2.get(list_count2));
                    list_count2++;
                }
                if (list_count1==list1.size()){
                    while (list_count2!=list2.size()){
                        stringList.add(list2.get(list_count2));
                        list_count2++;
                    }
                }else {
                    while (list_count1!=list1.size()){
                        stringList.add(list1.get(list_count1));
                        list_count1++;
                    }
                }

            }

            System.out.println(stringList.toString());
            stringList.clear();

        }


    }


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<Integer> IndexList1 = new ArrayList<>();
        List<Integer> IndexList2 = new ArrayList<>();
        for (int i = 0;i<10;i++){
            list.add(String.valueOf(i));
        }
        for (int k = 0;k<8;k++){
            IndexList1.add(k);
        }
        for (int j = 8;j<10;j++){
            IndexList2.add(j);
        }

        System.out.println(list+" "+IndexList1+""+IndexList2);
        IndexValuePairTestCaseTest indexValuePairTestCaseTest = new IndexValuePairTestCaseTest();
        indexValuePairTestCaseTest.IndexValuePairTestCase(list,IndexList1,IndexList2);

    }


}
