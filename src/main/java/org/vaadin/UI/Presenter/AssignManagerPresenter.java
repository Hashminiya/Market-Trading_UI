package org.vaadin.UI.Presenter;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.models.AssignManagerModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.AssignManagerView;

import java.util.Set;

public class AssignManagerPresenter implements IPresenter{

    private final AssignManagerView view;
    private final AssignManagerModel model;

    public AssignManagerPresenter(AssignManagerView view) {
        this.view = view;
        model = new AssignManagerModel();
    }


    public void onAssignManager(String userName, String storeName, Set<String> permissions) {
        String result = model.assignManager(Credentials.getToken(), userName, storeName, permissions);
        view.showNotification(result);
    }

    @Override
    public void onViewLoaded() {

    }

    @Override
    public void onViewStopped() {

    }
}
