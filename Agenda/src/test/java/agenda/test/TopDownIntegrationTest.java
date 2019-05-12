package agenda.test;

import agenda.exceptions.InvalidFormatException;
import agenda.model.Activity;
import agenda.model.Contact;
import agenda.service.ActivityService;
import agenda.service.ContactService;
import agenda.service.IActivityService;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class TopDownIntegrationTest {
    private ContactService contactService;
    private ActivityService activityService;

    @Before
    public void setUp() {
        this.contactService = new ContactService();
        this.activityService = new ActivityService();
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
    public void testExistingActivity() {
        assertEquals(0, this.activityService.count());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {
            Date start = df.parse("04/22/2019 14:00");
            Date end = df.parse("04/22/2019 16:00");

            this.activityService.addActivity(
                    "Cool activity",
                    start,
                    end,
                    null,
                    "VVSS lab");

            assertEquals(1, this.activityService.count());

            List<Activity> result = this.activityService.getActivitiesByDate("Cool activity", start);

            assertTrue(!result.isEmpty());
            assertEquals("Cool activity", result.get(0).getName());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIntegrateA() {
        try {
            Contact contact = new Contact("Tudor", "Constructorilor", "+40712345678");
        } catch (InvalidFormatException e) {
            fail();
        }
        assertEquals(3, this.contactService.count());
        Contact result = this.contactService.add("Tudor", "Constructorilor", "+40712345678");
        assertNotNull(result);
        assertEquals(4, this.contactService.count());

        IActivityService bcStub = new BCStub();
        assertEquals(0, bcStub.count());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {
            List<Contact> contacts = new LinkedList<Contact>();
            contacts.add(result);
            assertTrue(!contacts.isEmpty());
            bcStub.addActivity(
                    "Less cool activity",
                    df.parse("04/22/2019 13:00"),
                    df.parse("04/22/2019 15:00"),
                    contacts,
                    "Some lab");
            assertEquals(1, bcStub.count());
            assertEquals(bcStub.getAll().get(0).getName(), "Less cool activity");
            assertEquals(bcStub.getAll().get(0).getContacts().get(0).getName(), "Tudor");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIntegrateAB() {
        try {
            Contact contact = new Contact("Tudor", "Constructorilor", "+40712345678");
        } catch (InvalidFormatException e) {
            fail();
        }
        assertEquals(3, this.contactService.count());
        Contact result = this.contactService.add("Tudor", "Constructorilor", "+40712345678");
        assertNotNull(result);
        assertEquals(4, this.contactService.count());

        IActivityService cStub = new CStub();
        assertEquals(0, cStub.count());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {
            List<Contact> contacts = new LinkedList<Contact>();
            contacts.add(result);
            assertTrue(!contacts.isEmpty());
            cStub.addActivity(
                    "Less cool activity",
                    df.parse("04/22/2019 13:00"),
                    df.parse("04/22/2019 15:00"),
                    contacts,
                    "Some lab");
            assertEquals(1, cStub.count());
            assertEquals(cStub.getAll().get(0).getName(), "Less cool activity");
            assertEquals(cStub.getAll().get(0).getContacts().get(0).getName(), "Tudor");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIntegrateABC() {
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
