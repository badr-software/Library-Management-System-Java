package Client;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ViewBooks extends JFrame {

    private JPanel contentPane;
    private JTable table;
    
    public static int cSharpQuantity = 16; 

    public static void main(String[] args) {
        ViewBooks frame = new ViewBooks();
        frame.setVisible(true);
    }

    public ViewBooks() {
        setTitle("View Books");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 500);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 250, 205));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel title = new JLabel("View Books");
        title.setFont(new Font("Tempus Sans ITC", Font.BOLD, 28));
        title.setBounds(290, 25, 250, 40);
        contentPane.add(title);

        String[] columns = {"Book ID", "Book Name", "Author", "Category", "Quantity"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        model.addRow(new Object[]{"1", "Java Programming", "James Gosling", "Programming", "5"});
        model.addRow(new Object[]{"2", "OOP Basics", "Robert Martin", "Education", "3"});
        model.addRow(new Object[]{"3", "Database Systems", "Elmasri", "Computer Science", "4"});
        model.addRow(new Object[]{"4", "c++ Programming", "Badr", "Programming", "8"});
        model.addRow(new Object[]{"5", "python Basics", "Ahmed", "Education", "10"});
        model.addRow(new Object[]{"6", "sql", "ALI", "Computer DATABASE", "4"});
        model.addRow(new Object[]{"7", "html", "mohammed", "programing ", "3"});
        model.addRow(new Object[]{"8", "oracle", "salim", "Computer DATABASE", "13"});
        model.addRow(new Object[]{"9", "C#", "Hazem", "Programming Language", String.valueOf(cSharpQuantity)});

        table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setBackground(new Color(0, 51, 102));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 90, 630, 260);
        contentPane.add(scrollPane);

        JButton btnHome = new JButton("Home");
        btnHome.setBounds(210, 380, 130, 35);
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

        JButton btnDelete = new JButton("Delete Book");
        btnDelete.setBounds(390, 380, 140, 35);
        btnDelete.setBackground(new Color(204, 0, 0)); 
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFocusPainted(false);
        btnDelete.setOpaque(true);

        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a book from the table to delete!");
                return;
            }

            String bookId = model.getValueAt(selectedRow, 0).toString();
            String bookName = model.getValueAt(selectedRow, 1).toString();

            int confirm = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to delete:\n" + bookName + " (ID: " + bookId + ")?", 
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Book deleted successfully!");
            }
        });
        contentPane.add(btnDelete);
    }

    public JTable getTable() {
        return this.table;
    }
}