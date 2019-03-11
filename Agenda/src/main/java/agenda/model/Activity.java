package agenda.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import agenda.repository.interfaces.RepositoryContact;

public class Activity {
	private String name;
	private Date startDate;
	private Date endDate;
	private List<Contact> contacts;
	private String description;
	
	public Activity(String name, Date startDate, Date endDate, List<Contact> contacts,
					String description) {
		this.name = name;
		this.description = description;
		if (contacts == null)
			this.contacts = new LinkedList<Contact>();
		else
			this.contacts = new LinkedList<Contact>(contacts);

		this.startDate = new Date();
		this.startDate.setTime(startDate.getTime());
		this.endDate = new Date();
		this.endDate.setTime(endDate.getTime());
	}

	public String getName() {
		return name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Activity))
			return false;
		Activity act = (Activity) obj;
		if (act.description.equals(description) && startDate.equals(act.startDate)
				&& endDate.equals(act.endDate) && name.equals(act.name))
			return true;
		return false;
	}

	public boolean intersect(Activity act) {
		if (startDate.compareTo(act.endDate) < 0
				&& act.startDate.compareTo(endDate) < 0)
			return true;
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append("#");
		sb.append(startDate.getTime());
		sb.append("#");
		sb.append(endDate.getTime());
		sb.append("#");
		sb.append(description);
		sb.append("#");
		for (Contact c : contacts) {
			sb.append("#");
			sb.append(c.getName());
		}
		return sb.toString();
	}

	public static Activity fromString(String line, RepositoryContact repcon) {
		try {
			String[] str = line.split("#");
			String name = str[0];
			Date start = new Date(Long.parseLong(str[1]));
			Date end = new Date(Long.parseLong(str[2]));
			String description = str[3];
			List<Contact> conts = new LinkedList<Contact>();
			for (int i = 5; i < str.length; i++) {
				conts.add(repcon.getByName(str[i]));
			}
			return new Activity(name, start, end, conts, description);
		} catch (Exception e) {
			return null;
		}
	}
}
