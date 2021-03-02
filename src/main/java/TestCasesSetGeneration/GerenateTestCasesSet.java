package TestCasesSetGeneration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenateTestCasesSet {
    public List<String> SingleTestCase_str = new ArrayList<>();
    private boolean[] used;
    private List<List<String>> res = new ArrayList<>();

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
        permute(SingleTestCase_str,writer);
        writer.close();
    }

    public void generateFiveSet(String filename,String pattern) throws IOException {
        File file = new File(filename);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        switch (pattern){
            case "SingleItem":{
                SingleItemTestCases(SingleTestCase_str,writer,1);
                break;
            }

            case "IndexValuePair":{

            }

            case "MaxRow":{

            }

            case "FirstN":{

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
    }

    public void printTestCase(List<String> list,BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write("$");
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
            printTestCase(stringList,bufferedWriter);
            stringList.clear();
        }
    }







    private void StrConcatTestCase(List<String> list,BufferedWriter bufferedWriter) throws IOException {
        List<String> stringList = new ArrayList<>();
        for (int i = 0;i<list.size()-1;i++){
            stringList.add(list.get(i+1));
        }
        stringList.add(list.get(0));
        printTestCase(stringList,bufferedWriter);
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

            printTestCase(stringList,bufferedWriter);
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
            bufferedWriter.write("$");
            bufferedWriter.write("\n");
            for (int i = 0;i<preList.size();i++){
                bufferedWriter.write(preList.get(i));
                bufferedWriter.write("\n");
            }
            res.add(new ArrayList<>(preList));
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
}
