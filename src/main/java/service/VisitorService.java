package service;

import entity.Visitor;
import repository.jpa.VisitorRepository;

public class VisitorService {
    public void addVisitor(Visitor visitor){
        VisitorRepository visitorRepository = new VisitorRepository();
        visitorRepository.addVisitor(visitor);
    }
}
