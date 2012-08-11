package sk.seges.contapp.client.activity;

import java.util.ArrayList;
import java.util.List;

import sk.seges.contapp.client.configuration.ClientFactory;
import sk.seges.contapp.shared.service.ServiceFactory;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.user.client.ui.IsWidget;

public abstract class ContappActivity<View> extends AbstractActivity {

        protected List<HandlerRegistration> handlers = new ArrayList<HandlerRegistration>();

        protected final ClientFactory clientFactory;
        private View view;

        private List<PlaceChangeEvent.Handler> activityManagers = new ArrayList<PlaceChangeEvent.Handler>();

        protected ContappActivity(ClientFactory clientFactory) {
                this.clientFactory = clientFactory;
        }

        protected void registerActivityMapper(ActivityMapper activityMapper, IsWidget container) {
        	activityManagers.add(clientFactory.getActivityRegistry().registerActivityMapper(activityMapper, container));
        }

        protected ServiceFactory getServices() {
                return clientFactory.getServices();
        }

        protected void setView(View view) {
                this.view = view;
        }

        public View getView() {
                return view;
        }

        protected void register(HandlerRegistration handler) {
                handlers.add(handler);
        }

        protected void deregister() {
            for (HandlerRegistration handler : handlers) {
                    handler.removeHandler();
            }
            for (PlaceChangeEvent.Handler activityManager: activityManagers) {
            	clientFactory.getActivityRegistry().unregisterHandler(activityManager);
            }
            activityManagers.clear();
            handlers.clear();
    }

    @Override
    public void onStop() {
            deregister();
    }

    public void goTo(Place place) {
            clientFactory.getPlaceController().goTo(place);
    }
}
