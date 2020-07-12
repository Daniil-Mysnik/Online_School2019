package net.thumbtack.school.thread.Task9;

import java.util.LinkedList;
import java.util.Queue;

public class TraineeQueue {

    public Queue<Trainee> queue;

    public TraineeQueue() {
        this.queue = new LinkedList<>();
    }

    public void addTrainee(Trainee trainee){
        queue.add(trainee);
    }

    public Trainee removeTrainee() throws TrainingException{
        if (queue.peek() == null) {
            throw new  TrainingException(TrainingErrorCode.EMPTY_TRAINEE_QUEUE);
        }
        return queue.poll();
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }
}
