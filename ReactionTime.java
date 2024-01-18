import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReactionTime {
    private static JPanel panel;  // Move the panel declaration here
    private static int clickCount = 0;
    private static long startTime;
    private static long endTime;
    private static boolean isgreen = false;
    private static boolean jumpedthegun = false;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame();
        frame.setTitle("Reaction Time Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        JButton startButton = new JButton("Click to Begin the Reaction Time Test");
        startButton.setSize(500, 500);
        startButton.setBounds(450, 300, 650, 150);
        startButton.setFont(new Font("Arial", Font.PLAIN, 30));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clickCount == 0) 
                {
                     try 
                    {
                        begin(startButton);
                    } 
                    
                    catch (InterruptedException e1) 
                    {
                        e1.printStackTrace();
                    }
                }

                else 
                {
                    if (!isgreen && clickCount == 1) 
                    {
                        startButton.setText("Too Soon! Let's start over");
                        jumpedthegun = true;
                        System.exit(0);
                    }

                    if (isgreen && clickCount ==1) 
                    {
                        clickCount = 2;
                        endTime = System.currentTimeMillis();
                        long netTime = endTime - startTime;
                        startButton.setText("Your reaction time is "+netTime +" milliseconds");
                    }
                }
               
            }
        });

        panel = new JPanel();  // Initialize the panel here
        panel.setLayout(null);
        panel.add(startButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void begin(JButton button) throws InterruptedException {
       
        long timeout = (long) (Math.random() * 5000) + 500;
        button.setText("Wait for the screen to turn green");
         clickCount = 1;
         if (!jumpedthegun) 
         {
                Timer timer = new Timer((int) timeout, new ActionListener() {
                @Override
                    public void actionPerformed(ActionEvent e) {
                        panel.setBackground(Color.green);  // Now panel is accessible
                        button.setText("CLICK!!!");
                        isgreen = true;
                        startTime = System.currentTimeMillis();

                    }
                });

                timer.setRepeats(false);
                timer.start();
         }
    }
}    



