package org.vaadin.UI.presenter;

import com.vaadin.flow.component.UI;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.models.CreateStoreModel;
import org.vaadin.UI.view.CreateStoreView;
import org.vaadin.UI.presenter.Interfaces.IPresenter;

public class CreateStorePresenter implements IPresenter {

    private final CreateStoreView view;
    private final CreateStoreModel model;

    public CreateStorePresenter(CreateStoreView view) {
        this.view = view;
        model = new CreateStoreModel();
    }

    public void onCreateStore(String storeName, String storeDescription) {
        String result = model.createStore(Credentials.getToken(), storeName, storeDescription);
        view.showNotification(result);
        if (result.equals("Store created successfully")) {
            UI.getCurrent().navigate("settings/inventory");
        }
    }

    @Override
    public void onViewLoaded() {

    }

    @Override
    public void onViewStopped() {

    }
}
