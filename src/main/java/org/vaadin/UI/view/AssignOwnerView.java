package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.presenter.AssignOwnerPresenter;

@Route("settings/assign owner")
public class AssignOwnerView extends MainSettingView {

    private AssignOwnerPresenter assignOwnerPresenter;
    private final TextField userName;
    private final TextField storeName;

    public AssignOwnerView() {
        assignOwnerPresenter = new AssignOwnerPresenter(this);
        userName = new TextField("User Name");
        storeName = new TextField("Store Name");

        Button assignOwnerButton = new Button("Assign owner");
        assignOwnerButton.addClickListener(event -> assignOwnerPresenter.onAssignOwner(userName.getValue(),storeName.getValue()));
        assignOwnerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        VerticalLayout formLayout = new VerticalLayout(userName, storeName, assignOwnerButton);
        formLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        formLayout.setSpacing(true);
        VerticalLayout rightContent = getRightContent();
        rightContent.removeAll();
        rightContent.add(formLayout);
//        add(formLayout);
    }

    public void showNotification(String message) {
        Notification.show(message);
    }
}
