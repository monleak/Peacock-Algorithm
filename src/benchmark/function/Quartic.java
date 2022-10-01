package benchmark.function;

import benchmark.Function;
import util.Params;

public class Quartic extends Function {
    @Override
    public double getCost(double[] x){
        double result = 0;
        for(int i=0;i<this.dim;i++){
            result += i*Math.pow(x[i],4);
        }
        return result + Params.random.nextDouble() - this.Optimum;
    }
}
