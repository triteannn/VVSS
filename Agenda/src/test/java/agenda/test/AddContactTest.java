package agenda.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import agenda.exceptions.InvalidFormatException;

import agenda.model.Contact;
import agenda.repository.classes.RepositoryContactMock;
import agenda.repository.interfaces.RepositoryContact;


public class AddContactTest {

	private Contact contact;
	private RepositoryContact mockContactRepository;
	
	@Before
	public void setUp() {
		mockContactRepository = new RepositoryContactMock();
		contact = null;
	}

	@After
	public void tearDown() {
		contact = null;
	}
	
	@Test
	public void testPhoneNumberStartCharacter()
	{
		try {
			contact = new Contact("Tudor", "Constructorilor", "+40712345678");
		} catch (InvalidFormatException e) {
			fail();
		}
		assertEquals(3, mockContactRepository.count());
		assertTrue(contact.getTelefon().startsWith("+") || contact.getTelefon().startsWith("0"));
		mockContactRepository.addContact(contact);
		assertEquals(4, mockContactRepository.count());
		for(Contact c : mockContactRepository.getContacts()) {
			assertTrue(c.getTelefon().startsWith("+") || c.getTelefon().startsWith("0"));
		}
	}

	@Test
	public void testNameIsNotNull()
	{
		try {
			contact = new Contact("Tudor", "Constructorilor", "0712345678");
		} catch (InvalidFormatException e) {
			fail();
		}
		assertTrue(contact.getName() != null && contact.getName().length() > 0);
		assertEquals(3, mockContactRepository.count());
		mockContactRepository.addContact(contact);
		assertEquals(4, mockContactRepository.count());
		for(Contact c : mockContactRepository.getContacts()) {
			assertTrue(c.getName() != null && c.getName().length() > 0);
		}
	}

	@Test
	public void testNameShouldOnlyContainLetters()
	{
		try {
			contact = new Contact("Tudor", "Constructorilor", "0712345678");
		} catch (InvalidFormatException e) {
			fail();
		}

		for (Character c : contact.getName().toCharArray()) {
			if (!Character.isSpaceChar(c))
				assertTrue(Character.isLetter(c));
		}
		assertEquals(3, mockContactRepository.count());
		mockContactRepository.addContact(contact);
		assertEquals(4, mockContactRepository.count());
		for(Contact contact : mockContactRepository.getContacts()) {
			for (Character c : contact.getName().toCharArray()) {
				if (!Character.isSpaceChar(c))
					assertTrue(Character.isLetter(c));
			}
		}
	}

	@Test
	public void testPhoneNumberIsNotNull()
	{
		try {
			contact = new Contact("Tudor", "Constructorilor", "0712345678");
		} catch (InvalidFormatException e) {
			fail();
		}
		assertTrue(contact.getTelefon() != null && contact.getTelefon().length() > 0);
		assertEquals(3, mockContactRepository.count());
		mockContactRepository.addContact(contact);
		assertEquals(4, mockContactRepository.count());
		for(Contact c : mockContactRepository.getContacts()) {
			assertTrue(c.getTelefon() != null && c.getTelefon().length() > 0);
		}
	}

	@Test
	public void testPhoneNumberLengthIsLess()
	{
		try {
			contact = new Contact("Tudor", "Constructorilor", "071234567");
		} catch (InvalidFormatException e) {
			assertEquals("Invalid phone number", e.getCause().getMessage());
		}
	}

	@Test
	public void testPhoneNumberLengthIsGood()
	{
		try {
			contact = new Contact("Tudor", "Constructorilor", "+40712345678");
		} catch (InvalidFormatException e) {
			assertEquals("Invalid phone number", e.getCause().getMessage());
		}
		assertNotNull(contact);
		assertEquals(3, mockContactRepository.count());
		mockContactRepository.addContact(contact);
		assertEquals(4, mockContactRepository.count());
	}

	@Test
	public void testNameLengthIsTooLong()
	{
		try {
			contact = new Contact("Numefoartefoartefoartefoaaaaaartelungsiurat", "Constructorilor", "+40712345678");
		} catch (InvalidFormatException e) {
			assertEquals("Invalid name", e.getCause().getMessage());
		}
	}

	@Test
	public void testNameLengthIsGood()
	{
		try {
			contact = new Contact("Tudor Tritean", "Constructorilor", "+40712345678");
		} catch (InvalidFormatException e) {
			assertEquals("Invalid name", e.getCause().getMessage());
		}
		assertNotNull(contact);
		assertEquals(3, mockContactRepository.count());
		mockContactRepository.addContact(contact);
		assertEquals(4, mockContactRepository.count());
	}
}
