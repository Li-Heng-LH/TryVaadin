package me.liheng;

import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class MainView extends VerticalLayout {
    private CustomerService service = CustomerService.getInstance();
    private Grid<Customer> grid = new Grid<>(Customer.class);
    private TextField filterText = new TextField();

    private CustomerForm form = new CustomerForm(this);

    public MainView() {
        form.setCustomer(null);

        grid.setColumns("firstName", "lastName", "status");
        updateList();

        filterText.setPlaceholder("Filter by name...");
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> updateList());
        Button clearFilterTextBtn = new Button(VaadinIcons.CLOSE);
        clearFilterTextBtn.setDescription("Clear the current filter");
        clearFilterTextBtn.addClickListener(e -> filterText.clear());
        clearFilterTextBtn.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
        // CssLayout is a lightweight layout that is easy to customize with css
        // LAYOUT_COMPONENT_GROUP will create a nice compact "composition" of both the TextField and the clear button
        CssLayout filtering = new CssLayout();
        filtering.addComponents(filterText, clearFilterTextBtn);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);



        Button addCustomerBtn = new Button("Add new customer");
        addCustomerBtn.setStyleName(ValoTheme.BUTTON_PRIMARY);
        addCustomerBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setCustomer(new Customer());
        });

        HorizontalLayout toolbar = new HorizontalLayout(filtering, addCustomerBtn);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        addComponents(toolbar, mainContent);

        // A value change listener
        // to detect when the user selects or deselects a row on the Grid
        grid.asSingleSelect().addValueChangeListener(event ->
                form.setCustomer(grid.asSingleSelect().getValue()));
    }

    public void updateList() {
        grid.setItems(service.findAll(filterText.getValue()));
    }

}
