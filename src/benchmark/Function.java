package benchmark;

public abstract class Function {
    public double Optimum; //Điểm tối ưu

    public abstract double getCost(double[] solution);
}
