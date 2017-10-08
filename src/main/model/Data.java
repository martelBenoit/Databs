package model;

/**
 * This enumeration makes it possible to know 
 * if the data to be processed are for a table, a view or a trigger
 * @author Benoît and Alexandre
 * @version 1.0
 */
public enum Data {
	TABLE,
	VIEW,
	TRIGGER;
}
