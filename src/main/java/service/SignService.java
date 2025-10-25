package service;

import entity.Sign;
import repository.jpa.SignRepository;

public class SignService {
    public void addSign(Sign sign){
        SignRepository signRepository = new SignRepository();
        signRepository.addSign(sign);
    }
}
