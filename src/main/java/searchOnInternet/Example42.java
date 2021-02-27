package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//不可交换
//IndexValuePair


public class Example42 {
    public void reduce(ElemwntList list)  {
        // normalize the row of movieIdi
        // input: <movieIdi, (movieIdj1:count1, movieIdj2:count2, ...)>
        // output: <movieIdj, movieIdi=countj/totalCount>
        List<TwoTuple> output = new ArrayList<TwoTuple>();
        double totalCount = 0.0;
        Map<String, Integer> movie_count = new HashMap<String, Integer>();
        // fetch each movie_count pair and sum up the count
        for (Element value : list.getList()) {
            String[] val = value.toString().split(":");
            int count = Integer.parseInt(val[1]);
            totalCount += count;
            movie_count.put(val[0], count);
        }
        output.add(new TwoTuple(totalCount+"", movie_count.toString()));
        // normalize the count
        // prepare for matrix multiplication at next step by switching the positions of movieIds

    }
}
