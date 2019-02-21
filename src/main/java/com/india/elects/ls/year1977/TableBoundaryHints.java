package com.india.elects.ls.year1977;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Range;
import com.india.elects.pdf.model.TableBoundaryIndentificationHelper;

public class TableBoundaryHints {

	public static List<TableBoundaryIndentificationHelper> getHints() {
		List<TableBoundaryIndentificationHelper> tables = new ArrayList<>();

		TableBoundaryIndentificationHelper stateConstTableHelper = new TableBoundaryIndentificationHelper("ST",
				"SUMMARY", "MALE", false, false);
		stateConstTableHelper.setStartFlatDeduc(1);

		// Define column ranges
		List<Range<Integer>> stateConstRange = new ArrayList<>();
		stateConstRange.add(Range.closed(0, 310));
//		stateConstRange.add(Range.closed(159, 300));
		stateConstRange.add(Range.closed(311, 600));
		stateConstTableHelper.setColumnRanges(stateConstRange);

		TableBoundaryIndentificationHelper candidatesTableHelper = new TableBoundaryIndentificationHelper("Canditates",
				"MALE", "II.", true, false);
		List<Range<Integer>> rangeTable1 = new ArrayList<>();
		rangeTable1.add(Range.closed(1, 245));
		rangeTable1.add(Range.closed(246, 340));
		rangeTable1.add(Range.closed(341, 440));
		rangeTable1.add(Range.closed(441, 600));
		candidatesTableHelper.setColumnRanges(rangeTable1);

		TableBoundaryIndentificationHelper electorsTableHelper = new TableBoundaryIndentificationHelper("Electors",
				"II. ELECTORS", "III. ELECTORS WHO VOTED", true, false);
		List<Range<Integer>> rangeElectors = new ArrayList<>();
		rangeElectors.add(Range.closed(1, 245));
		rangeElectors.add(Range.closed(246, 340));
		rangeElectors.add(Range.closed(341, 440));
		rangeElectors.add(Range.closed(441, 600));
		electorsTableHelper.setColumnRanges(rangeElectors);

		TableBoundaryIndentificationHelper votersTableHelper = new TableBoundaryIndentificationHelper("Voters",
				"III. ELECTORS WHO VOTED", "IV. VOTES", false, false);
		List<Range<Integer>> rangeVoters = new ArrayList<>();
		rangeVoters.add(Range.closed(1, 245));
		rangeVoters.add(Range.closed(246, 340));
		rangeVoters.add(Range.closed(341, 440));
		rangeVoters.add(Range.closed(441, 600));
		votersTableHelper.setColumnRanges(rangeVoters);

		TableBoundaryIndentificationHelper votesTableHelper = new TableBoundaryIndentificationHelper("Votes",
				"IV. VOTES", "V. POLLING STATIONS", true, false);
		List<Range<Integer>> rangeVotes = new ArrayList<>();
		rangeVotes.add(Range.closed(1, 245));
		rangeVotes.add(Range.closed(246, 300));
		rangeVotes.add(Range.closed(301, 600));
		votesTableHelper.setColumnRanges(rangeVotes);

		TableBoundaryIndentificationHelper pollingStationTableHelper = new TableBoundaryIndentificationHelper(
				"PollStation", "V. POLLING STATIONS", "VI. DATES", false, false);
		List<Range<Integer>> rangePollingStation = new ArrayList<>();
		rangePollingStation.add(Range.closed(1, 165));
		rangePollingStation.add(Range.closed(166, 230));
		rangePollingStation.add(Range.closed(231, 487));
		rangePollingStation.add(Range.closed(488, 600));
		pollingStationTableHelper.setColumnRanges(rangePollingStation);

		TableBoundaryIndentificationHelper pollDatesTableHelper = new TableBoundaryIndentificationHelper("dates",
				"VI. DATES", "VII. RESULT", false, false);
		pollDatesTableHelper.setStartFlatDeduc(1);
		List<Range<Integer>> rangePollDates = new ArrayList<>();
		rangePollDates.add(Range.closed(1, 200));
		rangePollDates.add(Range.closed(201, 330));
		rangePollDates.add(Range.closed(330, 600));
		pollDatesTableHelper.setColumnRanges(rangePollDates);

		TableBoundaryIndentificationHelper resultTableHelper = new TableBoundaryIndentificationHelper("results",
				"VII. RESULT", "MARGIN", false, false);
		resultTableHelper.setStartFlatDeduc(1);
		List<Range<Integer>> rangeResult = new ArrayList<>();
		rangeResult.add(Range.closed(1, 135));
		rangeResult.add(Range.closed(136, 245));
		rangeResult.add(Range.closed(246, 445));
		rangeResult.add(Range.closed(445, 600));
		resultTableHelper.setColumnRanges(rangeResult);
		

		TableBoundaryIndentificationHelper marginTableHelper = new TableBoundaryIndentificationHelper("margin",
				"MARGIN", "Total Valid Votes", true, false);
		List<Range<Integer>> rangeMargin = new ArrayList<>();
		rangeMargin.add(Range.closed(1, 140));
		rangeMargin.add(Range.closed(141, 190));
		rangeMargin.add(Range.closed(191, 680));
		marginTableHelper.setColumnRanges(rangeMargin);

		
		tables.add(stateConstTableHelper);
		tables.add(candidatesTableHelper);
		tables.add(electorsTableHelper);
		tables.add(votersTableHelper);
		tables.add(votesTableHelper);
		tables.add(pollingStationTableHelper);
		tables.add(pollDatesTableHelper);
		tables.add(resultTableHelper);
		tables.add(marginTableHelper);
		
		
		
		return tables;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
