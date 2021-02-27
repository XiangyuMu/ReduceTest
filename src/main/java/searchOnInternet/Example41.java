package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.List;

//不可交换
//FirstN


public class Example41 {

    List<TwoTuple> output = new ArrayList<TwoTuple>();
    public void reduce(ElemwntList list)  {
        // sum up counts for the key
        int sum = 0;
        int count = 0;
        String key = (String)list.getList().get(0).getList().get(0);
        for (Element value : list.getList()) {
            count++;
            sum += (int)value.getList().get(1);
            if (count>5){
                break;
            }
        }

        // output (word, count)
        output.add(new TwoTuple(key, sum+""));
    }
}
