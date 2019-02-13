package com.india.elects.ls.year2014;

import java.io.File;
import java.io.FileInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.india.elects.ls.year2014.exception.UnexpectedExcelFormat;
import com.india.elects.ls.year2014.model.Ls2014Constituency;

public class ExcelReader {
	Logger LOG = LogManager.getRootLogger();

	private int totalConstituencies = 0;

	private Workbook workbook = null;

	public ExcelReader(File file) throws Exception {
		FileInputStream excelFile = new FileInputStream(file);
		workbook = new XSSFWorkbook(excelFile);
		totalConstituencies = workbook.getNumberOfSheets();
		LOG.info("TOTAL sheets " + totalConstituencies);
	}

	public Ls2014Constituency readConstituency(int sheetNr) {
		Ls2014Constituency model = new Ls2014Constituency();
		Sheet datatypeSheet = workbook.getSheetAt(sheetNr);
		Row row = datatypeSheet.getRow(1);
		readStateConstituencyName(model, row);

		readCandidatesInfo(model, datatypeSheet.getRow(2), datatypeSheet.getRow(3), datatypeSheet.getRow(4),
				datatypeSheet.getRow(5), datatypeSheet.getRow(6), datatypeSheet.getRow(7));

		return model;
	}

	// Read from Row 2
	private void readStateConstituencyName(Ls2014Constituency model, Row row) {
		if (!"State/UT & Code".equals(row.getCell(0).getStringCellValue())) {
			throw new UnexpectedExcelFormat("String State/UT & Code not found in Row 2");
		}

		String stateNameCode = row.getCell(1).getStringCellValue();
		String constituencyNameCode = row.getCell(3).getStringCellValue();

		String stateSplitArr[] = stateNameCode.split("-");
		model.setStateName(stateSplitArr[0]);
		model.setStateCd(stateSplitArr[1]);
		

		String constSplitArr[] = constituencyNameCode.split("-");

		model.setConstituencyName(constituencyNameCode.split("-[0-9]$")[0]);
		model.setConstituencyCd(Integer.parseInt(constSplitArr[constSplitArr.length-1]));
	}

	// Read from Row 3
	private void readCandidatesInfo(Ls2014Constituency model, Row row, Row nominatedRow, Row nomRejectedRow,
			Row withdrawnRow, Row contestedRow, Row forfeitedRow) {

		// Validation

		if (!"CANDIDATES".equals(row.getCell(0).getStringCellValue())) {
			throw new UnexpectedExcelFormat("String CANDIDATES not found in Row 3");
		}
		if (!"Men".equals(row.getCell(3).getStringCellValue())) {
			throw new UnexpectedExcelFormat("String Men not found in Row 3");
		}
		if (!"Woman".equals(row.getCell(5).getStringCellValue())) {
			throw new UnexpectedExcelFormat("String Woman not found in Row 3");
		}
		if (!"Others".equals(row.getCell(7).getStringCellValue())) {
			throw new UnexpectedExcelFormat("String Others not found in Row 3");
		}
		if (!"Total".equals(row.getCell(9).getStringCellValue())) {
			throw new UnexpectedExcelFormat("String Total not found in Row 3");
		}

		if (!"Nominated".equals(nominatedRow.getCell(1).getStringCellValue())) {
			throw new UnexpectedExcelFormat("String Nominated not found in Row 4 - Nominated");
		}
		if (!"Nomination Rejected".equals(nomRejectedRow.getCell(1).getStringCellValue())) {
			throw new UnexpectedExcelFormat("String Nominated not found in Row 5 - nomRejectedRow");
		}
		if (!"Withdrawn".equals(withdrawnRow.getCell(1).getStringCellValue())) {
			throw new UnexpectedExcelFormat("String Nominated not found in Row 6 - withdrawnRow");
		}
		if (!"Contested".equals(contestedRow.getCell(1).getStringCellValue())) {
			throw new UnexpectedExcelFormat("String Nominated not found in Row 7 - contestedRow");
		}
		if (!"Forfeited Deposit".equals(forfeitedRow.getCell(1).getStringCellValue())) {
			throw new UnexpectedExcelFormat("String Nominated not found in Row 8 - forfeitedRow");
		}

		// Extraction Nomination Row
		if (nominatedRow.getCell(3).getCellTypeEnum() == CellType.NUMERIC) {
			model.setNominatedMen((int) nominatedRow.getCell(3).getNumericCellValue());
		}
		if (nominatedRow.getCell(5).getCellTypeEnum() == CellType.NUMERIC) {
			model.setNominatedWomen((int) nominatedRow.getCell(5).getNumericCellValue());
		}
		if (nominatedRow.getCell(7).getCellTypeEnum() == CellType.NUMERIC) {
			model.setNominatedOther((int) nominatedRow.getCell(7).getNumericCellValue());
		}
		if (nominatedRow.getCell(9).getCellTypeEnum() == CellType.NUMERIC) {
			model.setNominatedTotal((int) nominatedRow.getCell(9).getNumericCellValue());
		}

		// Extraction NominationRejected Row
		if (nomRejectedRow.getCell(3).getCellTypeEnum() == CellType.NUMERIC) {
			model.setNominatedRejectedMen((int) nomRejectedRow.getCell(3).getNumericCellValue());
		}
		if (nomRejectedRow.getCell(5).getCellTypeEnum() == CellType.NUMERIC) {
			model.setNominatedRejectedWomen((int) nomRejectedRow.getCell(5).getNumericCellValue());
		}
		if (nomRejectedRow.getCell(7).getCellTypeEnum() == CellType.NUMERIC) {
			model.setNominatedRejectedOther((int) nomRejectedRow.getCell(7).getNumericCellValue());
		}
		if (nomRejectedRow.getCell(9).getCellTypeEnum() == CellType.NUMERIC) {
			model.setNominatedRejectedTotal((int) nomRejectedRow.getCell(9).getNumericCellValue());
		}

		// Extraction Withdrawn Row
		if (withdrawnRow.getCell(3).getCellTypeEnum() == CellType.NUMERIC) {
			model.setWithdrawnMen((int) withdrawnRow.getCell(3).getNumericCellValue());
		}
		if (withdrawnRow.getCell(5).getCellTypeEnum() == CellType.NUMERIC) {
			model.setWithdrawnWomen((int) withdrawnRow.getCell(5).getNumericCellValue());
		}
		if (withdrawnRow.getCell(7).getCellTypeEnum() == CellType.NUMERIC) {
			model.setWithdrawnOther((int) withdrawnRow.getCell(7).getNumericCellValue());
		}
		if (withdrawnRow.getCell(9).getCellTypeEnum() == CellType.NUMERIC) {
			model.setWithdrawnTotal((int) withdrawnRow.getCell(9).getNumericCellValue());
		}

		// Extraction Contested Row
		if (contestedRow.getCell(3).getCellTypeEnum() == CellType.NUMERIC) {
			model.setContestedMen((int) contestedRow.getCell(3).getNumericCellValue());
		}
		if (contestedRow.getCell(5).getCellTypeEnum() == CellType.NUMERIC) {
			model.setContestedWomen((int) contestedRow.getCell(5).getNumericCellValue());
		}
		if (contestedRow.getCell(7).getCellTypeEnum() == CellType.NUMERIC) {
			model.setContestedOther((int) contestedRow.getCell(7).getNumericCellValue());
		}
		if (contestedRow.getCell(9).getCellTypeEnum() == CellType.NUMERIC) {
			model.setContestedTotal((int) contestedRow.getCell(9).getNumericCellValue());
		}

		// Extraction forfeitedRow Row
		if (forfeitedRow.getCell(3).getCellTypeEnum() == CellType.NUMERIC) {
			model.setForfeitedDepositMen((int) forfeitedRow.getCell(3).getNumericCellValue());
		}
		if (forfeitedRow.getCell(5).getCellTypeEnum() == CellType.NUMERIC) {
			model.setForfeitedDepositWomen((int) forfeitedRow.getCell(5).getNumericCellValue());
		}
		if (forfeitedRow.getCell(7).getCellTypeEnum() == CellType.NUMERIC) {
			model.setForfeitedDepositOther((int) forfeitedRow.getCell(7).getNumericCellValue());
		}
		if (forfeitedRow.getCell(9).getCellTypeEnum() == CellType.NUMERIC) {
			model.setForfeitedDepositTotal((int) forfeitedRow.getCell(9).getNumericCellValue());
		}

	}

	public int getTotalConstituencies() {
		return totalConstituencies;
	}

}
