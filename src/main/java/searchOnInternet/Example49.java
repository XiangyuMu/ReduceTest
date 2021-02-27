package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//不可交换
//FirstN

public class Example49 {

    private Set<String> uvSet = new HashSet<>();
    List<TwoTuple> output = new ArrayList<>();

    protected void reduce(ElemwntList list)  {
        String key = (String)list.getList().get(0).getList().get(0);
        long pv = 0;
        uvSet.clear();
        for(Element mid : list.getList()) {
            pv++;
            uvSet.add(mid.getList().get(1).toString());
            if(pv>5){
                break;
            }
        }
        long uv = uvSet.size();
        String pvAndUv = pv + "\t" + uv;
        output.add(new TwoTuple(key, pvAndUv));
    }
}
