package service;

import entity.FitRoom;
import entity.FitroomWithSubselect;
import repository.jpa.FitRoomsRepository;

import java.util.List;
import java.util.Map;

public class FitroomService {

    public void addFitroom(FitRoom fitRoom){
        FitRoomsRepository fitRoomsRepository = new FitRoomsRepository();
        fitRoomsRepository.addFitRoom(fitRoom);
    }

    public FitRoom updatePrice(Long id, Double price){
        FitRoomsRepository fitRoomsRepository = new FitRoomsRepository();
        return fitRoomsRepository.updatePrice(id, price);
    }

    public FitRoom updateRoomnumberDetach(Long id, String roomNumber){
        FitRoomsRepository fitRoomsRepository = new FitRoomsRepository();
        return fitRoomsRepository.updateRoomnumberDetach(id, roomNumber);
    }

    public List<FitroomWithSubselect> findAllSub(){
        FitRoomsRepository fitRoomsRepository = new FitRoomsRepository();
        return fitRoomsRepository.findAllSub();
    }

    public void deleteById(Long id){
        FitRoomsRepository fitRoomsRepository = new FitRoomsRepository();
        fitRoomsRepository.deleteById(id);
    }

    public Map<String, Double> getPricePerUser(){
        FitRoomsRepository fitRoomsRepository = new FitRoomsRepository();
        return fitRoomsRepository.getPricePerUser();
    }
}
