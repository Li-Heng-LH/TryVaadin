package me.liheng;

import com.vaadin.data.Binder;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;


public class CustomerForm extends FormLayout {
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private ComboBox<CustomerStatus> status = new ComboBox<>("Status");
    private DateField birthDate = new DateField("Birthdate");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private Binder<Customer> binder = new Binder<>(Customer.class);

    private MainView mainView;
    private CustomerService service = CustomerService.getInstance();

    public CustomerForm(MainView mainView) {
        this.mainView = mainView;

        status.setItems(CustomerStatus.values());
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        addComponents(firstName, lastName, status, birthDate, buttons);

        // bindInstanceFields(this) method processes all the instance variables that are input fields
        // (for example, TextField and ComboBox) and maps them to the Java properties in the Customer class matching by name

        // You can override automatic mapping using the @PropertyId annotation
        // in the CustomerForm input fields to explicitly declare the corresponding Customer instance variables
        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }

    public void setCustomer(Customer customer) {
        // setBean connects the values in the customer object to the corresponding input fields of the form
        binder.setBean(customer);

        if (customer == null) {
            setVisible(false);
        } else {
            setVisible(true);
            firstName.focus();
        }
    }

    private void save() {
        Customer customer = binder.getBean();
        service.save(customer);
        mainView.updateList();
        setCustomer(null);
    }

    private void delete() {
        Customer customer = binder.getBean();
        service.delete(customer);
        mainView.updateList();
        setCustomer(null);
    }

}
