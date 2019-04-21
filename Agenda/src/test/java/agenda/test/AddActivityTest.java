package agenda.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import agenda.model.Activity;
import agenda.repository.classes.RepositoryActivityMock;
import agenda.repository.interfaces.RepositoryActivity;

import org.junit.Before;
import org.junit.Test;

public class AddActivityTest {
	private Activity activity;
	private RepositoryActivity repo;
	
	@Before
	public void setUp() throws Exception {
		repo = new RepositoryActivityMock();
	}

	@Test
	public void testNullActivity() {
		assertEquals(0, repo.count());
		activity = null;
		repo.addActivity(activity);
		assertEquals(0, repo.count());
	}

	@Test
	public void testCoolActivityStandalone() {
		assertEquals(0, repo.count());
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try {
			activity = new Activity("Cool activity",
					df.parse("04/22/2019 14:00"),
					df.parse("04/22/2019 16:00"),
					null,
					"VVSS lab");
			repo.addActivity(activity);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		assertEquals(1, repo.count());
	}

	@Test
	public void testCoolActivityWithConflict() {
		assertEquals(0, repo.count());
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try {
			activity = new Activity("Less cool activity",
					df.parse("04/22/2019 13:00"),
					df.parse("04/22/2019 15:00"),
					null,
					"Some lab");
			repo.addActivity(activity);

			assertEquals(1, repo.count());

			activity = new Activity("Cool activity",
					df.parse("04/22/2019 14:00"),
					df.parse("04/22/2019 16:00"),
					null,
					"VVSS lab");
			repo.addActivity(activity);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		assertEquals(1, repo.count());
	}

	@Test
	public void testCoolActivityWithoutConflict() {
		assertEquals(0, repo.count());
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try {
			activity = new Activity("Nice activity",
					df.parse("04/22/2019 10:00"),
					df.parse("04/22/2019 12:00"),
					null,
					"VVSS Seminar");
			repo.addActivity(activity);

			assertEquals(1, repo.count());

			activity = new Activity("Okay activity",
					df.parse("04/22/2019 18:00"),
					df.parse("04/22/2019 20:00"),
					null,
					"PPD lab");
			repo.addActivity(activity);

			assertEquals(2, repo.count());

			activity = new Activity("Cool activity",
					df.parse("04/22/2019 14:00"),
					df.parse("04/22/2019 16:00"),
					null,
					"VVSS lab");
			repo.addActivity(activity);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		assertEquals(3, repo.count());
	}
}
