package sk.seges.contapp.client.activity;

import sk.seges.contapp.client.place.HomePlace.HomePlaceTokenizer;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ HomePlaceTokenizer.class})
public interface ContappPlaceHistoryMapper extends PlaceHistoryMapper {}