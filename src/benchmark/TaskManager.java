package benchmark;

import java.util.ArrayList;

/*
    Quản lý các nhiệm vụ
 */
public class TaskManager {
    public int DIM; //Số chiều trong không gian chung
    public int TASKS_NUM; //Số lượng tác vụ có trong ID
    public ArrayList<Task> tasks; //Danh sách các tác vụ

    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.DIM=0;
        for(int i=0;i<tasks.size();i++){
            if(this.DIM < tasks.get(i).dim)
                this.DIM = tasks.get(i).dim;
        }
        this.TASKS_NUM = tasks.size();
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
