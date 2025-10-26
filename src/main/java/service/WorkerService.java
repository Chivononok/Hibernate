package service;

import entity.Worker;
import repository.jpa.WorkerRepository;

public class WorkerService {
    public void addWorker(Worker worker){
        WorkerRepository workerRepository = new WorkerRepository();
        workerRepository.addWorker(worker);
    }

    public Worker getWorkerWithMaxSalary(){
        WorkerRepository workerRepository = new WorkerRepository();
        return workerRepository.getWorkerWithMaxSalary();
    }

    public Worker getWorkerWithMinSalary(){
        WorkerRepository workerRepository = new WorkerRepository();
        return workerRepository.getWorkerWithMinSalary();
    }

    public Long getCostPerMonth(){
        WorkerRepository workerRepository = new WorkerRepository();
        return workerRepository.getCostPerMonth();
    }
}
