package co.edu.uniquindio.eps_uq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum PriorityLevel {
	HIGH(3,"Alta"), MEDIUM(2,"Media"), LOW (1,"Baja");
	
	private int value;
	private String text;
	
	public static int [] intValues(){
		PriorityLevel [] values= values();
		int [] textValues= new int [values.length];
		for(int i =0; i< values.length;i++) {
			textValues[i]= values[i].getValue();
		}
		return textValues;
		
	}
	public static String [] textValues(){
		PriorityLevel [] values= values();
		String[] textValues= new String[values.length];
		for(int i =0; i< values.length;i++) {
			textValues[i]= values[i].text;
		}
		return textValues;
		
	}
	
	public static PriorityLevel valueByIntValue(int value) {
		PriorityLevel [] values= values();
		for(PriorityLevel level: values) {
			if(level.getValue()==value)
				return level;
		}
		return null;
	}
	
	public static PriorityLevel valueByText(String text) {
		PriorityLevel [] values= values();
		for(PriorityLevel level: values) {
			if (level.text == text)
				return level;
		}
		return null;
	}
}
