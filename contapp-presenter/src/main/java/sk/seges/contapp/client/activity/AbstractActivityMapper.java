package sk.seges.contapp.client.activity;

import sk.seges.contapp.client.configuration.ClientFactory;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public abstract class AbstractActivityMapper implements ActivityMapper {

    protected Activity currentActivity;
    protected ClientFactory clientFactory;

    public AbstractActivityMapper(ClientFactory clientFactory) {
            super();
            this.clientFactory = clientFactory;
    }

    protected abstract Activity getRelevantActivity(Place place);

    @Override
    public final Activity getActivity(Place place) {
            Activity relevantActivity = getRelevantActivity(place);

            if (relevantActivity != null) {
                    currentActivity = relevantActivity;
            }

            return currentActivity;
    }
}

