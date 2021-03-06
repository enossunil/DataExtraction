package com.india.elects.ls.year2009;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Range;
import com.india.elects.pdf.model.TableBoundaryIndentificationHelper;

public class TableBoundaryHints {

	public static List<TableBoundaryIndentificationHelper> getHints() {

		List<TableBoundaryIndentificationHelper> hints = new ArrayList<>();

		TableBoundaryIndentificationHelper stateConstTableHelper = new TableBoundaryIndentificationHelper(
				"SC", "State/UT", "MEN", true, false);
		List<Range<Integer>> stateConstRange = new ArrayList<>();
		stateConstRange.add(Range.closed(1, 400));
		stateConstRange.add(Range.closed(400, 600));
		stateConstTableHelper.setColumnRanges(stateConstRange);

		TableBoundaryIndentificationHelper candidatesTableHelper = new TableBoundaryIndentificationHelper("Canditates",
				"MEN", "II.", true, false);
		List<Range<Integer>> rangeTable1 = new ArrayList<>();
		rangeTable1.add(Range.closed(1, 200));
		rangeTable1.add(Range.closed(201, 370));
		rangeTable1.add(Range.closed(371, 470));
		rangeTable1.add(Range.closed(471, 600));
		candidatesTableHelper.setColumnRanges(rangeTable1);

		TableBoundaryIndentificationHelper electorsTableHelper = new TableBoundaryIndentificationHelper("Electors",
				"ELECTORS", "III.", true, false);
		List<Range<Integer>> rangeTable2 = new ArrayList<>();
		rangeTable2.add(Range.closed(1, 200));
		rangeTable2.add(Range.closed(201, 370));
		rangeTable2.add(Range.closed(371, 470));
		rangeTable2.add(Range.closed(471, 600));
		electorsTableHelper.setColumnRanges(rangeTable2);

		TableBoundaryIndentificationHelper votersTableHelper = new TableBoundaryIndentificationHelper("Voters",
				"VOTERS", "III(A)", true, false, 7);
		List<Range<Integer>> rangeTable3 = new ArrayList<>();
		rangeTable3.add(Range.closed(1, 200));
		rangeTable3.add(Range.closed(201, 370));
		rangeTable3.add(Range.closed(371, 470));
		rangeTable3.add(Range.closed(471, 600));
		votersTableHelper.setColumnRanges(rangeTable3);

		TableBoundaryIndentificationHelper votesTableHelper = new TableBoundaryIndentificationHelper("Votes", "VOTES",
				"V. POLLING", true, false);
		List<Range<Integer>> rangeTable4 = new ArrayList<>();
		rangeTable4.add(Range.closed(1, 400));
		rangeTable4.add(Range.closed(400, 600));
		votesTableHelper.setColumnRanges(rangeTable4);

		TableBoundaryIndentificationHelper pollingPercentageHelper = new TableBoundaryIndentificationHelper(
				"Polling-Percantage", "POLLING PERCENTAGE", "IV. VOTES", true, false);
		List<Range<Integer>> pollingPercentageRange = new ArrayList<>();
		pollingPercentageRange.add(Range.closed(1, 190));
		pollingPercentageRange.add(Range.closed(200, 600));
		pollingPercentageHelper.setColumnRanges(pollingPercentageRange);
		pollingPercentageHelper.setStartFlatDeduc(16);

		TableBoundaryIndentificationHelper pollingStationsHelper = new TableBoundaryIndentificationHelper(
				"Polling-Stations", "POLLING STATIONS", "DATE(s)", false, false);
		List<Range<Integer>> pollingStationRange = new ArrayList<>();
		pollingStationRange.add(Range.closed(1, 89));
		pollingStationRange.add(Range.closed(90, 200));
		pollingStationRange.add(Range.closed(201, 500));
		pollingStationRange.add(Range.closed(501, 600));
		pollingStationsHelper.setColumnRanges(pollingStationRange);

		TableBoundaryIndentificationHelper repollDateHelper = new TableBoundaryIndentificationHelper("Re-Poll-Dates",
				"DATE(s) OF RE-POLL", "NUMBER OF POLLING", true, false);
		List<Range<Integer>> repollRange = new ArrayList<>();
		repollRange.add(Range.closed(1, 400));
		repollRange.add(Range.closed(400, 600));
		repollDateHelper.setColumnRanges(repollRange);

		TableBoundaryIndentificationHelper pollingDatesHelper = new TableBoundaryIndentificationHelper(
				"Polling-Counting-Dates", "VI. DATES", "VII. RESULT", false, false);
		List<Range<Integer>> pollingDatesRange = new ArrayList<>();

		pollingDatesRange.add(Range.closed(1, 200));
		pollingDatesRange.add(Range.closed(201, 400));
		pollingDatesRange.add(Range.closed(401, 600));

		pollingDatesHelper.setColumnRanges(pollingDatesRange);

		TableBoundaryIndentificationHelper resultHelper = new TableBoundaryIndentificationHelper("Results",
				"VII. RESULT", "MARGIN", false, false);

		List<Range<Integer>> resultRange = new ArrayList<>();

		resultRange.add(Range.closed(1, 90));
		resultRange.add(Range.closed(91, 250));
		resultRange.add(Range.closed(251, 425));
		resultRange.add(Range.closed(426, 600));
		resultHelper.setColumnRanges(resultRange);

		TableBoundaryIndentificationHelper marginHelper = new TableBoundaryIndentificationHelper("Margin", "MARGIN",
				null, true, false);
		List<Range<Integer>> marginRange = new ArrayList<>();
		marginRange.add(Range.closed(1, 75));
		marginRange.add(Range.closed(76, 600));
		marginHelper.setColumnRanges(marginRange);

		hints.add(stateConstTableHelper);
		hints.add(candidatesTableHelper);
		hints.add(electorsTableHelper);
		hints.add(votersTableHelper);
		hints.add(votesTableHelper);
		hints.add(pollingPercentageHelper);
		hints.add(pollingStationsHelper);
		hints.add(repollDateHelper);

		hints.add(pollingDatesHelper);
		hints.add(resultHelper);
		hints.add(marginHelper);

		
		return hints;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
