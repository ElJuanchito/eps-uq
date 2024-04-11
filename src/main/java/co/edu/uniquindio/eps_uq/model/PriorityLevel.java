package co.edu.uniquindio.eps_uq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum PriorityLevel {
	HIGH(3), MEDIUM(2), LOW (2);
	
	@Getter
	private int value;
	
	public static int [] intValues(){
		PriorityLevel [] values= values();
		int [] textValues= new int [values.length];
		for(int i =0; i< values.length;i++) {
			textValues[i]= values[i].getValue();
		}
		return textValues;
		
	}
	
	public PriorityLevel valueByIntValue(int value) {
		PriorityLevel [] values= values();
		for(PriorityLevel level: values) {
			if(level.getValue()==value)
				return level;
		}
		return null;
	}
}
