package searchOnInternet;

import java.util.ArrayList;
import java.util.List;

import reduceExample.Element;
import reduceExample.ElemwntList;

//输入<int,int>(key,value1,value2)
//输出为最小值
//不可交换
//MaxRow
public class Example13 {

	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	
	
	
	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	String v ="";
	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0);
        int max = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        int count = 0;
        int maxStr = 0;
        int minStr = 0;
        for (Element value : list.getList()) {
        	//System.out.println(value.getList().get(1));
            if (max < Integer.parseInt((String) value.getList().get(1))) {
                max = Integer.parseInt((String) value.getList().get(1));
                maxStr = Integer.parseInt((String) value.getList().get(2));
            }
            if (min > Integer.parseInt((String) value.getList().get(1))) {
                min = Integer.parseInt((String) value.getList().get(1));
                minStr = Integer.parseInt((String) value.getList().get(2));
            }
            sum += Integer.parseInt((String) value.getList().get(1));
            count++;
        }
        v=(sum / count)+" "+maxStr+" "+minStr;

        output.add(new TwoTuple(key, v+""));
	}


}
