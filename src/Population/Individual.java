package Population;

import benchmark.Task;
import util.Params;

public class Individual implements Comparable<Individual> {

    public static int counter = 0; //Đếm id của cá thể
    private int individual_id;

    private int DIM; //số chiều trong không gian chung
    private double[] chromosome; //Gen
    private double fitness;
    public Task task;
    public Individual(){

    }
    public Individual(int dim,Task task) {
        this.individual_id = Individual.counter++;
        this.DIM = dim;
        this.chromosome = new double[dim];
        this.fitness = Double.MAX_VALUE;
        this.task = task;
    }

    public Individual(double[] chromosome) {
        this.individual_id = Individual.counter++;
        this.DIM = chromosome.length;
        this.chromosome = chromosome.clone();
        this.fitness = Double.MAX_VALUE;
    }

    public void randomInit() {
        for (int i = 0; i < DIM; i++) {
            this.chromosome[i] = Params.random.nextDouble();
        }
    }

    public void setFitness(double value) {
        this.fitness = value;
    }

    public double getFitness() {
        return this.fitness;
    }

    public int getID() {
        return this.individual_id;
    }
    public void setID(int ID){
        this.individual_id = ID;
    }

    public int getDim() {
        return DIM;
    }

    public void setDim(int dim) {
        this.DIM = dim;
    }

    public double[] getChromosome() {
        return chromosome;
    }

    public void setChromosome(double[] chromosome) {
        this.chromosome = chromosome;
    }

    public double getGene(int index) {
        return this.chromosome[index];
    }

    public void setGene(int pos, double val) {
        this.chromosome[pos] = Math.max(0, Math.min(1, val));
    }

    @Override
    public int compareTo(Individual o) {
        return Double.valueOf(this.fitness).compareTo(o.fitness);
    }
}

