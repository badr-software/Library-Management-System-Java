package Client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class BorrowBook extends JFrame {

    private JPanel contentPane;
    private JTextField txtUserName;
    private JTextField txtBookId;

    public static void main(String[] args) {
        BorrowBook frame = new BorrowBook();
        frame.setVisible(true);
    }

    public BorrowBook() {
        setTitle("Borrow Book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 450);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 250, 205));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel title = new JLabel("Borrow Book");
        title.setFont(new Font("Tempus Sans ITC", Font.BOLD, 28));
        title.setBounds(240, 25, 250, 40);
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

        JLabel borrowImage = new JLabel();
        try {
            ImageIcon originalIcon = new ImageIcon(BorrowBook.class.getResource("/Images/images (6).jpg"));
            Image originalImage = originalIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(170, 130, Image.SCALE_SMOOTH);
            ImageIcon finalIcon = new ImageIcon(scaledImage);
            borrowImage.setIcon(finalIcon);
        } catch (Exception ex) {
            System.out.println("Image file images (6).jpg not found inside Images folder.");
        }
        borrowImage.setBounds(430, 105, 170, 130);
        contentPane.add(borrowImage);

        JButton btnBorrow = new JButton("Borrow");
        btnBorrow.setBounds(170, 270, 130, 35);
        btnBorrow.setBackground(new Color(0, 51, 102));
        btnBorrow.setForeground(Color.WHITE);
        btnBorrow.setFocusPainted(false);
        btnBorrow.setOpaque(true);
        btnBorrow.addActionListener(e -> borrowBook());
        contentPane.add(btnBorrow);

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

    private void borrowBook() {
        String userName = txtUserName.getText().trim();
        String bookId = txtBookId.getText().trim();

        if (userName.isEmpty() || bookId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }

        if (bookId.equals("9") || bookId.equalsIgnoreCase("C#")) {
            if (ViewBooks.cSharpQuantity > 0) {
                ViewBooks.cSharpQuantity -= 1;
            }
        }

        setVisible(false);
        ViewBooks vb = new ViewBooks();
        DefaultTableModel model = (DefaultTableModel) vb.getTable().getModel();
        boolean bookFound = false;

        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().equals(bookId)) {
                bookFound = true;
                int currentQty = Integer.parseInt(model.getValueAt(i, 4).toString());
                
                if (currentQty > 0) {
                    model.setValueAt(String.valueOf(currentQty - 1), i, 4);
                    JOptionPane.showMessageDialog(this, "Book borrowed successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Sorry, this book is out of stock!");
                }
                break;
            }
        }

        if (!bookFound) {
            JOptionPane.showMessageDialog(this, "Book ID not found in system.");
        }

        vb.setVisible(true);
        txtUserName.setText("");
        txtBookId.setText("");
    }
}