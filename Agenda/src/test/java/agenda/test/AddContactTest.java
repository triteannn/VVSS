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
		mockContactRepository.addContact(contact);
		try {
			mockContactRepository.addContact(new Contact(contact.getName(), contact.getAddress(), contact.getTelefon().substring(2)));
		} catch (InvalidFormatException e) {
			fail();
		}
		assertEquals(5, mockContactRepository.count());
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
	public void testPhoneNumberLength()
	{
		try {
			contact = new Contact("Tudor", "Constructorilor", "071234567");
			fail();
		} catch (InvalidFormatException e) {
			assertNull(contact);
		}

		contact = null;

		try {
			contact = new Contact("Tudor", "Constructorilor", "0712345678");
			assertNotNull(contact);
		} catch (InvalidFormatException e) {
			fail();
		}

		contact = null;

		try {
			contact = new Contact("Tudor", "Constructorilor", "+0712345678");
			fail();
		} catch (InvalidFormatException e) {
			assertNull(contact);
		}

		contact = null;

		try {
			contact = new Contact("Tudor", "Constructorilor", "+40712345678");
			assertNotNull(contact);
		} catch (InvalidFormatException e) {
			fail();
		}

		contact = null;

		try {
			contact = new Contact("Tudor", "Constructorilor", "+407123456789");
			fail();
		} catch (InvalidFormatException e) {
			assertNull(contact);
		}
	}

	@Test
	public void testNameLength()
	{
		try {
			contact = new Contact("T", "Constructorilor", "0712345678");
			fail();
		} catch (InvalidFormatException e) {
			assertNull(contact);
		}

		contact = null;

		try {
			contact = new Contact("Li", "Constructorilor", "0712345678");
			assertNotNull(contact);
		} catch (InvalidFormatException e) {
			fail();
		}

		contact = null;

		try {
			contact = new Contact("Bob", "Constructorilor", "0712345678");
			assertNotNull(contact);
		} catch (InvalidFormatException e) {
			fail();
		}

		contact = null;

		try {
			contact = new Contact("Alexandru Constantinescu", "Constructorilor", "+40712345678");
			assertNotNull(contact);
		} catch (InvalidFormatException e) {
			fail();
		}

		contact = null;

		try {
			contact = new Contact("Alexandru Constantinescul", "Constructorilor", "+40712345678");
			assertNotNull(contact);
		} catch (InvalidFormatException e) {
			fail();
		}

		contact = null;

		try {
			contact = new Contact("Alexandru Constantinescule", "Constructorilor", "+40712345678");
			fail();
		} catch (InvalidFormatException e) {
			assertNull(contact);
		}
	}

	@Test
	public void testNameWordCount()
	{
		try {
			contact = new Contact("", "Constructorilor", "0712345678");
			fail();
		} catch (InvalidFormatException e) {
			assertNull(contact);
		}

		contact = null;

		try {
			contact = new Contact("Tudor", "Constructorilor", "0712345678");
			assertNotNull(contact);
		} catch (InvalidFormatException e) {
			fail();
		}

		contact = null;

		try {
			contact = new Contact("Tudor Tritean", "Constructorilor", "0712345678");
			assertNotNull(contact);
		} catch (InvalidFormatException e) {
			fail();
		}

		contact = null;

		try {
			contact = new Contact("Tudor Adrian Tritean", "Constructorilor", "+40712345678");
			fail();
		} catch (InvalidFormatException e) {
			assertNull(contact);
		}
	}

	@Test
	public void testAddressLength()
	{
		try {
			contact = new Contact("Tudor", "Adr1", "0712345678");
			fail();
		} catch (InvalidFormatException e) {
			assertNull(contact);
		}

		contact = null;

		try {
			contact = new Contact("Tudor", "Addr1", "0712345678");
			assertNotNull(contact);
		} catch (InvalidFormatException e) {
			fail();
		}

		contact = null;

		try {
			contact = new Contact("Tudor", "Addr 1", "0712345678");
			assertNotNull(contact);
		} catch (InvalidFormatException e) {
			fail();
		}

		contact = null;

		try {
			contact = new Contact("Tudor", "Strada Constantin 6", "+40712345678");
			assertNotNull(contact);
		} catch (InvalidFormatException e) {
			assertNull(contact);
		}

		contact = null;

		try {
			contact = new Contact("Tudor", "Strada Constantin 62", "+40712345678");
			assertNotNull(contact);
		} catch (InvalidFormatException e) {
			assertNull(contact);
		}

		contact = null;

		try {
			contact = new Contact("Tudor", "Strada Constantin 625", "+40712345678");
			fail();
		} catch (InvalidFormatException e) {
			assertNull(contact);
		}
	}
}
