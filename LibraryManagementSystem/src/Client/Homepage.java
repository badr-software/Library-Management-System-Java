package Client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Homepage extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        Homepage frame = new Homepage();
        frame.setVisible(true);
    }

    public Homepage() {
        setTitle("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 550);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 250, 205)
);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel title = new JLabel("Library Management System");
        title.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
        title.setBounds(160, 20, 400, 40);
        contentPane.add(title);

        JLabel image = new JLabel();
        try {
            ImageIcon originalIcon = new ImageIcon(Homepage.class.getResource("/Images/library2.jpg"));
            Image originalImage = originalIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(450, 260, Image.SCALE_SMOOTH);
            ImageIcon finalIcon = new ImageIcon(scaledImage);
            image.setIcon(finalIcon);
        } catch (Exception ex) {
            System.out.println("Image file library2.jpg not found inside Images folder.");
        }
        image.setBounds(100, 80, 450, 260);
        contentPane.add(image);

        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds(40, 350, 130, 35);
        btnRegister.setBackground(new Color(0, 51, 102));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFocusPainted(false);
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Register r = new Register();
                r.setVisible(true);
            }
        });
        contentPane.add(btnRegister);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(460, 350, 130, 35);
        btnLogin.setBackground(new Color(0, 51, 102));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setOpaque(true);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Login l = new Login();
                l.setVisible(true);
            }
        });
        contentPane.add(btnLogin);
        JButton btnBooks = new JButton("Book Management");
        btnBooks.setBounds(250, 350, 150, 35);
        btnBooks.setBackground(new Color(0, 51, 102));
        btnBooks.setForeground(Color.WHITE);
        btnBooks.setFocusPainted(false);
        btnBooks.setOpaque(true);

        btnBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                BookManagement bm = new BookManagement();
                bm.setVisible(true);
            }
        });
        contentPane.add(btnBooks);

        JButton btnViewBooks = new JButton("View Books");
        btnViewBooks.setBounds(160, 410, 130, 35);
        btnViewBooks.setBackground(new Color(0, 51, 102));
        btnViewBooks.setForeground(Color.WHITE);
        btnViewBooks.setFocusPainted(false);
        btnViewBooks.setOpaque(true);
        btnViewBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ViewBooks vb = new ViewBooks();
                vb.setVisible(true);
            }
        });
        contentPane.add(btnViewBooks);

        JButton btnBorrowBook = new JButton("Borrow Book");
        btnBorrowBook.setBounds(350, 410, 130, 35);
        btnBorrowBook.setBackground(new Color(0, 51, 102));
        btnBorrowBook.setForeground(Color.WHITE);
        btnBorrowBook.setFocusPainted(false);
        btnBorrowBook.setOpaque(true);
        btnBorrowBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                BorrowBook bb = new BorrowBook();
                bb.setVisible(true);
            }
        });
        contentPane.add(btnBorrowBook);

        JButton btnReturnBook = new JButton("Return Book");
        btnReturnBook.setBounds(250, 460, 130, 35);
        btnReturnBook.setBackground(new Color(0, 51, 102));
        btnReturnBook.setForeground(Color.WHITE);
        btnReturnBook.setFocusPainted(false);
        btnReturnBook.setOpaque(true);

        btnReturnBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ReturnBook rb = new ReturnBook();
                rb.setVisible(true);
            }
        });
        contentPane.add(btnReturnBook);
    }
}