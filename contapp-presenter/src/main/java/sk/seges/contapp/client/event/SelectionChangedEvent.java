package sk.seges.contapp.client.event;

import java.util.ArrayList;
import java.util.List;

import sk.seges.contapp.client.event.handler.SelectionChangedEventHandler;

public class SelectionChangedEvent<T> extends AbstractTypeAwareEvent<SelectionChangedEventHandler> {

        private final List<T> ts;
        private final Class<?> clazz;

        public SelectionChangedEvent(T t, Class<?> clazz) {
        	this.ts = new ArrayList<T>();
        	this.ts.add(t);
        	this.clazz = clazz;
        }
        
        public SelectionChangedEvent(List<T> ts, Class<?> clazz) {
        	this.ts = ts;
        	this.clazz = clazz;
        }

        public List<T> getSelection() {
        	return ts;
        }
        
        public Class<?> getSelectionClass() {
			return clazz;
		}
        
        @Override
        protected void dispatch(SelectionChangedEventHandler handler) {
                handler.onSelectionChanged(this);
        }

        @Override
        public com.google.gwt.event.shared.GwtEvent.Type<SelectionChangedEventHandler> getAssociatedType() {
                return getAssociatedType(clazz);
        }

}