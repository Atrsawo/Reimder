package q22;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class Reminder extends JPanel {
    
    public Hashtable<Date,String> data;
    private Date oldDate;
    private Date newDate;
    private JTextArea text;
    private int flag = 0;

    /**
     * empty contractor
     */
    public Reminder() {
    	this(new Hashtable<Date,String>());
	}

	/**
     * Constructor with data
     * @param Data data to read from
     */
    public Reminder(Hashtable<Date,String> Data){
        data = Data;
        oldDate = new Date();
        newDate = new Date(oldDate);
        
        setLayout(new BorderLayout());
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(new JLabel("Date: "));
        
        JComboBox day = new JComboBox();
        for (int i = 1; i<=31; i++)
            day.addItem(new Comp(i, i+""));
        day.setSelectedIndex(oldDate.getDay() - 1);
        day.setName("Day");
        day.addActionListener(new ComboBoxHnadler());
        p.add(day);
        
        p.add(new JLabel("/"));
        
        JComboBox month = new JComboBox();
        for (Integer id : oldDate.MonthNum.keySet())
            month.addItem(new Comp(id,oldDate.MonthNum.get(id)));
        month.setSelectedIndex(oldDate.getMonth());
        month.setName("Month");
        month.addActionListener(new ComboBoxHnadler());
        p.add(month);
        
        p.add(new JLabel("/"));
        JComboBox year = new JComboBox();
        for (int i = oldDate.getYear(); i <= oldDate.getYear()+30; i++)
            year.addItem(new Comp(i, i+""));
        year.setSelectedIndex(0);
        year.setName("Year");
        year.addActionListener(new ComboBoxHnadler());
        p.add(year);
        
        JButton show = new JButton("Show note");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s1 = text.getText();
                String s2 ="";
                if(data.get(oldDate) != null) s2 = data.get(oldDate);
                if(!oldDate.equals(newDate)) flag = 0;
                if(!s1.equals(s2) && flag == 0)
                {        
                    int res = JOptionPane.showConfirmDialog(null, "Do you want to save note before continue?","Data is not saved over date: "+oldDate.toString(),JOptionPane.YES_NO_CANCEL_OPTION);               
                    if (res == 0)
                    {
                        data.put(newDate,text.getText());
                        oldDate = new Date(newDate);
                        getData();
                    }
                    if (res == 1)
                    {
                        oldDate = new Date(newDate);
                        getData();
                    }
                }
                else 
                {
                    oldDate = new Date(newDate);
                    getData();
                }
            }
        });
        p.add(show);
        
        add(p, BorderLayout.NORTH);

        text = new JTextArea(10,10);
        getData();
        add(text,BorderLayout.CENTER);

        JButton save = new JButton("save");
        save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				flag = 1;
				data.put(newDate,text.getText());
                JOptionPane.showMessageDialog(null, "Note is Saved","Massage",JOptionPane.INFORMATION_MESSAGE);
				
			}
		}); 
        add(save,BorderLayout.SOUTH);

    }

    /*
     * method to handle the button events
     */
    private class ComboBoxHnadler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox c =  (JComboBox)e.getSource();
            Comp comp = (Comp) c.getSelectedItem();
            if(c.getName().equals("Day"))	newDate.setDay(comp.getId());
            else if(c.getName().equals("Month")) newDate.setMonth(comp.getId());
            else if(c.getName().equals("Year"))newDate.setYear(comp.getId());
        }
    }
    
    /*
     * this method update the data in the text  box and print the value of hash table
     */
    private void getData(){
        String s = data.get(newDate);
        text.setText(s);
        System.out.printf("data:%s%n",s);
    }

    /**
     * class for items in JComboBox
     */
    private class Comp {

        private int id;
        private String compData;

        public Comp(int id, String s) {
            this.id = id;
            this.compData= s;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return compData;
        }
    }
}

