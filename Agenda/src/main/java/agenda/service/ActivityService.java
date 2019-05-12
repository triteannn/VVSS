package agenda.service;

import agenda.model.Activity;
import agenda.model.Contact;
import agenda.repository.classes.RepositoryActivityMock;

import java.util.Date;
import java.util.List;

public class ActivityService implements IActivityService {
    private RepositoryActivityMock repositoryActivityMock;

    public ActivityService() {
        this.repositoryActivityMock = new RepositoryActivityMock();
    }

    @Override
    public List<Activity> getAll() {
        return repositoryActivityMock.getActivities();
    }

    @Override
    public Activity addActivity(String name, Date startDate, Date endDate, List<Contact> contacts,
                               String description) {
        Activity activity = new Activity(name, startDate, endDate, contacts, description);
        return this.repositoryActivityMock.addActivity(activity);
    }

    @Override
    public int count() {
        return this.repositoryActivityMock.count();
    }

    @Override
    public List<Activity> getActivitiesByName(String name) {
        return this.repositoryActivityMock.activitiesByName(name);
    }

    @Override
    public List<Activity> getActivitiesByDate(String name, Date date) {
        return this.repositoryActivityMock.activitiesOnDate(name, date);
    }
}
