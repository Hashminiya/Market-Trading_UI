package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.Presenter.AssignOwnerPresenter;

@Route("settings/assign owner")
public class AssignOwnerView extends MainSettingView {

    private AssignOwnerPresenter assignOwnerPresenter;
    private final TextField userName;
    private final TextField storeNumber;

    public AssignOwnerView() {
        assignOwnerPresenter = new AssignOwnerPresenter(this);
        userName = new TextField("User Name");
        storeNumber = new TextField("Store Number");

        Button assignOwnerButton = new Button("Assign owner");
        assignOwnerButton.addClickListener(event -> assignOwnerPresenter.onAssignOwner(userName.getValue(),storeNumber.getValue()));
        assignOwnerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        VerticalLayout formLayout = new VerticalLayout(userName, storeNumber, assignOwnerButton);
        formLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        formLayout.setSpacing(true);
        add(formLayout);
    }

    public void showNotification(String message) {
        Notification.show(message);
    }
}
