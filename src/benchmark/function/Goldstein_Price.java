package benchmark.function;

import benchmark.Function;

public class Goldstein_Price extends Function {
    @Override
    public double getCost(double[] x){
        double result = 0;
        result = (1+ Math.pow(x[0]+x[1]+1,2)*(19-14*x[0]+3*x[0]*x[0]-14*x[1]+6*x[0]*x[1]+3*x[1]*x[1]))*(30+Math.pow(2*x[0]+1-3*x[1],2)*(18-32*x[0]+12*x[0]*x[0]+48*x[1]-36*x[0]*x[1]+27*x[1]*x[1]));
        return result - this.Optimum;
    }
}
