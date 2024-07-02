package org.vaadin.UI.Presenter;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.models.AssignOwnerModel;
import org.vaadin.UI.Presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.AssignOwnerView;

public class AssignOwnerPresenter implements IPresenter {

    private final AssignOwnerView view;
    private final AssignOwnerModel model;

    public AssignOwnerPresenter(AssignOwnerView view) {
        this.view = view;
        model = new AssignOwnerModel();
    }


    public void onAssignOwner(String userName, String storeNumber) {
        String result = model.assignOwner(Credentials.getToken(), userName, storeNumber);
        view.showNotification(result);
    }

    @Override
    public void onViewLoaded() {

    }

    @Override
    public void onViewStopped() {

    }
}
