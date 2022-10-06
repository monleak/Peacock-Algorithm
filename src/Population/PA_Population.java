package Population;

import Population.PA.IndivPeacock;
import Population.PA.IndivPeahen;
import benchmark.Task;
import util.Params;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Quản lý 1 quần thể.
 * Mỗi quần thể thực hiện 1 tác vụ riêng biệt.
 */
public class PA_Population {
    public ArrayList<Individual> individuals;
    public ArrayList<IndivPeacock> peacocks;
    public ArrayList<IndivPeahen> peahens;
    public Individual best;
    public Task task;

    public double[] Mating_Range; //Nếu kết quả không tốt cần sửa lại Mating_Range theo từng peacock

    public PA_Population(Task task){
        individuals = new ArrayList<Individual>();
        peacocks = new ArrayList<IndivPeacock>();
        peahens = new ArrayList<IndivPeahen>();
        this.task = task;
        Mating_Range = new double[]{(task.UB[0] - task.LB[0]) / 15, (task.UB[0] - task.LB[0]) / 17, (task.UB[0] - task.LB[0]) / 12, (task.UB[0] - task.LB[0]) / 20, (task.UB[0] - task.LB[0]) / 10};
    }
    public PA_Population(ArrayList<Individual> individuals, Task task){
        this.individuals = individuals;
        peacocks = new ArrayList<IndivPeacock>();
        peahens = new ArrayList<IndivPeahen>();
        this.task = task;
        Mating_Range = new double[]{(task.UB[0] - task.LB[0]) / 15, (task.UB[0] - task.LB[0]) / 17, (task.UB[0] - task.LB[0]) / 12, (task.UB[0] - task.LB[0]) / 20, (task.UB[0] - task.LB[0]) / 10};
    }

    public void randomInit(){
        int totalIndividual = Params.PARAM_NumberOfPeacocks + Params.PARAM_NumberOfPeahens; //tổng số cá thể của quần thể
        for(int i=0;i<totalIndividual;i++){
            Individual indiv = new Individual(task.dim);
            indiv.randomInit();
            indiv.setFitness(task.calculateFitnessValue(indiv.getChromosome()));
            individuals.add(indiv);
        }
    }
    public void setting(){
        //Chạy 1 lần khi khởi tạo quần thể

        sortPop();
        /*
          Lấy P cá thể tốt nhất làm peacock
          Các cá thể còn lại là peahen
         */
        best = individuals.get(0);
        for(int i=0;i<Params.PARAM_NumberOfPeacocks;i++){
            IndivPeacock peacock = new IndivPeacock(individuals.get(0));
            peacocks.add(peacock);
            individuals.remove(0);
        }
        for(int i=0;i<Params.PARAM_NumberOfPeahens;i++){
            IndivPeahen peahen = new IndivPeahen(individuals.get(0));
            peahens.add(peahen);
            individuals.remove(0);
        }
        individuals.clear();

        /*
            Tính toán lekDis cho Peacock và đặt follow cho Peahen
         */
        for(int i=0;i<peacocks.size();i++){
            peacocks.get(i).lekDis = lekDis(peacocks.get(i));
        }
        for(int i=0;i<peahens.size();i++){
            for(int j=0;j<peacocks.size();j++){
                double dis = calDistance(peahens.get(i),peacocks.get(j));
                double A = peacocks.get(j).getFitness()/dis;
                if(dis < peacocks.get(j).lekDis && peahens.get(i).A < A){
                    peahens.get(i).follow = peacocks.get(j);
                    peahens.get(i).A = A;
                }
            }
        }
    }
    public void sortPop() {
        /*
          Sắp xếp lại các cá thể trong quần thể
         */
        this.individuals.sort(new Comparator<Individual>() {
            @Override
            public int compare(Individual o1, Individual o2) {
                return Double.valueOf(o1.getFitness()).compareTo(o2.getFitness());
            }
        });
    }
    public double calDistance(Individual x1,Individual x2){
        /*
          Tính khoảng cách giữa 2 cá thể
         */
        double temp = 0;
        for(int i=0;i<task.dim;i++){
            temp += Math.pow(x1.getGene(i)-x2.getGene(i),2);
        }
        return Math.sqrt(temp);
    }
    public double lekDis(Individual peacock){
        /*
          Vùng ảnh hưởng của peacock

          Được tính bằng trung bình khoảng cách từ peacock
          đến các peahen khác
         */
        double temp = 0;
        try {
            for(int i=0;i<peahens.size();i++){
                temp += calDistance(peacock,peahens.get(i));
            }
            temp /= peahens.size();
        }
        catch (Exception e){
            return Double.MAX_VALUE;
        }
        return temp;
    }

    public void pushAway(){
        /*
            Nếu một peacock lại gần BEST thì nó sẽ bị đẩy ra
            Trong TH peacock và BEST có fitness tương tự nhau thì sẽ sử dụng công thức đb
         */
        for(int i=0;i<peacocks.size();i++){
            if(best.getID() != peacocks.get(i).getID()
            && calDistance(best,peacocks.get(i)) < Mating_Range[Params.random.nextInt(Mating_Range.length)-1]){
                if(peacocks.get(i).getFitness()/best.getFitness() >= 0.9){
                    for(int j=0;j<task.dim;j++){
                        peacocks.get(i).setGene(j,
                                Params.omega*peacocks.get(i).getGene(j) + (1-Params.omega)*best.getGene(j));
                    }
                }else{
                    for(int j=0;j<task.dim;j++){
                        peacocks.get(i).setGene(j,
                                peacocks.get(i).getGene(j)+(peacocks.get(i).getGene(j)-best.getGene(j))*calDistance(best,peacocks.get(i)));
                    }
                }
            }
        }
    }
    public void updateMating_Range(){
        /*
            Cập nhật Mating_Range nếu không có sự lai ghép nào trong thế hệ
         */
        for(int i=0;i<Mating_Range.length;i++){
            Mating_Range[i] = Mating_Range[i]*Params.PARAM_EvolutionFactor[i];
        }
    }
    public Individual laiGhep(IndivPeacock a,IndivPeahen b){
        Individual child = new Individual();
        for(int i=0;i< task.dim;i++){
            double rand = Params.random.nextDouble();
            if(rand<0.4)
                child.setGene(i,a.getGene(i));
            else if (rand<0.8)
                child.setGene(i,b.getGene(i));
            else
                child.setGene(i,a.eye[i]);

            //mutation
            if(Params.random.nextDouble() < 0.1){
                child.setGene(i,child.getGene(i)+(Params.random.nextDouble()-0.5)*Mating_Range[Params.random.nextInt(Mating_Range.length)-1]);
            }
        }
        return child;
    }
}
