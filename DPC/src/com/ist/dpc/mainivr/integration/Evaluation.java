package com.ist.dpc.mainivr.integration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ist.dpc.mainivr.configuration.C;
import com.ist.tools.v2.db.DBExecute;
import com.ist.tools.v2.db.PrepStmtWork;

/**
 * @author AliHelmy
 *
 */
public class Evaluation implements Serializable {
	private static final long serialVersionUID = 572753453055294745L;

	private static Logger LOGGER = Logger.getLogger(Evaluation.class);

	private String sessionID, callerNumber, agentID, agentName, agentExt;

	public Evaluation(String sessionID, String callerNumber, String agentID, String agentExt, String agentName) {
		this.sessionID = sessionID;
		this.callerNumber = callerNumber;
		this.agentID = agentID;
		this.agentName = agentName;
		this.agentExt = agentExt;
	}

	public void initRecord() {
		LOGGER.info("entry(" + callerNumber + ")");
		DBExecute db = null;
		try {
			db = DBExecute.getInstance(C.CONFIGURATOR, "UCCX", Evaluation.class);
			LOGGER.info(
					"Inserting empty record in evaluation DB [Session:" + sessionID + ", CallerNumber:" + callerNumber
							+ ", AgentID:" + agentID + ", AgentExt:" + agentExt + ", AgentName:" + agentName + "]...");
			db.execute(
					"INSERT INTO post_call_survey(sessionID, callerNumber,q1,q2,q3,agentID, agentName, agentExtension) VALUES (?, ?,'N/A','N/A','N/A', ?, ?, ?)",
					new PrepStmtWork() {
						@Override
						public void preExecution(Connection connection, PreparedStatement prepStmt)
								throws SQLException {
							prepStmt.setString(1, sessionID);
							prepStmt.setString(2, callerNumber);
							prepStmt.setString(3, agentID);
							prepStmt.setString(4, agentName);
							prepStmt.setString(5, agentExt);
						}

						@Override
						public void postExecution(Connection connection, PreparedStatement prepStmt)
								throws SQLException {
							super.postExecution(connection, prepStmt);
							LOGGER.info("Record inserted [Session:" + sessionID + "].");
						}
					});
		} catch (Exception ex) {
			LOGGER.error("Error while inserting evaluation q1", ex);
		} finally {
			if (db != null) {
				db.close();
			}
		}
		LOGGER.info("exit()");
	}

	public void evaluateQ1(final String answer) {
		LOGGER.info("entry(" + callerNumber + ")");
		DBExecute db = null;
		try {
			db = DBExecute.getInstance(C.CONFIGURATOR, "UCCX", Evaluation.class);
			LOGGER.info("Updating evaluation DB, Set q1 = " + answer + " for [SessionID:" + sessionID + "]...");
			db.execute("UPDATE post_call_survey set q1 = ? WHERE sessionID = ?", new PrepStmtWork() {
				@Override
				public void preExecution(Connection connection, PreparedStatement prepStmt) throws SQLException {
					prepStmt.setString(1, answer);
					prepStmt.setString(2, sessionID);
				}

				@Override
				public void postExecution(Connection connection, PreparedStatement prepStmt) throws SQLException {
					super.postExecution(connection, prepStmt);
					LOGGER.info("Evaluation q1 updated.");
				}
			});
		} catch (Exception ex) {
			LOGGER.error("Error while inserting evaluation q2", ex);
		} finally {
			if (db != null) {
				db.close();
			}
		}
		LOGGER.info("exit()");
	}

	public void evaluateQ2(final String answer) {
		LOGGER.info("entry(" + callerNumber + ")");
		DBExecute db = null;
		try {
			db = DBExecute.getInstance(C.CONFIGURATOR, "UCCX", Evaluation.class);
			LOGGER.info("Updating evaluation DB, Set q2 = " + answer + " for [SessionID:" + sessionID + "]...");
			db.execute("UPDATE post_call_survey set q2 = ? WHERE sessionID = ?", new PrepStmtWork() {
				@Override
				public void preExecution(Connection connection, PreparedStatement prepStmt) throws SQLException {
					prepStmt.setString(1, answer);
					prepStmt.setString(2, sessionID);
				}

				@Override
				public void postExecution(Connection connection, PreparedStatement prepStmt) throws SQLException {
					super.postExecution(connection, prepStmt);
					LOGGER.info("Evaluation q2 updated.");
				}
			});
		} catch (Exception ex) {
			LOGGER.error("Error while inserting evaluation q2", ex);
		} finally {
			if (db != null) {
				db.close();
			}
		}
		LOGGER.info("exit()");
	}

	public void evaluateQ3(final String answer) {
		
		LOGGER.info("entry(" + callerNumber + ")");
		DBExecute db = null;
		try {
			db = DBExecute.getInstance(C.CONFIGURATOR, "UCCX", Evaluation.class);
			LOGGER.info("Updating evaluation DB, Set q3 = " + answer + " for [SessionID:" + sessionID + "]...");
			db.execute("UPDATE post_call_survey set q3 = ? WHERE sessionID = ?", new PrepStmtWork() {
				@Override
				public void preExecution(Connection connection, PreparedStatement prepStmt) throws SQLException {
					prepStmt.setString(1, answer);
					prepStmt.setString(2, sessionID);
				}

				@Override
				public void postExecution(Connection connection, PreparedStatement prepStmt) throws SQLException {
					super.postExecution(connection, prepStmt);
					LOGGER.info("Evaluation q3 updated.");
				}
			});
		} catch (Exception ex) {
			LOGGER.error("Error while inserting evaluation q2", ex);
		} finally {
			if (db != null) {
				db.close();
			}
		}
		LOGGER.info("exit()");
	}
	
/*
	public static void main(String[] args) {
		Evaluation eval = new Evaluation("10000004", "71102", "agentID", "Name", "ext");
		eval.initRecord();
		eval.evaluateQ1("Yes");
		eval.evaluateQ2("Yes");
		eval.evaluateQ3("Yes");
	}
	*/
}
