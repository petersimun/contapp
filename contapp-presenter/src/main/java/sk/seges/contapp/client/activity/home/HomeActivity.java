package sk.seges.contapp.client.activity.home;

import java.util.List;

import sk.seges.contapp.client.activity.ContappActivity;
import sk.seges.contapp.client.activity.DetailActivityMapper;
import sk.seges.contapp.client.callback.DefaultAsyncCallback;
import sk.seges.contapp.client.configuration.ClientFactory;
import sk.seges.contapp.client.display.HomeDisplay;
import sk.seges.contapp.client.event.NavigationEvent;
import sk.seges.contapp.client.event.SelectionChangedEvent;
import sk.seges.contapp.client.place.ContactDetailPlace;
import sk.seges.contapp.shared.model.Contact;
import sk.seges.sesam.dao.Page;
import sk.seges.sesam.dao.PagedResult;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class HomeActivity extends ContappActivity<HomeDisplay> implements HomeDisplay.Presenter {

	public HomeActivity(ClientFactory clientFactory) {
		super(clientFactory);
		clientFactory.getEventBus().addHandler(NavigationEvent.TYPE, this);
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
        setView(clientFactory.getViews().getHomeView());

        panel.setWidget(getView().asWidget());
        
        clientFactory.getActivityRegistry().registerActivityMapper(new DetailActivityMapper(clientFactory), 
        		clientFactory.getViews().getContactDetailHolder());
		
        getServices().getContactService().getContacts(Page.ALL_RESULTS_PAGE, new DefaultAsyncCallback<PagedResult<List<Contact>>>() {

			@Override
			public void onSuccess(PagedResult<List<Contact>> result) {
				getView().setData(result.getResult(), Contact.class, result.getTotalResultCount());
				register(getView().addSelectionHandler(HomeActivity.this, Contact.class));
			}
        });
	}

	@Override
	public void onSelectionChanged(SelectionChangedEvent<?> event) {
		List<?> selection = event.getSelection();
		
		if (event.getSelectionClass().equals(Contact.class) && selection.size() > 0) {
			goTo(new ContactDetailPlace((Contact) selection.get(0)));
		}
	}

	@Override
	public void onNavigation(Place place) {
		goTo(place);
	}
}