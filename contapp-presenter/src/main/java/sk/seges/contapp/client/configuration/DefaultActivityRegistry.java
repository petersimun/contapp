package sk.seges.contapp.client.configuration;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceChangeEvent.Handler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

public class DefaultActivityRegistry implements ActivityRegistry {

	protected final EventBus eventBus;
	
	public DefaultActivityRegistry(EventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	@Override
	public PlaceChangeEvent.Handler registerActivityMapper(ActivityMapper activityMapper, IsWidget container) {
		if (container instanceof AcceptsOneWidget) {
	        ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
	        activityManager.setDisplay((AcceptsOneWidget) container);
	        return activityManager;
		}
		
		return null;
	}

	@Override
	public void unregisterHandler(Handler handler) {
		if (handler instanceof ActivityManager) {
			((ActivityManager)handler).setDisplay(null);
		}
	}

}
