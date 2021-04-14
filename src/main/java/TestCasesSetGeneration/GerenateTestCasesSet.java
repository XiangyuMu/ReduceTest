package TestCasesSetGeneration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenateTestCasesSet {
    public List<String> SingleTestCase_str = new ArrayList<>();
    private boolean[] used;
    private List<List<String>> res = new ArrayList<>();
    private List<List<String>> res1 = new ArrayList<>();
    private int num = 1;
    /**
     * 从初始测试用例中按照String读取每一行数据，为生成测试用例集做准备
     * @param filename
     * @throws IOException
     */
    public void readTestCase(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String keyWord = reader.readLine();
        while (keyWord!=null){
            SingleTestCase_str.add(keyWord);
            keyWord = reader.readLine();
        }
        reader.close();
    }

    public void generateWholeSet(String filename) throws IOException {
        File file = new File(filename);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        List<List<String>> list = permute(SingleTestCase_str,writer);
        System.out.println(list.size());
        writer.close();
    }

    public void generateFiveSet(String filename,String pattern,List<Integer> relevant,List<Integer> replacement,List<Integer> irrelevant) throws IOException {
        File file = new File(filename);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        switch (pattern){
            case "SingleItem":{
                if ((relevant.size()==1&&replacement.size()==0&&irrelevant.size()>0)||(relevant.size()==1&&replacement.size()>0&&irrelevant.size()==0)){
                    SingleItemTestCases(SingleTestCase_str,writer,relevant.get(0));
                }
                break;
            }

            case "IndexValuePair":{
                if((relevant.size()>1&&replacement.size()>0&&irrelevant.size()==0)||(relevant.size()>1&&replacement.size()==0&&irrelevant.size()>0)){
                    if (replacement.size()>0){
                        IndexValuePairTestCase(SingleTestCase_str,writer,relevant,replacement);
                    }else {
                        IndexValuePairTestCase(SingleTestCase_str,writer,relevant,irrelevant);
                    }
                }
                break;
            }

            case "MaxRow":{
                if (relevant.size()>0&&replacement.size()>0&&irrelevant.size()>0){
                    SingleItemTestCases(SingleTestCase_str,writer,relevant.get(0));
                }
                break;
            }

            case "FirstN":{

                if (relevant.size()>0&&replacement.size()==0&&irrelevant.size()>1){
                    int[] b = new int[relevant.size()];
                    FirstNTestCase(SingleTestCase_str,writer,SingleTestCase_str.size(),relevant.size(),SingleTestCase_str.size(),relevant.size(),b);
                }
            }

            case "StrConcat":{
                StrConcatTestCase(SingleTestCase_str,writer);
                break;
            }

            default:{
                otherTestCase(SingleTestCase_str,writer);
                break;
            }


        }
        writer.close();
    }

    public void printTestCase(List<String> list,BufferedWriter bufferedWriter,int i1) throws IOException {
        bufferedWriter.write("$"+i1);
        bufferedWriter.write("\n");
        for (int i = 0;i<list.size();i++){
            bufferedWriter.write(list.get(i));
            bufferedWriter.write("\n");
        }

    }
    private void SingleItemTestCases(List<String> list,BufferedWriter bufferedWriter,int selected) throws IOException {
        List<String> stringList = new ArrayList<>();
        String s = "";
        for (int i = 0;i<list.size();i++){
            for (int k = 0;k<list.size();k++){
                if (i==k){
                    stringList.add(list.get(selected));
                    continue;
                }
                if (k==selected){
                    stringList.add(list.get(i));
                    continue;
                }
                stringList.add(list.get(k));
            }
            printTestCase(stringList,bufferedWriter,i);
            stringList.clear();
        }
    }


    private void IndexValuePairTestCase(List<String> list,BufferedWriter bufferedWriter,List<Integer> Indexlist1,List<Integer> Indexlist2) throws IOException {
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
            printTestCase(stringList,bufferedWriter,k);
            System.out.println(stringList.toString());
            stringList.clear();

        }


    }






    private void FirstNTestCase(List<String> list,BufferedWriter bufferedWriter,int m,int n,int M,int N,int[] b) throws IOException {
        {
            int num = 0;
            List<String> stringList = new ArrayList<>();
            int i,j;
            for(i=n;i<=m;i++) {
                b[n-1] = i-1;
                if(n>1) {
                    FirstNTestCase(list,bufferedWriter,i - 1, n - 1,M,N,b);
                }else {

                    for(j=0;j<=N-1;j++){
                        stringList.add(list.get(b[j]));
                    }
                    System.out.println(stringList);
                    printTestCase(stringList,bufferedWriter,num);
                    num++;
                    stringList.clear();
                }
            }

        }
    }

    private void StrConcatTestCase(List<String> list,BufferedWriter bufferedWriter) throws IOException {
        List<String> stringList = new ArrayList<>();
        for (int i = 0;i<list.size()-1;i++){
            stringList.add(list.get(i+1));
        }
        stringList.add(list.get(0));
        printTestCase(stringList,bufferedWriter,0);
    }

    private void otherTestCase(List<String> list,BufferedWriter bufferedWriter) throws IOException {
        List<String> stringList = new ArrayList<>();
        for (int i = 0;i<list.size();i++){
            for (int j = i;j<list.size();j++){
                stringList.add(list.get(j));
            }
            for (int k = 0;k<i;k++){
                stringList.add(list.get(k));
            }

            printTestCase(stringList,bufferedWriter,i);
            stringList.clear();
        }
    }

    public List<List<String>> permute(List<String> nums,BufferedWriter bufferedWriter) throws IOException {
        if (nums.size() == 0) {
            return res;
        }

        used = new boolean[nums.size()];
        List<String> preList = new ArrayList<>();
        generatePermutation(nums, 0, preList,bufferedWriter);

        return res;

    }




    /**
     * 回溯
     * @param nums 给定数组
     * @param index 当前考察的索引位置
     * @param preList 先前排列好的子序列
     */
    private void generatePermutation(List<String> nums,int index,List<String> preList,BufferedWriter bufferedWriter) throws IOException {
        //index 等于给定数组的长度时，说明一种排列已经形成，直接将其加入成员变量 res 里
        if (index == nums.size()) {
            //这里需要注意java的值传递
            //此处必须使用重新创建对象的形式，否则 res 列表中存放的都是同一个引用
            bufferedWriter.write("$"+num);
            num++;
            bufferedWriter.write("\n");
            for (int i = 0;i<preList.size();i++){
                bufferedWriter.write(preList.get(i));
                bufferedWriter.write("\n");
            }
//            if (flag){
//                res.add(new ArrayList<>(preList));
//                flag = false;
//            }else {
//                res1.add(new ArrayList<>(preList));
//                flag = true;
//            }
//            System.out.println(res.size());
            System.out.println(num);
            return;
        }

        for(int i = 0; i < nums.size() ;i++) {
            if (!used[i]) {
                preList.add(nums.get(i));
                used[i] = true;
                generatePermutation(nums, index + 1, preList,bufferedWriter);
                //一定要记得回溯状态
                preList.remove(preList.size() - 1);
                used[i] = false;
            }
        }
        return;
    }




    public int[] combine( int a[], int n, int m,  int b[],  int M )
    {
        int[] d = new int[M];
        for(int i=n; i>=m; i--)   // 注意这里的循环范围
        {
            b[m-1] = i - 1;
            if (m > 1)
                combine(a,i-1,m-1,b,M);

        }
        return b;
    }

}
