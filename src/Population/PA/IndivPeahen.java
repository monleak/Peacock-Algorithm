package Population.PA;

import Population.Individual;
import util.Params;

public class IndivPeahen extends Individual {
    public IndivPeacock follow;

    public IndivPeahen(Individual indiv){
        this.setDim(indiv.getDim());
        this.setChromosome(indiv.getChromosome());
        this.setFitness(indiv.getFitness());
        this.setID(indiv.getID());
    }

    public void move(){
        for(int i=0;i<this.getDim();i++){
            this.setGene(i,this.getGene(i) + (follow.getGene(i)-this.getGene(i))* Params.random.nextDouble());
        }

        //Nếu peahen bắt đc 1 vị trí tốt hơn follow, vị trí đó được thêm vào eye lưu trũ trong follow
        if(this.getFitness() < follow.getFitness()){
            follow.addEye(this.getChromosome().clone(),this.getFitness());
        }
    }
}
