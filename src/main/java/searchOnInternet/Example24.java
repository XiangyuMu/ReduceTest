package searchOnInternet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import reduceExample.Element;
import reduceExample.ElemwntList;

//����<String,int>(key,value)
//���ֵΪ���ֵ
//���ɽ���
//MaxRow
public class Example24 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
	
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	//Logger.println(context.getConfiguration(), "Reducing ts=" + timestamp);
		output.add(new TwoTuple(key,MaxValue(list.getList().iterator())));
    }

    public String MaxValue(Iterator<Element> value) {
    	int max = 0;
    	String maxStr = "";
    	Element element ;
    	while(value.hasNext()) {
			element = value.next();
    		int v = (int)element.getList().get(1);
    		if(v>max) {
    			max = v;
    			maxStr = element.getList().get(2).toString();
    		}
    	}
    	return maxStr;
    }
}
