/**
 * Copyright (C) 2015, GIAYBAC
 *
 * Released under the MIT license
 */
package com.india.elects.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Range;
import com.india.elects.pdf.model.Table;
import com.india.elects.pdf.model.TableBoundaryIndentificationHelper;
import com.india.elects.pdf.model.TableCell;
import com.india.elects.pdf.model.TableRow;

/**
 *
 * @author THOQ LUONG Mar 22, 2015 3:34:29 PM
 */
public class PDFTableExtractor {

	// --------------------------------------------------------------------------
	// Members
	private final Logger logger = LogManager.getLogger(PDFTableExtractor.class);
	// contains pages that will be extracted table content.
	// If this variable doesn't contain any page, all pages will be extracted
	private final List<Integer> extractedPages = new ArrayList<>();
	private final List<Integer> exceptedPages = new ArrayList<>();
	// contains avoided line idx-s for each page,
	// if this multimap contains only one element and key of this element equals -1
	// then all lines in extracted pages contains in multi-map value will be avoided
	private final Multimap<Integer, Integer> pageNExceptedLinesMap = HashMultimap.create();

	private InputStream inputStream;
	private PDDocument document;
	private String password;

	// --------------------------------------------------------------------------
	// Initialization and releasation
	// --------------------------------------------------------------------------
	// Getter N Setter
	// --------------------------------------------------------------------------
	// Method binding
	public PDFTableExtractor setSource(InputStream inputStream) {
		this.inputStream = inputStream;
		return this;
	}

	public PDFTableExtractor setSource(InputStream inputStream, String password) {
		this.inputStream = inputStream;
		this.password = password;
		return this;
	}

	public PDFTableExtractor setSource(File file) {
		try {
			return this.setSource(new FileInputStream(file));
		} catch (FileNotFoundException ex) {
			throw new RuntimeException("Invalid pdf file", ex);
		}
	}

	public PDFTableExtractor setSource(String filePath) {
		return this.setSource(new File(filePath));
	}

	public PDFTableExtractor setSource(File file, String password) {
		try {
			return this.setSource(new FileInputStream(file), password);
		} catch (FileNotFoundException ex) {
			throw new RuntimeException("Invalid pdf file", ex);
		}
	}

	public PDFTableExtractor setSource(String filePath, String password) {
		return this.setSource(new File(filePath), password);
	}

	/**
	 * This page will be analyze and extract its table content
	 *
	 * @param pageIdx
	 * @return
	 */
	public PDFTableExtractor addPage(int pageIdx) {
		extractedPages.add(pageIdx);
		return this;
	}

	public PDFTableExtractor exceptPage(int pageIdx) {
		exceptedPages.add(pageIdx);
		return this;
	}

	/**
	 * Avoid a specific line in a specific page. LineIdx can be negative number, -1
	 * is the last line
	 *
	 * @param pageIdx
	 * @param lineIdxs
	 * @return
	 */
	public PDFTableExtractor exceptLine(int pageIdx, int[] lineIdxs) {
		for (int lineIdx : lineIdxs) {
			pageNExceptedLinesMap.put(pageIdx, lineIdx);
		}
		return this;
	}

	/**
	 * Avoid this line in all extracted pages. LineIdx can be negative number, -1 is
	 * the last line
	 *
	 * @param lineIdxs
	 * @return
	 */
	public PDFTableExtractor exceptLine(int[] lineIdxs) {
		this.exceptLine(-1, lineIdxs);
		return this;
	}

	public List<Table> extract() {
		List<Table> retVal = new ArrayList<>();
		Multimap<Integer, Range<Integer>> pageIdNLineRangesMap = LinkedListMultimap.create();
		Multimap<Integer, TextPosition> pageIdNTextsMap = LinkedListMultimap.create();
		try {
			this.document = this.password != null ? PDDocument.load(inputStream, this.password)
					: PDDocument.load(inputStream);
			for (int pageId = 0; pageId < document.getNumberOfPages(); pageId++) {
				boolean b = !exceptedPages.contains(pageId)
						&& (extractedPages.isEmpty() || extractedPages.contains(pageId));
				if (b) {
					List<TextPosition> texts = extractTextPositions(pageId);// sorted by .getY() ASC
					// extract line ranges
					List<Range<Integer>> lineRanges = getLineRanges(pageId, texts);
					// extract column ranges
					List<TextPosition> textsByLineRanges = getTextsByLineRanges(lineRanges, texts);

					pageIdNLineRangesMap.putAll(pageId, lineRanges);
					pageIdNTextsMap.putAll(pageId, textsByLineRanges);
				}
			}
			// Calculate columnRanges
			List<Range<Integer>> columnRanges = getColumnRanges(pageIdNTextsMap.values());
			for (int pageId : pageIdNTextsMap.keySet()) {
				Table table = buildTable(pageId, -1, (List) pageIdNTextsMap.get(pageId),
						(List) pageIdNLineRangesMap.get(pageId), columnRanges, null);
				retVal.add(table);
				// debug
				logger.debug("Found " + table.getRows().size() + " row(s) and " + columnRanges.size()
						+ " column(s) of a table in page " + pageId);
			}
		} catch (IOException ex) {
			throw new RuntimeException("Parse pdf file fail", ex);
		} finally {
			if (this.document != null) {
				try {
					this.document.close();
				} catch (IOException ex) {
					logger.error(ex);
				}
			}
		}
		// return
		return retVal;
	}

	public void open(String file) throws InvalidPasswordException, IOException {
		setSource(file);
		this.document = this.password != null ? PDDocument.load(inputStream, this.password)
				: PDDocument.load(inputStream);
	}

	public void open(File file) throws InvalidPasswordException, IOException {
		setSource(file);
		this.document = this.password != null ? PDDocument.load(inputStream, this.password)
				: PDDocument.load(inputStream);
	}
	public void close() throws IOException {
		document.close();
		inputStream.close();
	}

	public List<Table> extract(List<TableBoundaryIndentificationHelper> detectHelper, int pageId) {
		List<Table> retVal = new ArrayList<>();
		Multimap<Integer, Range<Integer>> tableIdNLineRangesMap = LinkedListMultimap.create();
		Multimap<Integer, TextPosition> tableIdNTextsMap = LinkedListMultimap.create();
		try {
			for (int tableId = 0; tableId < detectHelper.size(); tableId++) {
				List<TextPosition> texts = extractTextPositions(pageId, detectHelper.get(tableId));// sorted by .getY()
																									// ASC
				// extract line ranges
				List<Range<Integer>> lineRanges = getLineRanges(texts);
				// extract column ranges
				List<TextPosition> textsByLineRanges = getTextsByLineRanges(lineRanges, texts);

				tableIdNLineRangesMap.putAll(tableId, lineRanges);
				tableIdNTextsMap.putAll(tableId, textsByLineRanges);
			}

			// Calculate columnRanges
//			List<Range<Integer>> columnRanges = getColumnRanges(tableIdNTextsMap.values());

			for (int tableId : tableIdNTextsMap.keySet()) {
				List<Range<Integer>> columnRanges = null;
				if (detectHelper.get(tableId).getColumnRanges() == null) {
					columnRanges = getColumnRanges(tableIdNTextsMap.get(tableId));
				} else {
					columnRanges = detectHelper.get(tableId).getColumnRanges();
				}

				Table table = buildTable(pageId, tableId, (List) tableIdNTextsMap.get(tableId),
						(List) tableIdNLineRangesMap.get(tableId), columnRanges, detectHelper.get(tableId));
				retVal.add(table);
				// debug
				logger.debug("Found " + table.getRows().size() + " row(s) and " + columnRanges.size()
						+ " column(s) of a table in page " + pageId);
			}
		} catch (IOException ex) {
			throw new RuntimeException("Parse pdf file fail", ex);
		} finally {
			if (this.document != null) {
				try {
					this.document.close();
				} catch (IOException ex) {
					logger.error(ex);
				}
			}
		}
		// return
		return retVal;
	}

	// --------------------------------------------------------------------------
	// Implement N Override
	// --------------------------------------------------------------------------
	// Utils
	/**
	 * Texts in tableContent have been ordered by .getY() ASC
	 *
	 * @param pageIdx
	 * @param tableContent
	 * @param rowTrapRanges
	 * @param columnTrapRanges
	 * @return
	 */
	private Table buildTable(int pageIdx, int tableId, List<TextPosition> tableContent,
			List<Range<Integer>> rowTrapRanges, List<Range<Integer>> columnTrapRanges,
			TableBoundaryIndentificationHelper helper) {
		Table retVal = new Table(pageIdx, columnTrapRanges.size(), tableId, helper);
		int idx = 0;
		int rowIdx = 0;
		List<TextPosition> rowContent = new ArrayList<>();
		while (idx < tableContent.size()) {
			TextPosition textPosition = tableContent.get(idx);
			Range<Integer> rowTrapRange = rowTrapRanges.get(rowIdx);
			Range<Integer> textRange = Range.closed((int) textPosition.getY(),
					(int) (textPosition.getY() + textPosition.getHeight()));
			if (rowTrapRange.encloses(textRange)) {
				rowContent.add(textPosition);
				idx++;
			} else {
				TableRow row = buildRow(rowIdx, rowContent, columnTrapRanges);
				retVal.getRows().add(row);
				// next row: clear rowContent
				rowContent.clear();
				rowIdx++;
			}
		}
		// last row
		if (!rowContent.isEmpty() && rowIdx < rowTrapRanges.size()) {
			TableRow row = buildRow(rowIdx, rowContent, columnTrapRanges);
			retVal.getRows().add(row);
		}
		// return
		return retVal;
	}

	/**
	 *
	 * @param rowIdx
	 * @param rowContent
	 * @param columnTrapRanges
	 * @return
	 */
	private TableRow buildRow(int rowIdx, List<TextPosition> rowContent, List<Range<Integer>> columnTrapRanges) {
		TableRow retVal = new TableRow(rowIdx);
		// Sort rowContent
		Collections.sort(rowContent, new Comparator<TextPosition>() {
			@Override
			public int compare(TextPosition o1, TextPosition o2) {
				int retVal = 0;
				if (o1.getX() < o2.getX()) {
					retVal = -1;
				} else if (o1.getX() > o2.getX()) {
					retVal = 1;
				}
				return retVal;
			}
		});
		int idx = 0;
		int columnIdx = 0;
		List<TextPosition> cellContent = new ArrayList<>();
		while (idx < rowContent.size()) {
			TextPosition textPosition = rowContent.get(idx);
			Range<Integer> columnTrapRange = columnTrapRanges.get(columnIdx);
			Range<Integer> textRange = Range.closed((int) textPosition.getX(),
					(int) (textPosition.getX() + textPosition.getWidth()));
			if (columnTrapRange.encloses(textRange)) {
				cellContent.add(textPosition);
				idx++;
			} else {
				TableCell cell = buildCell(columnIdx, cellContent);
				retVal.getCells().add(cell);
				// next column: clear cell content
				cellContent.clear();
				columnIdx++;
			}
		}
		if (!cellContent.isEmpty() && columnIdx < columnTrapRanges.size()) {
			TableCell cell = buildCell(columnIdx, cellContent);
			retVal.getCells().add(cell);
		}
		// return
		return retVal;
	}

	private TableCell buildCell(int columnIdx, List<TextPosition> cellContent) {
		Collections.sort(cellContent, new Comparator<TextPosition>() {
			@Override
			public int compare(TextPosition o1, TextPosition o2) {
				int retVal = 0;
				if (o1.getX() < o2.getX()) {
					retVal = -1;
				} else if (o1.getX() > o2.getX()) {
					retVal = 1;
				}
				return retVal;
			}
		});
		// String cellContentString = Joiner.on("").join(cellContent.stream().map(e ->
		// e.getCharacter()).iterator());
		StringBuilder cellContentBuilder = new StringBuilder();
		for (TextPosition textPosition : cellContent) {
			cellContentBuilder.append(textPosition.getUnicode());
		}
		String cellContentString = cellContentBuilder.toString();
		return new TableCell(columnIdx, cellContentString);
	}

	private List<TextPosition> extractTextPositions(int pageId) throws IOException {
		TextPositionExtractor extractor = new TextPositionExtractor(document, pageId);
		return extractor.extract();
	}

	public List<TextPosition> printTextPositions(int pageId) throws IOException {
		TextPositionExtractor extractor = new TextPositionExtractor(document, pageId,true);
		return extractor.extract();
	}

	
	private List<TextPosition> extractTextPositions(int pageId, TableBoundaryIndentificationHelper bounds)
			throws IOException {
		TextPositionExtractor extractor = new TextPositionExtractor(document, pageId, bounds);
		return extractor.extract();
	}

	private boolean isExceptedLine(int pageIdx, int lineIdx) {
		boolean retVal = this.pageNExceptedLinesMap.containsEntry(pageIdx, lineIdx)
				|| this.pageNExceptedLinesMap.containsEntry(-1, lineIdx);
		return retVal;
	}

	/**
	 *
	 * Remove all texts in excepted lines
	 *
	 * TexPositions are sorted by .getY() ASC
	 *
	 * @param lineRanges
	 * @param textPositions
	 * @return
	 */
	private List<TextPosition> getTextsByLineRanges(List<Range<Integer>> lineRanges, List<TextPosition> textPositions) {
		List<TextPosition> retVal = new ArrayList<>();
		int idx = 0;
		int lineIdx = 0;
		while (idx < textPositions.size() && lineIdx < lineRanges.size()) {
			TextPosition textPosition = textPositions.get(idx);
			Range<Integer> textRange = Range.closed((int) textPosition.getY(),
					(int) (textPosition.getY() + textPosition.getHeight()));
			Range<Integer> lineRange = lineRanges.get(lineIdx);
			if (lineRange.encloses(textRange)) {
				retVal.add(textPosition);
				idx++;
			} else if (lineRange.upperEndpoint() < textRange.lowerEndpoint()) {
				lineIdx++;
			} else {
				idx++;
			}
		}
		// return
		return retVal;
	}

	/**
	 * @param texts
	 * @return
	 */
	private List<Range<Integer>> getColumnRanges(Collection<TextPosition> texts) {
		TrapRangeBuilder rangesBuilder = new TrapRangeBuilder();
		for (TextPosition text : texts) {
			Range<Integer> range = Range.closed((int) text.getX(), (int) (text.getX() + text.getWidth()));
			rangesBuilder.addRange(range);
		}
		return rangesBuilder.build();
	}

	private List<Range<Integer>> getLineRanges(List<TextPosition> pageContent) {
		TrapRangeBuilder lineTrapRangeBuilder = new TrapRangeBuilder();
		for (TextPosition textPosition : pageContent) {
			Range<Integer> lineRange = Range.closed((int) textPosition.getY(),
					(int) (textPosition.getY() + textPosition.getHeight()));
			// add to builder
			lineTrapRangeBuilder.addRange(lineRange);
		}
		List<Range<Integer>> lineTrapRanges = lineTrapRangeBuilder.build();
		return getLines(lineTrapRanges);
	}

	private List<Range<Integer>> getLineRanges(int pageId, List<TextPosition> pageContent) {
		TrapRangeBuilder lineTrapRangeBuilder = new TrapRangeBuilder();
		for (TextPosition textPosition : pageContent) {
			Range<Integer> lineRange = Range.closed((int) textPosition.getY(),
					(int) (textPosition.getY() + textPosition.getHeight()));
			// add to builder
			lineTrapRangeBuilder.addRange(lineRange);
		}
		List<Range<Integer>> lineTrapRanges = lineTrapRangeBuilder.build();
		List<Range<Integer>> retVal = removeExceptedLines(pageId, lineTrapRanges);
		return retVal;
	}

	private List<Range<Integer>> getLines(List<Range<Integer>> lineTrapRanges) {
		List<Range<Integer>> retVal = new ArrayList<>();
		for (int lineIdx = 0; lineIdx < lineTrapRanges.size(); lineIdx++) {
			retVal.add(lineTrapRanges.get(lineIdx));
		}
		// return
		return retVal;
	}

	private List<Range<Integer>> removeExceptedLines(int pageIdx, List<Range<Integer>> lineTrapRanges) {
		List<Range<Integer>> retVal = new ArrayList<>();
		for (int lineIdx = 0; lineIdx < lineTrapRanges.size(); lineIdx++) {
			boolean isExceptedLine = isExceptedLine(pageIdx, lineIdx)
					|| isExceptedLine(pageIdx, lineIdx - lineTrapRanges.size());
			if (!isExceptedLine) {
				retVal.add(lineTrapRanges.get(lineIdx));
			}
		}
		// return
		return retVal;
	}

	// --------------------------------------------------------------------------
	// Inner class
	private class TextPositionExtractor extends PDFTextStripper {

		private final List<TextPosition> textPositions = new ArrayList<>();
		private final int pageId;
		private TableBoundaryIndentificationHelper bounds;
		private boolean print = false;

		private TextPositionExtractor(PDDocument document, int pageId) throws IOException {
			super();
			super.setSortByPosition(true);
			super.document = document;
			this.pageId = pageId;
		}

		private TextPositionExtractor(PDDocument document, int pageId,boolean print) throws IOException {
			super();
			super.setSortByPosition(true);
			super.document = document;
			this.pageId = pageId;
			this.print = print;
		}

		
		private TextPositionExtractor(PDDocument document, int pageId, TableBoundaryIndentificationHelper bounds)
				throws IOException {
			super();
			super.setSortByPosition(true);
			super.document = document;
			this.pageId = pageId;
			this.bounds = bounds;
		}

		public void stripPage(int pageId) throws IOException {
			this.setStartPage(pageId + 1);
			this.setEndPage(pageId + 1);
			try (Writer writer = new OutputStreamWriter(new ByteArrayOutputStream())) {
				writeText(document, writer);
			}
		}

		@Override
		protected void writeString(String string, List<TextPosition> textPositions) throws IOException {
			this.textPositions.addAll(textPositions);
		}

		/**
		 * and order by textPosition.getY() ASC
		 *
		 * @return
		 * @throws IOException
		 */
		private List<TextPosition> extract() throws IOException {
			this.stripPage(pageId);

			StringBuffer buffer = new StringBuffer(textPositions.size());
			for (TextPosition txt : textPositions) {
				buffer.append(txt.getUnicode());
//				System.out.println(txt.getUnicode()+" Y["+txt.getY() +"],X["+txt.getX()+"], End Y"+txt.getEndY()+"],END X"+txt.getEndX());

			}
//			System.out.println("************END************");
//			System.out.println("************END************");

			// sort
			Collections.sort(textPositions, new Comparator<TextPosition>() {
				@Override
				public int compare(TextPosition o1, TextPosition o2) {
					int retVal = 0;
					if (Math.floor(o1.getY()) == Math.floor(o2.getY())) {

						if (o1.getX() < o2.getX()) {
							retVal = -1;
						} else if (o1.getX() > o2.getX()) {
							retVal = 1;
						}

					} else {

						if (Math.floor(o1.getY()) < Math.floor(o2.getY())) {
							retVal = -1;
						} else if (Math.floor(o1.getY()) > Math.floor(o2.getY())) {
							retVal = 1;
						}
					}
					return retVal;

				}
			});
			
			if(print) {
				print(textPositions);
			}
			
			if (bounds != null) {
				return filter(textPositions, bounds);
			}

			return this.textPositions;
		}

		private void print(List<TextPosition> list) {

				StringBuffer buffer = new StringBuffer(list.size());
				StringBuffer t1 = new StringBuffer(150);
				StringBuffer t2 = new StringBuffer(250);
				double previousX = 0;
				for (TextPosition txt : list) {
					buffer.append(txt.getUnicode());
					if (previousX > txt.getX()) {
	
						System.out.println(t1.toString());
						System.out.println(t2.toString());
						t1 = new StringBuffer(150);
						t2 = new StringBuffer(250);
					}
	
					t1.append(txt.getUnicode());
					t2.append("[" + txt.getUnicode());
					t2.append("(" + txt.getX() + ")] ");
					previousX = txt.getX();
				}
				System.out.println("*************************");
				System.out.println(buffer);
				System.out.println("*************************");

		}
		
		private List<TextPosition> filter(List<TextPosition> list, TableBoundaryIndentificationHelper bounds) {

			StringBuffer buffer = new StringBuffer(list.size());
			for (TextPosition txt : list) {
				buffer.append(txt.getUnicode());
			}
			int startIndex = buffer.indexOf(bounds.getStartText());
			int endIndex = buffer.length() - 1;

			if (bounds.getEndText() != null) {
				endIndex = buffer.indexOf(bounds.getEndText());
			}

			if (!bounds.isIncludeStartText()) {
				startIndex = startIndex + bounds.getStartText().length() + 1;
			}

			if (bounds.getEndText() != null && bounds.isIncludeEndText()) {
				endIndex = endIndex + bounds.getEndText().length() + 1;
			}
			if (bounds.getEndFlatDeduc() > 0) {
				endIndex = endIndex - bounds.getEndFlatDeduc();
			}

			if (bounds.getStartFlatDeduc() > 0) {
				startIndex = startIndex - bounds.getStartFlatDeduc();
			}

//			System.out.println("*************************");
//			System.out.println(buffer.substring(startIndex,endIndex));
//			System.out.println("*************************");

//			System.out.println("*************************");
//			System.out.println(bounds);
//			System.out.println("startIndex[" + startIndex + "] endIndex[" + endIndex + "]");
//
//			System.out.println("*************************");

			return list.subList(startIndex, endIndex);

		}
	}
}
