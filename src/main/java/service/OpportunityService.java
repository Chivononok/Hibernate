package service;

import entity.Opportunity;
import repository.jpa.OpportunityRepository;

public class OpportunityService {

    public void addOpportunity(Opportunity opportunity){
        OpportunityRepository opportunityRepository = new OpportunityRepository();
        opportunityRepository.addOpportunity(opportunity);
    }
}
