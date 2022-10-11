package Population.PA;

public class Eye implements Comparable<Eye>{
    public double[] Chromosome;
    public double Fitness;

    public Eye(double[] x,double y){
        Chromosome = x;
        Fitness = y;
    }
    @Override
    public int compareTo(Eye o) {
        return Double.valueOf(this.Fitness).compareTo(o.Fitness);
    }
}
