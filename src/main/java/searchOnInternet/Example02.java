package searchOnInternet;

import java.util.ArrayList;
import java.util.List;

import reduceExample.Element;
import reduceExample.ElemwntList;

//输入<String,int>(key,value)
//将value的值进行累加
//不可交换
//去掉了值为1的v
public class Example02 {

	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}

	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	public void reduce(ElemwntList list) {

		String key = (String)list.getList().get(0).getList().get(0);
        Integer sum = 0;
        for (Element i : list.getList()) {
        	System.out.println(i.getList().get(1));
			if (Integer.parseInt(i.getList().get(1).toString())==1){
				continue;
			}
            sum += Integer.parseInt(i.getList().get(1).toString());

        }
        output.add(new TwoTuple(key, sum.toString()));
	}

}
