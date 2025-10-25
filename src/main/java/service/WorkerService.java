package service;

import entity.Worker;
import repository.jpa.WorkerRepository;

public class WorkerService {
    public void addWorker(Worker worker){
        WorkerRepository workerRepository = new WorkerRepository();
        workerRepository.addWorker(worker);
    }
}
