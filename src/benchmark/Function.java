package benchmark;

public abstract class Function {
    public double Optimum; //Điểm tối ưu

    public Function(){

    }
    public Function(double optimum) {
        this.Optimum = optimum;
    }

    public abstract double getCost(double[] solution);
}
