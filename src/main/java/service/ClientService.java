package service;

import entity.Client;
import repository.jpa.ClientRepository;

import java.util.List;

public class ClientService {
    public void addClient(Client client){
        ClientRepository clientRepository = new ClientRepository();
        clientRepository.addClient(client);
    }

    public List<Client> getClients(){
        ClientRepository clientRepository = new ClientRepository();
        return clientRepository.getAll();
    }

    public Client getClientById(Long id){
        ClientRepository clientRepository = new ClientRepository();
        return clientRepository.getClientById(id);
    }

    public void removeClientById(Long id){
        ClientRepository clientRepository = new ClientRepository();
        clientRepository.removeClient(id);
    }

    public void updateClientStatus(Long id, String status){
        ClientRepository clientRepository = new ClientRepository();
        Client client = getClientById(id);
        client.setStatus(status);
        clientRepository.updateClient(client);
    }
}
