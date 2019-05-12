package agenda.test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import agenda.model.Activity;
import agenda.model.Contact;
import agenda.repository.classes.RepositoryActivityFile;
import agenda.repository.classes.RepositoryActivityMock;
import agenda.repository.classes.RepositoryContactFile;
import agenda.repository.interfaces.RepositoryContact;

import org.junit.Before;
import org.junit.Test;

public class AfisActivityTest {

	private RepositoryActivityMock repo;

	@Before
	public void setUp() {
		repo = new RepositoryActivityMock();
	}

	@Test
	public void testExistingActivity() {
		assertEquals(0, repo.count());
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try {
			Date start = df.parse("04/22/2019 14:00");
			Date end = df.parse("04/22/2019 16:00");

			Activity activity = new Activity(
					"Cool activity",
					 start,
					 end,
					null,
					"VVSS lab");
			repo.addActivity(activity);

			assertEquals(1, repo.count());

			List<Activity> result = repo.activitiesOnDate("Cool activity", start);

			assertTrue(!result.isEmpty());
			assertEquals("Cool activity", result.get(0).getName());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNonExistingActivity() {
		assertEquals(0, repo.count());
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try {
			Date start = df.parse("04/22/2019 14:00");
			Date end = df.parse("04/22/2019 16:00");

			Activity activity = new Activity(
					"Cool activity",
					start,
					end,
					null,
					"VVSS lab");
			repo.addActivity(activity);

			assertEquals(1, repo.count());

			List<Activity> result = repo.activitiesOnDate("Bad activity", start);

			assertTrue(result.isEmpty());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
