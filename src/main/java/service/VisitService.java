package service;

import entity.Visit;
import repository.jpa.VisitRepository;

public class VisitService {
    public void addVisit(Visit visit){
        VisitRepository visitRepository = new VisitRepository();
        visitRepository.addVisit(visit);
    }
}
