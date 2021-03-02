package BytemanProgram;
import ReliableVariableExtraction.ExtractRV;
import org.jboss.byteman.rule.Rule;
import org.jboss.byteman.rule.helper.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BytemanHelper extends Helper {
    protected BytemanHelper(Rule rule){
        super(rule);
    }

    public void initFunc() throws IOException {
        createLinkMap("KeyWordMap");
        createLinkMap("SubstitutionMap");

        createCounter("ValueNum",1);
        List<String> lk = new ExtractRV().readFromFile("Example31");
        traceln("lk: "+lk);
        for (String keyWord:lk){
            link("KeyWordMap",keyWord,new ArrayList<String>());
            link("SubstitutionMap",keyWord,new ArrayList<String>());
        }
        for (String Word:lk){
            traceln("KeyWordMap "+linked("KeyWordMap",Word));
        }
    }


    public void initList(){
        List<String> valueList = (List<String>) linked("KeyWordMap","list");
        valueList.add("v"+readCounter("ValueNum"));
        incrementCounter("ValueNum");
        traceln("valueList: "+linked("KeyWordMap","list"));


    }


    public  void valueTransform(String Target,String Older,String line){
        List<String> olderList = (List<String>) linked("KeyWordMap",Older);
        List<String> TargetList = (List<String>) linked("KeyWordMap",Target);
        List<String> SubsList = (List<String>) linked("SubstitutionMap",Target);
        for (String sub: TargetList){
            if (!SubsList.contains(sub)){
                SubsList.add(sub);
            }
        }
        TargetList.clear();
        if (readCounter(line)>=olderList.size()){
            readCounter(line,true);
        }
        TargetList.add(olderList.get(readCounter(line)));
        for (String s:TargetList){
            if (SubsList.contains(s)){
                SubsList.remove(s);
            }
        }
        incrementCounter(line);
        traceln(Target+" TargetList: "+linked("KeyWordMap",Target));
    }


    public void valueTransformUnion(String Target,String Older){
        List<String> olderList = (List<String>) linked("KeyWordMap",Older);
        List<String> TargetList = (List<String>) linked("KeyWordMap",Target);
        for (String olderValue : olderList){
            if (!TargetList.contains(olderValue)){
                TargetList.add(olderValue);
            }
        }
        traceln(Target+" TargetList: "+linked("KeyWordMap",Target));
    }

    public void valueTransformCover(String Target,String Older){
        List<String> olderList = (List<String>) linked("KeyWordMap",Older);
        List<String> TargetList = (List<String>) linked("KeyWordMap",Target);
        List<String> SubsList = (List<String>) linked("SubstitutionMap",Target);
        System.out.println("TargetList: "+TargetList);
    if (TargetList.size()!=0){
        for (String sub: TargetList){
            if (!SubsList.contains(sub)){
            SubsList.add(sub);
            }
        }
    }

        TargetList.clear();
        for (String olderValue : olderList){
            if (!TargetList.contains(olderValue)){
                TargetList.add(olderValue);
            }
        }
        for (String s:TargetList){
            if (SubsList.contains(s)){
                SubsList.remove(s);
            }
        }
        traceln(Target +" TargetList: "+linked("KeyWordMap",Target));
    }
}
