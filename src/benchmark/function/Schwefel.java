package benchmark.function;

import benchmark.Function;
import util.Params;

public class Schwefel extends Function {
    @Override
    public double getCost(double[] x){
        double result = 0;
        for(int i=0;i<x.length;i++){
            result += x[i]*Math.sin(Math.sqrt(Math.abs(x[i])));
        }
        return result*(-1) - this.Optimum;
    }
}
