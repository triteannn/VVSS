package agenda.repository.classes;

import java.util.LinkedList;
import java.util.List;

import agenda.model.Contact;
import agenda.repository.interfaces.RepositoryContact;
import agenda.exceptions.InvalidFormatException;

public class RepositoryContactMock implements RepositoryContact {

private List<Contact> contacts;
	
	public RepositoryContactMock() {
		contacts = new LinkedList<Contact>();
		try {
			Contact c = new Contact("Name", "address1", "+40712345678");
			contacts.add(c);
			c = new Contact("Another name", "address 2", "+40711222333");
			contacts.add(c);
			c = new Contact("My name", "address 3", "0711223344");
			contacts.add(c);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Contact> getContacts() {
		return new LinkedList<Contact>(contacts);
	}

	@Override
	public Contact addContact(Contact contact) {
		contacts.add(contact);
		return contact;
	}

	@Override
	public boolean removeContact(Contact contact) {
		int index = contacts.indexOf(contact);
		if (index < 0) return false;
		else contacts.remove(index);
		return true;
	}

	@Override
	public boolean saveContracts() {
		return true;
	}

	@Override
	public int count() {
		return contacts.size();
	}

	@Override
	public Contact getByName(String string) {
		for(Contact c : contacts)
			if (c.getName().equals(string)) return c;
		return null;
	}
	
}
