package sk.seges.contapp.mobile.client;

import sk.seges.contapp.client.place.HomePlace;
import sk.seges.contapp.mobile.client.action.ActionNames;
import sk.seges.contapp.mobile.client.event.ActionEvent;
import sk.seges.contapp.mobile.client.event.AnimationSelectedEvent;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.History;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.googlecode.mgwt.dom.client.event.mouse.HandlerRegistrationCollection;
import com.googlecode.mgwt.mvp.client.history.HistoryHandler;
import com.googlecode.mgwt.mvp.client.history.HistoryObserver;
import com.googlecode.mgwt.ui.client.MGWT;

public class AppHistoryObserver implements HistoryObserver {

	@Override
	public void onPlaceChange(Place place, HistoryHandler handler) {

	}

	@Override
	public void onHistoryChanged(Place place, HistoryHandler handler) {

	}

	@Override
	public void onAppStarted(Place place, HistoryHandler historyHandler) {
		historyHandler.replaceCurrentPlace(place);
	}

	@Override
	public HandlerRegistration bind(EventBus eventBus, final HistoryHandler historyHandler) {

		HandlerRegistration addHandler = eventBus.addHandler(AnimationSelectedEvent.getType(), new AnimationSelectedEvent.Handler() {

			@Override
			public void onAnimationSelected(AnimationSelectedEvent event) {

				Place place = new HomePlace();

				if (MGWT.getOsDetection().isTablet()) {

					historyHandler.replaceCurrentPlace(place);
					historyHandler.goTo(place, true);
				} else {
					historyHandler.goTo(place);
				}

			}
		});

		HandlerRegistration register2 = ActionEvent.register(eventBus, ActionNames.BACK, new ActionEvent.Handler() {

			@Override
			public void onAction(ActionEvent event) {

				History.back();

			}
		});

		HandlerRegistration register = ActionEvent.register(eventBus, ActionNames.ANIMATION_END, new ActionEvent.Handler() {

			@Override
			public void onAction(ActionEvent event) {
				if (MGWT.getOsDetection().isPhone()) {
					History.back();
				} else {
					historyHandler.goTo(new HomePlace(), true);
				}

			}
		});

		HandlerRegistrationCollection col = new HandlerRegistrationCollection();
		col.addHandlerRegistration(register);
		col.addHandlerRegistration(register2);
		col.addHandlerRegistration(addHandler);
		return col;
	}

}
