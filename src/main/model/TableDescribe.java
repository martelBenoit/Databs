package model;

import java.util.ArrayList;

/**
 * This class is the table which is represented by attributes and table name
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.0
 */
public class TableDescribe {
	
	/**
	 * This assignment corresponds to the ArrayList of attributes
	 */
	private ArrayList<AttributDescribe> attributs;
	
	/**
	 * This assignment corresponds to the table name
	 */
	private String nameTable;
	
	/**
	 * Initialize a TableDescribe object (Override)
	 * @param nameTable The name of the table
	 */
	public TableDescribe(String nameTable){
		this.setNameTable(nameTable);
		this.attributs = new ArrayList<AttributDescribe>();
	}
	
	/**
	 * Initialize a TableDescribe object (Override)
	 * @param nameTable the name of the table
	 * @param attributs the list of attributs of the table
	 */
	public TableDescribe(String nameTable, ArrayList<AttributDescribe> attributs){
		this.setNameTable(nameTable);
		this.attributs = attributs;
	}
	
	/**
	 * This method allow to add attributes to the ArryyList 
	 * @param attribut An attribute to add
	 */
	public void addAtributDescribe(AttributDescribe attribut){

		this.attributs.add(attribut);
	}
	
	/**
	 * Getter
	 * @return The attribute
	 */
	public ArrayList<AttributDescribe> getAttributs(){
		return this.attributs;
	}
	
	

	/**
	 * Getter
	 * @return the nameTable
	 */
	public String getNameTable() {
		return nameTable;
	}

	/**
	 * Setter
	 * @param nameTable the nameTable to set
	 */
	public void setNameTable(String nameTable) {
		this.nameTable = nameTable;
	}
	
	/**
	 * Methods toString() of the class TableDescribe
	 * @return ret The string which describe the TableDescribe object
	 */
	@Override
	public String toString(){
		String ret = this.nameTable+"\nPK\tName\tType\tTaille\tNul\tUnique";
		for(AttributDescribe attribut : attributs){
			ret = ret.concat("\n"+attribut.getPk()+"\t"+attribut.getName()+"\t"+attribut.getType()+"\t"+attribut.getTaille()
							+"\t"+attribut.getNul()+"\t"+attribut.getUk());
		} 
		
		return ret;
	}

	/**
	 * This method overrides the equals method of Object.
	 * <br>It verifies whether the parameters of the two objects specified in the constructor are the same.
	 * @param obj the object to compare with this.
	 * @return true, if the objects are equal between their parameters. 
	 */
	public boolean equals(Object obj){
		
		if (this == obj)
			return true;
	    if (obj == null)
	    	return false;
	    if (getClass() != obj.getClass())
	    	return false;
	    
	   	TableDescribe other = (TableDescribe) obj;
	    if (this.nameTable == null) {
	        if (other.getNameTable() != null)
	          return false;
	      } else if (!this.nameTable.equals(other.getNameTable()))
	        return false;
		if (this.attributs == null) {
	        if (other.getAttributs() != null)
	          return false;
	      } else if (!this.attributs.equals(other.getAttributs()))
	        return false;
	  
	     return true;
	}
}
