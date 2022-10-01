package benchmark.function;

import benchmark.Function;

public class Rosenbrock extends Function {
    @Override
    public double getCost(double[] x){
        double result = 0;
        for(int i=0;i<this.dim-1;i++){
            result += 100*Math.pow(x[i+1]-x[i],2) + Math.pow(x[i]-1,2);
        }
        return result - this.Optimum;
    }
}
