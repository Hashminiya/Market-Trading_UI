package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.model.DTOs.PolicyViewDTO;
import org.vaadin.UI.presenter.PolicyPresenter;
import org.vaadin.UI.view.AddPolicy.AddPolicyDialog;

import java.util.List;
@Route("settings/policies")
public class PolicyView extends MainSettingView {
    private ComboBox<String> chooseStoreComboBox;
    private Grid<PolicyViewDTO> policiesGrid;
    private Button addNewPolicyButton;
    private PolicyPresenter presenter;

    public PolicyView(){
        presenter = new PolicyPresenter(this);

        VerticalLayout rightContent = getRightContent();
        rightContent.removeAll();

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.add(new H1("Policy"));
        chooseStoreComboBox = new ComboBox<>("Select your store");
        chooseStoreComboBox.setPlaceholder("No store selected yet");
        presenter.onChoosingStore();

        addNewPolicyButton = new Button("Add New Policy", new Icon(VaadinIcon.PLUS));
        topLayout.add(chooseStoreComboBox);
        topLayout.add(addNewPolicyButton);
        topLayout.setWidthFull();
        topLayout.setAlignItems(FlexComponent.Alignment.END);
        rightContent.add(topLayout);

        policiesGrid = createPoliciesGrid();
        policiesGrid.setWidthFull();
        rightContent.add(policiesGrid);

        chooseStoreComboBox.addValueChangeListener(event -> {
            String selectedOptionStoreName = event.getValue();
            presenter.onSelectStore(selectedOptionStoreName);
        });

        addNewPolicyButton.addClickListener(event -> {
            AddPolicyDialog dialog = new AddPolicyDialog(presenter, null);
            dialog.open();
        });
    }

    public void fillUpPolicies(List<PolicyViewDTO> storeItems) {
        policiesGrid.setItems(storeItems);
    }

    public void fillChooseStoreComboBox(List<String> stores) {
        chooseStoreComboBox.setItems(stores);
    }

    public void showNotification(String message) {
        Notification.show(message);
    }

    private Grid<PolicyViewDTO> createPoliciesGrid() {
        Grid<PolicyViewDTO> grid = new Grid<>(PolicyViewDTO.class);
        return grid;
    }
}
