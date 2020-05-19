package me.liheng;

import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;

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
        Button addCustomerBtn = new Button("Add new customer");
        addCustomerBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setCustomer(new Customer());
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addCustomerBtn);

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
