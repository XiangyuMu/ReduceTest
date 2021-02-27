package ReliableVariableExtraction;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExtractRV {

    public static String filepath = "src/main/java/searchOnInternet/Example31.java";



    private static class ExpressVisitor extends VoidVisitorAdapter<List>{
        @Override
        public void visit(ForStmt n, List arg) {
            super.visit(n, arg);
            System.out.println("ForStmt:"+n);
        }


        @Override
        public void visit(ForEachStmt n, List arg) {
            super.visit(n, arg);
            System.out.println("ForEachStmt:"+n.getIterable());
            if (hasKeyWord(n.getIterable(),arg)){
                if (!arg.contains(n.getVariable().getVariable(0).getName().toString().trim())){
                    arg.add(n.getVariable().getVariable(0).getName().toString().trim());
                }

            }

        }

        @Override
        public void visit(AssignExpr n, List arg) {
            super.visit(n, arg);
            System.out.println("AssignStmt:"+n);
            System.out.println("AssignTargetType: "+n.getTarget().getMetaModel());
            if (hasKeyWord(n.getValue(),arg)){

                if (!arg.contains(getSingleKeyWord(n.getTarget()))){
                    arg.add(getSingleKeyWord(n.getTarget()));
                }

            }
        }

        @Override
        public void visit(VariableDeclarationExpr n, List arg) {
            super.visit(n, arg);
            System.out.println("VariableDeclarationExpr: "+n.getVariable(0).getInitializer());
            if (n.getVariable(0).getInitializer().toString()!="Optional.empty"){
                Expression expression = n.getVariable(0).getInitializer().get();
                if (hasKeyWord(expression,arg)){

                    if (!arg.contains(getSingleKeyWord(n.getVariable(0).getNameAsExpression()))){
                        arg.add(getSingleKeyWord(n.getVariable(0).getNameAsExpression()));
                    }

                }
            }
        }

        @Override
        public void visit(MethodCallExpr n, List arg) {
            super.visit(n, arg);
            System.out.println("MethodCallExpr: "+n.getScope().get());
            System.out.println("scope: "+getMethodScope(n));
            for (int i = 0;i<n.getArguments().size();i++){
                System.out.println("Argument: "+hasKeyWord(n.getArguments().get(i),arg));
                if (hasKeyWord(n.getArguments().get(i),arg)){
                    if (!arg.contains(getMethodScope(n).trim())&&getMethodScope(n)!=""){
                        if(!(getMethodScope(n).equals("Float")||getMethodScope(n).equals("Integer")||getMethodScope(n).equals("String")||getMethodScope(n).equals("Char")||getMethodScope(n).equals("Boolean")||getMethodScope(n).equals("System.out")))
                            arg.add(getMethodScope(n).trim());
                    }
                }
            }
        }


        public String getSingleKeyWord(Expression e){
            {
                Stack<Node> stack = new Stack<>();
                for(int i = 0;i<e.getChildNodes().size();i++){
                    stack.add(e.getChildNodes().get(i));
                }
                while(!stack.isEmpty()){
                    Node node = stack.pop();
                    if(node.getChildNodes().size()==0){
                        if(node.getMetaModel().toString().equals("NameExpr")||node.getMetaModel().toString().equals("SimpleName")){
                            if(!(node.toString().equals("Float")||node.toString().equals("Integer")||node.toString().equals("String")||node.toString().equals("Char")||node.toString().equals("Boolean")||node.toString().equals("System.out"))){
                                System.out.println("Stack: "+node.toString());
                                return node.toString();
                            }

                        }
                    }else{
                        for(int j = 0;j<node.getChildNodes().size();j++){
                            stack.add(node.getChildNodes().get(j));
                        }
                    }
                }
                return "ERROR!";
            }
        }

        public boolean hasKeyWord(Expression e,List<String> keyWordList){
            Stack<Node> stack = new Stack<>();
            for(int i = 0;i<e.getChildNodes().size();i++){
                stack.add(e.getChildNodes().get(i));
            }
            while(!stack.isEmpty()){
                Node node = stack.pop();
                if(node.getChildNodes().size()==0){
                    if(node.getMetaModel().toString().equals("NameExpr")||node.getMetaModel().toString().equals("SimpleName")){
                        if(!(node.toString().equals("Float")||node.toString().equals("Integer")||node.toString().equals("String")||node.toString().equals("Char")||node.toString().equals("Boolean")||node.toString().equals("System.out"))){
                            System.out.println("Stack: "+node.toString());
                            System.out.println("keyWordList: "+keyWordList);
                            if (keyWordList.contains(node.toString().trim())){
                                return true;
                            }
                        }

                    }
                }else{
                    for(int j = 0;j<node.getChildNodes().size();j++){
                        stack.add(node.getChildNodes().get(j));
                    }
                }
            }
            return false;
        }
    }

    public static String getMethodScope(MethodCallExpr n){
        if (n.getScope().get().isMethodCallExpr()){
            return getMethodScope(n.getScope().get().asMethodCallExpr());
        }
        else if (n.getScope().get().isNameExpr()){
            return n.getScope().get().toString();
        }
        else {
            return "";
        }
    }


    public void printToFile(String filename,List<String> list) throws IOException {
        File file = new File("src/main/java/keyWord/"+filename+".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (String keyWord : list){
            writer.write(keyWord);
            writer.write("\n");
        }
        writer.close();
    }


    public List<String> readFromFile(String filename) throws IOException {
        List<String> list = new ArrayList<>();
        File file = new File("keyWord/"+filename+".txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String keyWord = reader.readLine();
        while (keyWord!=null){
            list.add(keyWord);
            keyWord = reader.readLine();
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        CompilationUnit cu = StaticJavaParser.parse(new File(filepath));

        List<String> ms = new ArrayList<>();
        ms.add("list");
        cu.accept(new ExpressVisitor(),ms);
        int formerMs = 0;
        while(ms.size()!=formerMs){
            formerMs = ms.size();
            cu.accept(new ExpressVisitor(),ms);

        }
        ExtractRV extractRV = new ExtractRV();
        extractRV.printToFile("Example31",ms);
        System.out.println("ms: "+ms);
    }
}
