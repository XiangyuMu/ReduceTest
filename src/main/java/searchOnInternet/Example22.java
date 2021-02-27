package searchOnInternet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import reduceExample.Element;
import reduceExample.ElemwntList;

//输入<String,String>(key,value)
//输出值为value的累加
//不可交换
//FirstN
public class Example22 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
	
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	 int sum = 0;
         Iterator<Element> iterator = list.getList().iterator();
        int count = 0;
         while (iterator.hasNext()){
             sum += (Integer)iterator.next().getList().get(1);
             count++;
             if (count>7){
                 break;
             }
         }


         output.add(new TwoTuple(key, sum+""));
    }


}
