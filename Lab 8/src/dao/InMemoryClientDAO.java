package dao;

import model.Client;
import util.IdGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryClientDAO implements ClientDAO {
    private final Map<Integer, Client> clients;
    private final IdGenerator idGenerator;

    public InMemoryClientDAO(){
        clients = new HashMap<>();
        idGenerator = new IdGenerator();
    }

    @Override
    public List<Client> getAllClients() {
        return clients.values().stream()
                .map(Client::copy)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Client> getClientById(int id) {
        Client client = clients.get(id);

        if (client == null) {
            return Optional.empty();
        }
        return Optional.of(client.copy());
    }

    @Override
    public boolean addClient(Client client) {
        if(client == null || clients.containsKey(client.getClientId())){
            return false;
        }

        client.setClientId(idGenerator.generate());
        clients.put(client.getClientId(), client.copy());
        return true;
    }

    @Override
    public boolean updateClient(Client client) {
        if (client == null || !clients.containsKey(client.getClientId())) {
            return false;
        }

        clients.put(client.getClientId(), client.copy());
        return true;
    }

    @Override
    public boolean deleteClient(int id) {
        return clients.remove(id) != null;
    }
}
