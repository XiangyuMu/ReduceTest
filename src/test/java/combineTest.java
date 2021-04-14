public class combineTest {

    public int[] combine( int a[], int n, int m,  int b[],  int M )
    {

        for(int i=n; i>=m; i--)   // 注意这里的循环范围
        {
            b[m-1] = i - 1;
            if (m > 1)
                combine(a,i-1,m-1,b,M);

        }
        return b;
    }

    public static void main(String[] args) {
        int[] a  = new int[3628800];
        for (int i = 0;i<3628800;i++){
            a[i] = i;
        }

        int[] b = new int[(int)(3628800*0.1)];
        combineTest combineTest = new combineTest();

        System.out.println(combineTest.combine(a,3628800,(int)(3628800*0.1),b,(int)(3628800*0.1)));
    }

}
