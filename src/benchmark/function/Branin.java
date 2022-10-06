package benchmark.function;

import benchmark.Function;

public class Branin extends Function {
    @Override
    public double getCost(double[] x){
        double result = 0;
        result = Math.pow(x[1] - 5.1*x[0]/(4*Math.PI*Math.PI)+5*x[0]/Math.PI-6,2) + 10*(1-1/(8*Math.PI))*Math.cos(x[0]) + 10;
        return result - this.Optimum;
    }
}
