package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.Presenter.CreateStorePresenter;

@Route("createStore")
public class CreateStoreView extends ViewTemplate {

    private CreateStorePresenter createStorePresenter;
    private final TextField storeName;
    private final TextField storeDescription;

    public CreateStoreView() {
        createStorePresenter = new CreateStorePresenter(this);
        storeName = new TextField("Store name");
        storeDescription = new TextField("Store description");

        Button createStoreButton = new Button("Create Store");
        createStoreButton.addClickListener(event -> createStorePresenter.onCreateStore(storeName.getValue(),storeDescription.getValue()));
        createStoreButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        VerticalLayout formLayout = new VerticalLayout(storeName, storeDescription, createStoreButton);
        add(formLayout);
    }

    public void showNotification(String message) {
        Notification.show(message);
    }
}
