package org.vaadin.UI.view.AddPolicy;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.Policies.AgeRestrictedPolicyDTO;
import org.vaadin.UI.model.DTOs.Policies.ComplexPolicyDto;
import org.vaadin.UI.model.DTOs.Policies.MaximumQuantityPolicyDTO;
import org.vaadin.UI.model.DTOs.Policies.PolicyDTO;
import org.vaadin.UI.presenter.PolicyPresenter;

import java.util.ArrayList;
import java.util.List;

public class AddPolicyDialog extends Dialog {
    private PolicyPresenter presenter;
    private ComboBox<String> policyTypeComboBox;
    private VerticalLayout contentLayout;
    private AddPolicyDialog parent;
    private List<PolicyDTO> policyDTOList;
    private VerticalLayout policyListLayout;
    private VerticalLayout applyOnLayout;
    private List<ItemDTO> itemsChosen;
    private List<String> categoriesChosen;
    private boolean isAllStorePolicy;
    private VerticalLayout productsListLayout;

    public AddPolicyDialog(PolicyPresenter presenter, AddPolicyDialog parent) {
        policyDTOList = new ArrayList<>();
        this.parent = parent;
        this.presenter = presenter;
        setWidth("1000px");  // Set initial width
        setHeight("800px"); // Set initial height

        contentLayout = new VerticalLayout();
        policyTypeComboBox = new ComboBox<>("Select Policy Type");
        policyTypeComboBox.setItems("Complex Policy", "Maximum Quantity Policy", "Age Restricted Policy");
        policyTypeComboBox.setPlaceholder("Select Policy Type");

        policyTypeComboBox.addValueChangeListener(event -> {
            String selectedPolicyType = event.getValue();
            switch (selectedPolicyType) {
                case "Complex Policy":
                    showComplexPolicyForm();
                    break;
                case "Maximum Quantity Policy":
                    showMaxAmountPolicyForm();
                    break;
                case "Age Restricted Policy":
                    showAgeRestrictedPolicyForm();
                    break;
                default:
                    break;
            }
        });

        add(policyTypeComboBox, contentLayout);
    }

    private String addOrSaveButton() {
        return (parent == null) ? "save" : "add";
    }

    private void showComplexPolicyForm() {
        contentLayout.removeAll();
        policyListLayout = new VerticalLayout();
        TextField nameField = new TextField("Name");
        ComboBox<String> logicalRoleField = new ComboBox<>("Logical Role");
        logicalRoleField.setItems("OR", "AND", "XOR");

        Button addSubPolicyButton = new Button("Add Sub-Policy");
        addSubPolicyButton.addClickListener(event -> {
            AddPolicyDialog dialog = new AddPolicyDialog(presenter, this);
            dialog.open();
        });

        contentLayout.add(nameField, logicalRoleField, addSubPolicyButton, policyListLayout);

        Button saveButton = new Button(addOrSaveButton());
        saveButton.addClickListener(event -> {
            ComplexPolicyDto complexPolicy = new ComplexPolicyDto();
            complexPolicy.setLogicalRole(logicalRoleField.getValue());
            complexPolicy.setPolicies(policyDTOList);
            complexPolicy.setName(nameField.getValue());

            if (parent != null) {
                parent.policyDTOList.add(complexPolicy);
            } else {
                presenter.savePolicy(complexPolicy);
            }
            close();
        });
        contentLayout.add(saveButton);
    }

    private void showMaxAmountPolicyForm() {
        contentLayout.removeAll();

        TextField nameField = new TextField("Name");
        IntegerField maxField = new IntegerField("Maximum Amount");
        applyOnLayout = applyOnSelector();
        contentLayout.add(nameField, maxField, applyOnLayout);

        Button saveButton = new Button(addOrSaveButton());
        saveButton.addClickListener(event -> {
            MaximumQuantityPolicyDTO maxAmountPolicy = new MaximumQuantityPolicyDTO();
            maxAmountPolicy.setName(nameField.getValue());
            maxAmountPolicy.setMaxAmount(maxField.getValue());
            maxAmountPolicy.setAllStorePolicy(isAllStorePolicy);
            if (itemsChosen != null) {
                maxAmountPolicy.setItems(itemsChosen.stream().map(ItemDTO::getItemId).toList());
            } else {
                maxAmountPolicy.setItems(null);
            }
            maxAmountPolicy.setCategories(categoriesChosen);

            if (parent != null) {
                parent.policyDTOList.add(maxAmountPolicy);
                parent.displayPolicyList();
            } else {
                presenter.savePolicy(maxAmountPolicy);
            }
            close();
        });
        contentLayout.add(saveButton);
    }


    private void showAgeRestrictedPolicyForm() {
        contentLayout.removeAll();
        TextField nameField = new TextField("Name");
        IntegerField ageField = new IntegerField("Minimum Age");

        applyOnLayout = applyOnSelector();
        contentLayout.add(nameField, ageField, applyOnLayout);


        Button saveButton = new Button(addOrSaveButton());
        saveButton.addClickListener(event -> {
            AgeRestrictedPolicyDTO ageRestrictedPolicy = new AgeRestrictedPolicyDTO();
            ageRestrictedPolicy.setName(nameField.getValue());
            ageRestrictedPolicy.setAge(ageField.getValue());
            ageRestrictedPolicy.setAllStorePolicy(isAllStorePolicy);
            if (itemsChosen != null) {
                ageRestrictedPolicy.setItems(itemsChosen.stream().map(ItemDTO::getItemId).toList());
            } else {
                ageRestrictedPolicy.setItems(null);
            }
            ageRestrictedPolicy.setCategories(categoriesChosen);
            // Add logic to save the age restricted policy
            if (parent != null) {
                parent.policyDTOList.add(ageRestrictedPolicy);
                parent.displayPolicyList();
            } else {
                presenter.savePolicy(ageRestrictedPolicy);
            }
            close();
        });
        contentLayout.add(saveButton);
    }

    private void displayPolicyList() {
        // Remove existing policy list display
        contentLayout.remove(policyListLayout);

        policyListLayout = new VerticalLayout(); // Initialize or clear policy list layout

        for (PolicyDTO policy : policyDTOList) {
            HorizontalLayout policyLayout = new HorizontalLayout();

            if (policy instanceof MaximumQuantityPolicyDTO) {
                MaximumQuantityPolicyDTO maxQuantityPolicy = (MaximumQuantityPolicyDTO) policy;
                policyLayout.add(new Text("Maximum Quantity Policy: " + maxQuantityPolicy.getName()));
            } else if (policy instanceof AgeRestrictedPolicyDTO) {
                AgeRestrictedPolicyDTO ageRestrictedPolicy = (AgeRestrictedPolicyDTO) policy;
                policyLayout.add(new Text("Age Restricted Policy: " + ageRestrictedPolicy.getName()));
            }
            // Add other else if conditions for other types of PolicyDTO as needed

            // Add remove button with trash can icon
            Button removeButton = new Button(new Icon(VaadinIcon.TRASH));
            removeButton.addClickListener(event -> {
                policyDTOList.remove(policy); // Remove policy from the list
                displayPolicyList(); // Refresh the policy list display
            });
            policyLayout.add(removeButton);

            policyListLayout.add(policyLayout); // Add policy layout to the list layout
        }

        contentLayout.add(policyListLayout); // Add policy list layout to the main content layout
    }

    private VerticalLayout applyOnSelector() {
        VerticalLayout layout = new VerticalLayout();
        ComboBox<String> options = new ComboBox<String>("Apply on");
        options.setItems("All Store Policy", "Specific Items", "Specific Categories");
        options.addValueChangeListener(event -> {
            String selectedPolicyType = event.getValue();
            isAllStorePolicy = false;
            itemsChosen = new ArrayList<>();
            categoriesChosen = new ArrayList<>();
            switch (selectedPolicyType) {
                case "All Store Policy":
                    isAllStorePolicy = true;
                    itemsChosen = null;
                    categoriesChosen = null;
                    break;
                case "Specific Items":
                    Dialog itemsDialog = new ChooseItemsDialog(itemsChosen, presenter, this);
                    categoriesChosen = null;
                    isAllStorePolicy = false;
                    itemsDialog.open();
                    break;
                case "Specific Categories":
                    Dialog categoryDialog = new ChooseCategoryDialog(categoriesChosen, presenter, this);
                    itemsChosen = null;
                    isAllStorePolicy = false;
                    categoryDialog.open();
                    break;
                default:
                    break;
            }
        });
        layout.add(options);
        return layout;
    }

    public void ItemsOrCategories() {
        if (productsListLayout != null) {
            contentLayout.remove(productsListLayout);
        }

        productsListLayout = new VerticalLayout();

        if (itemsChosen != null) {
            for (ItemDTO itemDTO : itemsChosen) {
                HorizontalLayout itemLayout = new HorizontalLayout();
                TextField itemName = new TextField();
                itemName.setValue(itemDTO.getItemName());
                itemName.setReadOnly(true);

                Button removeButton = new Button(new Icon(VaadinIcon.TRASH), event -> {
                    itemsChosen.remove(itemDTO);
                    ItemsOrCategories(); // Refresh the list
                });

                itemLayout.add(itemName, removeButton);
                productsListLayout.add(itemLayout);
            }
        } else if (categoriesChosen != null) {
            for (String category : categoriesChosen) {
                HorizontalLayout categoryLayout = new HorizontalLayout();
                TextField categoryName = new TextField();
                categoryName.setValue(category);
                categoryName.setReadOnly(true);

                Button removeButton = new Button(new Icon(VaadinIcon.TRASH), event -> {
                    categoriesChosen.remove(category);
                    ItemsOrCategories(); // Refresh the list
                });

                categoryLayout.add(categoryName, removeButton);
                productsListLayout.add(categoryLayout);
            }
        }
        contentLayout.add(productsListLayout);
    }
}
