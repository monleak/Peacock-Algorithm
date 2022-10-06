package Population.PA;

import Population.Individual;
import util.Params;

public class IndivPeahen extends Individual {
    public IndivPeacock follow;
    public double A; //Độ hấp dẫn của con đực đối với con cái

    public IndivPeahen(Individual indiv){
        this.setDim(indiv.getDim());
        this.setChromosome(indiv.getChromosome());
        this.setFitness(indiv.getFitness());
        this.setID(indiv.getID());
        this.A = 0;
    }

    public void move(){
        for(int i=0;i<this.getDim();i++){
            this.setGene(i,this.getGene(i) + (follow.getGene(i)-this.getGene(i))* Params.random.nextDouble());
        }
    }
}
