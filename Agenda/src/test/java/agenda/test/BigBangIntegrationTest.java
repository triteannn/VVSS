package agenda.test;

import agenda.exceptions.InvalidFormatException;
import agenda.model.Contact;
import agenda.service.ActivityService;
import agenda.service.ContactService;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class BigBangIntegrationTest {
    private ActivityService activityService;
    private ContactService contactService;

    @Before
    public void setUp() {
        this.activityService = new ActivityService();
        this.contactService = new ContactService();
    }

    @Test
    public void testAddValidCoolActivity() {
        assertEquals(0, this.activityService.count());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {
            this.activityService.addActivity(
                    "Cool activity",
                    df.parse("04/22/2019 14:00"),
                    df.parse("04/22/2019 16:00"),
                    null,
                    "VVSS lab");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertEquals(1, this.activityService.count());
    }

    @Test
    public void testAddValidContact()
    {
        try {
            Contact contact = new Contact("Tudor", "Constructorilor", "+40712345678");
        } catch (InvalidFormatException e) {
            fail();
        }
        assertEquals(3, this.contactService.count());
        this.contactService.add("Tudor", "Constructorilor", "+40712345678");
        assertEquals(4, this.contactService.count());
    }

    @Test
    public void testCoolActivityWithConflict() {
        assertEquals(0, this.activityService.count());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {
            this.activityService.addActivity(
                    "Less cool activity",
                    df.parse("04/22/2019 13:00"),
                    df.parse("04/22/2019 15:00"),
                    null,
                    "Some lab");

            assertEquals(1, this.activityService.count());

            this.activityService.addActivity(
                    "Cool activity",
                    df.parse("04/22/2019 14:00"),
                    df.parse("04/22/2019 16:00"),
                    null,
                    "VVSS lab");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertEquals(1, this.activityService.count());
    }

    @Test
    public void testIntegration() {
        try {
            Contact contact = new Contact("Tudor", "Constructorilor", "+40712345678");
        } catch (InvalidFormatException e) {
            fail();
        }
        assertEquals(3, this.contactService.count());
        Contact result = this.contactService.add("Tudor", "Constructorilor", "+40712345678");
        assertNotNull(result);
        assertEquals(4, this.contactService.count());


        assertEquals(0, this.activityService.count());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {
            List<Contact> contacts = new LinkedList<Contact>();
            contacts.add(result);
            assertTrue(!contacts.isEmpty());
            this.activityService.addActivity(
                    "Less cool activity",
                    df.parse("04/22/2019 13:00"),
                    df.parse("04/22/2019 15:00"),
                    contacts,
                    "Some lab");
            assertEquals(1, this.activityService.count());
            assertEquals(this.activityService.getAll().get(0).getName(), "Less cool activity");
            assertEquals(this.activityService.getAll().get(0).getContacts().get(0).getName(), "Tudor");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
