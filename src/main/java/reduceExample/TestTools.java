package reduceExample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TestTools {
    public ElemwntList ellist = new ElemwntList();


    /**
     * 从文件中读入测试用例每个元素为Atom（名字。行，列）的形式存储
     * @param filepath
     * @return
     */
    public ElemwntList readTestCase(String filepath){

        File file = new File(filepath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            //	int count = 0;
            int row = 0;
            int column = 0;
            while ((tempString = reader.readLine()) != null) {
                String str[] = tempString.split("#");
                System.out.println("str "+str.length);
                Element el = new Element();
                for(int i = 0;i<str.length;i++) {
                    Atom atom = new Atom(str[i]);
                    atom.setColumn(row);
                    atom.setRow(column);
                    el.getAtomlist().add(atom);
                    el.getList().add(str[i]);
                    row = row + 1;
                }
                row = 0;
                column = column + 1;
                ellist.getList().add(el);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ellist;
    }




}
