package com.india.elects.pdf.model;

import java.util.List;

import com.google.common.collect.Range;

public class TableBoundaryIndentificationHelper {
	private String tableId;
	private String startText;
	private String endText;
	private boolean includeStartText = true;
	private boolean includeEndText = false;
	private int endFlatDeduc = -1;
	private int startFlatDeduc = -1;

	List<Range<Integer>> columnRanges = null;

	public TableBoundaryIndentificationHelper(String tableId) {
		this.tableId = tableId;
	}

	
	
	public TableBoundaryIndentificationHelper(String tableId,String startText, String endText, boolean includeStartText,
			boolean includeEndText, int endFlatDeduc, List<Range<Integer>> columnRanges) {
		this(tableId);		
		this.startText = startText;
		this.endText = endText;
		this.includeStartText = includeStartText;
		this.includeEndText = includeEndText;
		this.endFlatDeduc = endFlatDeduc;
		this.columnRanges = columnRanges;
	}



	public TableBoundaryIndentificationHelper(String tableId,String startText, String endText, boolean includeStartText,
			boolean includeEndText) {
		this(tableId);
		this.startText = startText;
		this.endText = endText;
		this.includeStartText = includeStartText;
		this.includeEndText = includeEndText;
	}

	public TableBoundaryIndentificationHelper(String tableId,String startText, String endText, boolean includeStartText,
			boolean includeEndText,int endFlatDeduc) {
		this(tableId);
		this.startText = startText;
		this.endText = endText;
		this.includeStartText = includeStartText;
		this.includeEndText = includeEndText;
		this.endFlatDeduc = endFlatDeduc;
	}
	
	public TableBoundaryIndentificationHelper(String tableId,String startText, String endText) {
		this(tableId);
		this.startText = startText;
		this.endText = endText;
	}

	public String getStartText() {
		return startText;
	}

	public void setStartText(String startText) {
		this.startText = startText;
	}

	public String getEndText() {
		return endText;
	}

	public void setEndText(String endText) {
		this.endText = endText;
	}

	public boolean isIncludeStartText() {
		return includeStartText;
	}

	public void setIncludeStartText(boolean includeStartText) {
		this.includeStartText = includeStartText;
	}

	public boolean isIncludeEndText() {
		return includeEndText;
	}

	public void setIncludeEndText(boolean includeEndText) {
		this.includeEndText = includeEndText;
	}

	public int getEndFlatDeduc() {
		return endFlatDeduc;
	}

	public void setEndFlatDeduc(int endFlatDeduc) {
		this.endFlatDeduc = endFlatDeduc;
	}


	public List<Range<Integer>> getColumnRanges() {
		return columnRanges;
	}

	public void setColumnRanges(List<Range<Integer>> columnRanges) {
		this.columnRanges = columnRanges;
	}



	public int getStartFlatDeduc() {
		return startFlatDeduc;
	}



	public void setStartFlatDeduc(int startFlatDeduc) {
		this.startFlatDeduc = startFlatDeduc;
	}



	public String getTableId() {
		return tableId;
	}



	public void setTableId(String tableId) {
		this.tableId = tableId;
	}



	@Override
	public String toString() {
		return "TableBoundaryIndentificationHelper [tableId=" + tableId + ", startText=" + startText + ", endText="
				+ endText + ", includeStartText=" + includeStartText + ", includeEndText=" + includeEndText
				+ ", endFlatDeduc=" + endFlatDeduc + ", startFlatDeduc=" + startFlatDeduc + ", columnRanges="
				+ columnRanges + "]";
	}

}
