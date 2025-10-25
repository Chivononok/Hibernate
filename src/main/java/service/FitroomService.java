package service;

import entity.FitRoom;
import entity.FitroomWithSubselect;
import repository.jpa.FitRoomsRepository;

import java.util.List;

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
}
