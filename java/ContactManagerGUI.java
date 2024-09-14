import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Contact {
    String name;
    String phone;
    String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return name + " - " + phone + " - " + email;
    }
}

public class ContactManagerGUI extends JFrame {
    private ArrayList<Contact> contacts;
    private DefaultListModel<String> listModel;
    private JList<String> contactList;
    
    public ContactManagerGUI() {
        contacts = new ArrayList<>();
        listModel = new DefaultListModel<>();
        contactList = new JList<>(listModel);
        
        setTitle("Contact Manager");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panels for adding, editing, deleting contacts
        JPanel buttonPanel = new JPanel();
        JPanel formPanel = new JPanel(new GridLayout(3, 2));

        // Form fields
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);

        // Buttons for actions
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Scrollable list for displaying contacts
        JScrollPane scrollPane = new JScrollPane(contactList);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(formPanel, BorderLayout.NORTH);

        // Button functionality
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();
                if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                    Contact contact = new Contact(name, phone, email);
                    contacts.add(contact);
                    listModel.addElement(contact.toString());
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill all fields.");
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = contactList.getSelectedIndex();
                if (index >= 0) {
                    String name = nameField.getText();
                    String phone = phoneField.getText();
                    String email = emailField.getText();
                    if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                        Contact updatedContact = new Contact(name, phone, email);
                        contacts.set(index, updatedContact);
                        listModel.set(index, updatedContact.toString());
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill all fields.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a contact to edit.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = contactList.getSelectedIndex();
                if (index >= 0) {
                    contacts.remove(index);
                    listModel.remove(index);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a contact to delete.");
                }
            }
        });

        setVisible(true);
    }

    // Clear input fields
    private void clearFields() {
        for (Component c : ((JPanel) getContentPane().getComponent(2)).getComponents()) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ContactManagerGUI();
            }
        });
    }
}
