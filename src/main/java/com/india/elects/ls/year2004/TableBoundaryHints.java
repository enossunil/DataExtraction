package com.india.elects.ls.year2004;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Range;
import com.india.elects.pdf.model.TableBoundaryIndentificationHelper;


	public class TableBoundaryHints {
		
public static List<TableBoundaryIndentificationHelper> getHints() {
			
			// HINTS FOR 1991 Election

			List<TableBoundaryIndentificationHelper> hints = new ArrayList<>();

			TableBoundaryIndentificationHelper stateConstTableHelper = new TableBoundaryIndentificationHelper(
					"SC", "SUMMARY", "MALE", false, false);
			List<Range<Integer>> stateConstRange = new ArrayList<>();
			stateConstRange.add(Range.closed(1, 160));
			stateConstRange.add(Range.closed(161, 400));
			stateConstRange.add(Range.closed(401, 440));
			stateConstRange.add(Range.closed(441, 600));
			stateConstTableHelper.setStartFlatDeduc(1);
			stateConstTableHelper.setColumnRanges(stateConstRange);

			TableBoundaryIndentificationHelper candidatesTableHelper = new TableBoundaryIndentificationHelper("Canditates",
					"MALE", "II.", true, false);
			List<Range<Integer>> rangeTable1 = new ArrayList<>();
			rangeTable1.add(Range.closed(1, 260));
			rangeTable1.add(Range.closed(261, 340));
			rangeTable1.add(Range.closed(341, 460));
			rangeTable1.add(Range.closed(461, 600));
			candidatesTableHelper.setColumnRanges(rangeTable1);

			TableBoundaryIndentificationHelper electorsTableHelper = new TableBoundaryIndentificationHelper("Electors",
					"ELECTORS", "III.", true, false);
			List<Range<Integer>> rangeTable2 = new ArrayList<>();
			rangeTable2.add(Range.closed(1, 240));
			rangeTable2.add(Range.closed(241, 340));
			rangeTable2.add(Range.closed(341, 450));
			rangeTable2.add(Range.closed(451, 600));
			electorsTableHelper.setColumnRanges(rangeTable2);
			
			TableBoundaryIndentificationHelper votersTableHelper = new TableBoundaryIndentificationHelper("Voters",
					"III.", "IV.", false, false);
			List<Range<Integer>> rangeTable3 = new ArrayList<>();
			rangeTable3.add(Range.closed(1, 225));
			rangeTable3.add(Range.closed(226, 325));
			rangeTable3.add(Range.closed(326, 425));
			rangeTable3.add(Range.closed(426, 600));
			votersTableHelper.setColumnRanges(rangeTable3);

			TableBoundaryIndentificationHelper votesTableHelper = new TableBoundaryIndentificationHelper("Votes", "VOTES",
					"V. POLLING", true, false);
			List<Range<Integer>> rangeTable4 = new ArrayList<>();
			rangeTable4.add(Range.closed(1, 400));
			rangeTable4.add(Range.closed(400, 600));
			votesTableHelper.setColumnRanges(rangeTable4);
			
			TableBoundaryIndentificationHelper pollingPercentageHelper = new TableBoundaryIndentificationHelper(
					"Votes-Polling-Percentage", "IV. VOTES", "V. POLLING STATIONS", true, false);
			List<Range<Integer>> pollingPercentageRange = new ArrayList<>();
			
			pollingPercentageRange.add(Range.closed(1, 260));
			pollingPercentageRange.add(Range.closed(261, 310));
			pollingPercentageRange.add(Range.closed(311, 441));
			pollingPercentageRange.add(Range.closed(442, 600));
			pollingPercentageHelper.setColumnRanges(pollingPercentageRange);
			pollingPercentageHelper.setStartFlatDeduc(23);
			
			
			// Reduced to 2 columns for 1998 due to issues
			TableBoundaryIndentificationHelper pollingStationsHelper = new TableBoundaryIndentificationHelper(
					"Polling-Stations", "POLLING STATIONS", "VI. DATES", false, false);
			List<Range<Integer>> pollingStationRange = new ArrayList<>();
			pollingStationRange.add(Range.closed(1, 240));
			pollingStationRange.add(Range.closed(241, 600));
			pollingStationsHelper.setColumnRanges(pollingStationRange);
			pollingStationsHelper.setStartFlatDeduc(1);
			
			
			TableBoundaryIndentificationHelper repollDateHelper = new TableBoundaryIndentificationHelper("Re-Poll-Dates",
					"DATE(s) OF RE-POLL", "NUMBER OF POLLING", true, false);
			List<Range<Integer>> repollRange = new ArrayList<>();
			repollRange.add(Range.closed(1, 450));
			repollRange.add(Range.closed(451, 600));
			repollDateHelper.setColumnRanges(repollRange);

			TableBoundaryIndentificationHelper pollingDatesHelper = new TableBoundaryIndentificationHelper(
					"Polling-Counting-Dates", "VI. DATES", "VII. RESULT", false, false);
			List<Range<Integer>> pollingDatesRange = new ArrayList<>();

			pollingDatesRange.add(Range.closed(1, 210));
			pollingDatesRange.add(Range.closed(211, 340));
			pollingDatesRange.add(Range.closed(341, 600));
			pollingDatesHelper.setStartFlatDeduc(1);

			pollingDatesHelper.setColumnRanges(pollingDatesRange);

			TableBoundaryIndentificationHelper resultHelper = new TableBoundaryIndentificationHelper("Results",
					"VII. RESULT", "MARGIN", false, false);

			List<Range<Integer>> resultRange = new ArrayList<>();

			resultRange.add(Range.closed(1, 145));
			resultRange.add(Range.closed(146, 230));
			resultRange.add(Range.closed(231, 425));
			resultRange.add(Range.closed(426, 600));
			resultHelper.setStartFlatDeduc(1);
			resultHelper.setColumnRanges(resultRange);

			TableBoundaryIndentificationHelper marginHelper = new TableBoundaryIndentificationHelper("Margin", "MARGIN :",
					")", true, false);
			List<Range<Integer>> marginRange = new ArrayList<>();
			marginRange.add(Range.closed(1, 145));
			marginRange.add(Range.closed(146, 180));
			marginRange.add(Range.closed(181, 320));
			marginRange.add(Range.closed(321, 600));
			
			marginHelper.setColumnRanges(marginRange);

			
			// Calling Table builder functions
			hints.add(stateConstTableHelper);
			hints.add(candidatesTableHelper);
			hints.add(electorsTableHelper);
			hints.add(votersTableHelper);
			hints.add(pollingPercentageHelper);		
			hints.add(pollingStationsHelper);
 			hints.add(pollingDatesHelper);
 			hints.add(resultHelper);
//			hints.add(marginHelper);


			
			//hints.add(votesTableHelper);
			
//			hints.add(repollDateHelper);
			return hints;
		}

		
//public static List<TableBoundaryIndentificationHelper> getHints() {
//			
//			// HINTS FOR 1992 Election
//
//			List<TableBoundaryIndentificationHelper> hints = new ArrayList<>();
//
//			TableBoundaryIndentificationHelper stateConstTableHelper = new TableBoundaryIndentificationHelper(
//					"SC", "SUMMARY", "MALE", false, false);
//			List<Range<Integer>> stateConstRange = new ArrayList<>();
//			stateConstRange.add(Range.closed(1, 160));
//			stateConstRange.add(Range.closed(161, 400));
//			stateConstRange.add(Range.closed(401, 440));
//			stateConstRange.add(Range.closed(441, 600));
//			stateConstTableHelper.setStartFlatDeduc(1);
//			stateConstTableHelper.setColumnRanges(stateConstRange);
//
//			TableBoundaryIndentificationHelper candidatesTableHelper = new TableBoundaryIndentificationHelper("Canditates",
//					"MALE", "II.", true, false);
//			List<Range<Integer>> rangeTable1 = new ArrayList<>();
//			rangeTable1.add(Range.closed(1, 260));
//			rangeTable1.add(Range.closed(261, 340));
//			rangeTable1.add(Range.closed(341, 460));
//			rangeTable1.add(Range.closed(461, 600));
//			candidatesTableHelper.setColumnRanges(rangeTable1);
//
//			TableBoundaryIndentificationHelper electorsTableHelper = new TableBoundaryIndentificationHelper("Electors",
//					"ELECTORS", "III.", true, false);
//			List<Range<Integer>> rangeTable2 = new ArrayList<>();
//			rangeTable2.add(Range.closed(1, 240));
//			rangeTable2.add(Range.closed(241, 340));
//			rangeTable2.add(Range.closed(341, 450));
//			rangeTable2.add(Range.closed(451, 600));
//			electorsTableHelper.setColumnRanges(rangeTable2);
//			
//			TableBoundaryIndentificationHelper votersTableHelper = new TableBoundaryIndentificationHelper("Voters",
//					"III.", "IV.", false, false);
//			List<Range<Integer>> rangeTable3 = new ArrayList<>();
//			rangeTable3.add(Range.closed(1, 225));
//			rangeTable3.add(Range.closed(226, 325));
//			rangeTable3.add(Range.closed(326, 425));
//			rangeTable3.add(Range.closed(426, 600));
//			votersTableHelper.setColumnRanges(rangeTable3);
//
//			TableBoundaryIndentificationHelper votesTableHelper = new TableBoundaryIndentificationHelper("Votes", "VOTES",
//					"V. POLLING", true, false);
//			List<Range<Integer>> rangeTable4 = new ArrayList<>();
//			rangeTable4.add(Range.closed(1, 400));
//			rangeTable4.add(Range.closed(400, 600));
//			votesTableHelper.setColumnRanges(rangeTable4);
//			
//			TableBoundaryIndentificationHelper pollingPercentageHelper = new TableBoundaryIndentificationHelper(
//					"Votes-Polling-Percentage", "IV. VOTES", "V. POLLING STATIONS", true, false);
//			List<Range<Integer>> pollingPercentageRange = new ArrayList<>();
//			
//			pollingPercentageRange.add(Range.closed(1, 260));
//			pollingPercentageRange.add(Range.closed(261, 310));
//			pollingPercentageRange.add(Range.closed(311, 441));
//			pollingPercentageRange.add(Range.closed(442, 600));
//			pollingPercentageHelper.setColumnRanges(pollingPercentageRange);
//			pollingPercentageHelper.setStartFlatDeduc(23);
//			
//			
//			// Reduced to 2 columns for 1998 due to issues
//			TableBoundaryIndentificationHelper pollingStationsHelper = new TableBoundaryIndentificationHelper(
//					"Polling-Stations", "POLLING STATIONS", "VI. DATES", false, false);
//			List<Range<Integer>> pollingStationRange = new ArrayList<>();
//			pollingStationRange.add(Range.closed(1, 240));
//			pollingStationRange.add(Range.closed(241, 600));
//			pollingStationsHelper.setColumnRanges(pollingStationRange);
//			pollingStationsHelper.setStartFlatDeduc(1);
//			
//			
//			TableBoundaryIndentificationHelper repollDateHelper = new TableBoundaryIndentificationHelper("Re-Poll-Dates",
//					"DATE(s) OF RE-POLL", "NUMBER OF POLLING", true, false);
//			List<Range<Integer>> repollRange = new ArrayList<>();
//			repollRange.add(Range.closed(1, 450));
//			repollRange.add(Range.closed(451, 600));
//			repollDateHelper.setColumnRanges(repollRange);
//
//			TableBoundaryIndentificationHelper pollingDatesHelper = new TableBoundaryIndentificationHelper(
//					"Polling-Counting-Dates", "VI. DATES", "VII. RESULT", false, false);
//			List<Range<Integer>> pollingDatesRange = new ArrayList<>();
//
//			pollingDatesRange.add(Range.closed(1, 210));
//			pollingDatesRange.add(Range.closed(211, 340));
//			pollingDatesRange.add(Range.closed(341, 600));
//			pollingDatesHelper.setStartFlatDeduc(1);
//
//			pollingDatesHelper.setColumnRanges(pollingDatesRange);
//
//			TableBoundaryIndentificationHelper resultHelper = new TableBoundaryIndentificationHelper("Results",
//					"VII. RESULT", "MARGIN", false, false);
//
//			List<Range<Integer>> resultRange = new ArrayList<>();
//
//			resultRange.add(Range.closed(1, 145));
//			resultRange.add(Range.closed(146, 230));
//			resultRange.add(Range.closed(231, 425));
//			resultRange.add(Range.closed(426, 600));
//			resultHelper.setStartFlatDeduc(1);
//			resultHelper.setColumnRanges(resultRange);
//
//			TableBoundaryIndentificationHelper marginHelper = new TableBoundaryIndentificationHelper("Margin", "MARGIN :",
//					")", true, false);
//			List<Range<Integer>> marginRange = new ArrayList<>();
//			marginRange.add(Range.closed(1, 145));
//			marginRange.add(Range.closed(146, 180));
//			marginRange.add(Range.closed(181, 320));
//			marginRange.add(Range.closed(321, 600));
//			
//			marginHelper.setColumnRanges(marginRange);
//
//			
//			// Calling Table builder functions
//			hints.add(stateConstTableHelper);
//			hints.add(candidatesTableHelper);
//			hints.add(electorsTableHelper);
//			hints.add(votersTableHelper);
//			hints.add(pollingPercentageHelper);		
//			hints.add(pollingStationsHelper);
// 			hints.add(pollingDatesHelper);
// 			hints.add(resultHelper);
////			hints.add(marginHelper);
//
//
//			
//			//hints.add(votesTableHelper);
//			
////			hints.add(repollDateHelper);
//			return hints;
//		}

		
//public static List<TableBoundaryIndentificationHelper> getHints() {
//			
//			// HINTS FOR 1996 Election
//
//			List<TableBoundaryIndentificationHelper> hints = new ArrayList<>();
//
//			TableBoundaryIndentificationHelper stateConstTableHelper = new TableBoundaryIndentificationHelper(
//					"SC", "SUMMARY", "MALE", false, false);
//			List<Range<Integer>> stateConstRange = new ArrayList<>();
//			stateConstRange.add(Range.closed(1, 160));
//			stateConstRange.add(Range.closed(161, 400));
//			stateConstRange.add(Range.closed(401, 440));
//			stateConstRange.add(Range.closed(441, 600));
//			stateConstTableHelper.setStartFlatDeduc(1);
//			stateConstTableHelper.setColumnRanges(stateConstRange);
//
//			TableBoundaryIndentificationHelper candidatesTableHelper = new TableBoundaryIndentificationHelper("Canditates",
//					"MALE", "II.", true, false);
//			List<Range<Integer>> rangeTable1 = new ArrayList<>();
//			rangeTable1.add(Range.closed(1, 260));
//			rangeTable1.add(Range.closed(261, 340));
//			rangeTable1.add(Range.closed(341, 460));
//			rangeTable1.add(Range.closed(461, 600));
//			candidatesTableHelper.setColumnRanges(rangeTable1);
//
//			TableBoundaryIndentificationHelper electorsTableHelper = new TableBoundaryIndentificationHelper("Electors",
//					"ELECTORS", "III.", true, false);
//			List<Range<Integer>> rangeTable2 = new ArrayList<>();
//			rangeTable2.add(Range.closed(1, 240));
//			rangeTable2.add(Range.closed(241, 340));
//			rangeTable2.add(Range.closed(341, 450));
//			rangeTable2.add(Range.closed(451, 600));
//			electorsTableHelper.setColumnRanges(rangeTable2);
//			
//			TableBoundaryIndentificationHelper votersTableHelper = new TableBoundaryIndentificationHelper("Voters",
//					"III.", "IV.", false, false);
//			List<Range<Integer>> rangeTable3 = new ArrayList<>();
//			rangeTable3.add(Range.closed(1, 225));
//			rangeTable3.add(Range.closed(226, 325));
//			rangeTable3.add(Range.closed(326, 425));
//			rangeTable3.add(Range.closed(426, 600));
//			votersTableHelper.setColumnRanges(rangeTable3);
//
//			TableBoundaryIndentificationHelper votesTableHelper = new TableBoundaryIndentificationHelper("Votes", "VOTES",
//					"V. POLLING", true, false);
//			List<Range<Integer>> rangeTable4 = new ArrayList<>();
//			rangeTable4.add(Range.closed(1, 400));
//			rangeTable4.add(Range.closed(400, 600));
//			votesTableHelper.setColumnRanges(rangeTable4);
//			
//			TableBoundaryIndentificationHelper pollingPercentageHelper = new TableBoundaryIndentificationHelper(
//					"Votes-Polling-Percentage", "IV. VOTES", "V. POLLING STATIONS", true, false);
//			List<Range<Integer>> pollingPercentageRange = new ArrayList<>();
//			
//			pollingPercentageRange.add(Range.closed(1, 240));
//			pollingPercentageRange.add(Range.closed(241, 310));
//			pollingPercentageRange.add(Range.closed(311, 441));
//			pollingPercentageRange.add(Range.closed(442, 600));
//			pollingPercentageHelper.setColumnRanges(pollingPercentageRange);
//			pollingPercentageHelper.setStartFlatDeduc(23);
//			
//			
//			// Reduced to 2 columns for 1998 due to issues
//			TableBoundaryIndentificationHelper pollingStationsHelper = new TableBoundaryIndentificationHelper(
//					"Polling-Stations", "POLLING STATIONS", "VI. DATES", false, false);
//			List<Range<Integer>> pollingStationRange = new ArrayList<>();
//			pollingStationRange.add(Range.closed(1, 240));
//			pollingStationRange.add(Range.closed(241, 600));
//			pollingStationsHelper.setColumnRanges(pollingStationRange);
//			pollingStationsHelper.setStartFlatDeduc(1);
//			
//			
//			TableBoundaryIndentificationHelper repollDateHelper = new TableBoundaryIndentificationHelper("Re-Poll-Dates",
//					"DATE(s) OF RE-POLL", "NUMBER OF POLLING", true, false);
//			List<Range<Integer>> repollRange = new ArrayList<>();
//			repollRange.add(Range.closed(1, 450));
//			repollRange.add(Range.closed(451, 600));
//			repollDateHelper.setColumnRanges(repollRange);
//
//			TableBoundaryIndentificationHelper pollingDatesHelper = new TableBoundaryIndentificationHelper(
//					"Polling-Counting-Dates", "VI. DATES", "VII. RESULT", false, false);
//			List<Range<Integer>> pollingDatesRange = new ArrayList<>();
//
//			pollingDatesRange.add(Range.closed(1, 210));
//			pollingDatesRange.add(Range.closed(211, 340));
//			pollingDatesRange.add(Range.closed(341, 600));
//			pollingDatesHelper.setStartFlatDeduc(1);
//
//			pollingDatesHelper.setColumnRanges(pollingDatesRange);
//
//			TableBoundaryIndentificationHelper resultHelper = new TableBoundaryIndentificationHelper("Results",
//					"VII. RESULT", "MARGIN", false, false);
//
//			List<Range<Integer>> resultRange = new ArrayList<>();
//
//			resultRange.add(Range.closed(1, 145));
//			resultRange.add(Range.closed(146, 230));
//			resultRange.add(Range.closed(231, 425));
//			resultRange.add(Range.closed(426, 600));
//			resultHelper.setStartFlatDeduc(1);
//			resultHelper.setColumnRanges(resultRange);
//
//			TableBoundaryIndentificationHelper marginHelper = new TableBoundaryIndentificationHelper("Margin", "MARGIN :",
//					")", true, false);
//			List<Range<Integer>> marginRange = new ArrayList<>();
//			marginRange.add(Range.closed(1, 145));
//			marginRange.add(Range.closed(146, 180));
//			marginRange.add(Range.closed(181, 320));
//			marginRange.add(Range.closed(321, 600));
//			
//			marginHelper.setColumnRanges(marginRange);
//
//			
//			// Calling Table builder functions
//			hints.add(stateConstTableHelper);
//			hints.add(candidatesTableHelper);
//			hints.add(electorsTableHelper);
//			hints.add(votersTableHelper);
//			hints.add(pollingPercentageHelper);		
//			hints.add(pollingStationsHelper);
// 			hints.add(pollingDatesHelper);
// 			hints.add(resultHelper);
////			hints.add(marginHelper);
//
//
//			
//			//hints.add(votesTableHelper);
//			
////			hints.add(repollDateHelper);
//			return hints;
//		}	
		
		
		
		// HINTS FOR 1998
		
//		public static List<TableBoundaryIndentificationHelper> getHints() {
//			
//			
//
//			List<TableBoundaryIndentificationHelper> hints = new ArrayList<>();
//
//			TableBoundaryIndentificationHelper stateConstTableHelper = new TableBoundaryIndentificationHelper(
//					"SC", "SUMMARY", "MALE", false, false);
//			List<Range<Integer>> stateConstRange = new ArrayList<>();
//			stateConstRange.add(Range.closed(1, 160));
//			stateConstRange.add(Range.closed(161, 400));
//			stateConstRange.add(Range.closed(401, 440));
//			stateConstRange.add(Range.closed(441, 600));
//			stateConstTableHelper.setStartFlatDeduc(1);
//			stateConstTableHelper.setColumnRanges(stateConstRange);
//
//			TableBoundaryIndentificationHelper candidatesTableHelper = new TableBoundaryIndentificationHelper("Canditates",
//					"MALE", "II.", true, false);
//			List<Range<Integer>> rangeTable1 = new ArrayList<>();
//			rangeTable1.add(Range.closed(1, 260));
//			rangeTable1.add(Range.closed(261, 340));
//			rangeTable1.add(Range.closed(341, 460));
//			rangeTable1.add(Range.closed(461, 600));
//			candidatesTableHelper.setColumnRanges(rangeTable1);
//
//			TableBoundaryIndentificationHelper electorsTableHelper = new TableBoundaryIndentificationHelper("Electors",
//					"ELECTORS", "III.", true, false);
//			List<Range<Integer>> rangeTable2 = new ArrayList<>();
//			rangeTable2.add(Range.closed(1, 240));
//			rangeTable2.add(Range.closed(241, 340));
//			rangeTable2.add(Range.closed(341, 450));
//			rangeTable2.add(Range.closed(451, 600));
//			electorsTableHelper.setColumnRanges(rangeTable2);
//			
//			TableBoundaryIndentificationHelper votersTableHelper = new TableBoundaryIndentificationHelper("Voters",
//					"III.", "IV.", false, false);
//			List<Range<Integer>> rangeTable3 = new ArrayList<>();
//			rangeTable3.add(Range.closed(1, 225));
//			rangeTable3.add(Range.closed(226, 325));
//			rangeTable3.add(Range.closed(326, 425));
//			rangeTable3.add(Range.closed(426, 600));
//			votersTableHelper.setColumnRanges(rangeTable3);
//
//			TableBoundaryIndentificationHelper votesTableHelper = new TableBoundaryIndentificationHelper("Votes", "VOTES",
//					"V. POLLING", true, false);
//			List<Range<Integer>> rangeTable4 = new ArrayList<>();
//			rangeTable4.add(Range.closed(1, 400));
//			rangeTable4.add(Range.closed(400, 600));
//			votesTableHelper.setColumnRanges(rangeTable4);
//			
//			TableBoundaryIndentificationHelper pollingPercentageHelper = new TableBoundaryIndentificationHelper(
//					"Votes-Polling-Percentage", "IV. VOTES", "V. POLLING STATIONS", true, false);
//			List<Range<Integer>> pollingPercentageRange = new ArrayList<>();
//			
//			pollingPercentageRange.add(Range.closed(1, 240));
//			pollingPercentageRange.add(Range.closed(241, 310));
//			pollingPercentageRange.add(Range.closed(311, 441));
//			pollingPercentageRange.add(Range.closed(442, 600));
//			pollingPercentageHelper.setColumnRanges(pollingPercentageRange);
//			pollingPercentageHelper.setStartFlatDeduc(23);
//			
//			
//			// Reduced to 2 columns for 1998 due to issues
//			TableBoundaryIndentificationHelper pollingStationsHelper = new TableBoundaryIndentificationHelper(
//					"Polling-Stations", "POLLING STATIONS", "VI. DATES", false, false);
//			List<Range<Integer>> pollingStationRange = new ArrayList<>();
//			pollingStationRange.add(Range.closed(1, 240));
//			pollingStationRange.add(Range.closed(241, 600));
//			pollingStationsHelper.setColumnRanges(pollingStationRange);
//			pollingStationsHelper.setStartFlatDeduc(1);
//			
//			
//			TableBoundaryIndentificationHelper repollDateHelper = new TableBoundaryIndentificationHelper("Re-Poll-Dates",
//					"DATE(s) OF RE-POLL", "NUMBER OF POLLING", true, false);
//			List<Range<Integer>> repollRange = new ArrayList<>();
//			repollRange.add(Range.closed(1, 450));
//			repollRange.add(Range.closed(451, 600));
//			repollDateHelper.setColumnRanges(repollRange);
//
//			TableBoundaryIndentificationHelper pollingDatesHelper = new TableBoundaryIndentificationHelper(
//					"Polling-Counting-Dates", "VI. DATES", "VII. RESULT", false, false);
//			List<Range<Integer>> pollingDatesRange = new ArrayList<>();
//
//			pollingDatesRange.add(Range.closed(1, 210));
//			pollingDatesRange.add(Range.closed(211, 340));
//			pollingDatesRange.add(Range.closed(341, 600));
//			pollingDatesHelper.setStartFlatDeduc(1);
//
//			pollingDatesHelper.setColumnRanges(pollingDatesRange);
//
//			TableBoundaryIndentificationHelper resultHelper = new TableBoundaryIndentificationHelper("Results",
//					"VII. RESULT", "MARGIN", false, false);
//
//			List<Range<Integer>> resultRange = new ArrayList<>();
//
//			resultRange.add(Range.closed(1, 145));
//			resultRange.add(Range.closed(146, 230));
//			resultRange.add(Range.closed(231, 425));
//			resultRange.add(Range.closed(426, 600));
//			resultHelper.setStartFlatDeduc(1);
//			resultHelper.setColumnRanges(resultRange);
//
//			TableBoundaryIndentificationHelper marginHelper = new TableBoundaryIndentificationHelper("Margin", "MARGIN :",
//					")", true, false);
//			List<Range<Integer>> marginRange = new ArrayList<>();
//			marginRange.add(Range.closed(1, 145));
//			marginRange.add(Range.closed(146, 180));
//			marginRange.add(Range.closed(181, 320));
//			marginRange.add(Range.closed(321, 600));
//			
//			marginHelper.setColumnRanges(marginRange);
//
//			
//			// Calling Table builder functions
//			hints.add(stateConstTableHelper);
//			hints.add(candidatesTableHelper);
//			hints.add(electorsTableHelper);
//			hints.add(votersTableHelper);
//			hints.add(pollingPercentageHelper);		
//			hints.add(pollingStationsHelper);
// 			hints.add(pollingDatesHelper);
// 			hints.add(resultHelper);
////			hints.add(marginHelper);
//
//
//			
//			//hints.add(votesTableHelper);
//			
////			hints.add(repollDateHelper);
//			return hints;
//		}	
		
//============================================================================
		

	/* HINTS FOR 1999 LOK SABHA ELECTIONS
	 * 
	 * public static List<TableBoundaryIndentificationHelper> getHints() {
	 * 
	 * List<TableBoundaryIndentificationHelper> hints = new ArrayList<>();
	 * 
	 * TableBoundaryIndentificationHelper stateConstTableHelper = new
	 * TableBoundaryIndentificationHelper( "SC", "SUMMARY", "MALE", false, false);
	 * List<Range<Integer>> stateConstRange = new ArrayList<>();
	 * stateConstRange.add(Range.closed(1, 160));
	 * stateConstRange.add(Range.closed(161, 400));
	 * stateConstRange.add(Range.closed(401, 440));
	 * stateConstRange.add(Range.closed(441, 600));
	 * stateConstTableHelper.setStartFlatDeduc(1);
	 * stateConstTableHelper.setColumnRanges(stateConstRange);
	 * 
	 * TableBoundaryIndentificationHelper candidatesTableHelper = new
	 * TableBoundaryIndentificationHelper("Canditates", "MALE", "II.", true, false);
	 * List<Range<Integer>> rangeTable1 = new ArrayList<>();
	 * rangeTable1.add(Range.closed(1, 260)); rangeTable1.add(Range.closed(261,
	 * 340)); rangeTable1.add(Range.closed(341, 460));
	 * rangeTable1.add(Range.closed(461, 600));
	 * candidatesTableHelper.setColumnRanges(rangeTable1);
	 * 
	 * TableBoundaryIndentificationHelper electorsTableHelper = new
	 * TableBoundaryIndentificationHelper("Electors", "ELECTORS", "III.", true,
	 * false); List<Range<Integer>> rangeTable2 = new ArrayList<>();
	 * rangeTable2.add(Range.closed(1, 240)); rangeTable2.add(Range.closed(241,
	 * 340)); rangeTable2.add(Range.closed(341, 450));
	 * rangeTable2.add(Range.closed(451, 600));
	 * electorsTableHelper.setColumnRanges(rangeTable2);
	 * 
	 * // 1999 - Header name in PDF file given as " ELECTORS WHO VOTED" instead of
	 * VOTERS
	 * 
	 * TableBoundaryIndentificationHelper votersTableHelper = new
	 * TableBoundaryIndentificationHelper("Voters", "III.", "IV.", false, false);
	 * List<Range<Integer>> rangeTable3 = new ArrayList<>();
	 * rangeTable3.add(Range.closed(1, 225)); rangeTable3.add(Range.closed(226,
	 * 325)); rangeTable3.add(Range.closed(326, 425));
	 * rangeTable3.add(Range.closed(426, 600));
	 * votersTableHelper.setColumnRanges(rangeTable3);
	 * 
	 * // Not Applicable for 1999
	 * 
	 * TableBoundaryIndentificationHelper votesTableHelper = new
	 * TableBoundaryIndentificationHelper("Votes", "VOTES", "V. POLLING", true,
	 * false); List<Range<Integer>> rangeTable4 = new ArrayList<>();
	 * rangeTable4.add(Range.closed(1, 400)); rangeTable4.add(Range.closed(400,
	 * 600)); votesTableHelper.setColumnRanges(rangeTable4);
	 * 
	 * // Polling Percentage and Votes merged for 1999
	 * 
	 * TableBoundaryIndentificationHelper pollingPercentageHelper = new
	 * TableBoundaryIndentificationHelper( "Votes-Polling-Percentage", "IV. VOTES",
	 * "V. POLLING STATIONS", true, false); List<Range<Integer>>
	 * pollingPercentageRange = new ArrayList<>();
	 * 
	 * pollingPercentageRange.add(Range.closed(1, 240));
	 * pollingPercentageRange.add(Range.closed(241, 310));
	 * pollingPercentageRange.add(Range.closed(311, 441));
	 * pollingPercentageRange.add(Range.closed(442, 600));
	 * pollingPercentageHelper.setColumnRanges(pollingPercentageRange);
	 * pollingPercentageHelper.setStartFlatDeduc(23);
	 * 
	 * TableBoundaryIndentificationHelper pollingStationsHelper = new
	 * TableBoundaryIndentificationHelper( "Polling-Stations", "POLLING STATIONS",
	 * "VI. DATES", false, false); List<Range<Integer>> pollingStationRange = new
	 * ArrayList<>(); pollingStationRange.add(Range.closed(1, 165));
	 * pollingStationRange.add(Range.closed(166, 230));
	 * pollingStationRange.add(Range.closed(231, 485));
	 * pollingStationRange.add(Range.closed(486, 600));
	 * pollingStationsHelper.setColumnRanges(pollingStationRange);
	 * pollingStationsHelper.setStartFlatDeduc(1);
	 * 
	 * 
	 * // No Repolling Info for 1999 TableBoundaryIndentificationHelper
	 * repollDateHelper = new TableBoundaryIndentificationHelper("Re-Poll-Dates",
	 * "DATE(s) OF RE-POLL", "NUMBER OF POLLING", true, false); List<Range<Integer>>
	 * repollRange = new ArrayList<>(); repollRange.add(Range.closed(1, 450));
	 * repollRange.add(Range.closed(451, 600));
	 * repollDateHelper.setColumnRanges(repollRange);
	 * 
	 * TableBoundaryIndentificationHelper pollingDatesHelper = new
	 * TableBoundaryIndentificationHelper( "Polling-Counting-Dates", "VI. DATES",
	 * "VII. RESULT", false, false); List<Range<Integer>> pollingDatesRange = new
	 * ArrayList<>();
	 * 
	 * pollingDatesRange.add(Range.closed(1, 210));
	 * pollingDatesRange.add(Range.closed(211, 340));
	 * pollingDatesRange.add(Range.closed(341, 600));
	 * pollingDatesHelper.setStartFlatDeduc(1);
	 * 
	 * pollingDatesHelper.setColumnRanges(pollingDatesRange);
	 * 
	 * TableBoundaryIndentificationHelper resultHelper = new
	 * TableBoundaryIndentificationHelper("Results", "VII. RESULT", "MARGIN", false,
	 * false);
	 * 
	 * List<Range<Integer>> resultRange = new ArrayList<>();
	 * 
	 * resultRange.add(Range.closed(1, 145)); resultRange.add(Range.closed(146,
	 * 230)); resultRange.add(Range.closed(231, 425));
	 * resultRange.add(Range.closed(426, 600)); resultHelper.setStartFlatDeduc(1);
	 * resultHelper.setColumnRanges(resultRange);
	 * 
	 * TableBoundaryIndentificationHelper marginHelper = new
	 * TableBoundaryIndentificationHelper("Margin", "MARGIN :", ")", true, false);
	 * List<Range<Integer>> marginRange = new ArrayList<>();
	 * marginRange.add(Range.closed(1, 145)); marginRange.add(Range.closed(146,
	 * 180)); marginRange.add(Range.closed(181, 320));
	 * marginRange.add(Range.closed(321, 600));
	 * 
	 * marginHelper.setColumnRanges(marginRange);
	 * 
	 * 
	 * // Calling Table builder functions hints.add(stateConstTableHelper);
	 * hints.add(candidatesTableHelper); hints.add(electorsTableHelper);
	 * hints.add(votersTableHelper); hints.add(pollingPercentageHelper);
	 * 
	 * 
	 * hints.add(pollingStationsHelper);
	 * 
	 * // // hints.add(pollingDatesHelper); hints.add(resultHelper); //
	 * hints.add(marginHelper);
	 * 
	 * 
	 * // Votes NA for 1999 merged with polling percentage
	 * //hints.add(votesTableHelper); // No Repolling info for 1999 //
	 * hints.add(repollDateHelper); return hints; }
	 */
	
	

	/*
	 * 2004 Table Genenarator Code
	 * public static List<TableBoundaryIndentificationHelper> getHints() {

		List<TableBoundaryIndentificationHelper> hints = new ArrayList<>();

		TableBoundaryIndentificationHelper stateConstTableHelper = new TableBoundaryIndentificationHelper(
				"SC", "SUMMARY", "MEN", false, false);
		List<Range<Integer>> stateConstRange = new ArrayList<>();
		stateConstRange.add(Range.closed(1, 160));
		stateConstRange.add(Range.closed(161, 400));
		stateConstRange.add(Range.closed(401, 440));
		stateConstRange.add(Range.closed(441, 600));
		stateConstTableHelper.setStartFlatDeduc(1);
		stateConstTableHelper.setColumnRanges(stateConstRange);

		TableBoundaryIndentificationHelper candidatesTableHelper = new TableBoundaryIndentificationHelper("Canditates",
				"MEN", "II.", true, false);
		List<Range<Integer>> rangeTable1 = new ArrayList<>();
		rangeTable1.add(Range.closed(1, 260));
		rangeTable1.add(Range.closed(261, 340));
		rangeTable1.add(Range.closed(341, 460));
		rangeTable1.add(Range.closed(461, 600));
		candidatesTableHelper.setColumnRanges(rangeTable1);

		TableBoundaryIndentificationHelper electorsTableHelper = new TableBoundaryIndentificationHelper("Electors",
				"ELECTORS", "III.", true, false);
		List<Range<Integer>> rangeTable2 = new ArrayList<>();
		rangeTable2.add(Range.closed(1, 240));
		rangeTable2.add(Range.closed(241, 340));
		rangeTable2.add(Range.closed(341, 450));
		rangeTable2.add(Range.closed(451, 600));
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
				"Polling-Percantage", "III(A).", "IV. VOTES", false, false);
		List<Range<Integer>> pollingPercentageRange = new ArrayList<>();
		
		pollingPercentageRange.add(Range.closed(1, 240));
		pollingPercentageRange.add(Range.closed(241, 320));
		pollingPercentageRange.add(Range.closed(321, 420));
		pollingPercentageRange.add(Range.closed(421, 600));
		pollingPercentageHelper.setColumnRanges(pollingPercentageRange);
		pollingPercentageHelper.setStartFlatDeduc(23);

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
		repollRange.add(Range.closed(1, 450));
		repollRange.add(Range.closed(451, 600));
		repollDateHelper.setColumnRanges(repollRange);

		TableBoundaryIndentificationHelper pollingDatesHelper = new TableBoundaryIndentificationHelper(
				"Polling-Counting-Dates", "VI. DATES", "VII. RESULT", false, false);
		List<Range<Integer>> pollingDatesRange = new ArrayList<>();

		pollingDatesRange.add(Range.closed(1, 210));
		pollingDatesRange.add(Range.closed(211, 340));
		pollingDatesRange.add(Range.closed(341, 600));
		pollingDatesHelper.setStartFlatDeduc(1);

		pollingDatesHelper.setColumnRanges(pollingDatesRange);

		TableBoundaryIndentificationHelper resultHelper = new TableBoundaryIndentificationHelper("Results",
				"VII. RESULT", "MARGIN", false, false);

		List<Range<Integer>> resultRange = new ArrayList<>();

		resultRange.add(Range.closed(1, 145));
		resultRange.add(Range.closed(146, 230));
		resultRange.add(Range.closed(231, 425));
		resultRange.add(Range.closed(426, 600));
		resultHelper.setStartFlatDeduc(1);
		resultHelper.setColumnRanges(resultRange);

		TableBoundaryIndentificationHelper marginHelper = new TableBoundaryIndentificationHelper("Margin", "MARGIN :",
				")", true, false);
		List<Range<Integer>> marginRange = new ArrayList<>();
		marginRange.add(Range.closed(1, 145));
		marginRange.add(Range.closed(146, 180));
		marginRange.add(Range.closed(181, 320));
		marginRange.add(Range.closed(321, 600));
		
		marginHelper.setColumnRanges(marginRange);

		
		// Calling Table builder functions
		hints.add(stateConstTableHelper);
		hints.add(candidatesTableHelper);
 		hints.add(electorsTableHelper);
		hints.add(votersTableHelper);
		hints.add(pollingPercentageHelper);
		hints.add(votesTableHelper);
		
		hints.add(pollingStationsHelper);
		hints.add(repollDateHelper);
//
		hints.add(pollingDatesHelper);
		hints.add(resultHelper);
//		hints.add(marginHelper);

		
		return hints;
	} */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

