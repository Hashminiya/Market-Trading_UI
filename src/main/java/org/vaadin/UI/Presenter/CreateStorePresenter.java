package org.vaadin.UI.Presenter;
import org.vaadin.UI.model.models.CreateStoreModel;
import org.vaadin.UI.view.CreateStoreView;
import org.vaadin.UI.presenter.Interfaces.IPresenter;

public class CreateStorePresenter implements IPresenter {

    private final CreateStoreView view;
    private final CreateStoreModel model;
    private String token;

    public CreateStorePresenter(CreateStoreView view) {
        this.view = view;
        model = new CreateStoreModel();
    }


    public void onCreateStore(String storeName, String storeDescription) {
        String result = model.createStore(token, storeName, storeDescription);
        view.showNotification(result);
    }

    @Override
    public void onViewLoaded() {

    }

    @Override
    public void onViewStopped() {

    }
}
