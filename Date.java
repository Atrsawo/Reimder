package q22;


import java.util.Hashtable;

/*
 * this class represent a date 
 * 
 */
public class Date  {
    
    private int day;
    private int month;
    private int year;
  
    public Hashtable<Integer,String> MonthNum = new Hashtable<Integer, String>();

    public Date(int d, int m, int y){
    	MonthsNameTab();
        day = d;
        month = m;
        year = y;
    }
    public Date(){
    	MonthsNameTab();
    	day = 1;
    	month = 11;
    	year = 2021;
    }
    public Date(Date OldDate){
        this(OldDate.day, OldDate.month ,OldDate.year);
    }

    /*
     * this method create a month table to hold the months 
     */
    private void MonthsNameTab(){
    	for(int i=0; i<12;i++)
        {    		
        	MonthNum.put(i,i+1+"");
        }   	
    }

    @Override
    public String toString() {
        return day+"/"+MonthNum.get(month)+"/"+year;
    }

    @Override
    public int hashCode() {
        return year*month*day;
    }

    
    @Override
    public boolean equals(Object o) {
        if(o instanceof Date){
            return ((Date)o).day == this.day && ((Date)o).month == this.month && ((Date)o).year == this.year;
        }
        return super.equals(o);
    }

    /**
     * get and set functions
     */
    public int getDay(){
    	return day;
    	}
    public int getMonth(){
    	return month;
    	}
    public int getYear(){
    	return year;
    }
    public void setDay(int Day){
    	day = Day;
    }
    public void setMonth(int Month){
    	month = Month;
    }
    public void setYear(int Year){
    	year = Year;
    } 
}
