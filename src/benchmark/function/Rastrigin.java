package benchmark.function;

import benchmark.Function;

public class Rastrigin extends Function {
    @Override
    public double getCost(double[] x){
        double result = 0;
        for (int i=0;i<x.length;i++){
            result += Math.pow(Math.pow(x[i],2) - 10*Math.cos(2*Math.PI*x[i]) +10,2);
        }
        return result - this.Optimum;
    }
}
