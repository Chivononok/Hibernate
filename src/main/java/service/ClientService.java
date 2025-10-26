package service;

import entity.Client;
import entity.ClientPremium;
import repository.jpa.ClientRepository;

import java.util.List;

public class ClientService {
    /*
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


     */

    public void addClientS(Client client){
        ClientRepository clientRepository = new ClientRepository();
        clientRepository.addClientS(client);
    }
    public Client findClientByIdS(Long id){
        ClientRepository clientRepository = new ClientRepository();
        return clientRepository.findByIdS(id);
    }

    public List<ClientPremium> findAllPremium(){
        ClientRepository clientRepository = new ClientRepository();
        return  clientRepository.findAllPremium();
    }

    public List<Client> fiendByNameJPQL(String name){
        ClientRepository clientRepository = new ClientRepository();
        return clientRepository.findByNameJPQL(name);
    }

    public List<Client> getClientBetweenAgeCriteria(Long minAge, Long maxAge){
        ClientRepository clientRepository = new ClientRepository();
        return clientRepository.getClientBetweenAgeCriteria(minAge, maxAge);
    }
}
