package layout.classGUI.manager;

import layout.MainFrame;
import layout.dialog.OperationFailureDialog;
import layout.dialog.OperationSuccessfulDialog;
import model.Manager;

import javax.swing.*;

public class AddEmployeeForm {
    private JTextField nameField;
    private JTextField sinField;
    private JTextField phoneNumField;
    private JButton addButton;
    private JButton backButton;
    private JPanel mainPanel;

    private MainFrame mainFrame;

    public AddEmployeeForm(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void createUIComponents() {
        /*
         * Name field setup
         */
        nameField = new JTextField();

        /*
         * SIN field setup
         */
        sinField = new JTextField();

        /*
         * Name field setup
         */
        phoneNumField = new JTextField();

        /*
         * Add button setup
         */
        addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            int nextAvailableEid = Manager.getNextAvailableEid();
            String name = nameField.getText();
            int sin = Integer.parseInt(sinField.getText());
            String phoneNum = phoneNumField.getText();

            if (Manager.addEmployee(nextAvailableEid, name, sin, phoneNum)) {
                OperationSuccessfulDialog operationSuccessfulDialog = new OperationSuccessfulDialog();
                operationSuccessfulDialog.pack();
                operationSuccessfulDialog.setLocationRelativeTo(mainPanel);
                operationSuccessfulDialog.setVisible(true);

                mainFrame.backAndRefreshManageEmployeeForm();
            } else {
                OperationFailureDialog operationFailureDialog = new OperationFailureDialog();
                operationFailureDialog.pack();
                operationFailureDialog.setLocationRelativeTo(mainPanel);
                operationFailureDialog.setVisible(true);
            }
        });

        /*
         * Back button setup
         */
        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            mainFrame.backToPreviousForm();
        });
    }
}
