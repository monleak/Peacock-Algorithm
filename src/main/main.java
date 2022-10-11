package main;

import benchmark.TaskManager;
import benchmark.getBenchmark;
import core.Peacock_Algorithm;
import util.Params;

import java.io.*;
import java.util.Random;

public class main {
    /** Có in hướng dẫn ra màn hình hay không ?. */
    public static final String A_HELP = "-help";
    /**Chạy chương trình từ file save trước đó*/
    public static final String A_CHECKPOINT = "-checkpoint";


    public static void checkForHelp(String[] args){
        for (String arg : args)
            if (arg.equals(A_HELP)) {
                System.err.println(
                        """
                                HƯỚNG DẪN:
                                -help                   In ra hướng dẫn.

                                -checkpoint CHECKPOINT  Tiếp tục chạy chương trình từ 1 file save.
                                """
                );
                System.exit(1);
            }
    }
    public static boolean checkForCheckpoint(String[] args){
        //TODO: Load file check point để tiếp tục chạy
        for (String arg : args)
            if (arg.equals(A_CHECKPOINT)) {
                System.err.println("LOAD CHECKPOINT...");
                return true;
            }
        return false;
    }
    public static void main(String[] args) throws IOException {
        checkForHelp(args);
        checkForCheckpoint(args);

        TaskManager taskManager = new TaskManager(getBenchmark.getBenchmark());
        double[][][] mem_f = new double[Params.REPT][taskManager.TASKS_NUM][Params.recordsNum];
        double mean[] = new double[taskManager.TASKS_NUM];
        for(int seed = 0;seed < Params.REPT;seed++){
            Params.random = new Random(seed);
            System.err.println("Program running with seed = " + seed);
            Peacock_Algorithm solver = new Peacock_Algorithm(taskManager);
            double[] result = solver.run1(mem_f[seed]);

            for (int t = 0; t < taskManager.TASKS_NUM; t++) {
                mean[t] += result[t] / Params.REPT;
            }
        }
        System.err.println("Mean result:");
        for (int t = 0; t < taskManager.TASKS_NUM; t++) {
            System.out.println("Task " + t + ":\t" + String.format("%.6f", mean[t]));
        }

        // print results
        String rootFolder = "results/";
        File dir = new File(rootFolder);
        if (!dir.exists()) {
            dir.mkdir();
        }

        String dirType = rootFolder + "benchmark";
        dir = new File(dirType);
        if (!dir.exists()) {
            dir.mkdir();
        }

        int evalsPerRecord = Params.maxEvals / Params.recordsNum;
        String fitnessFile = dirType + "/benchmarkFitness.txt";
        DataOutputStream outFit = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fitnessFile)));
        for (int r = 0; r < Params.recordsNum; r++) {
            outFit.writeBytes((r + 1) * evalsPerRecord + ", ");
            for (int seed = 0; seed < Params.REPT; seed++) {
                for (int task = 0; task < taskManager.TASKS_NUM; task++) {
                    if (seed < Params.REPT - 1 || task < taskManager.TASKS_NUM - 1) {
                        outFit.writeBytes(String.format("%.6f", mem_f[seed][task][r]) + ", ");
                    } else {
                        outFit.writeBytes("" + String.format("%.6f", mem_f[seed][task][r]) + "\n");
                    }
                }
            }
        }
        outFit.close();

        String dirMean = dirType + "/Mean";
        dir = new File(dirMean);
        if (!dir.exists()) {
            dir.mkdir();
        }

        String meanFile = dirMean + "/benchmarkMean.txt";
        DataOutputStream outMean = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(meanFile)));
        for (int task = 0; task < taskManager.TASKS_NUM; task++) {
            outMean.writeBytes("" + (task + 1) + ", " + String.format("%.6f", mean[task]) + "\n");
        }
        outMean.close();
    }
}
