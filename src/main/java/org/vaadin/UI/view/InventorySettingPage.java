package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("settings/inventory")
public class InventorySettingPage  extends MainSettingPage {

    public InventorySettingPage() {
        HorizontalLayout restOfPage = new HorizontalLayout();
        restOfPage.add(createLeftBar());
        restOfPage.add(createManagerSettings());
        add(restOfPage);
    }

    private VerticalLayout createLeftBar() {
        VerticalLayout leftBar = new VerticalLayout();
        leftBar.setSpacing(true);
        leftBar.setPadding(true);

        // Inventory Button
        Button inventoryButton = new Button("Inventory", new Icon(VaadinIcon.CART));
        inventoryButton.setWidthFull();

        // About Button
        Button aboutButton = new Button("About", new Icon(VaadinIcon.INFO_CIRCLE));
        aboutButton.setWidthFull();

        // Admin Button
        Button adminButton = new Button("Admin", new Icon(VaadinIcon.USER_STAR));
        adminButton.setWidthFull();

        // Logout Button
        Button logoutButton = new Button("Logout", new Icon(VaadinIcon.SIGN_OUT));
        logoutButton.setWidthFull();

        // Add buttons to the layout
        leftBar.add(inventoryButton, aboutButton, adminButton, logoutButton);

        return leftBar;
    }

    private VerticalLayout createManagerSettings() {
        VerticalLayout settingsLayout = new VerticalLayout();

        // Manager Info Section
        HorizontalLayout managerInfoLayout = new HorizontalLayout();

        VerticalLayout leftColumn = new VerticalLayout();
        TextField managerNameField = new TextField("Manager Name");
        TextField managerEmailField = new TextField("Manager Email");
        PasswordField newPasswordField = new PasswordField("New Password");
        leftColumn.add(managerNameField, managerEmailField, newPasswordField);

        VerticalLayout rightColumn = new VerticalLayout();
        TextField storeNameField = new TextField("Store Name");
        TextField managerCodeField = new TextField("Manager Code");
        TextField currentRoleField = new TextField("Current Role");
        rightColumn.add(storeNameField, managerCodeField, currentRoleField);

        managerInfoLayout.add(leftColumn, rightColumn);
        settingsLayout.add(managerInfoLayout);

        // Permissions Section
        HorizontalLayout permissionsLayout = new HorizontalLayout();

        // Left Column (Manager Permissions)
        VerticalLayout permissionsLeftColumn = new VerticalLayout();
        permissionsLeftColumn.add(new Checkbox("Store Manager Permissions"));
        permissionsLeftColumn.add(new Checkbox("Product Editor"));
        permissionsLeftColumn.add(new Checkbox("Select Manager"));
        permissionsLeftColumn.add(new Checkbox("Manager Options"));
        permissionsLeftColumn.add(new Checkbox("Store Admin"));
        permissionsLeftColumn.add(new Checkbox("Financial Admin"));

        // Right Column (Manager Actions)
        VerticalLayout permissionsRightColumn = new VerticalLayout();
        for (String action : new String[]{"View", "Edit", "Create", "Approval"}) {
            HorizontalLayout actionLayout = new HorizontalLayout();
            actionLayout.add(new Checkbox(action));
            permissionsRightColumn.add(actionLayout);
        }

        permissionsLayout.add(permissionsLeftColumn, permissionsRightColumn);
        settingsLayout.add(permissionsLayout);

        // Update Permissions Button
        Button updatePermissionsButton = new Button("Update Permissions");
        settingsLayout.add(updatePermissionsButton);

        return settingsLayout;
    }
}
