package sk.seges.contapp.desktop.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractView extends SimpleLayoutPanel {

	private boolean widgetInitializing;
	private boolean widgetInitialized;

	@Override
	protected void onLoad() {

		ensureWidgetInitialized();
		super.onLoad();
	}

	private void ensureWidgetInitialized() {
		if (widgetInitializing || widgetInitialized) {
			return;
		}

		widgetInitializing = true;

		asyncOnInitialize(new AsyncCallback<Widget>() {
			public void onFailure(Throwable reason) {
				widgetInitializing = false;
				widgetInitialized = false;
				Window.alert("Failed to download code for this widget ("
						+ reason + ")");
			}

			public void onSuccess(Widget result) {

				Widget widget = result;
				setWidget(widget);
				onInitializeComplete();

				widgetInitializing = false;
				widgetInitialized = true;
			}
		});
	}

	public void onInitializeComplete() {
	}

	protected void asyncOnInitialize(final AsyncCallback<Widget> callback) {
		GWT.runAsync(new RunAsyncCallback() {

			@Override
			public void onFailure(Throwable caught) {
				callback.onFailure(caught);
			}

			@Override
			public void onSuccess() {
				callback.onSuccess(onInitialize());
			}
		});
	}

	public abstract Widget onInitialize();

}
