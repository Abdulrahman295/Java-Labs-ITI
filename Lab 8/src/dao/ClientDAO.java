package dao;

import model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientDAO {
    public List<Client> getAllClients();

    public Optional<Client> getClientById(int id);

    public boolean addClient(Client client);

    public boolean updateClient(Client client);

    public boolean deleteClient(int id);
}
