package Population.PA;

import Population.Individual;
import util.Params;

import java.util.ArrayList;

public class IndivPeacock extends Individual {
    public double lekDis;
    public double[] eye;

    public IndivPeacock(Individual indiv){
        this.setDim(indiv.getDim());
        this.setChromosome(indiv.getChromosome());
        this.setFitness(indiv.getFitness());
        this.setID(indiv.getID());
        this.lekDis = 0;
        this.eye = this.getChromosome().clone();
    }
    public void move(double[] Mating_Range){
        for(int i=0;i<this.getDim();i++){
            try{
                this.setGene(i,this.getGene(i) + (Params.random.nextDouble()-0.5)*Mating_Range[Params.random.nextInt(Mating_Range.length)-1]);
            }
            catch (NullPointerException e){
                System.err.println("Mating_Range null??? Peacock don't move: " + e.toString());
            }
        }
    }
}
