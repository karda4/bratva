package ua.kardach.mmo.bratva.util;

import java.util.HashMap;
import java.util.Map;

public class Message {
	
	public static enum Type{
		TREAT_ENERGY_BY_PODGON,
		TREAT_ENERGY_BY_CASH,
		REACH_NEW_LEVEL
	}
	
	public Message(){
		mapStates = new HashMap<Type, Boolean>();
	}
	
	private Map<Type, Boolean> mapStates; 
	
	public boolean isSet(Type type){
		Boolean value = mapStates.get(type);
		if(value == null){
			return false;
		}
		return value;
	}
	
	public boolean isSetTreatEnergyByPodgon(){
		return isSet(Type.TREAT_ENERGY_BY_PODGON);
	}
	
	public void init(Type key, boolean value){
		mapStates.put(key, value);
	}
		
	public boolean isAvailable(){
		for(Type type : Type.values()){
			if(isSet(type)){
				return false;
			}
		}
		return true;
	}
}
