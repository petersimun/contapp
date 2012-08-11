package sk.seges.contapp.mobile.client.configuration;

import sk.seges.contapp.client.configuration.ActivityRegistry;
import sk.seges.contapp.client.configuration.DefaultActivityRegistry;
import sk.seges.contapp.mobile.client.MobileAnimationMapper;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceChangeEvent.Handler;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.mvp.client.AnimatableDisplay;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;

public class MobileActivityRegistry extends DefaultActivityRegistry implements ActivityRegistry {
	
	public MobileActivityRegistry(EventBus eventBus) {
		super(eventBus);
	}
	
	@Override
	public PlaceChangeEvent.Handler registerActivityMapper(ActivityMapper activityMapper, IsWidget container) {

		if (container instanceof AnimatableDisplay) {
			MobileAnimationMapper appAnimationMapper = new MobileAnimationMapper();
			AnimatingActivityManager activityManager = new AnimatingActivityManager(activityMapper, appAnimationMapper,
					eventBus);
	
			activityManager.setDisplay((AnimatableDisplay)container);
			
			return activityManager;
		}
		
		return super.registerActivityMapper(activityMapper, container);
	}

	@Override
	public void unregisterHandler(Handler handler) {
		if (handler instanceof AnimatingActivityManager) {
			((AnimatingActivityManager)handler).setDisplay(null);
		}
		
		super.unregisterHandler(handler);
	}
}