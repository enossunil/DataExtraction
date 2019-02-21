package com.india.elects.ls.year1977.def.validation;

import com.india.elects.extractor.IPreProcessor;
import com.india.elects.extractor.IValidateMappings;
import com.india.elects.extractor.model.Position;

public enum Validations implements IValidateMappings {

	STATE_NM("ST", new Position(0, 0), "STATE/UT", null),
	STATE_CD("ST", new Position(0, 1), "CODE", null),
	CONS_NM("ST", new Position(1, 0), "CONSTITUENCY", null),
	CONS_CD("ST", new Position(1, 1), "NO", null),

	CANDIDATE_ML("Canditates", new Position(0, 1), "MALE", null),
	CANDIDATE_FEMALE("Canditates", new Position(0, 2), "FEMALE", null),
	CANDIDATE_TOTAL("Canditates", new Position(0, 3), "TOTAL", null),
	CANDIDATE_CAN("Canditates", new Position(1, 0), "I. CANDIDATES", null),
	CANDIDATE_NOM("Canditates", new Position(2, 0), "1.NOMINATED", null),
	CANDIDATE_REJ("Canditates", new Position(3, 0), "2.REJECTED", null),	
	CANDIDATE_WITHDRN("Canditates", new Position(4, 0), "3.WITHDRAWN", null),
	CANDIDATE_CONTESTED("Canditates", new Position(5,0), "4.CONTESTED", null),	
	CANDIDATE_FORFEITED("Canditates", new Position(6, 0), "5.FORFEITED DEPOSIT", null),

	ELECTORS_ELEC("Electors", new Position(0, 0), "II. ELECTORS", null),
	ELECTORS_GEN("Electors", new Position(1, 0), "1.GENERAL", null),
	ELECTORS_SER("Electors", new Position(2, 0), "2.SERVICE", null),	
	ELECTORS_TOT("Electors", new Position(3, 0), "3.TOTAL", null),


	VOTERS_GEN("Voters", new Position(0, 0), "1.GENERAL", null),
	VOTERS_POS("Voters", new Position(1, 0), "2.POSTAL", null),
	VOTERS_TOT("Voters", new Position(2, 0), "3.TOTAL", null),	

	
	
	VOTE("Votes", new Position(0, 0), "IV. VOTES", null),
	VOTE_POLLED("Votes", new Position(1, 0), "1.POLLED", null),
	VOTE_VALID("Votes", new Position(2, 0), "2.VALID", null),
	VOTE_REJECTED("Votes", new Position(3, 0), "3.REJECTED", null),
	VOTE_MISSING("Votes", new Position(4, 0), "4.MISSING", null),
	VOTE_TENDERED("Votes", new Position(5, 0), "5.TENDERED", null),
	
//	POLLING_PERCENTAGE("Polling-Percantage", new Position(1, 0), "III(A). POLLING PERCENTAGE", null),
	
	POLLING_STATIONS_NR("PollStation", new Position(0, 0), "NUMBER", null),
	POLLING_STATIONS_AVG("PollStation", new Position(0, 1), "AVERAGE ELECTORS PER POLLING STATION", null),
	
	
	POLLING_DATES("dates", new Position(0, 0), "POLLING", null),
	COUNTING_DATES("dates", new Position(0, 1), "COUNTING", null),
	RESULT_DATES("dates", new Position(0, 2), "DECLARATION OF RESULT", null),
	
	RESULT_PARTY("results", new Position(0, 1), "PARTY", null),
	RESULT_CAN("results", new Position(0, 2), "CANDIDATE", null),
	RESULT_VOTES("results", new Position(0, 3), "VOTES", null),
	RESULT_WIN("results", new Position(1, 0), "WINNER", null),
	RESULT_RUN("results", new Position(3, 0), "RUNER-UP", null),
	
	MARGIN("margin", new Position(0, 0), "MARGIN", null);

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
