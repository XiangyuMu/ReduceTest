package searchOnInternet;

import com.google.common.collect.Lists;
import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//不可交换
//非五类
public class Example50 {
    List<TwoTuple> output = new ArrayList<>();
    public void reduce(ElemwntList list) {
        String key = (String)list.getList().get(0).getList().get(0);
        List<Element> studentEvents = Lists.newArrayList();
        for (Element studentEvent : list.getList()) {
            studentEvents.add(studentEvent);
        }

        Collections.sort(studentEvents, new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                return Integer.parseInt(o1.getList().get(1).toString())-Integer.parseInt(o2.getList().get(1).toString());
            }
        });

        long currentEventId = Long.MIN_VALUE;
        // Loop for keeping only the latest student events.
        for (Element studentEvent : studentEvents) {
            if (currentEventId == Long.parseLong(studentEvent.getList().get(1).toString())) {
                continue;
            }
            String value = studentEvent.getList().get(2).toString();
            output.add(new TwoTuple(key, value));
            currentEventId = Long.parseLong(studentEvent.getList().get(1).toString()
            );
        }
    }
}
