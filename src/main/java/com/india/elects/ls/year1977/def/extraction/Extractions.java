package com.india.elects.ls.year1977.def.extraction;

import com.india.elects.extractor.IExtractMappings;
import com.india.elects.extractor.IPreProcessor;
import com.india.elects.extractor.model.Position;

public enum Extractions implements IExtractMappings {
	
	REJECTED_VOTE("Votes", new Position(2, 1), "polledPostalInvalid", null),
	NOT_EVM("Votes", new Position(4, 1), "polledTestEvm", null),
	TOTAL_VALID("Votes", new Position(6, 1), "polledValidTotal", null),
	TENDERED_VOTES("Votes", new Position(8, 1), "polledTenderedVotes", null),
	
//	TODO ENO POLLING_PERCENTAGE("Polling-Percantage", new Position(0, 0), "", null),
	
	POLLING_STATIONS_NR("Polling-Stations", new Position(1, 1), "pollingStationCount", null),
	POLLING_STATIONS_AVG("Polling-Stations", new Position(1, 3), "pollingStationElectoralAvgCnt", null),
	REPOLL_DATES("Re-Poll-Dates", new Position(0, 1), "repollDate", null),
	POLLING_DATES("Polling-Counting-Dates", new Position(3, 0), "pollingDate", null),
	COUNTING_DATES("Polling-Counting-Dates", new Position(3, 1), "countingDate", null),
	RESULT_DATES("Polling-Counting-Dates", new Position(3, 2), "resultDate", null),
	RESULT_PARTY_WIN("Results", new Position(2, 1), "resultWinnerParty", null),
	RESULT_CAN_WIN("Results", new Position(2, 2), "resultWinnerCandidate", null),
	RESULT_VOTES_WIN("Results", new Position(2, 3), "resultWinnerVote", null),
	RESULT_PARTY_RUN("Results", new Position(4, 1), "resultRunnerParty", null),
	RESULT_CAN_RUN("Results", new Position(4, 2), "resultRunnerCandidate", null),
	RESULT_VOTES_RUN("Results", new Position(4, 3), "resultRunnerVote", null),
	MARGIN("Margin", new Position(0, 1), "margin", null);

	private String tableId;
	private Position position;
	private String fieldName;
	private IPreProcessor preProcessor;

	private Extractions(String tableId, Position position, String fieldName, IPreProcessor preProcessor) {
		this.tableId = tableId;
		this.position = position;
		this.fieldName = fieldName;
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
	public String getFieldName() {
		return fieldName;
	}

	@Override
	public IPreProcessor getPreProcessor() {
		return preProcessor;
	}

}
