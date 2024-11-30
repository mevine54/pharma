package fr.mevine.controller;

import fr.mevine.model.Client;
import fr.mevine.model.Adresse;
import java.util.ArrayList;
import java.util.List;

public class ClientController {
    private List<Client> clients = new ArrayList<>();

    public void ajouterClient(Client client) {
        clients.add(client);
    }

    public List<Client> listerClients() {
        return clients;
    }

    public Client getClientByName(String fullName) {
        for (Client client : clients) {
            String clientFullName = client.getNom() + " " + client.getPrenom();
            if (clientFullName.equalsIgnoreCase(fullName)) {
                return client;
            }
        }
        return null;
    }

    public void modifierClient(Client client) {
        // Si vous utilisez une base de données, effectuez la mise à jour ici
        // Sinon, les modifications sont déjà effectuées sur l'objet client
    }

    public void supprimerClient(Client client) {
        clients.remove(client);
        // Si vous utilisez une base de données, effectuez la suppression ici
    }
}
