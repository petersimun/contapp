package sk.seges.contapp.client.callback;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class DefaultAsyncCallback<T> implements AsyncCallback<T> {

	@Override
	public void onFailure(Throwable arg0) {
		Window.alert(arg0.getMessage());
	}

}
