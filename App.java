import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class App extends JFrame {

  private JTextField taskInputField;

  private DefaultListModel<String> taskListModel;

  private JList<String> taskList;

  private JButton addTaskButton;

  private JButton deleteTaskButton;

  public App() {

    setTitle("To-Do List Application");

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setSize(500, 400);

    setLocationRelativeTo(null);

    taskListModel = new DefaultListModel<>();

    taskList = new JList<>(taskListModel);

    JScrollPane scrollPane = new JScrollPane(taskList);

    taskInputField = new JTextField(20);

    addTaskButton = new JButton("Add Task");

    deleteTaskButton = new JButton("Delete Task");

    setLayout(new BorderLayout(10, 10));

    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    inputPanel.add(new JLabel("New Task:"));
    inputPanel.add(taskInputField);
    inputPanel.add(addTaskButton);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    buttonPanel.add(deleteTaskButton);

    add(inputPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    addTaskButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        addTask();
      }
    });

    deleteTaskButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        deleteTask();
      }
    });

    setVisible(true);
  }

  private void addTask() {
    String task = taskInputField.getText().trim();
    if (!task.isEmpty()) {
      taskListModel.add(0, task);
      taskInputField.setText("");
    } else {

      JOptionPane.showMessageDialog(this, "Task cannot be empty!", "Input Error", JOptionPane.WARNING_MESSAGE);
    }
  }

  private void deleteTask() {
    int selectedIndex = taskList.getSelectedIndex();
    if (selectedIndex != -1) {
      taskListModel.remove(selectedIndex);
    } else {

      JOptionPane.showMessageDialog(this, "Please select a task to delete.", "Selection Error",
          JOptionPane.WARNING_MESSAGE);
    }
  }

  public static void main(String[] args) {

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new App();
      }
    });
  }
}
