package com.india.elects.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Range;

import com.india.elects.ls.year2009.TableBoundaryHints;

import com.india.elects.pdf.core.PDFTableExtractor;
import com.india.elects.pdf.model.Table;
import com.india.elects.pdf.model.TableBoundaryIndentificationHelper;

public class PrintHmlTables {

	public static void main(String[] args) throws IOException {
		if (args == null || args.length < 2) {
			System.out.println(
					"Input arguments should be 3 args like $inFilePdf[FullPath]  $pageNr $outHtmlFile{fullpath] ");
			return;
		}

		testHelper(args);
	}

	public static void testHelper(String[] args) throws IOException {

		int pageId = Integer.parseInt(args[1]);

		PDFTableExtractor extractor = new PDFTableExtractor();

//		for (int pageId = 0; pageId < 544; pageId++) {
		extractor.open(args[0]);

		List<Table> tables = extractor.extract(getHints(), pageId);

		Writer writer = new OutputStreamWriter(new FileOutputStream(args[2]));

		for (Table table : tables) {
			writer.write("<b> Page Nr  </b>: " + (table.getPageIdx() + 1));
			writer.write("<b> Table Id </b>: " + (table.getHelper().getTableId()) + "\n");
			writer.write("<BR>");
			writer.write(table.toHtml());
			writer.write("<BR>");
		}
		writer.flush();
		writer.close();
		extractor.close();

	}

	public static List<TableBoundaryIndentificationHelper> getHints() {


		
		return TableBoundaryHints.getHints();
	}

	}

	/*
	 * //Sample Impl public static List<TableBoundaryIndentificationHelper>
	 * getHints() { List<TableBoundaryIndentificationHelper> textBounds = new
	 * ArrayList<>();
	 * 
	 * TableBoundaryIndentificationHelper stateConstTableHelper = new
	 * TableBoundaryIndentificationHelper( "State-Constituency", "State/UT", "MEN",
	 * true, false); List<Range<Integer>> stateConstRange = new ArrayList<>();
	 * stateConstRange.add(Range.closed(1, 400));
	 * stateConstRange.add(Range.closed(400, 600));
	 * stateConstTableHelper.setColumnRanges(stateConstRange);
	 * 
	 * TableBoundaryIndentificationHelper candidatesTableHelper = new
	 * TableBoundaryIndentificationHelper("Canditates", "MEN", "II.", true, false);
	 * List<Range<Integer>> rangeTable1 = new ArrayList<>();
	 * rangeTable1.add(Range.closed(1, 200)); rangeTable1.add(Range.closed(201,
	 * 370)); rangeTable1.add(Range.closed(371, 470));
	 * rangeTable1.add(Range.closed(471, 600));
	 * candidatesTableHelper.setColumnRanges(rangeTable1);
	 * 
	 * TableBoundaryIndentificationHelper electorsTableHelper = new
	 * TableBoundaryIndentificationHelper("Electors", "ELECTORS", "III.", true,
	 * false); List<Range<Integer>> rangeTable2 = new ArrayList<>();
	 * rangeTable2.add(Range.closed(1, 200)); rangeTable2.add(Range.closed(201,
	 * 370)); rangeTable2.add(Range.closed(371, 470));
	 * rangeTable2.add(Range.closed(471, 600));
	 * electorsTableHelper.setColumnRanges(rangeTable2);
	 * 
	 * TableBoundaryIndentificationHelper votersTableHelper = new
	 * TableBoundaryIndentificationHelper("Voters", "VOTERS", "III(A)", true, false,
	 * 7); List<Range<Integer>> rangeTable3 = new ArrayList<>();
	 * rangeTable3.add(Range.closed(1, 200)); rangeTable3.add(Range.closed(201,
	 * 370)); rangeTable3.add(Range.closed(371, 470));
	 * rangeTable3.add(Range.closed(471, 600));
	 * votersTableHelper.setColumnRanges(rangeTable3);
	 * 
	 * TableBoundaryIndentificationHelper votesTableHelper = new
	 * TableBoundaryIndentificationHelper("Votes", "VOTES", "V. POLLING", true,
	 * false); List<Range<Integer>> rangeTable4 = new ArrayList<>();
	 * rangeTable4.add(Range.closed(1, 400)); rangeTable4.add(Range.closed(400,
	 * 600)); votesTableHelper.setColumnRanges(rangeTable4);
	 * 
	 * TableBoundaryIndentificationHelper pollingPercentageHelper = new
	 * TableBoundaryIndentificationHelper( "Polling Percantage",
	 * "POLLING PERCENTAGE", "IV. VOTES", true, false); List<Range<Integer>>
	 * pollingPercentageRange = new ArrayList<>();
	 * pollingPercentageRange.add(Range.closed(1, 190));
	 * pollingPercentageRange.add(Range.closed(200, 600));
	 * pollingPercentageHelper.setColumnRanges(pollingPercentageRange);
	 * pollingPercentageHelper.setStartFlatDeduc(16);
	 * 
	 * TableBoundaryIndentificationHelper pollingStationsHelper = new
	 * TableBoundaryIndentificationHelper( "Polling Stations", "POLLING STATIONS",
	 * "DATE(s)", false, false); List<Range<Integer>> pollingStationRange = new
	 * ArrayList<>(); pollingStationRange.add(Range.closed(1, 89));
	 * pollingStationRange.add(Range.closed(90, 200));
	 * pollingStationRange.add(Range.closed(201, 500));
	 * pollingStationRange.add(Range.closed(501, 600));
	 * pollingStationsHelper.setColumnRanges(pollingStationRange);
	 * 
	 * TableBoundaryIndentificationHelper repollDateHelper = new
	 * TableBoundaryIndentificationHelper("Re-Poll Dates", "DATE(s) OF RE-POLL",
	 * "NUMBER OF POLLING", true, false); List<Range<Integer>> repollRange = new
	 * ArrayList<>(); repollRange.add(Range.closed(1, 400));
	 * repollRange.add(Range.closed(400, 600));
	 * repollDateHelper.setColumnRanges(repollRange);
	 * 
	 * TableBoundaryIndentificationHelper pollingDatesHelper = new
	 * TableBoundaryIndentificationHelper( "Polling/Counting Dates", "VI. DATES",
	 * "VII. RESULT", false, false); List<Range<Integer>> pollingDatesRange = new
	 * ArrayList<>();
	 * 
	 * pollingDatesRange.add(Range.closed(1, 200));
	 * pollingDatesRange.add(Range.closed(201, 400));
	 * pollingDatesRange.add(Range.closed(401, 600));
	 * 
	 * pollingDatesHelper.setColumnRanges(pollingDatesRange);
	 * 
	 * TableBoundaryIndentificationHelper resultHelper = new
	 * TableBoundaryIndentificationHelper("Results", "VII. RESULT", "MARGIN", false,
	 * false);
	 * 
	 * List<Range<Integer>> resultRange = new ArrayList<>();
	 * 
	 * resultRange.add(Range.closed(1, 90)); resultRange.add(Range.closed(91, 250));
	 * resultRange.add(Range.closed(251, 425)); resultRange.add(Range.closed(426,
	 * 600)); resultHelper.setColumnRanges(resultRange);
	 * 
	 * TableBoundaryIndentificationHelper marginHelper = new
	 * TableBoundaryIndentificationHelper("Margin", "MARGIN", null, true, false);
	 * List<Range<Integer>> marginRange = new ArrayList<>();
	 * marginRange.add(Range.closed(1, 75)); marginRange.add(Range.closed(76, 600));
	 * marginHelper.setColumnRanges(marginRange);
	 * 
	 * textBounds.add(stateConstTableHelper); textBounds.add(candidatesTableHelper);
	 * textBounds.add(electorsTableHelper); textBounds.add(votersTableHelper);
	 * textBounds.add(votesTableHelper); textBounds.add(pollingPercentageHelper);
	 * textBounds.add(pollingStationsHelper); textBounds.add(repollDateHelper);
	 * 
	 * textBounds.add(pollingDatesHelper); textBounds.add(resultHelper);
	 * textBounds.add(marginHelper);
	 * 
	 * return textBounds; }
	 */
}
