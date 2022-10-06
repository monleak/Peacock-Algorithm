package benchmark.function;

import benchmark.Function;

public class Schwefel2_22 extends Function {
    @Override
    public double getCost(double[] x){
        double sum = 0;
        double mul = 1;
        for (int i=0;i<x.length;i++) {
            sum += Math.abs(x[i]);
            mul *= Math.abs(x[i]);
        }
        return sum + mul - this.Optimum;
    }
}
