import entity.*;
import repository.jpa.FitRoomsRepository;
import service.ClientService;
import service.FitroomService;
import service.OpportunityService;

import java.util.List;

public class Main {
    public static void main(String[] args){
        ClientService clientService = new ClientService();


        /*
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
        /*
        //=== 6 дз ===
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
        */

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
}
