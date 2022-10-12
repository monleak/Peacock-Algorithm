package Population;

import Population.PA.IndivPeacock;
import Population.PA.IndivPeahen;
import benchmark.Task;
import benchmark.TaskManager;
import util.Params;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Quản lý 1 quần thể.
 * Mỗi quần thể thực hiện 1 tác vụ riêng biệt.
 */
public class PA_Population {
    public TaskManager taskManager;
    public int taskIndex;

    public ArrayList<Individual> individuals;
    public ArrayList<IndivPeacock> peacocks;
    public ArrayList<IndivPeahen> peahens;
    public Individual best;

    public double[] Mating_Range; //Nếu kết quả không tốt cần sửa lại Mating_Range theo từng peacock

    public PA_Population(TaskManager taskManager, int task_id){
        individuals = new ArrayList<Individual>();
        peacocks = new ArrayList<IndivPeacock>();
        peahens = new ArrayList<IndivPeahen>();
        this.taskIndex = task_id;
        this.taskManager = taskManager;
        Mating_Range = new double[]{(taskManager.getTask(taskIndex).UB[0] - taskManager.getTask(taskIndex).LB[0]) / 15, (taskManager.getTask(taskIndex).UB[0] - taskManager.getTask(taskIndex).LB[0]) / 17, (taskManager.getTask(taskIndex).UB[0] - taskManager.getTask(taskIndex).LB[0]) / 12, (taskManager.getTask(taskIndex).UB[0] - taskManager.getTask(taskIndex).LB[0]) / 20, (taskManager.getTask(taskIndex).UB[0] - taskManager.getTask(taskIndex).LB[0]) / 10};
    }
    public PA_Population(ArrayList<Individual> individuals, TaskManager taskManager, int task_id){
        this.individuals = individuals;
        peacocks = new ArrayList<IndivPeacock>();
        peahens = new ArrayList<IndivPeahen>();
        this.taskIndex = task_id;
        this.taskManager = taskManager;
        Mating_Range = new double[]{(taskManager.getTask(taskIndex).UB[0] - taskManager.getTask(taskIndex).LB[0]) / 15, (taskManager.getTask(taskIndex).UB[0] - taskManager.getTask(taskIndex).LB[0]) / 17, (taskManager.getTask(taskIndex).UB[0] - taskManager.getTask(taskIndex).LB[0]) / 12, (taskManager.getTask(taskIndex).UB[0] - taskManager.getTask(taskIndex).LB[0]) / 20, (taskManager.getTask(taskIndex).UB[0] - taskManager.getTask(taskIndex).LB[0]) / 10};
    }

    public void randomInit(){
        int totalIndividual = Params.PARAM_NumberOfPeacocks + Params.PARAM_NumberOfPeahens; //tổng số cá thể của quần thể
        for(int i=0;i<totalIndividual;i++){
            Individual indiv = new Individual(taskManager.DIM,taskManager.getTask(taskIndex));
            indiv.randomInit();
            indiv.setFitness(taskManager.getTask(taskIndex).calculateFitnessValue(indiv.getChromosome()));
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
                try {
                    if(dis < peacocks.get(j).lekDis && peahens.get(i).follow.getFitness()/calDistance(peahens.get(i),peahens.get(i).follow) < A){
                        peahens.get(i).follow = peacocks.get(j);
                    }
                }catch (Exception e){
                    peahens.get(i).follow = peacocks.get(j);
                }
            }
        }
    }
    public void update(){
        //TODO: Check số thế hệ không lai ghép của peacock
        //TODO: Tăng countGeneration của peacock nếu không xảy ra peacock
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
        for(int i=0;i<taskManager.getTask(taskIndex).dim;i++){
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
                    for(int j=0;j<taskManager.getTask(taskIndex).dim;j++){
                        peacocks.get(i).setGene(j,
                                Params.omega*peacocks.get(i).getGene(j) + (1-Params.omega)*best.getGene(j));
                    }
                }else{
                    for(int j=0;j<taskManager.getTask(taskIndex).dim;j++){
                        peacocks.get(i).setGene(j,
                                peacocks.get(i).getGene(j)+(peacocks.get(i).getGene(j)-best.getGene(j))*calDistance(best,peacocks.get(i)));
                    }
                }
            }
        }
    }
    public void noSuccessScenario(){
        /*
            Kiểm tra số thế hệ không lai ghép của peacock
         */
        for(int i=0;i<peacocks.size();i++){
            if(peacocks.get(i).countGeneration > Params.G){
                updateMating_Range(i);
            }
        }
    }
    public void updateMating_Range(int idPeacock){
        /*
            Cập nhật Mating_Range nếu không có sự lai ghép nào sau G thế hệ
         */
        Mating_Range[idPeacock] = Mating_Range[idPeacock]*Params.PARAM_EvolutionFactor[Params.random.nextInt(Params.PARAM_EvolutionFactor.length)-1];
    }
    public int Attractiveness(IndivPeahen peahen){
        double currentA = peahen.follow.getFitness()/calDistance(peahen,peahen.follow);
        for(int i=0;i<peacocks.size();i++){
            double newA = peacocks.get(i).getFitness()/calDistance(peahen,peacocks.get(i));
            if(newA > currentA){
                return peacocks.get(i).getID();
            }
        }
        return peahen.follow.getID();
    }
    public int findPeacock(IndivPeacock a){
        for(int i=0;i<peacocks.size();i++){
            if(a.getID() == peacocks.get(i).getID())
                return i;
        }
        return -1;
    }
    public void checkCrossover(){
        /*
            Kiểm tra xem peahen có đồng ý lai ghép với peacock không ?

            Nếu không peahen sẽ chuyển hướng qua một peacock khác dựa trên độ
            hấp dẫn A

            Nếu có thì sinh ra cá thể child (Quyết định giới tính dựa trên fitness)
         */
        for(int i=0;i<peahens.size();i++){
            try{
                if(calDistance(peahens.get(i),peahens.get(i).follow) < peahens.get(i).follow.lekDis*Mating_Range[findPeacock(peahens.get(i).follow)]
                        && Attractiveness(peahens.get(i)) == peahens.get(i).follow.getID()){
                    //TODO: Đồng ý lai ghép
                    Individual child = Crossover(peahens.get(i).follow,peahens.get(i));

                }else {
                    //TODO: Không đồng ý lai ghép
                    peahens.get(i).follow = peacocks.get(Attractiveness(peahens.get(i)));
                }
            }catch (Exception e){
                System.err.println("Lỗi ở hàm checkCrossover: "+e.getMessage());
            }
        }
    }
    public Individual Crossover(IndivPeacock a,IndivPeahen b){
        Individual child = new Individual();
        for(int i=0;i< taskManager.getTask(taskIndex).dim;i++){
            double rand = Params.random.nextDouble();
            if(rand<0.4)
                child.setGene(i,a.getGene(i));
            else if (rand<0.8)
                child.setGene(i,b.getGene(i));
            else
                child.setGene(i,a.eyes.get(Params.random.nextInt(a.eyes.size()-1)).Chromosome[i]);

            //mutation
            if(Params.random.nextDouble() < 0.1){
                child.setGene(i,child.getGene(i)+(Params.random.nextDouble()-0.5)*Mating_Range[Params.random.nextInt(Mating_Range.length)-1]);
            }
        }
        child.setID(Individual.counter++);
        child.task = taskManager.getTask(taskIndex);
        child.setDim(taskManager.DIM);
        child.setFitness(taskManager.getTask(taskIndex).calculateFitnessValue(child.getChromosome()));
        return child;
    }
}
