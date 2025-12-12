package model;

import java.util.HashSet;
import java.util.Set;

public class Client {
    private Integer clientId;
    private String name;
    private String email;
    private final Set<Integer> borrowedItems;

    public Client(String name, String email) {
        setName(name);
        setEmail(email);
        borrowedItems = new HashSet<>();
    }

    protected Client(Client other) {
        this.clientId = other.clientId;
        this.name = other.name;
        this.email = other.email;
        this.borrowedItems = new HashSet<>(other.borrowedItems);
    }

    public Integer getClientId() {
        return clientId;
    }

    public void  setClientId(Integer clientId) {
        if(clientId == null) {
            throw new IllegalArgumentException("Client ID cannot be null");
        }

        if(clientId <= 0) {
            throw new IllegalArgumentException("Client ID must be a positive integer");
        }

        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        this.email = email;
    }

    public Set<Integer> getBorrowedItems() {
        return new HashSet<>(borrowedItems);
    }

    public boolean hasBorrowedItem(Integer itemId) {
        if(itemId == null) {
            throw new IllegalArgumentException("Item ID cannot be null");
        }

        return  borrowedItems.contains(itemId);
    }

    public void addItem(Integer itemId) {
        if(itemId == null) {
            throw new IllegalArgumentException("Item ID cannot be null");
        }

        borrowedItems.add(itemId);
    }

    public void removeItem(Integer itemId) {
        if(itemId == null) {
            throw new IllegalArgumentException("Item ID cannot be null");
        }

        borrowedItems.remove(itemId);
    }

    public void getClientInfo() {
        System.out.println("model.Client ID: " + clientId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
    }

    public Client copy() {
        return new Client(this);
    }
}
