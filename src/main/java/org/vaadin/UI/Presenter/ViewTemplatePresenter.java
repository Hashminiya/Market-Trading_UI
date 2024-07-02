package org.vaadin.UI.Presenter;
import org.vaadin.UI.Presenter.Interfaces.IPresenter;
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
