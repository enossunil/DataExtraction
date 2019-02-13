package com.india.elects.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

import com.google.common.collect.Range;
import com.india.elects.pdf.model.Table;
import com.india.elects.pdf.model.TableBoundaryIndentificationHelper;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {

	}

	public static void printTextArray(String[] args) throws InvalidPasswordException, IOException {
		
		PDFTableExtractor extractor = new PDFTableExtractor();
		try {
		extractor.open(args[0]);
		extractor.printTextPositions(0);
		}finally {
			extractor.close();
		}
	}

	public static void testHelper(String[] args) throws IOException {
		List<TableBoundaryIndentificationHelper> textBounds = new ArrayList<>();

		TableBoundaryIndentificationHelper stateConstTableHelper = new TableBoundaryIndentificationHelper(
				"State-Constituency", "State/UT", "MEN", true, false);
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
				"Polling Percantage", "POLLING PERCENTAGE", "IV. VOTES", true, false);
		List<Range<Integer>> pollingPercentageRange = new ArrayList<>();
		pollingPercentageRange.add(Range.closed(1, 190));
		pollingPercentageRange.add(Range.closed(200, 600));
		pollingPercentageHelper.setColumnRanges(pollingPercentageRange);
		pollingPercentageHelper.setStartFlatDeduc(16);

		TableBoundaryIndentificationHelper pollingStationsHelper = new TableBoundaryIndentificationHelper(
				"Polling Stations", "POLLING STATIONS", "DATE(s)", false, false);
		List<Range<Integer>> pollingStationRange = new ArrayList<>();
		pollingStationRange.add(Range.closed(1, 89));
		pollingStationRange.add(Range.closed(90, 200));
		pollingStationRange.add(Range.closed(201, 500));
		pollingStationRange.add(Range.closed(501, 600));
		pollingStationsHelper.setColumnRanges(pollingStationRange);

		TableBoundaryIndentificationHelper repollDateHelper = new TableBoundaryIndentificationHelper("Re-Poll Dates",
				"DATE(s) OF RE-POLL", "NUMBER OF POLLING", true, false);
		List<Range<Integer>> repollRange = new ArrayList<>();
		repollRange.add(Range.closed(1, 400));
		repollRange.add(Range.closed(400, 600));
		repollDateHelper.setColumnRanges(repollRange);

		TableBoundaryIndentificationHelper pollingDatesHelper = new TableBoundaryIndentificationHelper(
				"Polling/Counting Dates", "VI. DATES", "VII. RESULT", false, false);
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

		textBounds.add(stateConstTableHelper);
		textBounds.add(candidatesTableHelper);
		textBounds.add(electorsTableHelper);
		textBounds.add(votersTableHelper);
		textBounds.add(votesTableHelper);
		textBounds.add(pollingPercentageHelper);
		textBounds.add(pollingStationsHelper);
		textBounds.add(repollDateHelper);

		textBounds.add(pollingDatesHelper);
		textBounds.add(resultHelper);
		textBounds.add(marginHelper);

		String outDir = args[1];
		PDFTableExtractor extractor = new PDFTableExtractor();

//		for (int pageId = 0; pageId < 544; pageId++) {
		int pageId = 0;
		extractor.open(args[0]);
		List<Table> tables = extractor.extract(textBounds, pageId);

		StringBuilder bldr = new StringBuilder();
		bldr.append(outDir);
		bldr.append(File.separator);
		bldr.append("page_");
		bldr.append(pageId);
		bldr.append(".html");
		Writer writer = new OutputStreamWriter(new FileOutputStream(bldr.toString()));

		for (Table table : tables) {
			writer.write("Page: " + (table.getPageIdx() + 1) + " / Table " + (table.getHelper().getTableId()) + "\n");
			writer.write(table.toHtml());
		}
		writer.flush();
		writer.close();
		extractor.close();

	}

}
