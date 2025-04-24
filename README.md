# Table-Application
# Java Swing Table Application with Undo/Redo

## 📝 Description

This is a simple Java desktop application built using **Java Swing**. It displays a table where users can:

- ➕ Add rows with auto-generated IDs
- ❌ Delete selected rows
- ↩️ Undo the last Add/Delete operation
- ↪️ Redo the last undone operation

The app uses **Stacks** to manage undo and redo functionality, making it a great example of basic state management and GUI interaction in Java.

---

## 🖥️ Technologies Used

- Java SE
- Swing (`JFrame`, `JTable`, `JButton`, `JScrollPane`)
- Stack data structure

---

## 📦 Features

- Displays a dynamic table with ID and item name columns
- Buttons for:
  - `Add`: Insert a row with a unique ID and dummy text
  - `Delete`: Remove a selected row
  - `Undo`: Revert last added or deleted row
  - `Redo`: Reapply the last undone action
- Easy-to-read UI layout using `BorderLayout` and `JPanel`

---

## 🚀 How to Run

1. Make sure Java is installed on your machine.
2. Save the code in a file called `Table.java`.
3. Open terminal or command prompt and navigate to the file's directory.
4. Compile and run:

```bash
javac Table.java
java Table
