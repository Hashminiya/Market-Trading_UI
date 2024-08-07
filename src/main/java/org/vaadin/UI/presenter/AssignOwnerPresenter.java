package org.vaadin.UI.presenter;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.models.AssignOwnerModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.AssignOwnerView;

public class AssignOwnerPresenter implements IPresenter {

    private final AssignOwnerView view;
    private final AssignOwnerModel model;

    public AssignOwnerPresenter(AssignOwnerView view) {
        this.view = view;
        model = new AssignOwnerModel();
    }


    public void onAssignOwner(String userName, String storeName) {
        String result = model.assignOwner(Credentials.getToken(), userName, storeName);
        view.showNotification(result);
    }

    @Override
    public void onViewLoaded() {

    }

    @Override
    public void onViewStopped() {

    }
}
