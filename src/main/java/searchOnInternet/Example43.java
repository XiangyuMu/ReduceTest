package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.List;

//不可交换
//StrConcat


public class Example43 {
    List<TwoTuple> output = new ArrayList<TwoTuple>();
    public void reduce(ElemwntList list)  {
        // aggregate the rating data by userId
        // input: <userId, (movieId1:rating1, movieId2:rating2, ...)>
        // output: <userId, movieId1:rating1,movieID2:rating2,...>
        String key = (String)list.getList().get(0).getList().get(0);
        // append all the values to a string
        StringBuilder outputValue = new StringBuilder();
        for (Element value : list.getList()) {
            String val = value.getList().get(1).toString();
            outputValue.append(val).append(',');
        }
        outputValue.deleteCharAt(outputValue.length() - 1);
        output.add(new TwoTuple(key, outputValue.toString()));
    }

}
