package model;

/**
 * This class allows you to define the parameters of a specified column.
 * @author Benoît and Alexandre
 * @version 1.0
 * @since 20 juin 2017
 */
public class AttributDescribe {
	
	/**
	 * Primary key
	 */
	private Boolean pk;
	
	/**
	 * Nul
	 */
	private Boolean nul;
	
	/**
	 * Unique key
	 */
	private Boolean uk;
	
	/**
	 * Name of the column
	 */
	private String name;
	
	/**
	 * Maximum size of values in column
	 */
	private String taille;
	
	/**
	 * Type of values in column
	 */
	private String type;
	
	/**
	 * Constructor of the class.
	 * <br> It takes as a parameter all the specifications for a column.
	 * @param pk primary key
	 * @param name name of the column
	 * @param type type of values in column
	 * @param taille maximum size of values in column
	 * @param nul nul
	 * @param uk unique key
	 */
	public AttributDescribe(Boolean pk, String name, String type, String taille, Boolean nul, Boolean uk){
		
		this.setPk(pk);
		this.setName(name);
		this.setType(type);
		this.setTaille(taille);
		this.setNul(nul);
		this.setUk(uk);
	}

	/**
	 * Getter
	 * @return the pk
	 */
	public Boolean getPk() {
		return pk;
	}

	/**
	 * Setter
	 * @param pk the pk to set
	 */
	public void setPk(Boolean pk) {
		this.pk = pk;
	}

	/** 
	 * Getter
	 * @return the nul
	 */
	public Boolean getNul() {
		return nul;
	}

	/**
	 * Setter
	 * @param nul the nul to set
	 */
	public void setNul(Boolean nul) {
		this.nul = nul;
	}

	/**
	 * Getter
	 * @return the uk
	 */
	public Boolean getUk() {
		return uk;
	}

	/**
	 * Setter
	 * @param uk the uk to set
	 */
	public void setUk(Boolean uk) {
		this.uk = uk;
	}

	/**
	 * Getter 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter
	 * @return the taille
	 */
	public String getTaille() {
		return taille;
	}

	/**
	 * Setter
	 * @param taille the taille to set
	 */
	public void setTaille(String taille) {
		this.taille = taille; 
	}

	/**
	 * Getter
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Setter
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	    
	    AttributDescribe other = (AttributDescribe) obj;
	    if (this.pk == null) {
	        if (other.getPk() != null)
	          return false;
	      } else if (!this.pk.equals(other.getPk()))
	        return false;
		if (this.nul == null) {
	        if (other.getNul() != null)
	          return false;
	      } else if (!this.nul.equals(other.getNul()))
	        return false;
	    if (this.uk == null) {
	        if (other.getUk() != null)
	          return false;
	      } else if (!this.uk.equals(other.getUk()))
	        return false;
	    if (this.name == null) {
	        if (other.getName() != null)
	          return false;
	      } else if (!this.name.equals(other.getName()))
	        return false;
	    if (this.taille == null) {
	        if (other.getTaille() != null)
	          return false;
	      } else if (!this.taille.equals(other.getTaille()))
	        return false;
	    if (this.type == null) {
	        if (other.getType() != null)
	          return false;
	      } else if (!this.type.equals(other.getType()))
	        return false;
	     return true;
	}


}
