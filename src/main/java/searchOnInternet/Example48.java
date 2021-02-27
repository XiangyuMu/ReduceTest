package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.List;
//不可交换
//SingleItem
public class Example48 {
    List<TwoTuple> output = new ArrayList<>();
    public void reduce(ElemwntList coValues) {
        int measures = 0;
        double totalCo = 0.0f;
        String key = (String)coValues.getList().get(0).getList().get(0);
        for (Element coValue : coValues.getList()) {
            totalCo += Double.parseDouble(coValue.getList().get(1).toString());
            measures++;
            break;
        }

        if (measures > 0) {
            output.add(new TwoTuple(key, (totalCo / measures)+""));
        }
    }
}
