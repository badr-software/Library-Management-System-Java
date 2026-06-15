package Client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

    private JPanel contentPane;
    private JTextField txtUser;
    private JPasswordField txtPassword;

    public static void main(String[] args) {
        Login frame = new Login();
        frame.setVisible(true);
    }

    public Login() {

        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 250, 205));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Library Login");
        lblTitle.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
        lblTitle.setBounds(360, 30, 200, 40);
        contentPane.add(lblTitle);

        JLabel lblUser = new JLabel("Username");
        lblUser.setFont(new Font("Arial", Font.BOLD, 14));
        lblUser.setBounds(290, 110, 100, 30);
        contentPane.add(lblUser);

        txtUser = new JTextField();
        txtUser.setBounds(390, 110, 160, 30);
        contentPane.add(txtUser);

        JLabel lblPass = new JLabel("Password");
        lblPass.setFont(new Font("Arial", Font.BOLD, 14));
        lblPass.setBounds(290, 170, 100, 30);
        contentPane.add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(390, 170, 160, 30);
        contentPane.add(txtPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(430, 250, 120, 35);
        btnLogin.setBackground(new Color(0, 51, 102));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setOpaque(true);
        contentPane.add(btnLogin);

        JButton btnHome = new JButton("Home");
        btnHome.setBounds(290, 250, 120, 35);
        btnHome.setBackground(new Color(0, 51, 102));
        btnHome.setForeground(Color.WHITE);
        btnHome.setFocusPainted(false);
        btnHome.setOpaque(true);
        contentPane.add(btnHome);

        JLabel loginImage = new JLabel();
        try {
            ImageIcon originalIcon = new ImageIcon(Login.class.getResource("/Images/library4.png"));
            Image originalImage = originalIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(230, 260, Image.SCALE_SMOOTH);
            ImageIcon finalIcon = new ImageIcon(scaledImage);
            loginImage.setIcon(finalIcon);
        } catch (Exception ex) {
            System.out.println("Image file library4.png not found inside Images folder.");
        }
        loginImage.setBounds(30, 50, 230, 260);
        contentPane.add(loginImage);

        btnLogin.addActionListener(e -> {
            String username = txtUser.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter username and password");
                return;
            }

            if (username.equals(Register.registeredUser) && password.equals(Register.registeredPassword)) {
                JOptionPane.showMessageDialog(null, "Login Successful! Welcome " + username);
                setVisible(false);
                Homepage hp = new Homepage();
                hp.setVisible(true);
                return; 
            }

            try {
                Connection con = DBConnection.getConnection();

                String sql =
                        "SELECT username FROM login_users WHERE username = ? AND password = ? " +
                        "UNION " +
                        "SELECT name FROM users WHERE (name = ? OR email = ?) AND password = ?";

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, username);
                ps.setString(4, username);
                ps.setString(5, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login Successful");
                    setVisible(false);
                    Homepage hp = new Homepage();
                    hp.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
                    txtUser.setText("");
                    txtPassword.setText("");
                }

                rs.close();
                ps.close();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
            }
        });

        btnHome.addActionListener(e -> {
            setVisible(false);
            Homepage hp = new Homepage();
            hp.setVisible(true);
        });
    }
}