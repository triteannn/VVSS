package agenda.service;

import agenda.model.Activity;
import agenda.model.Contact;

import java.util.Date;
import java.util.List;

public interface IActivityService {

    List<Activity> getAll();

    Activity addActivity(String name, Date startDate, Date endDate, List<Contact> contacts,
                               String description);

    int count();

    List<Activity> getActivitiesByName(String name);

    List<Activity> getActivitiesByDate(String name, Date date);
}
