package benchmark.function;

import benchmark.Function;

public class Griewank extends Function {
    @Override
    public double getCost(double[] x){
        double result = 0;
        double sum=0,mul=1;
        for(int i=0;i<this.dim;i++){
            sum+=Math.pow(x[i]-100,2);
            mul*=Math.cos((x[i]-100)/Math.sqrt(i));
        }
        result = sum/4000 - mul +1;
        return result - this.Optimum;
    }
}
