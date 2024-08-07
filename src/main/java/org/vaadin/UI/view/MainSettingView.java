package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.presenter.MainSettingsPresenter;

@Route("settings")
public class MainSettingView extends ViewTemplate {

    private MainSettingsPresenter mainSettingsPresenter;
    private VerticalLayout rightContent;
    private boolean isAdmin = Credentials.getUserName().equals("admin");

    public MainSettingView() {
        mainSettingsPresenter = new MainSettingsPresenter(this);
        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setSizeFull();

        VerticalLayout leftBar = createLeftBar();
        leftBar.setWidth("200px");
        mainLayout.add(leftBar);

        rightContent = new VerticalLayout();
        rightContent.setSizeFull();
        mainLayout.add(rightContent);

        mainLayout.expand(rightContent);

        add(mainLayout);
    }

    public void setUp() {
        // Setup logic if required
    }

    private VerticalLayout createLeftBar() {
        VerticalLayout leftBar = new VerticalLayout();
        leftBar.setSpacing(true);
        leftBar.setPadding(true);

        Button inventoryButton = new Button("Inventory", new Icon(VaadinIcon.SHOP));
        inventoryButton.setWidthFull();
        inventoryButton.addClickListener(event -> {
            mainSettingsPresenter.onInventoryButtonClick();
            getUI().ifPresent(ui -> ui.navigate("settings/inventory"));
        });

        Button purchaseHistoryButton = new Button("Purchases", new Icon(VaadinIcon.SHOP));
        purchaseHistoryButton.setWidthFull();
        purchaseHistoryButton.addClickListener(event -> {
            mainSettingsPresenter.onPurchaseHistoryButton();
            getUI().ifPresent(ui -> ui.navigate("settings/purchases"));
        });

        Button manageStoreButton = new Button("Manage Store", new Icon(VaadinIcon.COGS));
        manageStoreButton.setWidthFull();
        manageStoreButton.addClickListener(event -> {
            mainSettingsPresenter.onStoreManagementButtonClick();
            getUI().ifPresent(ui -> ui.navigate("settings/store-setting"));
        });

        Button assignOwnerButton = new Button("Assign owner", new Icon(VaadinIcon.COGS));
        assignOwnerButton.setWidthFull();
        assignOwnerButton.addClickListener(event -> {
            mainSettingsPresenter.onStoreManagementButtonClick();
            getUI().ifPresent(ui -> ui.navigate("settings/assign owner"));
        });

        Button assignManagerButton = new Button("Assign manager", new Icon(VaadinIcon.COGS));
        assignManagerButton.setWidthFull();
        assignManagerButton.addClickListener(event -> {
            mainSettingsPresenter.onStoreManagementButtonClick();
            getUI().ifPresent(ui -> ui.navigate("settings/assign manager"));
        });

        Button marketHistoryButton = new Button("Market History", new Icon(VaadinIcon.TIME_BACKWARD));
        marketHistoryButton.setWidthFull();
        marketHistoryButton.addClickListener(event -> {
            mainSettingsPresenter.onMarketHistoryButtonClick();
            getUI().ifPresent(ui -> ui.navigate("settings/market-history"));
        });

        Button policyButton = new Button("Policies", new Icon(VaadinIcon.LIST_OL));
        policyButton.setWidthFull();
        policyButton.addClickListener(event -> {
            mainSettingsPresenter.onPolicyButtonClick();
            getUI().ifPresent(ui -> ui.navigate("settings/policies"));
        });

        Button discountButton = new Button("Discounts", new Icon(VaadinIcon.BOOK_PERCENT));
        discountButton.setWidthFull();
        discountButton.addClickListener(event -> {
            mainSettingsPresenter.onDiscountButtonClick();
            getUI().ifPresent(ui -> ui.navigate("settings/discounts"));
        });

        // Add buttons to the layout
        leftBar.add(inventoryButton, purchaseHistoryButton, manageStoreButton, assignOwnerButton, assignManagerButton, policyButton , discountButton);
        if (isAdmin) {
            leftBar.add(marketHistoryButton);
        }
        return leftBar;
    }

    protected VerticalLayout getRightContent() {
        return rightContent;
    }
}
