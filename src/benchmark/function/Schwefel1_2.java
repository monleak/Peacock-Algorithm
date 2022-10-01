package benchmark.function;

import benchmark.Function;

public class Schwefel1_2 extends Function {
    @Override
    public double getCost(double[] x){
        double result = 0;
        for(int i=0;i<this.dim;i++){
            double sum = 0;
            for (int j=0;j<=i;j++){
                sum += x[j];
            }
            result += Math.pow(sum,2);
        }
        return result - this.Optimum;
    }
}
