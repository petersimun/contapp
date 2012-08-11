package sk.seges.contapp.client.display;

import java.util.List;

import sk.seges.contapp.client.event.handler.ListViewEventHandler;

import com.google.gwt.event.shared.HandlerRegistration;

public interface TableDisplay {

	HandlerRegistration registerListViewHandler(ListViewEventHandler handler, Class<?> clazz);

	<T> void setData(List<T> data, Class<T> clazz, int totalSize);
}