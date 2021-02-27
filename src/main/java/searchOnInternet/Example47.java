package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


//不可交换
//StrConcat

public class Example47 {
    List<TwoTuple> output_1 = new ArrayList<>();
    public void reduce(ElemwntList list)  {
        String key = (String)list.getList().get(0).getList().get(0);
        String output = "";
        while(list.getList().listIterator().hasNext())
        {
            Element anagam = list.getList().listIterator().next();
            output = output + anagam.getList().get(1).toString() + "~";
        }
        StringTokenizer outputTokenizer = new StringTokenizer(output,"~");
        if(outputTokenizer.countTokens()>=2)
        {
            output = output.replace("~", ",");

            output_1.add(new TwoTuple(key, output));
        }
    }

}
