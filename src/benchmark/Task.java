package benchmark;

import benchmark.Function;
import util.Params;

public class Task {
    public int task_id;
    public Function function;
    public int dim;
    public double[] UB;
    public double[] LB;

    public Task(int task_id, Function function) {
        this.task_id = task_id;
        this.function = function;
    }

    public Task(int task_id) {
        this.task_id = task_id;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public double calculateFitnessValue(double[] x) {

        if (Params.countEvals > Params.maxEvals) {
            return Double.MAX_VALUE;
        }
        Params.countEvals++;

        double[] de_normalized = new double[dim]; //Dãy số thu được sau khi mã hóa NST từ không gian chung ra không gian riêng
        for (int i = 0; i < dim; i++) {
            de_normalized[i] = x[i] * (UB[i] - LB[i]) + LB[i];
        }

        double f = function.getCost(de_normalized);
        return f;
    }

}
