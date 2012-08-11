package sk.seges.contapp.client.configuration;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.user.client.ui.IsWidget;

public interface ActivityRegistry {

	PlaceChangeEvent.Handler registerActivityMapper(ActivityMapper activityMapper, IsWidget container);
	
	void unregisterHandler(PlaceChangeEvent.Handler handler);
}
