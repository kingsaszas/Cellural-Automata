package sample.com;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Main extends JFrame {

    DataGenerator dg;

    private JDrawPanel jDrawPanel;
    private JPanel optionsPanel, mainPanel;
    private JButton showButton, clearButton;

    private JComboBox typeOfArray;
    private JTextArea jTextArea;

    public void showPicture() {

        BufferedImage image = new BufferedImage(dg.wiLength,dg.hiLength,BufferedImage.TYPE_INT_RGB);
        if(dg.tabFilled != null) {
            for (int hi = 0; hi < dg.hiLength; hi++) {
                for (int wi = 0; wi < dg.wiLength; wi++) {
                    if (dg.tabFilled[hi][wi] == 1) {
                        image.setRGB(wi, hi, Color.BLACK.getRGB());
                    } else {
                        image.setRGB(wi, hi, Color.WHITE.getRGB());
                    }
                }
            }
            dg.image = image;
        }
    }

    public void clearPicture() {
        BufferedImage image = new BufferedImage(dg.wiLength,dg.hiLength,BufferedImage.TYPE_INT_RGB);
        for (int hi = 0; hi < dg.hiLength; hi++) {
            for (int wi = 0; wi < dg.wiLength; wi++) {
                image.setRGB(wi, hi, Color.WHITE.getRGB());
            }
        }
        dg.image = image;
    }

    public Main(String title) {
        super(title);
        this.dg = new DataGenerator();          //!!!!!!!
        //Functions f = new Functions(dg);
        FunctionsNew f = new FunctionsNew(dg);

        showButton = new JButton("show picture");
        clearButton = new JButton(("clear window"));

        typeOfArray = new JComboBox<String>();
        typeOfArray.addItem("1 on the edges");
        typeOfArray.addItem("periodic statements");

        jTextArea = new JTextArea(1,4);
        jTextArea.setText("0");

        //panels
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        optionsPanel.add(typeOfArray);
        optionsPanel.add(jTextArea);
        optionsPanel.add(showButton);
        optionsPanel.add(clearButton);

        jDrawPanel = new JDrawPanel(dg);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(BorderLayout.NORTH, optionsPanel);
        mainPanel.add(BorderLayout.CENTER, jDrawPanel);


        //window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 100);
        this.setContentPane(mainPanel);
        this.setSize(new Dimension(600, 400));
        this.setResizable(false);
        this.setVisible(true);

        //buttons
        typeOfArray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox jcb = (JComboBox)e.getSource();
                String selectedFunction = jcb.getSelectedItem().toString();
                if(selectedFunction.equals("1 on the edges")) {
                    dg.selectedTypeOfArray = "oneEdges";
                    System.out.println(dg.selectedTypeOfArray);
                } else if(selectedFunction.equals("periodic statements")) {
                    dg.selectedTypeOfArray = "periodic";
                    System.out.println(dg.selectedTypeOfArray);
                }
            }
        });

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dg.selectedRule = jTextArea.getText();
                if (dg.selectedTypeOfArray != null) {
                    if (dg.selectedTypeOfArray.equals("periodic")) {
                        f.fillPeriedicArray();
                    } else {
                        f.fillArrayWithTheOneEdges();
                    }

                    showPicture();
                    jDrawPanel.repaint();
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearPicture();
                jDrawPanel.repaint();
            }
        });

    }

    public static void main(String[] args) {
        Main main = new Main("Automaty komórkowe");
        main.jDrawPanel.repaint();

    }
}
