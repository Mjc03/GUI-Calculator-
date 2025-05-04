import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextArea text_field;
    JButton[] NumButton = new JButton[10];
    JButton AddButton, SubButton, MulButton, DivButton;
    JButton EquButton, DecButton, Clear_All_Button, Clear_Last_Button;

    Double input1, input2, outcome;
    String final_Answer;
    char operation;


    Calculator() {

        /** Creates a frame the generates the Gui with a set size that
         * is not able to be resizable.
         *
         */
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(325, 400);
        frame.setResizable(false);

        /** creates a textarea to display the calculations and
         * a scroll bar on the right side of the frame.
         *
         */
        text_field = new JTextArea();
        text_field.setBounds(50, 50, 50, 50);
        text_field.setEditable(false);

        JScrollPane scroll = new JScrollPane(text_field);
        frame.add(scroll, BorderLayout.EAST);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        /** Creates various function buttons.
         *
         */
        AddButton = new JButton("+");
        SubButton = new JButton("-");
        MulButton = new JButton("*");
        DivButton = new JButton("/");
        EquButton = new JButton("=");
        DecButton = new JButton(".");
        Clear_All_Button = new JButton("Clear All");
        Clear_Last_Button = new JButton("Clear Last");

        /** Creates number buttons using a for loop and
         *  action listeners for each number buttons.
         *
         */
        for (int i = 0; i < 10; i++) {
            NumButton[i] = new JButton(String.valueOf(i));
            NumButton[i].addActionListener(this);

        }
        /** Creates action listeners for the function buttons.
         *
         */
        clear clear = new clear();
        decimal_error decimal = new decimal_error();
        AddButton.addActionListener(this);
        SubButton.addActionListener(this);
        MulButton.addActionListener(this);
        DivButton.addActionListener(this);
        EquButton.addActionListener(this);
        DecButton.addActionListener(decimal);
        Clear_All_Button.addActionListener(clear);
        Clear_Last_Button.addActionListener(clear);

        /** Adds a panel in a 4 by 4 grid layout and adds the buttons
         *  to the panel in the required order.
         *
         */
        JPanel Panel = new JPanel();
        Panel.setBounds(50, 100, 300, 75);
        frame.add(Panel, BorderLayout.SOUTH);
        Panel.setLayout(new GridLayout(4, 4));

        Panel.add(NumButton[7]);
        Panel.add(NumButton[8]);
        Panel.add(NumButton[9]);
        Panel.add(AddButton);
        Panel.add(NumButton[4]);
        Panel.add(NumButton[5]);
        Panel.add(NumButton[6]);
        Panel.add(SubButton);
        Panel.add(NumButton[1]);
        Panel.add(NumButton[2]);
        Panel.add(NumButton[3]);
        Panel.add(MulButton);
        Panel.add(NumButton[0]);
        Panel.add(DecButton);
        Panel.add(EquButton);
        Panel.add(DivButton);

        /** Adds the clear last and clear all buttons to the top
         * of the panel.
         *
         */
        JPanel top = new JPanel();
        top.add(Clear_Last_Button);
        top.add(Clear_All_Button);
        frame.add(top, BorderLayout.NORTH);

        /** Adds text field to frame and makes the
         *  frame visible.
         *
         */
        frame.add(text_field);
        frame.setVisible(true);

    }

    public static void main(String[] args) {

        new Calculator();

    }

    /** This class throws an error when the user clicks the decimal
     * button more than once.
     *
     */
    class decimal_error implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent click) {
            if (click.getSource() == DecButton) {
                text_field.setText(text_field.getText().concat("."));
            }
            if (text_field.getText().contains("..")) {
                try {
                    throw new Exception("Error can't have multiple decimals ");
                } catch (Exception e1) {
                    text_field.setText("Error can't have multiple decimals  ");
                }
            }
        }
    }
    /**
     * Add action performed method for the various buttons and when they are
     * clicked it displays the button on the screen.
     * For every operator the first number is displayed then the operator on
     * the same line. e.g. 2+
     *
     * @param click when button is clicked displays it on the screen.
     */
    @Override
    public void actionPerformed(ActionEvent click) {


            for (int i = 0; i < 10; i++) {
                if (click.getSource() == NumButton[i]) {
                    text_field.setText(text_field.getText().concat(String.valueOf(i)));
                }
            }

            if (click.getSource() == AddButton) {
                input1 = Double.parseDouble(text_field.getText());
                operation = '+';
                text_field.setText("");
            }
            if (click.getSource() == SubButton) {
                input1 = Double.parseDouble(text_field.getText());
                operation = '-';
                text_field.setText("");

            }
            if (click.getSource() == MulButton) {
                input1 = Double.parseDouble(text_field.getText());
                operation = '*';
                text_field.setText("");
            }
            if (click.getSource() == DivButton) {
                input1 = Double.parseDouble(text_field.getText());
                operation = '/';
                text_field.setText("");
            }

            if (click.getSource() == EquButton) {
                input2 = Double.parseDouble(text_field.getText());

                /** Switch case for the various operators and formats a string to
                 *  display the first,second and final answer on different lines.
                 *
                 */
                switch (operation) {
                    case '+':
                        outcome = input1 + input2;
                        final_Answer = String.format("%.2f+" + "\n" + "%.2f =" + "\n" + "%.2f", input1, input2, outcome);
                        break;
                    case '-':
                        outcome = input1 - input2;
                        final_Answer = String.format("%.2f-" + "\n" + "%.2f =" + "\n" + "%.2f", input1, input2, outcome);
                        break;
                    case '*':
                        outcome = input1 * input2;
                        final_Answer = String.format("%.2f*" + "\n" + "%.2f =" + "\n" + "%.2f", input1, input2, outcome);
                        break;
                    case '/':
                        outcome = input1 / input2;
                        final_Answer = String.format("%.2f/" + "\n" + "%.2f =" + "\n" + "%.2f", input1, input2, outcome);
                        break;
                }

                text_field.setText(final_Answer);
                input1 = outcome;

                if (operation == '/' && input2 == 0) {
                    text_field.setText("Error you are not able to divide by zero!");

                }
          }

    }

    /** Method to clear the information displayed in the text box
     *  which clears the previous character or clears the whole screen.
     *
     */

    public class clear implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == Clear_All_Button) {
                text_field.setText("");
            }

            if (e.getSource() == Clear_Last_Button) {
                if (text_field.getText().length()!=0){
                    String b = text_field.getText().substring(0,text_field.getText().length()-1);
                    text_field.setText(b);
                }
            }
       }
    }
}







