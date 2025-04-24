import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Table {
    public static void main(String[] args) {
        // Run the GUI
        SwingUtilities.invokeLater(() -> {
            TableFrame frame = new TableFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(420, 380);
            frame.setVisible(true);
        });
    }
}

// This class creates the main window with table and buttons
class TableFrame extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton addButton, deleteButton, undoButton, redoButton;
    private Stack<Object[]> undoStack, redoStack;
    private int currentID;

    public TableFrame() {
        setTitle("Table");

        // Create a table with 2 columns
        tableModel = new DefaultTableModel(new Object[] { "ID", "Item name" }, 0);
        table = new JTable(tableModel);

        // Create buttons
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        undoButton = new JButton("Undo");
        redoButton = new JButton("Redo");

        // Create stacks for undo and redo
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        currentID = 1;

        // Set layout and add table
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Add buttons to a panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(redoButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // When "Add" is clicked
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uniqueID = String.valueOf(currentID++);
                String dummyText = generateDummyText();
                Object[] rowData = { uniqueID, dummyText };
                tableModel.addRow(rowData);      // Add new row to table
                undoStack.push(rowData);         // Save to undo stack
                redoStack.clear();               // Clear redo history
            }
        });

        // When "Delete" is clicked
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    // Save the deleted row for undo
                    Object[] rowData = new Object[] {
                            tableModel.getValueAt(selectedRow, 0),
                            tableModel.getValueAt(selectedRow, 1)
                    };
                    undoStack.push(rowData);
                    tableModel.removeRow(selectedRow); // Remove row
                    redoStack.clear();                 // Clear redo history
                }
            }
        });

        // When "Undo" is clicked
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!undoStack.isEmpty()) {
                    Object[] rowData = undoStack.pop();
                    redoStack.push(rowData);         // Save to redo stack
                    tableModel.addRow(rowData);      // Re-add to table
                }
            }
        });

        // When "Redo" is clicked
        redoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!redoStack.isEmpty()) {
                    Object[] rowData = redoStack.pop();
                    undoStack.push(rowData);         // Save back to undo stack
                    tableModel.addRow(rowData);      // Re-add to table
                }
            }
        });

        // Resize window to fit content
        pack();
    }

    // Dummy text generator
    private String generateDummyText() {
        return "Text ";
    }
}
