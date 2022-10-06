package benchmark;

import java.util.ArrayList;

/*
    Quản lý các nhiệm vụ
 */
public class TaskManager {
    public int DIM; //Số chiều trong không gian chung
    public int TASKS_NUM; //Số lượng tác vụ có trong ID
    public ArrayList<Task> tasks; //Danh sách các tác vụ

    public TaskManager() {
        tasks = new ArrayList<Task>();
        DIM = 0;
        TASKS_NUM = 0;
    }

    public void addTask(Task task) {
        tasks.add(task);
        TASKS_NUM++;
        DIM = Math.max(DIM, task.dim);
    }

    public Task getTask(int task_id) {
        return tasks.get(task_id);
    }
}
