package net.thumbtack.school.thread.Task17;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Task17 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>();
        int developersInProcess = 0;
        int tasksInProcess = 0;
        Thread[] developers = new Thread[3];
        Thread[] executors = new Thread[5];
        for (int i = 0; i < developers.length; i++) {
            developers[i] = new Thread(new Developer(taskQueue, eventQueue));
            developers[i].start();
        }

        for (int i = 0; i < executors.length; i++) {
            executors[i] = new Thread(new Executor(taskQueue, eventQueue));
            executors[i].start();
        }

        while (true) {
            switch (eventQueue.take()) {
                case PRODUSER_STARTED:
                    developersInProcess++;
                    break;
                case TASK_POSTED:
                    tasksInProcess++;
                    break;
                case PRODUSER_FINISHED:
                    developersInProcess--;
                    break;
                case FINISHED:
                    tasksInProcess--;
                    break;
            }
            if (developersInProcess == 0 && tasksInProcess == 0) {
                for (int i = 0; i < executors.length; i++)
                    taskQueue.put(new Poison());
                break;
            }
        }
    }

}

class Developer implements Runnable {
    private BlockingQueue<Task> queue;
    private BlockingQueue<Event> eventQueue;

    public Developer(BlockingQueue<Task> queue, BlockingQueue<Event> eventQueue) {
        this.queue = queue;
        this.eventQueue = eventQueue;
    }

    @Override
    public void run() {
        try {
            eventQueue.put(Event.PRODUSER_STARTED);
            Task task = new Task("Task" + new Random().nextInt(100), new Random().nextInt(3) + 1);
            queue.put(task);
            eventQueue.put(Event.TASK_POSTED);
            System.out.println("Developer posted " + task.getName() + " (" + task.getStages().size() + " stages)");
            eventQueue.put(Event.PRODUSER_FINISHED);
            System.out.println("Developer finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class Executor implements Runnable {
    private BlockingQueue<Task> queue;
    private BlockingQueue<Event> eventQueue;

    public Executor(BlockingQueue<Task> queue, BlockingQueue<Event> eventQueue) {
        this.queue = queue;
        this.eventQueue = eventQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = queue.take();
                if (task instanceof Poison) {
                    System.out.println("Executor poisoned");
                    break;
                }
                task.executeStage();
                if (!task.isDone())
                    queue.put(task);
                else
                    eventQueue.put(Event.FINISHED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class Task {
    private String name;
    private List<Executable> stages = new ArrayList<>();
    private int index;

    public Task() {
    }

    public Task(String name, int count) {
        this.name = name;
        this.index = 0;
        for (int i = 0; i < count; i++)
            stages.add(new Stage(name));
    }

    public void executeStage() {
        stages.get(index++).execute();
    }

    public boolean isDone() {
        return index == stages.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Executable> getStages() {
        return stages;
    }

}

class Stage implements Executable {
    private String taskName;

    public Stage(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute() {
        System.out.println("Completed stage of " + taskName);
    }

}

interface Executable {
    void execute();

}

class Poison extends Task {
    public Poison() {
    }

}
