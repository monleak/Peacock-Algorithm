package benchmark.function;

import benchmark.Function;

public class Six_Hump_Camel extends Function {
    @Override
    public double getCost(double[] x){
        double result = 0;
        result = 4*Math.pow(x[0],2) - 2.1*Math.pow(x[0],4) + Math.pow(x[0],6)/3 + x[0]*x[1] - 4*Math.pow(x[1],2) + 4*Math.pow(x[1],4);
        return result - this.Optimum;
    }
}
