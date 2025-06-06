import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegistrationForm extends JFrame {
    // Declare GUI components
    private JTextField nameField, mobileField;
    private JRadioButton maleRadio, femaleRadio;
    private JComboBox<String> dayCombo, monthCombo, yearCombo;
    private JTextArea addressArea, displayArea;
    private JCheckBox termsCheckBox;
    private JButton submitButton, resetButton;
    private ButtonGroup genderGroup;

    public RegistrationForm() {
        // Set basic frame properties
        setTitle("Registration Form");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null); // Center   the window
        setResizable(false);

        initializeUI(); // Initialize   all UI Components
    }

    private void initializeUI() {
        // Create and set up the title label
        JLabel titleLabel = new JLabel("Registration Form", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(titleLabel, BorderLayout.NORTH);

        // Create main panel
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // In here Create form Panel with vertical layout
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createTitledBorder("Enter Details"));

        // In here  Initialize input fields
        nameField = new JTextField(15);
        mobileField = new JTextField(15);

        // Create and  group gender radio buttons
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        maleRadio.setSelected(true); // Set default selection

        // Populate day combo box
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) days[i] = String.valueOf(i + 1);
        dayCombo = new JComboBox<>(days);

        // In this code segment Define months
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        monthCombo = new JComboBox<>(months);

        // Populate year combo box
        String[] years = new String[74];
        for (int i = 0; i < 74; i++) years[i] = String.valueOf(2023 - i);
        yearCombo = new JComboBox<>(years);

        // In this code segment Create address input area
        addressArea = new JTextArea(3, 15);

        addressArea.setLineWrap(true);

        JScrollPane addressScroll = new JScrollPane(addressArea);

        // In this code segment Create terms and conditions checkbox
        termsCheckBox = new JCheckBox("Accept Terms And Conditions.");

        // Create submit and reset buttons
        submitButton = new JButton("Submit");

        resetButton = new JButton("Reset");

        // Add form fields to the form panel
        formPanel.add(createFieldPanel("Name  : ", nameField));
        formPanel.add(Box.createVerticalStrut(10)); // from this code create Add vertical spacing
        formPanel.add(createFieldPanel("Mobile : ", mobileField));
        formPanel.add(createFieldPanel("Gender", new JPanel(new FlowLayout(FlowLayout.LEFT)) {{
            add(maleRadio);
            add(femaleRadio);


        }}));//DOB
        formPanel.add(createFieldPanel("DOB", new JPanel(new FlowLayout(FlowLayout.LEFT)) {{
            add(dayCombo);
            add(monthCombo);
            add(yearCombo);
        }}));




        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(createFieldPanel("Address", addressScroll));
        formPanel.add(termsCheckBox);
        formPanel.add(new JPanel(new FlowLayout(FlowLayout.CENTER)) {{
            add(submitButton);
            add(resetButton);
        }});

        // Create Display area to show submitted data
        displayArea = new JTextArea();



        displayArea.setEditable(false);
        displayArea.setBorder(BorderFactory.createTitledBorder("Submitted Data"));
        displayArea.setBackground(new Color(240, 240, 240));
        JScrollPane displayScroll = new JScrollPane(displayArea);

        // Add both form and display panels to main panel
        mainPanel.add(formPanel);
        mainPanel.add(displayScroll);




        // Add main panel to the center of the frame
        add(mainPanel, BorderLayout.CENTER);

        // Set Up button actions
        setupActions();
    }

    // Helper method to wrap label   and input  component    in a panel
    private JPanel createFieldPanel(String label, Component field) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.add(new JLabel(label), BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        return panel;
    }

    // Set up button functionality
    private void setupActions() {
        // Handle submit button click
        submitButton.addActionListener(e -> {
            if (validateForm()) {
                displayData();
                JOptionPane.showMessageDialog(this, "Registration Successfully..", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Handle reset button click
        resetButton.addActionListener(e -> resetForm());
    }

    // from this code Validate input  fields   before    submission
    private boolean validateForm() {
        if (nameField.getText().trim().isEmpty()) {
            showError("Please enter your name.");
            return false;
        }
        if (mobileField.getText().trim().isEmpty()) {
            showError("Please enter your mobile number.");
            return false;
        }
        if (!termsCheckBox.isSelected()) {
            showError("Please accept the terms and conditions.");
            return false;
        }
        return true;
    }

    // Show error message dialog
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // from this code segment Display  form data in the display area
    private void displayData() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(nameField.getText()).append("\n")
                .append("Mobile: ").append(mobileField.getText()).append("\n")
                .append("Gender: ").append(maleRadio.isSelected() ? "Male" : "Female").append("\n")
                .append("DOB: ").append(dayCombo.getSelectedItem()).append("/")
                .append(monthCombo.getSelectedItem()).append("/")
                .append(yearCombo.getSelectedItem()).append("\n")
                .append("Address: ").append(addressArea.getText());

        displayArea.setText(sb.toString());
    }

    // from this code segmet reset all fields to default state
    private void resetForm() {
        nameField.setText("");
        mobileField.setText("");
        maleRadio.setSelected(true);
        dayCombo.setSelectedIndex(0);
        monthCombo.setSelectedIndex(0);
        yearCombo.setSelectedIndex(0);
        addressArea.setText("");
        termsCheckBox.setSelected(false);
        displayArea.setText("");
    }

    public static void main(String[] args) {
        try {
            // In here definr Set system look and feel for consistent appearance
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        // Run the form on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new RegistrationForm().setVisible(true));
    }
}
