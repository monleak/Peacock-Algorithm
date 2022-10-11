package core;

import Population.PA_Population;
import benchmark.TaskManager;
import util.Params;

import java.util.ArrayList;

public class Peacock_Algorithm {
    public ArrayList<PA_Population> pops;
    public TaskManager taskManager;

    public Peacock_Algorithm(TaskManager taskManager){
        this.taskManager = taskManager;
        this.pops = new ArrayList<PA_Population>();
        for (int i=0;i<taskManager.TASKS_NUM;i++){
            //Khởi tạo quần thể cho từng tác vụ
            PA_Population sub_pop = new PA_Population(taskManager,i);
            pops.add(sub_pop);
        }
    }
    public double[] run1(double[][] mem_f){
        /*
            Trường hợp không có sự trao đổi thông tin giữa các tác vụ
         */
        Params.countEvals = 0;
        Params.maxEvals = Params.PARAM_NumberOfFunctionEvaluations * taskManager.TASKS_NUM;

        double[] result = new double[taskManager.TASKS_NUM];
        int recordCounter = 0; //Biến đếm số lần ghi lại kết quả
        int evalsPerRecord = Params.maxEvals / Params.recordsNum;

        for (int i=0; i<taskManager.TASKS_NUM;i++){
            pops.get(i).randomInit();
            pops.get(i).setting();
        }

        if (Params.countEvals >= (recordCounter + 1) * evalsPerRecord) {
            //Lấy giá trị tốt nhất của từng thế hệ
            for (int i = 0; i < taskManager.TASKS_NUM; i++) {
                mem_f[i][recordCounter] = pops.get(i).best.getFitness();
            }
            recordCounter++;
        }

        int gen=0; //Biến đếm thế hệ
        boolean stop = false;
        while (Params.countEvals < Params.maxEvals && !stop){
            stop = true;
            for (int i = 0; i < taskManager.TASKS_NUM; i++){
                if (pops.get(i).best.getFitness() < Params.EPSILON) {
                    //nếu đạt giá trị tối ưu ==> bỏ qua k chạy nữa
                    continue;
                }
                stop=false;

                //TODO: Viết xử lý tác vụ
                //Peacock move
                for(int idPeacock=0;idPeacock < pops.get(i).peacocks.size();idPeacock++){
                    pops.get(i).peacocks.get(idPeacock).move(pops.get(i).Mating_Range);
                }
                //Peahen move
                for(int idPeahen=0;idPeahen < pops.get(i).peahens.size();idPeahen++){
                    pops.get(i).peahens.get(idPeahen).move();
                }

                while (Params.countEvals >= (recordCounter + 1) * evalsPerRecord) {
                    for (int j = 0; j < taskManager.TASKS_NUM; j++) {
                        mem_f[j][recordCounter] = pops.get(j).best.getFitness();
                    }
                    recordCounter++;
                }
            }
            gen++;
        }
        while (recordCounter < Params.recordsNum) {
            for (int i = 0; i < taskManager.TASKS_NUM; i++) {
                mem_f[i][recordCounter] = pops.get(i).best.getFitness();
            }
            recordCounter++;
        }
        for(int i=0;i<taskManager.TASKS_NUM;i++){
            System.out.println(i + 1 + ":\t" + String.format("%.6f", pops.get(i).best.getFitness()));
            result[i] = pops.get(i).best.getFitness();
        }
        System.out.println("-------------------------");
        return result;
    }
}
