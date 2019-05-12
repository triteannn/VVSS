package agenda.test;

import agenda.model.Activity;
import agenda.model.Contact;
import agenda.service.IActivityService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CStub implements IActivityService {

    private List<Activity> list;

    public CStub() {
        list = new ArrayList<Activity>();
    }

    @Override
    public List<Activity> getAll() {
        return list;
    }

    @Override
    public Activity addActivity(String name, Date startDate, Date endDate, List<Contact> contacts, String description) {
        Activity activity = new Activity(name, startDate, endDate, contacts, description);
        this.list.add(activity);
        return activity;
    }

    @Override
    public int count() {
        return this.list.size();
    }

    @Override
    public List<Activity> getActivitiesByName(String name) {
        return null;
    }

    @Override
    public List<Activity> getActivitiesByDate(String name, Date date) {

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        Activity act = null;
        try {
            act = new Activity("Cool activity",
                    df.parse("04/22/2019 14:00"),
                    df.parse("04/22/2019 16:00"),
                    null,
                    name);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Activity> list = new ArrayList<Activity>();
        list.add(act);
        return list;
    }
}
