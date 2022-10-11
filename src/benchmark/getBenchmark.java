package benchmark;

import benchmark.function.*;

import java.util.ArrayList;
import java.util.Arrays;

public class getBenchmark {
    public static ArrayList<Task> getBenchmark(){
        ArrayList<Task> tasks = new ArrayList<Task>();
        //Task 1
        Function f1 = new Sphere();
        f1.Optimum =0;
        Task t1 = new Task(1,f1);
        t1.dim = 30;
        t1.UB = new double[t1.dim];Arrays.fill(t1.UB,100);
        t1.LB = new double[t1.dim];Arrays.fill(t1.LB,-100);
        tasks.add(t1);
        //Task 2
        Function f2 = new Schwefel2_22();
        f2.Optimum = 0;
        Task t2 = new Task(2,f2);
        t2.dim = 30;
        t2.UB = new double[t2.dim];Arrays.fill(t2.UB,10);
        t2.LB = new double[t2.dim];Arrays.fill(t2.LB,-10);
        tasks.add(t2);
        //Task 3
        Function f3 = new Schwefel1_2();
        f3.Optimum = 0;
        Task t3 = new Task(3,f3);
        t3.dim = 30;
        t3.UB = new double[t3.dim];Arrays.fill(t3.UB,100);
        t3.LB = new double[t3.dim];Arrays.fill(t3.LB,-100);
        tasks.add(t3);
        //Task 4
        Function f4 = new Schwefel2_21();
        f4.Optimum = 0;
        Task t4 = new Task(4,f4);
        t4.dim = 30;
        t4.UB = new double[t4.dim];Arrays.fill(t4.UB,100);
        t4.LB = new double[t4.dim];Arrays.fill(t4.LB,-100);
        tasks.add(t4);
        //Task 5
        Function f5 = new Rosenbrock();
        f5.Optimum = 0;
        Task t5 = new Task(5,f5);
        t5.dim = 30;
        t5.UB = new double[t5.dim];Arrays.fill(t5.UB,30);
        t5.LB = new double[t5.dim];Arrays.fill(t5.LB,-30);
        tasks.add(t5);
        //Task 6
        Function f6 = new Step();
        f6.Optimum = 0;
        Task t6 = new Task(6,f6);
        t6.dim = 30;
        t6.UB = new double[t6.dim];Arrays.fill(t6.UB,100);
        t6.LB = new double[t6.dim];Arrays.fill(t6.LB,-100);
        tasks.add(t6);
        //Task 7
        Function f7 = new Quartic();
        f7.Optimum = 0;
        Task t7 = new Task(7,f7);
        t7.dim = 30;
        t7.UB = new double[t7.dim];Arrays.fill(t7.UB,1.28);
        t7.LB = new double[t7.dim];Arrays.fill(t7.LB,-1.28);
        tasks.add(t7);
        //Task 8
        Function f8 = new Schwefel();
        f8.Optimum = -12569.5;
        Task t8 = new Task(8,f8);
        t8.dim = 30;
        t8.UB = new double[t8.dim];Arrays.fill(t8.UB,500);
        t8.LB = new double[t8.dim];Arrays.fill(t8.LB,-500);
        tasks.add(t8);
        //Task 9
        Function f9 = new Rastrigin();
        f9.Optimum = 0;
        Task t9 = new Task(9,f9);
        t9.dim = 30;
        t9.UB = new double[t9.dim];Arrays.fill(t9.UB,5.12);
        t9.LB = new double[t9.dim];Arrays.fill(t9.LB,-5.12);
        tasks.add(t9);
        //Task 10
        Function f10 = new Ackley();
        f10.Optimum = 0;
        Task t10 = new Task(10,f10);
        t10.dim = 30;
        t10.UB = new double[t10.dim];Arrays.fill(t10.UB,32);
        t10.LB = new double[t10.dim];Arrays.fill(t10.LB,-32);
        tasks.add(t10);
        //Task 11
        Function f11 = new Griewank();
        f11.Optimum = 0;
        Task t11 = new Task(11,f11);
        t11.dim = 30;
        t11.UB = new double[t11.dim];Arrays.fill(t11.UB,600);
        t11.LB = new double[t11.dim];Arrays.fill(t11.LB,-600);
        tasks.add(t11);
        //Task12
        Function f12 = new Six_Hump_Camel();
        f12.Optimum = -1.0316;
        Task t12 = new Task(12,f12);
        t12.dim = 2;
        t12.UB = new double[t12.dim];Arrays.fill(t12.UB,5);
        t12.LB = new double[t12.dim];Arrays.fill(t12.LB,-5);
        tasks.add(t12);
        //Task 13
        Function f13 = new Branin();
        f13.Optimum = 0.398;
        Task t13 = new Task(13,f13);
        t13.dim = 2;
        t13.UB = new double[t13.dim];t13.UB[0] = 10;t13.UB[1] = 15;
        t13.LB = new double[t13.dim];t13.LB[0] = -5;t13.LB[1] = 0;
        tasks.add(t13);
        //Task 14
        Function f14 = new Goldstein_Price();
        f14.Optimum = 3;
        Task t14 = new Task(14,f14);
        t14.dim = 2;
        t14.UB = new double[t14.dim];Arrays.fill(t12.UB,2);
        t14.LB = new double[t14.dim];Arrays.fill(t14.LB,-2);
        tasks.add(t14);
        return tasks;
    }
}
