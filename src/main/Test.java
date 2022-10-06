package main;

import Population.PA_Population;
import benchmark.Function;
import benchmark.function.Ackley;
import benchmark.Task;
import util.Params;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Params.random = new Random();
        double[] lb = {-5,-5,-5,-5,-5,-5,-5,-5,-5,-5};
        double[] ub = {5,5,5,5,5,5,5,5,5,5};
        Function func = new Ackley();
        Task task = new Task(1,func);
        task.dim =10;
        task.LB = lb;
        task.UB = ub;

        PA_Population population = new PA_Population(task);
        population.randomInit();
        population.setting();
    }
}
