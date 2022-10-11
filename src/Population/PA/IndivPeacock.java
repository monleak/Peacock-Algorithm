package Population.PA;

import Population.Individual;
import util.Params;

import java.util.ArrayList;
import java.util.Comparator;

public class IndivPeacock extends Individual {
    public double lekDis;
    public ArrayList<Eye> eyes;

    public IndivPeacock(Individual indiv){
        this.setDim(indiv.getDim());
        this.setChromosome(indiv.getChromosome());
        this.setFitness(indiv.getFitness());
        this.setID(indiv.getID());
        this.lekDis = 0;
        this.eyes.add(new Eye(this.getChromosome().clone(),this.getFitness()));
    }
    public void move(double[] Mating_Range){
        double[] newChromosome = new double[this.getDim()];
        try {
            for(int i=0;i<this.getDim();i++){
                newChromosome[i] = this.getGene(i) + (Params.random.nextDouble()-0.5)*Mating_Range[Params.random.nextInt(Mating_Range.length)-1];
            }
        }
        catch (NullPointerException e){
            System.err.println("Mating_Range null??? Peacock don't move: " + e.toString());
        }

        //Eyes: maintaining repository of positions traversed
        double newFitness = this.task.calculateFitnessValue(newChromosome);
        if(newFitness <= this.getFitness()){
            addEye(this.getChromosome().clone(),this.getFitness());

            this.setChromosome(newChromosome);
            this.setFitness(newFitness);
        }else {
            addEye(newChromosome,newFitness);
        }
    }
    public void addEye(double[] newChromosome, double newFitness){
        if(eyes.size() < Params.PARAM_NumberOfEyes){
            this.eyes.add(new Eye(newChromosome,newFitness));
        }else {
            sortEye();
            this.eyes.remove(eyes.size()-1);
            this.eyes.add(new Eye(newChromosome,newFitness));
        }
    }
    public void sortEye() {
        /*
          Sắp xếp lại các cá thể trong quần thể
         */
        this.eyes.sort(new Comparator<Eye>() {
            @Override
            public int compare(Eye o1, Eye o2) {
                return Double.valueOf(o1.Fitness).compareTo(o2.Fitness);
            }
        });
    }
}
