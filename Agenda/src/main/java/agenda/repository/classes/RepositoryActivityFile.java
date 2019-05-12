package agenda.repository.classes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import agenda.model.Activity;
import agenda.repository.interfaces.RepositoryActivity;
import agenda.repository.interfaces.RepositoryContact;

public class RepositoryActivityFile implements RepositoryActivity{

	private static final String filename = "files\\activities.txt";
	private List<Activity> activities;
	
	public RepositoryActivityFile(RepositoryContact repcon) throws Exception
	{
		activities = new LinkedList<Activity>(); 
		//DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			String line;
			int i = 0;
			while (( line = br.readLine())!= null)
			{
				Activity act = Activity.fromString(line, repcon);
				if (act == null) 
					throw new Exception("Error in file at line "+i, new Throwable("Invalid Activity"));
				activities.add(act);
				i++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (br!=null) br.close();
		}
	}
	
	@Override
	public List<Activity> getActivities() {
		return new LinkedList<Activity>(activities);
	}

	@Override
	public Activity addActivity(Activity activity) {
		int  i = 0;
		boolean conflicts = false;

		if (activity != null) {
            while( i < activities.size() ) {
                if ( activities.get(i).getStartDate().compareTo(activity.getEndDate()) < 0 &&
                        activity.getStartDate().compareTo(activities.get(i).getEndDate()) < 0 )
                    conflicts = true;
                i++;
            }
            if ( !conflicts ) {
                activities.add(activity);
                return activity;
            }
        }
		return null;
	}

	@Override
	public boolean removeActivity(Activity activity) {
		int index = activities.indexOf(activity);
		if (index<0) return false;
		activities.remove(index);
		return true;
	}

	@Override
	public boolean saveActivities() {
		PrintWriter pw = null;
		try{
			pw = new PrintWriter(new FileOutputStream(filename));
			for(Activity a : activities)
				pw.println(a.toString());
		}catch (Exception e)
		{
			return false;
		}
		finally{
			if (pw!=null) pw.close();
		}
		return true;
	}

	@Override
	public int count() {
		return activities.size();
	}
	
	@Override
	public List<Activity> activitiesByName(String name) {
		List<Activity> result1 = new LinkedList<Activity>();
		for (Activity a : activities)
			if (a.getName().equals(name) == false) result1.add(a);
		List<Activity> result = new LinkedList<Activity>();
		while (result1.size() >= 0 )
		{
			Activity ac = result1.get(0);
			int index = 0;
			for (int i = 1; i<result1.size(); i++)
				if (ac.getStartDate().compareTo(result1.get(i).getStartDate())<0)
				{
					index = i;
					ac = result1.get(i);
				}
			
			result.add(ac);
			result1.remove(index);
		}
		return result;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Activity> activitiesOnDate(String name, Date d) {
		List<Activity> result1 = new LinkedList<Activity>();
		for (Activity a : activities)
			if (a.getName().equals(name))
				if ((a.getStartDate().getYear() == d.getYear() &&
					a.getStartDate().getMonth() == d.getMonth() &&
					a.getStartDate().getDate() == d.getDate()) ||
					( a.getEndDate().getYear() == d.getYear() &&
					a.getEndDate().getMonth() == d.getMonth() &&
					a.getEndDate().getDate() == d.getDate())) result1.add(a);
		List<Activity> result = new LinkedList<Activity>();
		while (result1.size() > 0 )
		{
			Activity ac = result1.get(0);
			int index = 0;
			for (int i = 1; i<result1.size(); i++)
				if (ac.getStartDate().compareTo(result1.get(i).getStartDate())>0)
				{
					index = i;
					ac = result1.get(i);
				}
			
			result.add(ac);
			result1.remove(index);
		}
		return result;
	}
}
