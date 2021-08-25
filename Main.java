package q22;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Hashtable;

/*
 * Main function, manage the communication with the user
 */
public class Main {
    private static Reminder rem;
    
    public static void main(String[] args) {
    	final int fSize = 400;
        if (JOptionPane.showConfirmDialog(null, "do you want to load input data from file?","Open File",JOptionPane.YES_NO_OPTION) == 0)
        {
            try{//get input file from the user
	                JFileChooser file = new JFileChooser();
	                file.setCurrentDirectory(new File("."));
	                file.setFileSelectionMode(JFileChooser.FILES_ONLY);
	                int result = file.showDialog(null,"select file");
	                if (result == JFileChooser.APPROVE_OPTION  || file.getSelectedFile().toPath()!= null) {      
	                Path path = file.getSelectedFile().toPath();
	                ObjectInputStream input = new ObjectInputStream(Files.newInputStream(path));
	                Hashtable<Date,String> data = (Hashtable<Date,String>)input.readObject();
	                rem = new Reminder(data);
	                }

           		}
            catch (Exception e){
                JOptionPane.showMessageDialog(null, "Can not read from file, file may be empty or illegals","ERROR",JOptionPane.ERROR_MESSAGE);
                rem = new Reminder();
            }
        }
        else {
        	rem = new Reminder();
        }
        // create main frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Renainder");
        frame.setSize(fSize,fSize);
        frame.setVisible(true);
        frame.add(rem);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent w) {
                int res = JOptionPane.showConfirmDialog(frame,"Do you want to save your notes?", "",JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);        
                if (res != JOptionPane.CLOSED_OPTION)
                {
                    if (res == JOptionPane.YES_OPTION)
                    {
                        JFileChooser outputFile = new JFileChooser();
                        int command = outputFile.showSaveDialog(null);
                        if (command == JFileChooser.APPROVE_OPTION) {
                            try
                            {//try to save data into file
                            	FileOutputStream f = new FileOutputStream(outputFile.getSelectedFile());                      
                                ObjectOutputStream objStream = new ObjectOutputStream( f ); 
                                objStream.writeObject(rem.data);                           
                                objStream.close();                              
                            }
                            catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Saving data failed","ERROR",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    System.exit(0);
                }
            }
        });
    }
    
    
    
}
