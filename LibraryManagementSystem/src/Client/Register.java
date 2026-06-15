package Client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Register extends JFrame {

    private JPanel contentPane;

    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JPasswordField txtPassword;

    public static String registeredUser = "";
    public static String registeredPassword = "";

    public static void main(String[] args) {
        Register frame = new Register();
        frame.setVisible(true);
    }

    public Register() {
        setTitle("Register Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 450);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(255, 250, 205));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel title = new JLabel("Register User");
        title.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
        title.setBounds(180, 20, 250, 40);
        contentPane.add(title);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(80, 100, 100, 30);
        contentPane.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(200, 100, 180, 30);
        contentPane.add(txtName);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(80, 150, 100, 30);
        contentPane.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(200, 150, 180, 30);
        contentPane.add(lblEmail.getParent() != null ? txtEmail : txtEmail); // أمان إضافي للـ Layout
        contentPane.add(txtEmail);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(80, 200, 100, 30);
        contentPane.add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(200, 200, 180, 30);
        contentPane.add(txtPhone);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(80, 250, 100, 30);
        contentPane.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(200, 250, 180, 30);
        contentPane.add(txtPassword);

        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds(250, 320, 120, 35);
        btnRegister.setBackground(new Color(0, 51, 102));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFocusPainted(false);
        btnRegister.setOpaque(true);
        contentPane.add(btnRegister);

        JButton btnHome = new JButton("Home");
        btnHome.setBounds(100, 320, 120, 35);
        btnHome.setBackground(new Color(0, 51, 102));
        btnHome.setForeground(Color.WHITE);
        btnHome.setFocusPainted(false);
        btnHome.setOpaque(true);
        contentPane.add(btnHome);

        JLabel lblImage = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(Register.class.getResource("/Images/library3.jpg"));
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            lblImage.setIcon(new ImageIcon(newImg));
        } catch(Exception e) {
            System.out.println("Image not found");
        }
        lblImage.setBounds(410, 120, 150, 150);
        contentPane.add(lblImage);

        btnRegister.addActionListener(e -> {
            String name = txtName.getText().trim();
            String email = txtEmail.getText().trim();
            String phone = txtPhone.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();

            if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields");
            } else {
                
                registeredUser = name;
                registeredPassword = password;

                try {
                    Connection con = DBConnection.getConnection();
                    String sql = "INSERT INTO users (name, email, phone, password) VALUES (?, ?, ?, ?)";
                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setString(1, name);
                    ps.setString(2, email);
                    ps.setString(3, phone);
                    ps.setString(4, password);

                    int rows = ps.executeUpdate();
                    ps.close();

                    if(rows > 0) {
                        JOptionPane.showMessageDialog(null, 
                                "User Registered Successfully!\n\n" +
                                "Name: " + name + "\n" +
                                "Email: " + email + "\n" +
                                "Phone: " + phone);

                        // تصفير الخانات
                        txtName.setText("");
                        txtEmail.setText("");
                        txtPhone.setText("");
                        txtPassword.setText("");

                        setVisible(false);
                        Login loginScreen = new Login();
                        loginScreen.setVisible(true);
                    }

                } catch(SQLException ex) {
                    JOptionPane.showMessageDialog(null, 
                            "Registered locally (Memory)! \nDatabase message: " + ex.getMessage());
                    
                    txtName.setText("");
                    txtEmail.setText("");
                    txtPhone.setText("");
                    txtPassword.setText("");

                    setVisible(false);
                    Login loginScreen = new Login();
                    loginScreen.setVisible(true);
                }
            }
        });

        btnHome.addActionListener(e -> {
            setVisible(false);
            Homepage hp = new Homepage();
            hp.setVisible(true);
        });
    }
}