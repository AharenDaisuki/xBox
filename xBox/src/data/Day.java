package data;
public class Day implements Cloneable{
	
	private int year;
	private int month;
	private int day;
	private static final String MonthNames="JanFebMarAprMayJunJulAugSepOctNovDec";

	public Day(int y, int m, int d) {
		this.year=y;
		this.month=m;
		this.day=d;		
	}    
    
    public Day(String sDay) { set(sDay); }
    
	
    public void set(String sDay) { //Set year,month,day based on a string like 01-Jan-2021
        String[] sDayParts = sDay.split("-");
        this.year = Integer.parseInt(sDayParts[2]);
        this.day = Integer.parseInt(sDayParts[0]); 
        this.month = MonthNames.indexOf(sDayParts[1])/3+1;
    }

    @Override
    public String toString() {		
        return day+"-"+ MonthNames.substring((month-1)*3,month*3) + "-"+ year; // (month-1)*3,(month)*3
    }
    

	public boolean isBigger(Day d) {
		if (year > d.getYear()) return true;
		else if (year==d.getYear()){
			if (month > d.getMonth()){
				return true;
			} 
			else if(month==d.getMonth()){
				if (day > d.getDay())
					return true;
			}
		}
		return false;
	}

    @Override
    public Day clone() {
        Day copy = null;
        try {
            copy = (Day) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return copy;
    }

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public Day getNextDay() {
        int m=month,y=year,d=day;
        if (m==2){
            if (isLeapYear(y)){
                if (d <= 28) 
                    return new Day(y,m,d+1);
                else
                    return new Day(y,m+1,1);
            } else{
                if (d <= 27) 
                    return new Day(y,m,d+1);
                else
                    return new Day(y,m+1,1);
            }

            
        } else if (m==1||m==3 ||m==5 ||m==7 ||m==8 ||m==10 ||m==12 ){
            if (d <= 30) return new Day(y,m,d+1);
            else{
                if (m==12)
                    return new Day(y+1,1,1);
                
                else
                    return new Day(y,m+1,1);
            }
        } else{
            if (d <= 29) return new Day(y,m,d+1);
            else{
                if (m==12)
                    return new Day(y+1,1,1);
                else
                    return new Day(y,m+1,1);
            }
        }
    } 
    
	public Day getDueDay() {
        int m=month,y=year,d=day;
        if (m==2){
            if (isLeapYear(y)){
                if (d <= 26) 
                    return new Day(y,m,d+3);
                else
                    return new Day(y,m+1,d-26);
            } else{
                if (d <= 25) 
                    return new Day(y,m,d+3);
                else
                    return new Day(y,m+1,d-25);
            }

            
        } else if (m==1||m==3 ||m==5 ||m==7 ||m==8 ||m==10 ||m==12 ){
            if (d <= 28) return new Day(y,m,d+3);
            else{
                if (m==12)
                    return new Day(y+1,1,d-28);
                
                else
                    return new Day(y,m+1,d-28);
            }
        } else{
            if (d <= 27) return new Day(y,m,d+3);
            else{
                if (m==12)
                    return new Day(y+1,1,d-27);
                else
                    return new Day(y,m+1,d-27);
            }
        }
    } 


    static public boolean isLeapYear(int y) {
        if (y%400==0)
            return true;
        else if (y%100==0)
            return false;
        else if (y%4==0)
            return true;
        else
            return false;
    }

}






