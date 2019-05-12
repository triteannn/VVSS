package agenda.service;

import agenda.exceptions.InvalidFormatException;
import agenda.model.Contact;
import agenda.repository.classes.RepositoryContactMock;

import java.util.List;

public class ContactService {
    private RepositoryContactMock repositoryContactMock;

    public ContactService() {
        this.repositoryContactMock = new RepositoryContactMock();
    }

    public List<Contact> getAll() {
        return this.repositoryContactMock.getContacts();
    }

    public Contact add(String name, String address, String telefon) {
        Contact contact = null;
        try {
            contact = new Contact(name, address, telefon);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return this.repositoryContactMock.addContact(contact);
    }

    public int count() {
        return this.repositoryContactMock.count();
    }

    public Contact getContactsByName(String name) {
        return this.repositoryContactMock.getByName(name);
    }
}
