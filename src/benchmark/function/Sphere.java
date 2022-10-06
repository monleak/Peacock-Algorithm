package benchmark.function;

import benchmark.Function;

public class Sphere extends Function {
    @Override
    public double getCost(double[] x){
        double result = 0;
        for(int i=0;i<x.length;i++){
            result += Math.pow(x[i],2);
        }
        return result - this.Optimum;
    }
}
