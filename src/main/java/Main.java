import entity.Client;
import service.ClientService;

import java.util.List;

public class Main {
    public static void main(String[] args){
        ClientService clientService = new ClientService();
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
    }
}
