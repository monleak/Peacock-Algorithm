package util;

import java.util.Random;

public class Params {
    public static int PARAM_NumberOfPeacocks = 5;
    public static int PARAM_NumberOfPeahens = 20;
    public static int PARAM_NumberOfFunctionEvaluations = 50000;
    public static int PARAM_NumberOfEyes = 50;
    public static int PARAM_GenerationCounter = 5;
    public static double[] PARAM_EvolutionFactor = {0.5, 0.35, 0.65, 0.1, 0.95};
    public static double omega = 0.5; //tham số sử dụng trong PA_Population.pushAway
    public static final double EPSILON = 5e-7; //điểm tối ưu (coi như bằng 0)
    public static int recordsNum = 1000; //Số lần ghi lại kết quả
    public static final int REPT = 30; //Số lần chạy

    public static Random random;
    public static int countEvals = 0;
    public static int maxEvals = 1000000000;
}
