package org.vaadin.UI.presenter;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.ViewTemplate;

public class ViewTemplatePresenter implements IPresenter {

    private final ViewTemplate view;

    public ViewTemplatePresenter(ViewTemplate view) {
        this.view = view;
    }

    @Override
    public void onViewLoaded() {

    }

    @Override
    public void onViewStopped() {

    }
}
