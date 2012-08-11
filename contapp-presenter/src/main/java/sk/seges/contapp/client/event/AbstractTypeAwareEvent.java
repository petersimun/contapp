package sk.seges.contapp.client.event;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public abstract class AbstractTypeAwareEvent<H extends EventHandler> extends GwtEvent<H> {

    private static Map<Class<?>, Type<?>> types = new HashMap<Class<?>, Type<?>>();

    @SuppressWarnings("unchecked")
    private static <S> com.google.gwt.event.shared.GwtEvent.Type<S> cast(com.google.gwt.event.shared.GwtEvent.Type<?> type) {
            return (com.google.gwt.event.shared.GwtEvent.Type<S>)type;
    }

    private static <S> com.google.gwt.event.shared.GwtEvent.Type<?> uncast(com.google.gwt.event.shared.GwtEvent.Type<S> type) {
            return (com.google.gwt.event.shared.GwtEvent.Type<?>)type;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static <S, T extends EventHandler> com.google.gwt.event.shared.GwtEvent.Type<T> getAssociatedType(Class<S> clazz) {
            com.google.gwt.event.shared.GwtEvent.Type<?> type = cast(types.get(clazz));
            if (type == null) {
                    type = new com.google.gwt.event.shared.GwtEvent.Type();
                    types.put(clazz, uncast(type));
            }

            return (com.google.gwt.event.shared.GwtEvent.Type<T>) type;
    }

}