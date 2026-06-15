package Client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ReturnBook extends JFrame {

    private JPanel contentPane;
    private JTextField txtUserName;
    private JTextField txtBookId;

    public static void main(String[] args) {
        ReturnBook frame = new ReturnBook();
        frame.setVisible(true);
    }

    public ReturnBook() {
        setTitle("Return Book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 450);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 250, 205));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel title = new JLabel("Return Book");
        title.setFont(new Font("Tempus Sans ITC", Font.BOLD, 28));
        title.setBounds(250, 25, 250, 40);
        contentPane.add(title);

        JLabel lblUserName = new JLabel("User Name");
        lblUserName.setFont(new Font("Arial", Font.BOLD, 15));
        lblUserName.setBounds(90, 110, 120, 30);
        contentPane.add(lblUserName);

        txtUserName = new JTextField();
        txtUserName.setBounds(220, 110, 190, 30);
        contentPane.add(txtUserName);

        JLabel lblBookId = new JLabel("Book ID");
        lblBookId.setFont(new Font("Arial", Font.BOLD, 15));
        lblBookId.setBounds(90, 170, 120, 30);
        contentPane.add(lblBookId);

        txtBookId = new JTextField();
        txtBookId.setBounds(220, 170, 190, 30);
        contentPane.add(txtBookId);

        JLabel returnImage = new JLabel();
        try {
            ImageIcon originalIcon = new ImageIcon(ReturnBook.class.getResource("/Images/image10.jpg"));
            Image originalImage = originalIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(170, 130, Image.SCALE_SMOOTH);
            ImageIcon finalIcon = new ImageIcon(scaledImage);
            returnImage.setIcon(finalIcon);
        } catch (Exception ex) {
            System.out.println("Image file image10.jpg not found inside Images folder.");
        }
        returnImage.setBounds(430, 105, 170, 130);
        contentPane.add(returnImage);

        JButton btnReturn = new JButton("Return");
        btnReturn.setBounds(170, 270, 130, 35);
        btnReturn.setBackground(new Color(0, 51, 102));
        btnReturn.setForeground(Color.WHITE);
        btnReturn.setFocusPainted(false);
        btnReturn.setOpaque(true);
        btnReturn.addActionListener(e -> returnBook());
        contentPane.add(btnReturn);

        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(330, 270, 130, 35);
        btnClear.setBackground(new Color(0, 51, 102));
        btnClear.setForeground(Color.WHITE);
        btnClear.setFocusPainted(false);
        btnClear.setOpaque(true);
        btnClear.addActionListener(e -> {
            txtUserName.setText("");
            txtBookId.setText("");
        });
        contentPane.add(btnClear);

        JButton btnHome = new JButton("Home");
        btnHome.setBounds(250, 330, 130, 35);
        btnHome.setBackground(new Color(0, 51, 102));
        btnHome.setForeground(Color.WHITE);
        btnHome.setFocusPainted(false);
        btnHome.setOpaque(true);
        btnHome.addActionListener(e -> {
            setVisible(false);
            Homepage h = new Homepage();
            h.setVisible(true);
        });
        contentPane.add(btnHome);
    }

    private void returnBook() {
        String userName = txtUserName.getText();
        String bookId = txtBookId.getText();

        if (userName.isEmpty() || bookId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
        } else {
            if (bookId.equals("9") || bookId.equalsIgnoreCase("C#")) {
                if (ViewBooks.cSharpQuantity < 16) {
                    ViewBooks.cSharpQuantity += 1;
                    JOptionPane.showMessageDialog(this, "Book returned successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "This book is already at maximum stock (16)!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Book returned successfully");
            }
            
            txtUserName.setText("");
            txtBookId.setText("");
        }
    }
}