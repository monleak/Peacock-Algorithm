package benchmark;

public abstract class Function {
    public int dim;	// dimension
    public double[] LB;
    public double[] UB;

    public double Optimum; //Điểm tối ưu

    public Function(){

    }
    public Function(int dim, double[] lb, double[] ub, double optimum) {
        this.dim = dim;
        this.UB = ub;
        this.LB = lb;
        this.Optimum = optimum;
    }

    public abstract double getCost(double[] solution);
}
