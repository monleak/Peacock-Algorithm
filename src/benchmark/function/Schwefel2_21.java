package benchmark.function;

import benchmark.Function;

public class Schwefel2_21 extends Function {
    @Override
    public double getCost(double[] x){
        double max = -1;
        for(int i=0;i<this.dim;i++){
            max = Math.max(max,Math.abs(x[i]));
        }
        return max - this.Optimum;
    }
}
