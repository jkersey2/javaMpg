/*
 * Program Name:   KersJeGuiGasMileage
 * Author:         Jeffrey Ryan Kersey
 * Date Written:   6/11/2017
 * Description:    This program displays a gui that prompts the user for beginning and ending
 * odometer readings, and gallons of gas used. The user then may click the calculate button, the
 * program will then calculate miles per gallon, distance travelled, and the distance the user may
 * travel based on remaining fuel and mpg. 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KersJeGuiGasMileage extends JFrame
{
    private JButton buttonCalculate;
    private JButton exitButton;
    private JTextField odoOne;
    private JTextField odoTwo;
    private JTextField galUsed;
    private JTextField milesField;
    private JTextField mpgAvg;
    private JTextField distRemain;
    private JPanel panelOne;  
    private double milesDriven;
    private String miles;
    
    
    public static void main(String[] args)
    {
        new KersJeGuiGasMileage();
    }
    public KersJeGuiGasMileage()
    {
        
        JFrame frame = new JFrame("MPG Calculator"); // Creates new frame
        frame.pack();
        frame.setSize(318, 250);
        frame.setLocation(350, 300);
        frame.setTitle("MPG Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
        ButtonListener bl = new ButtonListener();
        
        JPanel panelOne = new JPanel();
        
        
        panelOne.add(new JLabel("This Program Calculates Gas Mileage"));
        
        panelOne.add(new JLabel("Enter the beginning odometer mileage: "));
        odoOne = new JTextField(5);
        panelOne.add(odoOne);
        
        panelOne.add(new JLabel("Enter the ending odometer mileage: "));
        odoTwo = new JTextField(5);
        panelOne.add(odoTwo);
        
        panelOne.add(new JLabel("Enter the gallons of fuel used: "));
        galUsed = new JTextField(5);
        panelOne.add(galUsed);
        
        panelOne.add(new JLabel("Total miles for this trip: "));
        milesField = new JTextField(3); // New uneditable text field
        milesField.setEditable(false); 
        panelOne.add(milesField); 
        
        panelOne.add(new JLabel("Your MPG for this trip"));
        mpgAvg = new JTextField(3);
        mpgAvg.setEditable(false);
        panelOne.add(mpgAvg);
        
        panelOne.add(new JLabel("Remaining miles you may drive: "));
        distRemain = new JTextField(3);
        distRemain.setEditable(false);
        panelOne.add(distRemain);
        
        buttonCalculate = new JButton("Calculate");
        buttonCalculate.addActionListener(bl);
        buttonCalculate.setToolTipText("Calculate MPG");
        panelOne.add(buttonCalculate);
        
        exitButton = new JButton("Exit");
        exitButton.addActionListener(bl);
        exitButton.setToolTipText("Exit the program");
        panelOne.add(exitButton);
                
        frame.add(panelOne);
        
        frame.setVisible(true);
    }

    public class ButtonListener implements ActionListener
    {
       public void actionPerformed(ActionEvent e)
       {
         double begMiles = Double.parseDouble(odoOne.getText()); // Parses JText input as
         double endMiles = Double.parseDouble(odoTwo.getText()); // doubles
      
         if(e.getSource() == buttonCalculate) // Tests if calculate button has been clicked
         {
             if(endMiles > begMiles) // If statement tests to see if beginning odo reading is greater
             {                       // than ending
             milesDriven = (endMiles - begMiles);
             milesField.setText(String.format("%.1f", milesDriven));
             }
             else if(begMiles > endMiles) // This displays an error if the beginning odo
             {                            // reading is greater than the end reading.
             JOptionPane.showMessageDialog(KersJeGuiGasMileage.this, "Invalid Odometer input",
             "MPG Results",JOptionPane.INFORMATION_MESSAGE);
             }
             double fuelUsed = Double.parseDouble(galUsed.getText());
             
             double gasMileage = (milesDriven / fuelUsed);
             mpgAvg.setText(String.format("%.1f", gasMileage));
             
             double milesRemain = (20 - fuelUsed)*gasMileage; // Calculates the number of miles the user may still travel.
             distRemain.setText(String.format("%.1f", milesRemain));
                 
             odoOne.requestFocus();
         }
         else if(e.getSource() == exitButton) // Listens for exit button to be fired.
         {
             System.exit(0);
         }
     }
     
  }
}