package searchOnInternet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import reduceExample.Element;
import reduceExample.ElemwntList;

//����<String,String>(key,value)
//����key��Ӧ��ip�ĸ���
//���ɽ���
//�������У�������SingleItem
public class Example03 {
	
	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	public void reduce(ElemwntList list) {
        //ʹ��set���ϼ��Զ���ipͳ��
		list.getList().remove(1);
//		String key = (String)list.getList().get(0).getList().get(0).getAtom();
		String key = "1";
        Set<String> ips = new HashSet<String>();

        for (Element ip : list.getList()) {
            ips.add(ip.getList().get(1).toString());
        }
        int count =ips.size();
        output.add(new TwoTuple(key,""+count));
    }

}
