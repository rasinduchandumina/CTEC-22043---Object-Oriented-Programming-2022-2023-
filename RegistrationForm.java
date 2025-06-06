import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class RegistrationForm extends JFrame {
    private JTextField nameField;
    private JTextField mobileField;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private ButtonGroup genderGroup;
    private JComboBox<String> dayCombo;
    private JComboBox<String> monthCombo;
    private JComboBox<String> yearCombo;
    private JTextArea addressArea;
    private JCheckBox termsCheckBox;
    private JTextArea displayArea;
    private JButton submitButton;
    private JButton resetButton;

    public RegistrationForm() {
        initializeComponents();
        setupLayout();
        setupEventHandlers();

        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initializeComponents() {
        // Text fields
        nameField = new JTextField(15);
        mobileField = new JTextField(15);

        // Gender radio buttons
        maleRadio = new JRadioButton("Male", true);
        femaleRadio = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        // Date of birth combo boxes
        dayCombo = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            dayCombo.addItem(String.valueOf(i));
        }
        dayCombo.setSelectedIndex(0);

        monthCombo = new JComboBox<>(new String[]{
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        });
        monthCombo.setSelectedIndex(0);

        yearCombo = new JComboBox<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear; i >= 1950; i--) {
            yearCombo.addItem(String.valueOf(i));
        }
        yearCombo.setSelectedIndex(0);

        // Address text area
        addressArea = new JTextArea(3, 15);
        addressArea.setBorder(BorderFactory.createLoweredBevelBorder());

        // Terms checkbox
        termsCheckBox = new JCheckBox("Accept Terms And Conditions.", false);

        // Display area
        displayArea = new JTextArea(8, 20);
        displayArea.setEditable(false);
        displayArea.setBackground(Color.LIGHT_GRAY);
        displayArea.setBorder(BorderFactory.createLoweredBevelBorder());

        // Buttons
        submitButton = new JButton("Submit");
        resetButton = new JButton("Reset");
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        // here is the code segment of Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Here is the Form panel (left side)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Name row cde segment 
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Name"), gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        // Mobile row code segment 
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Mobile"), gbc);
        gbc.gridx = 1;
        formPanel.add(mobileField, gbc);

        // Gender row code segment 
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Gender"), gbc);
        gbc.gridx = 1;
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        formPanel.add(genderPanel, gbc);

        // Here is the code segment for   DOB row
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("DOB"), gbc);
        gbc.gridx = 1;
        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        dobPanel.add(dayCombo);
        dobPanel.add(monthCombo);
        dobPanel.add(yearCombo);
        formPanel.add(dobPanel, gbc);

        //Here is the code segment for    address row
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(new JLabel("Address"), gbc);
        gbc.gridx = 1;
        JScrollPane addressScroll = new JScrollPane(addressArea);
        addressScroll.setPreferredSize(new Dimension(200, 60));
        formPanel.add(addressScroll, gbc);

        //Here is the code segment for   terms checkbox
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(termsCheckBox, gbc);

        //Here is the code segment for  Button panel
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(submitButton);
        buttonPanel.add(resetButton);
        formPanel.add(buttonPanel, gbc);

        // Display panel (right side)
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20));
        JScrollPane displayScroll = new JScrollPane(displayArea);
        displayScroll.setPreferredSize(new Dimension(250, 200));
        displayPanel.add(displayScroll, BorderLayout.CENTER);

        // Add panels to main panel
        mainPanel.add(formPanel, BorderLayout.WEST);
        mainPanel.add(displayPanel, BorderLayout.EAST);

        // Add title
        JLabel titleLabel = new JLabel("Registration Form", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        add(titleLabel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }
         // this is code segment for Submit button action
    private void setupEventHandlers() {
       
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateForm()) {
                    updateDisplay();
                    JOptionPane.showMessageDialog(RegistrationForm.this,
                            "Registration Successfully..",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Reset button action
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        // Real-time update when fields change 
        nameField.addActionListener(e -> updateDisplay());
        mobileField.addActionListener(e -> updateDisplay());
        maleRadio.addActionListener(e -> updateDisplay());
        femaleRadio.addActionListener(e -> updateDisplay());
        dayCombo.addActionListener(e -> updateDisplay());
        monthCombo.addActionListener(e -> updateDisplay());
        yearCombo.addActionListener(e -> updateDisplay());
    }

    private boolean validateForm() {
        if (nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your name.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (mobileField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your mobile number.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!termsCheckBox.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please accept the terms and conditions.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void updateDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name - ").append(nameField.getText()).append("\n");
        sb.append("Mobile - ").append(mobileField.getText()).append("\n");
        sb.append("Gender - ").append(maleRadio.isSelected() ? "Male" : "Female").append("\n");
        sb.append("DOB - ").append(dayCombo.getSelectedItem()).append("/")
                .append(monthCombo.getSelectedItem()).append("/")
                .append(yearCombo.getSelectedItem()).append("\n");
        sb.append("Address - ").append(addressArea.getText().replace("\n", "\n"));

        displayArea.setText(sb.toString());
    }

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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new RegistrationForm().setVisible(true);
            }
        });
    }
}
