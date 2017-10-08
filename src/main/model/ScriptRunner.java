package model;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import control.Controller;

/**
 * This class allows you to execute a sql script
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.2
 */
public class ScriptRunner {
	
	/**
	 * The default delimiter
	 */
    private static final String DEFAULT_DELIMITER = ";";
    
    /**
     * The PL_SQL_BLOCK_SPLIT_DELIMITER
     */
    private static final String PL_SQL_BLOCK_SPLIT_DELIMITER = "+";
    
    /**
     * The PL_SQL_BLOCK_END_DELIMITER
     */
    private static final String PL_SQL_BLOCK_END_DELIMITER = "#";
    
    /**
     * the parameter for the execution of the script
     */
    private final boolean autoCommit, stopOnError;
    
    /**
     * The connection
     */
    private final Connection connection;
    
    /**
     * The delimiter
     */
    private String delimiter = ScriptRunner.DEFAULT_DELIMITER;

    /**
     * The controller
     */
    private Controller control;

    /**
     * The constructor of the class ScriptRunner
     * @param control the controller
     * @param connection the connection 
     * @param autoCommit isAutoCommit
     * @param stopOnError isStopOnError
     */
    public ScriptRunner(Controller control, final Connection connection, final boolean autoCommit, final boolean stopOnError) {
        
    	this.control = control;
    	
    	if (connection == null) {
            throw new RuntimeException("Connection non valide");
        }
		 
        this.connection = connection;
        this.autoCommit = autoCommit;
        this.stopOnError = stopOnError;
    }
    
    /**
     * The methods to allow the run the script
     * @param reader the reader where there is the script a execute
     * @throws SQLException the sqlException
     * @throws IOException the IOException
     */
    public void runScript(final Reader reader) throws SQLException, IOException {
        final boolean originalAutoCommit = this.connection.getAutoCommit();
        try {
            if (originalAutoCommit != this.autoCommit) {
                this.connection.setAutoCommit(this.autoCommit);
            }
            this.runScript(this.connection, reader);
        } finally {
            this.connection.setAutoCommit(originalAutoCommit);
        }
    }

    /**
     * The methods to allow the run the script
     * @param conn the connection
     * @param reader the reader where there is the script a execute
     * @throws SQLException the sqlException
     * @throws IOException the IOException
     */
    private void runScript(final Connection conn, final Reader reader) throws SQLException, IOException {
        StringBuffer command = null;

        try {
            final LineNumberReader lineReader = new LineNumberReader(reader);
            String line = null;
            while ((line = lineReader.readLine()) != null) {
                if (command == null) {
                    command = new StringBuffer();
                }

                String trimmedLine = line.trim();

				// Interpret SQL Comment & Some statement that are not executable
                if (trimmedLine.startsWith("--")
                        || trimmedLine.startsWith("//")
                        || trimmedLine.startsWith("#")
                        || trimmedLine.toLowerCase().startsWith("rem inserting into")
                        || trimmedLine.toLowerCase().startsWith("set define off")) {

                    // do nothing...
                } else if (trimmedLine.endsWith(this.delimiter) || trimmedLine.endsWith(PL_SQL_BLOCK_END_DELIMITER)) { // Line is end of statement
                    
                    // Append
                    if (trimmedLine.endsWith(this.delimiter)) {
                        command.append(line.substring(0, line.lastIndexOf(this.delimiter)));
                        command.append(" ");
                    } else if (trimmedLine.endsWith(PL_SQL_BLOCK_END_DELIMITER)) {
                        command.append(line.substring(0, line.lastIndexOf(PL_SQL_BLOCK_END_DELIMITER)));
                        command.append(" ");
                    }

                    Statement stmt = null;
                    ResultSet rs = null;
                    try {
                        stmt = conn.createStatement();
                        boolean hasResults = false;
                        if (this.stopOnError) {
                            hasResults = stmt.execute(command.toString());
                        } else {
                            try {
                                stmt.execute(command.toString());
                            } catch (final SQLException e) {
                            	this.control.printResult("Error executing SQL Command: \"" + command + "\"");
                            	this.control.printResult(e.getMessage());
                                throw e;
                            }
                        }

                        rs = stmt.getResultSet();
                        this.control.printResult(conn, command.toString());
        
                        command = null;
                    } finally {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (final Exception e) {
                            	this.control.printResult("Failed to close result: " + e.getMessage());
                            }
                        }
                        if (stmt != null) {
                            try {
                                stmt.close();
                            } catch (final Exception e) {
                            	this.control.printResult("Failed to close statement: " + e.getMessage());
                            }
                        }
                    }
                } else if (trimmedLine.endsWith(PL_SQL_BLOCK_SPLIT_DELIMITER)) {
                    command.append(line.substring(0, line.lastIndexOf(this.PL_SQL_BLOCK_SPLIT_DELIMITER)));
                    command.append(" ");
                } else { // Line is middle of a statement

                    // Append
                    command.append(line);
                    command.append(" ");
                }
            }
            if (!this.autoCommit) {
                conn.commit();
            }
        } catch (final SQLException e) {
            conn.rollback();
            this.control.printResult("Error executing SQL Command: \"" + command + "\"");
            this.control.printResult(e.getMessage());
            throw e;
        } catch (final IOException e) {
        	this.control.printResult("Error reading SQL Script.");
        	this.control.printResult(e.getMessage());
            throw e;
        }
    }
}
