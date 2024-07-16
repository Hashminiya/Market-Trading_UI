package org.vaadin.UI.view.AddDiscount;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import org.vaadin.UI.model.DTOs.Discounts.ConditionCompositeDTO;
import org.vaadin.UI.model.DTOs.Discounts.ConditionDTO;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.presenter.DiscountPresenter;
import org.vaadin.UI.presenter.PolicyPresenter;
import org.vaadin.UI.view.Abstracts.IDialog;
import org.vaadin.UI.view.AddPolicy.ChooseCategoryDialog;
import org.vaadin.UI.view.AddPolicy.ChooseItemsDialog;

import java.util.ArrayList;
import java.util.List;

public class AddDiscountDialog extends Dialog implements IDialog {
    private DiscountPresenter presenter;
    private PolicyPresenter policyPresenter;
    private ComboBox<String> discountTypeComboBox;
    private VerticalLayout contentLayout;
    private AddDiscountDialog parent;
    private List<ConditionDTO> conditionDTOList;
    private VerticalLayout discountListLayout;
    private VerticalLayout applyOnLayout;
    private List<ItemDTO> itemsChosen;
    private List<String> categoriesChosen;
    private boolean isAllStoreDiscount;
    private VerticalLayout productsListLayout;
    public AddDiscountDialog(DiscountPresenter presenter , AddDiscountDialog parent) {
        conditionDTOList = new ArrayList<>();
        this.parent = parent;
        this.presenter = presenter;
        setWidth("1000px");  // Set initial width
        setHeight("800px"); // Set initial height

        contentLayout = new VerticalLayout();
        discountTypeComboBox = new ComboBox<>("Select Discount Type");
        discountTypeComboBox.setItems("Condition Composite", "Logical Discount Composite", "Numeric Discount Composite");
        discountTypeComboBox.setPlaceholder("Select Discount Type");

        discountTypeComboBox.addValueChangeListener(event -> {
            String selectedPolicyType = event.getValue();
            switch (selectedPolicyType) {
                case "Condition Composite":
                    showConditionCompositeForm();
                    break;
                case "Condition":
                    showConditionForm();
                    break;
                case "Hidden Discount":
                    //showAgeRestrictedPolicyForm();
                    break;
                default:
                    break;
            }
        });

        add(discountTypeComboBox, contentLayout);
    }

    private void showConditionForm() {
        contentLayout.removeAll();

        TextField nameField = new TextField("Name");
        IntegerField countField = new IntegerField("Count");
        applyOnLayout = applyOnSelector();
        contentLayout.add(nameField ,countField, applyOnLayout);

        Button saveButton = new Button(addOrSaveButton());
        saveButton.addClickListener(event -> {
            ConditionDTO conditionDTO = new ConditionDTO();
            conditionDTO.setName(nameField.getValue());
            conditionDTO.setCount(countField.getValue());
            conditionDTO.setAllStoreCondition(isAllStoreDiscount);
            if(itemsChosen != null) {
                conditionDTO.setItems(itemsChosen.stream().map(ItemDTO::getItemId).toList());
            }
            else{
                conditionDTO.setItems(null);
            }
            conditionDTO.setCategories(categoriesChosen);

            if(parent != null){
                parent.conditionDTOList.add(conditionDTO);
                parent.displayDiscountList();
            }
            else {
                presenter.saveDiscount(conditionDTO);
            }
            close();
        });
        contentLayout.add(saveButton);
    }

    private void displayDiscountList() {
    }

    private void showConditionCompositeForm() {
        contentLayout.removeAll();
        discountListLayout = new VerticalLayout();
        TextField nameField = new TextField("Name");
        ComboBox<String> logicalRoleField = new ComboBox<>("Logical Role");
        logicalRoleField.setItems("OR", "AND", "XOR");

        Button addSubDiscountButton = new Button("Add Sub-discount");
        addSubDiscountButton.addClickListener(event -> {
            AddDiscountDialog dialog = new AddDiscountDialog(presenter, this);
            dialog.open();
        });

        contentLayout.add(nameField, logicalRoleField, addSubDiscountButton, discountListLayout);

        Button saveButton = new Button(addOrSaveButton());
        saveButton.addClickListener(event -> {
            ConditionCompositeDTO conditionComposite = new ConditionCompositeDTO();
            conditionComposite.setLogicalRole(logicalRoleField.getValue());
            conditionComposite.setConditions(conditionDTOList);
            conditionComposite.setName(nameField.getValue());

            if(parent != null){
                parent.conditionDTOList.add(conditionComposite);
            }
            else {
                presenter.saveCondition(conditionComposite);
            }
            close();
        });
        contentLayout.add(saveButton);
    }

    private String addOrSaveButton(){
        return (parent == null) ? "save" : "add";
    }
    private VerticalLayout applyOnSelector() {
        VerticalLayout layout = new VerticalLayout();
        ComboBox<String> options = new ComboBox<String>("Apply on");
        options.setItems("All Store Policy", "Specific Items", "Specific Categories");
        options.addValueChangeListener(event -> {
            String selectedPolicyType = event.getValue();
            isAllStoreDiscount = false;
            itemsChosen = new ArrayList<>();
            categoriesChosen = new ArrayList<>();
            switch (selectedPolicyType) {
                case "All Store Policy":
                    isAllStoreDiscount = true;
                    itemsChosen = null;
                    categoriesChosen = null;
                    break;
                case "Specific Items":
                    Dialog itemsDialog  = new ChooseItemsDialog(itemsChosen, policyPresenter, this);
                    categoriesChosen = null;
                    isAllStoreDiscount = false;
                    itemsDialog.open();
                    break;
                case "Specific Categories":
                    Dialog categoryDialog  = new ChooseCategoryDialog(categoriesChosen, policyPresenter, this);
                    itemsChosen = null;
                    isAllStoreDiscount = false;
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