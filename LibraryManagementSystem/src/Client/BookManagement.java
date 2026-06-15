package Client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BookManagement extends JFrame {

    private JPanel contentPane;
    JTextField txtId, txtName, txtAuthor, txtCategory, txtQuantity;

    public BookManagement() {
        setTitle("Book Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 250, 205));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Manage Books");
        lblTitle.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
        lblTitle.setBounds(150, 20, 250, 40);
        contentPane.add(lblTitle);

        JLabel lblID = new JLabel("Book ID");
        lblID.setBounds(50, 100, 100, 30);
        contentPane.add(lblID);

        txtId = new JTextField();
        txtId.setBounds(180, 100, 180, 30);
        contentPane.add(txtId);

        JLabel lblBook = new JLabel("Book Name");
        lblBook.setBounds(50, 150, 100, 30);
        contentPane.add(lblBook);

        txtName = new JTextField();
        txtName.setBounds(180, 150, 180, 30);
        contentPane.add(txtName);

        JLabel lblAuthor = new JLabel("Author");
        lblAuthor.setBounds(50, 200, 100, 30);
        contentPane.add(lblAuthor);

        txtAuthor = new JTextField();
        txtAuthor.setBounds(180, 200, 180, 30);
        contentPane.add(txtAuthor);

        JLabel lblCategory = new JLabel("Category");
        lblCategory.setBounds(50, 250, 100, 30);
        contentPane.add(lblCategory);

        txtCategory = new JTextField();
        txtCategory.setBounds(180, 250, 180, 30);
        contentPane.add(txtCategory);

        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setBounds(50, 300, 100, 30);
        contentPane.add(lblQuantity);

        txtQuantity = new JTextField();
        txtQuantity.setBounds(180, 300, 180, 30);
        contentPane.add(txtQuantity);

        JButton btnAdd = new JButton("Add Book");
        btnAdd.setBounds(50, 380, 110, 35);
        btnAdd.setBackground(new Color(0, 51, 102));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFocusPainted(false);
        btnAdd.setOpaque(true);
        contentPane.add(btnAdd);

        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(180, 380, 100, 35);
        btnClear.setBackground(new Color(0, 51, 102));
        btnClear.setForeground(Color.WHITE);
        btnClear.setFocusPainted(false);
        btnClear.setOpaque(true);
        contentPane.add(btnClear);

        JButton btnHome = new JButton("Home");
        btnHome.setBounds(300, 380, 100, 35);
        btnHome.setBackground(new Color(0, 51, 102));
        btnHome.setForeground(Color.WHITE);
        btnHome.setFocusPainted(false);
        btnHome.setOpaque(true);
        contentPane.add(btnHome);

        JLabel bookImage = new JLabel();
        try {
            ImageIcon originalIcon = new ImageIcon(BookManagement.class.getResource("/Images/library6.png"));
            Image originalImage = originalIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(250, 260, Image.SCALE_SMOOTH);
            bookImage.setIcon(new ImageIcon(scaledImage));
        } catch (Exception ex) {
            System.out.println("Image not found.");
        }
        bookImage.setBounds(410, 100, 250, 260);
        contentPane.add(bookImage);

        btnAdd.addActionListener(e -> {
            if(txtId.getText().trim().isEmpty() || txtName.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in at least Book ID and Name!");
                return;
            }

            JOptionPane.showMessageDialog(null, txtName.getText() + " Added Successfully");
            
            setVisible(false);
            ViewBooks vb = new ViewBooks();
            
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) vb.getTable().getModel();
            model.addRow(new Object[]{
                txtId.getText().trim(),
                txtName.getText().trim(),
                txtAuthor.getText().trim(),
                txtCategory.getText().trim(),
                txtQuantity.getText().trim()
            });
            
            vb.setVisible(true);
        });

        btnClear.addActionListener(e -> {
            txtId.setText("");
            txtName.setText("");
            txtAuthor.setText("");
            txtCategory.setText("");
            txtQuantity.setText("");
        });

        btnHome.addActionListener(e -> {
            setVisible(false);
            Homepage hp = new Homepage();
            hp.setVisible(true);
        });
    }
}