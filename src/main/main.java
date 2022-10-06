package main;

import java.io.Console;

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
    public static void main(String[] args) {
        checkForHelp(args);

    }
}
