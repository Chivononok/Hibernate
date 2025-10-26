import entity.*;
import service.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        //run5HomeWork();
        //run6HomeWork();
        //run7HomeWork();
        //run8HomeWork();
        //run9HomeWork();
        //runHomeWork10();
        run11HomeWork();
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

    public static void run8HomeWork(){
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

    public static void run9HomeWork(){

        //=== связь между таблицами Услуги и Помещения 1 ко многим ===
        OpportunityService opportunityService = new OpportunityService();
        FitroomService fitroomService = new FitroomService();
        FitRoom fitRoom1 = new FitRoom("зал первый", "142 ", 9L, "ACTIVE", 22.3D);
        FitRoom fitRoom2 = new FitRoom("зал второй", "17 ", 16L, "ACTIVE", 94D);
        List<FitRoom> fitRoomList = new ArrayList<>();
        fitRoomList.add(fitRoom1);
        fitRoomList.add(fitRoom2);
        fitroomService.addFitroom(fitRoom1);
        fitroomService.addFitroom(fitRoom2);
        Opportunity opportunity = new Opportunity("услуга первая" , 170D);
        opportunity.setFitRoomList(fitRoomList);
        opportunityService.addOpportunity(opportunity);
        //=============================================================

        //=== связь Посетитель-Посещения Один ко многим ===
        VisitorService visitorService = new VisitorService();
        VisitService visitService = new VisitService();
        Visitor visitor = new Visitor("27.04.2025","21.04.2025" , "ACTIVE",19D);
        visitor.setAddress(new Address("Minsk", "Яблоневая", "6а", "220025"));
        visitor.setName("Иван");
        visitor.setSurname("Спортивный");
        visitor.setBirthdate("07.11.1983");

        Visit visit1 = new Visit("21.04.2025", new BigDecimal(4));
        Visit visit2 = new Visit("27.04.2025", new BigDecimal(15));

        List<Visit> visitList = new ArrayList<>();
        visitList.add(visit1);
        visitList.add(visit2);

        visitor.setVisitList(visitList);

        visitService.addVisit(visit1);
        visitService.addVisit(visit2);
        visitorService.addVisitor(visitor);
        //=================================================

        //===добавление таблицы с Записью и связь Многие к одному с Клиентом и Залами ===
        ClientService clientService = new ClientService();
        SignService signService = new SignService();

        Sign sign1 = new Sign("02.12.2024", "17:30");
        Sign sign2 = new Sign("07.12.2024", "14:05");

        Client client = new Client("Сергей", "Андронов", 34, "111111111", "ACTIVE", 76D, new Address("Minsk", "Космонавтов", "3", "220011"));
        sign1.setClient(client);
        sign2.setClient(client);

        clientService.addClientS(client);

        FitRoom fitRoom = new FitRoom("зал третий", "127 ", 17L, "ACTIVE", 19.17);
        sign1.setFitRoom(fitRoom);
        sign2.setFitRoom(fitRoom);

        fitroomService.addFitroom(fitRoom);

        signService.addSign(sign1);
        signService.addSign(sign2);
        //============================================================
        //==== Каскадное удаление зала ===
        fitroomService.deleteById(10L);
    }

    public static void runHomeWork10(){

        //=== найти клиента по имени ===
        System.out.println("===== Ищем клиентов по имене Сергей =====");
        ClientService clientService = new ClientService();
        List<Client> clientList = clientService.fiendByNameJPQL("Сергей");
        System.out.println(clientList);
        System.out.println("=========================================");

        //=== найти сотрудника с максимальной/минимальной зарплатой ===
        System.out.println("===== Ищем сотрудника с максимальной зп =====");
        WorkerService workerService = new WorkerService();
        Worker workerWithMaxSalary = workerService.getWorkerWithMaxSalary();
        System.out.println("Работник с max зар. платой: " + workerWithMaxSalary);
        Worker workerWithMinSalary = workerService.getWorkerWithMinSalary();
        System.out.println("Работник с min зар. платой: " + workerWithMinSalary);
        System.out.println("=============================================");

        //=== найти расходы в месяц на персонал ===
        System.out.println("Расходы в месяц на персонал: " + workerService.getCostPerMonth());
        //=== найти стоимость 1 человекр-места в каждом зале ===
        FitroomService fitroomService = new FitroomService();
        Map<String, Double> pricePerUser = fitroomService.getPricePerUser();
        System.out.println(pricePerUser);
    }

    public static void run11HomeWork(){

        //=== получение всех сотрудников с использованием criteria ===
        WorkerService workerService = new WorkerService();
        List<Worker> workerList = workerService.getAllWorkersCriteria();
        System.out.println(workerList);

        //=== услуга с минимальной ценой ===
        OpportunityService opportunityService = new OpportunityService();
        Opportunity opportunityWithMinPrice = opportunityService.getOpportunityWithMinPriceCriteria();
        System.out.println(opportunityWithMinPrice);

        //=== общее кол-во человек в помещении ===
        FitroomService fitroomService = new FitroomService();
        System.out.println("Общее число клиентов в залах одновременно: " + fitroomService.getAllCountClientsInTimeCriteria());

        //=== поиск пользователей по диапазону возраста (от 15 до 18) ===
        ClientService clientService = new ClientService();
        System.out.println(clientService.getClientBetweenAgeCriteria(15L, 18L));

        //=== поиск залов, которые посещали пользователи старше 32 лет ===
        System.out.println(fitroomService.getRoomsVisitedByClientsOverThan(32L));

    }
}
