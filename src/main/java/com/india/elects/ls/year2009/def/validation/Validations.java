package com.india.elects.ls.year2009.def.validation;

import com.india.elects.extractor.IPreProcessor;
import com.india.elects.extractor.IValidateMappings;
import com.india.elects.extractor.model.Position;

public enum Validations implements IValidateMappings {

	VOTE("Votes", new Position(0, 0), "VOTES", null),
	REJECTED_VOTE("Votes", new Position(2, 0), "1. REJECTED VOTES (POSTAL)", null),
	NOT_EVM("Votes", new Position(4, 0), "2. VOTES NOT RETREIVED FROM EVM", null),
	TOTAL_VALID("Votes", new Position(6, 0), "3. TOTAL VALID VOTES POLLED", null),
	TENDERED_VOTES("Votes", new Position(8, 0), "4. TENDERED VOTES", null),
	
	POLLING_PERCENTAGE("Polling-Percantage", new Position(1, 1), "III(A). POLLING PERCENTAGE", null),
	POLLING_STATIONS_NR("Polling-Stations", new Position(1, 1), "NUMBER", null),
	POLLING_STATIONS_AVG("Polling-Stations", new Position(1, 3), "AVERAGE ELECTORS PER POLLING STATION", null),
	REPOLL_DATES("Re-Poll-Dates", new Position(0, 0), "DATE(s) OF RE-POLL, IF ANY", null),
	POLLING_DATES("Polling-Counting-Dates", new Position(1, 0), "POLLING", null),
	COUNTING_DATES("Polling-Counting-Dates", new Position(1, 1), "COUNTING", null),
	RESULT_DATES("Polling-Counting-Dates", new Position(1, 2), "DECLARATION OF RESULT", null),
	RESULT_PARTY("Results", new Position(0, 1), "PARTY", null),
	RESULT_CAN("Results", new Position(0, 2), "CANDIDATE", null),
	RESULT_VOTES("Results", new Position(0, 3), "VOTES", null),
	RESULT_WIN("Results", new Position(2, 0), "WINNER", null),
	RESULT_RUN("Results", new Position(4, 0), "RUNER-UP", null), MARGIN("Margin", new Position(8, 0), "MARGIN", null);

	private String tableId;
	private Position position;
	private String expectedValue;
	private IPreProcessor preProcessor;

	private Validations(String tableId, Position position, String expectedValue, IPreProcessor preProcessor) {
		this.tableId = tableId;
		this.position = position;
		this.expectedValue = expectedValue;
		this.preProcessor = preProcessor;
	}

	@Override
	public String getTableId() {
		return tableId;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public String getExpectedValue() {
		return expectedValue;
	}

	@Override
	public IPreProcessor getPreProcessor() {
		return preProcessor;
	}

}
