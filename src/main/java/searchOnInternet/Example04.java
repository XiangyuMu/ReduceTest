package searchOnInternet;

import java.util.ArrayList;
import java.util.List;

import reduceExample.Element;
import reduceExample.ElemwntList;

//����<String,String>(key,value)
//��value��ֵͨ���� ������
//���ɽ�����ȷ����
//Strconcat
public class Example04 {

	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0);
        StringBuilder totalStr = new StringBuilder();
        for (Element value : list.getList()) {
            totalStr.append(value.getList().get(1).toString()).append(" ");
        }
        String v =totalStr.toString();
        output.add(new TwoTuple(key, v));
    }



}
