package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.List;

public class Example58 {

    List<TwoTuple> output = new ArrayList<TwoTuple>();
    public void reduce(ElemwntList list)  {
        // sum up counts for the key
        int sum = 0;
        int count = 0;
        String sumStr = "";
        String key = (String)list.getList().get(0).getList().get(0);
        for (Element value : list.getList()) {
            count++;
            if (count<5){
                sum += (int)value.getList().get(1);
            }else {
                sumStr += value.getList().get(1).toString()+"/";
            }

        }
        // output (word, count)
        output.add(new TwoTuple(key, sum+sumStr));
    }
}
