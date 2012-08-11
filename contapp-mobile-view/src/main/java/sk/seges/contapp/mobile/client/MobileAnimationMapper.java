package sk.seges.contapp.mobile.client;

import sk.seges.contapp.client.place.ContactDetailPlace;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationMapper;

public class MobileAnimationMapper implements AnimationMapper {

	@Override
	public Animation getAnimation(Place oldPlace, Place newPlace) {
		if (oldPlace instanceof ContactDetailPlace) {
			return Animation.POP;
		}
		
		return Animation.POP;
	}
}