package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

//不可交换
//非五类（类似于FirstN）（LastN）

public class Example45 {
    List<TwoTuple> output = new ArrayList<>();

    public void reduce(ElemwntList list) {
        // input: <row, (subSum1, subSum2, ...)>
        // output: <row, subSum1+subSum2+...>
        String key = (String)list.getList().get(0).getList().get(0);
        double sum = 0.0;
        int count = 0;
        for (Element subSum : list.getList()) {
            count++;
            if (count<2){
                continue;
            }
            sum += Double.parseDouble(subSum.getList().get(1).toString());
        }
        DecimalFormat df = new DecimalFormat("#.0000");
        sum = Double.valueOf(df.format(sum));
        output.add(new TwoTuple(key, sum+""));
    }
}
