import entity.*;
import repository.jpa.FitRoomsRepository;
import service.*;

import java.util.List;

public class Main {
    public static void main(String[] args){
        //run5HomeWork();
        //run6HomeWork();
        //run7HomeWork();
        run8HomeWork();
    }
    public static void run5HomeWork(){
        /*
        ClientService clientService = new ClientService();
        // === 5 ДЗ ===
        // === заводим новых клиентов ===
        for (int i = 0; i < 5; i++) {
            Client client = new Client(Long.valueOf(i), "name"+i, "surname"+i, 14+i, "123456789"+i, "ACTIVE", (double) (i * 10));
            clientService.addClient(client);
        }
        //===============================

        clientService.removeClientById(3L); //удалили клиента с id=3

        clientService.updateClientStatus(2L, "PREMIUM");    //обновили запись с id=2

        //=== вывели всех клиентов ===
        List<Client> clients = clientService.getClients();
        System.out.println(clients);
        //============================
        */
    }

    public static void run6HomeWork(){
        ClientService clientService = new ClientService();
        Client client = clientService.findClientByIdS(4L);  //поиск клиента по Ид
        System.out.println(client);
        //=== наполнение таблицы с услугами ===
        OpportunityService opportunityService = new OpportunityService();
        for (int i = 0; i < 5; i++) {
            Opportunity opportunity = new Opportunity("услуга " + i, i+0.1);
            opportunityService.addOpportunity(opportunity);
        }
        //======================================
        //== наполнение таблицы с помещениями ==
        FitroomService fitroomService = new FitroomService();
        for (int i = 0; i < 5; i++) {
            FitRoom fitRoom = new FitRoom("зал " + i, "идентификационнвй номер " + i, (long)i+3, "ACTIVE", (double)i+9);
            fitroomService.addFitroom(fitRoom);
        }
        //======================================

        fitroomService.updateRoomnumberDetach(2l, "создан копированием через detach");  //создание через detach
        fitroomService.updatePrice(4l, 7D); //обновление цены

    }

    public static void run7HomeWork(){
        ClientService clientService = new ClientService();
        //==== 7 дз ====
        Client client = new Client("name", "surname", 14, "123456789", "ACTIVE", 16D, new Address("Minsk", "Яблоневая", "7а", "220025"));
        clientService.addClientS(client);   //добавили клиента с адресом

        //=== стоимость помещений, вместимостью не больше 10 человек ===
        FitroomService fitroomService = new FitroomService();
        Double total=0D;
        List<FitroomWithSubselect> fitroomWithSubselects = fitroomService.findAllSub();
        for (FitroomWithSubselect elem:fitroomWithSubselects
        ) {
            total = total + elem.getPrice();
        }
        System.out.println(total);
        //==============================================================

        List<ClientPremium> clientPremiumList = clientService.findAllPremium(); //клиенты со статусом PREMIUM ы использованием @Where
        System.out.println(clientPremiumList);
        //==============
    }

    public static  void run8HomeWork(){
        VisitorService visitorService = new VisitorService();
        WorkerService workerService = new WorkerService();

        for (int i = 0; i < 4; i++) {
            Visitor visitor = new Visitor("1"+i+".04.2025","2"+i+".04.2025" , "ACTIVE",12D + i);
            visitor.setAddress(new Address("Minsk", "Яблоневая", i+"а", "220025"));
            visitor.setName("visitor_Имя" + i);
            visitor.setSurname("visitor_Фамилия" +i);
            visitor.setBirthdate("1"+i+".04.1983");
            visitorService.addVisitor(visitor);
        }

        for (int i = 0; i <4; i++) {
            Worker worker = new Worker("1"+i+".04.2025", i*10+1L, "");
            worker.setAddress(new Address("Gomel", "Грушевая", i+"д", "220071"));
            worker.setName("worker_Имя" + i);
            worker.setSurname("worker_Фамилия" +i);
            worker.setBirthdate("1"+i+".04.1987");
            workerService.addWorker(worker);
        }
    }
}
