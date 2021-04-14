import java.util.ArrayList;
import java.util.List;

public class FirstNTestCaseTest {

    public void firstN(List<String> list,int m,int n,int M,int N,int[] b){

        List<String> stringList = new ArrayList<>();
        int i,j;
        for(i=n;i<=m;i++) {
            b[n-1] = i-1;
            if(n>1) {
                firstN(list,i - 1, n - 1,M,N,b);
            }else {
                List<String> op= new ArrayList<>();
                for(j=0;j<=N-1;j++){
                    op.add(list.get(b[j])+"");

                }
                System.out.println(op);
            }
        }

    }


    public static void main(String[] args) {
        FirstNTestCaseTest firstNTestCaseTest = new FirstNTestCaseTest();
        List<String> list = new ArrayList<>();
        for (int i  = 0;i<10;i++){
            list.add(String.valueOf(i));
        }
        int[] b = new int[3];
        firstNTestCaseTest.firstN(list,10,3,10,3,b);
    }

}

