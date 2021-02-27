package searchOnInternet;

import java.util.ArrayList;
import java.util.List;

import reduceExample.Element;
import reduceExample.ElemwntList;

//����<int,int>(key,value)
//��valueΪ1ʱ�������Լ�reputation��ֵ
//���ɽ���
//SingleItem
public class Example14 {
	
	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);
        int postsNumber = 0;
        int reputation = 0;
        String authorId = key.toString();

        for (Element value : list.getList()) {

            int intValue = Integer.parseInt((String) value.getList().get(1)) ;
            if (intValue == 1) {
                postsNumber ++;
            }
            else {
                // we subtract two because of the comment on lines 80/81
                reputation = intValue -2;
            }
        }

        v=(reputation + "\t" + postsNumber);
        output.add(new TwoTuple(authorId, v));
    }
}
