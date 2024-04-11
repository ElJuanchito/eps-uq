package co.edu.uniquindio.eps_uq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum PriorityLevel {
	HIGH("High"), MEDIUM("Medium"), LOW ("Low");
	
	@Getter
	private String text;
	
	public static String [] textValues(){
		PriorityLevel [] values= values();
		String [] textValues= new String [values.length];
		for(int i =0; i< values.length;i++) {
			textValues[i]= values[i].getText();
		}
		return textValues;
		
	}
	
	public PriorityLevel valueByText(String text) {
		PriorityLevel [] values= values();
		for(PriorityLevel level: values) {
			if(level.getText().equals(text))
				return level;
		}
		return null;
	}
}
