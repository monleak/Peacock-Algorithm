package benchmark.function;

import benchmark.Function;

public class Ackley extends Function {
    @Override
    public double getCost(double[] x){
        double result = 0;
        double sum1 = 0,sum2 =0;
        for(int i=0;i<this.dim;i++){
            sum1+=Math.pow(x[i],2);
            sum2+=Math.cos(2* Math.PI*x[i]);
        }
        result = -20 * Math.exp(-0.2*Math.sqrt(sum1/this.dim)) - Math.exp(sum2/this.dim) +20+Math.E;
        return result - this.Optimum;
    }
}
