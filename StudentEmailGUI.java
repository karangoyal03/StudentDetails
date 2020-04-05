package proj2;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class StudentEmailGUI extends JFrame
{
   // Constants:

   // GUI Componet:
   JTextArea  studentTextArea   = new JTextArea ();

   JLabel     idLabel           = new JLabel     ("ID: ");
   JTextField idTextField       = new JTextField (10);
   JLabel     nameLabel         = new JLabel     ("Name: ");
   JTextField nameTextField     = new JTextField (10);

   JButton    addButton         = new JButton ("Add");
   JButton    deleteButton      = new JButton ("Delete");
   JButton    displayAllButton  = new JButton ("Display All");
   JButton    exitButton        = new JButton ("Exit");

   // Class Instance Data:
   private LinkedList<StudentEmail> studentLinkedList = new LinkedList<StudentEmail> ();

   public StudentEmailGUI ()
   {
      JPanel flow1Panel = new JPanel (new FlowLayout (FlowLayout.CENTER));
      JPanel flow2Panel = new JPanel (new FlowLayout (FlowLayout.CENTER));
      JPanel gridPanel  = new JPanel (new GridLayout (2, 1));

      studentTextArea.setEditable (false);

      flow1Panel.add (idLabel);
      flow1Panel.add (idTextField);
      flow1Panel.add (nameLabel);
      flow1Panel.add (nameTextField);

      flow2Panel.add (addButton);
      flow2Panel.add (deleteButton);
      flow2Panel.add (displayAllButton);
      flow2Panel.add (exitButton);

      gridPanel.add (flow1Panel);
      gridPanel.add (flow2Panel);

      add (studentTextArea, BorderLayout.CENTER);
      add (gridPanel,       BorderLayout.SOUTH);


      addButton.addActionListener        (event -> addStudent ());
      displayAllButton.addActionListener (event -> displayAll ());
      exitButton.addActionListener       (event -> exitApplication ());
      deleteButton.addActionListener     (event -> deleteStudent ());
   }

   private boolean isStudentIdInLinkedList (String idStr)
   {
      boolean inList = false;

      for (StudentEmail stud : studentLinkedList)
      {
         if (stud.getId ().compareToIgnoreCase (idStr) == 0)
         {
            inList = true;
         }
      }

      return inList;
   }

   private void addStudent ()
   {
      if (isStudentIdInLinkedList (idTextField.getText()) == true)
      {
         JOptionPane.showMessageDialog (null, "Error: student ID is already in the database.");
      }
      else
      {
         try
         {
            StudentEmail stud = new StudentEmail (nameTextField.getText(),
                                                  idTextField.getText());

            studentLinkedList.add (stud);

            displayAll ();

            nameTextField.setText("");
            idTextField.setText("");


         }
         catch (StudentEmailException error)
         {
            JOptionPane.showMessageDialog (null, error.toString ());
            // myLabel.setText (error.toString ());


         }

      }
   }

   private void deleteStudent ()
   {
      if (studentLinkedList.size() == 0)
      {
         JOptionPane.showMessageDialog (null, "Error: Database is empty.");
      }
      else if (isStudentIdInLinkedList (idTextField.getText()) == false)
      {
         JOptionPane.showMessageDialog (null, "Error: student ID is not in the database.");
      }
      else
      {
         for (int s = 0; s < studentLinkedList.size(); s++)
         {
            String currId = studentLinkedList.get (s).getId ();

            if (currId.compareToIgnoreCase (idTextField.getText()) == 0)
            {
               studentLinkedList.remove (s);
            }
         }

         displayAll ();

         nameTextField.setText("");
         idTextField.setText("");
      }
   }

   private void displayAll ()
   {
      studentTextArea.setText ("");

      for (StudentEmail stud : studentLinkedList)
      {
         studentTextArea.append (stud + "\n");
      }
   }

   private void exitApplication ()
   {
      System.exit (0); // All is OK.
   }

   public static void main (String[] args)
   {
      StudentEmailGUI app = new StudentEmailGUI ();
      app.setVisible  (true);
      app.setSize     (500, 310);
      app.setLocation (200, 100);
      app.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
   }
}