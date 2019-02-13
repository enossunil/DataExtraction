package com.india.elects.ls.year2009;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

import com.india.elects.ls.year2009.exception.UnxpectedDataFormatInExtractedPDFTable;
import com.india.elects.ls.year2009.model.Ls2009Constituency;
import com.india.elects.pdf.PDFTableExtractor;
import com.india.elects.pdf.model.Table;
import com.india.elects.pdf.model.TableBoundaryIndentificationHelper;
import com.india.elects.utils.StringUtil;
import com.india.elects.utils.Utils;

public class PDFReader {
	private File file = null;
	private List<TableBoundaryIndentificationHelper> hints = null;
	private boolean printHtml = false;
	private String outDir = null;

	public PDFReader(File file, List<TableBoundaryIndentificationHelper> hints, boolean printHtml) throws IOException {
		this.file = file;
		this.hints = hints;
		this.printHtml = printHtml;
		outDir = file.getParent() + file.separator + "html";
	}

	public Ls2009Constituency extractConstituencyInPage(int pageNr) throws InvalidPasswordException, IOException {

		PDFTableExtractor extractor = new PDFTableExtractor();
		Ls2009Constituency model = new Ls2009Constituency();
		try {
			extractor.open(file);

			List<Table> tables = extractor.extract(hints, pageNr);
			if (printHtml) {
				printHtml(outDir, pageNr, tables);
			}

			extractStateConstituencyNameCd(tables.get(0), model);
			extractCandidates(tables.get(1), model);
			extractElectors(tables.get(2), model);
			extractVoters(tables.get(3), model);
			extractVotes(tables.get(4), model);

			return model;
		} finally {
			extractor.close();
		}
	}

	private void extractStateConstituencyNameCd(Table table, Ls2009Constituency model) {
		// Validation

		if (!table.getRows().get(0).getCells().get(0).getContent().trim().startsWith("State/UT :")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("State/UT : not found");
		}

		if (!table.getRows().get(2).getCells().get(0).getContent().trim().startsWith("Constituency :")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("Constituency : not found");
		}

		// Extracting....

		// State_Cd
		String stateCd = table.getRows().get(0).getCells().get(1).getContent().trim();
		model.setStateCd(stateCd.split(":")[1].trim());
		// Constituency Name
		String constituencyName = table.getRows().get(2).getCells().get(0).getContent().trim();
		model.setConstituencyName(constituencyName.split(":")[1].trim());

		// Constituency Cd
		String constituencyCd = table.getRows().get(1).getCells().get(1).getContent().trim();
		model.setConstituencyCd(Integer.parseInt(constituencyCd.split(":")[1].trim()));

	}

	private void extractCandidates(Table table, Ls2009Constituency model) {
		// Validation

		if (!table.getRows().get(0).getCells().get(1).getContent().trim().startsWith("MEN")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("MEN : not found while extractCandidates");
		}

		if (!table.getRows().get(0).getCells().get(2).getContent().trim().startsWith("WOMEN")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("WOMEN : not found while extractCandidates");
		}

		if (!table.getRows().get(0).getCells().get(3).getContent().trim().startsWith("TOTAL")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("TOTAL : not found while extractCandidates");
		}

		if (!table.getRows().get(1).getCells().get(0).getContent().trim().startsWith("I. CANDIDATES")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("I. CANDIDATES : not found while extractCandidates");
		}
		if (!table.getRows().get(3).getCells().get(0).getContent().trim().startsWith("1. NOMINATED")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("1. NOMINATED : not found while extractCandidates");
		}
		if (!table.getRows().get(5).getCells().get(0).getContent().trim().startsWith("2. NOMINATION REJECTED")) {
			throw new UnxpectedDataFormatInExtractedPDFTable(
					"2. NOMINATION REJECTED : not found while extractCandidates");
		}
		if (!table.getRows().get(7).getCells().get(0).getContent().trim().startsWith("3. WITHDRAWN")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("3. WITHDRAWN : not found while extractCandidates");
		}
		if (!table.getRows().get(9).getCells().get(0).getContent().trim().startsWith("4. CONTESTED")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("4. CONTESTED : not found while extractCandidates");
		}
		if (!table.getRows().get(11).getCells().get(0).getContent().trim().startsWith("5. FORFEITED DEPOSIT")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("5. FORFEITED DEPOSIT: not found while extractCandidates");
		}

		// Extracting....
		String candiatestMen = table.getRows().get(1).getCells().size() > 1
				? table.getRows().get(1).getCells().get(1).getContent().trim()
				: null;
		model.setContestedMen(StringUtil.isEmpty(candiatestMen) ? null : Integer.parseInt(candiatestMen));
		String candiatestWomen = table.getRows().get(1).getCells().size() > 2
				? table.getRows().get(1).getCells().get(2).getContent().trim()
				: null;
		model.setContestedWomen(StringUtil.isEmpty(candiatestWomen) ? null : Integer.parseInt(candiatestWomen));
		String candiatestTotal = table.getRows().get(1).getCells().size() > 3
				? table.getRows().get(1).getCells().get(3).getContent().trim()
				: null;
		model.setContestedTotal(StringUtil.isEmpty(candiatestTotal) ? null : Integer.parseInt(candiatestTotal));

		String nominatedMen = table.getRows().get(3).getCells().size() > 1
				? table.getRows().get(3).getCells().get(1).getContent().trim()
				: null;
		model.setNominatedMen(StringUtil.isEmpty(nominatedMen) ? null : Integer.parseInt(nominatedMen));
		String nominatedtWomen = table.getRows().get(3).getCells().size() > 2
				? table.getRows().get(3).getCells().get(2).getContent().trim()
				: null;
		model.setNominatedWomen(StringUtil.isEmpty(nominatedtWomen) ? null : Integer.parseInt(nominatedtWomen));
		String nominatedTotal = table.getRows().get(3).getCells().size() > 3
				? table.getRows().get(3).getCells().get(3).getContent().trim()
				: null;
		model.setNominatedTotal(StringUtil.isEmpty(nominatedTotal) ? null : Integer.parseInt(nominatedTotal));

		String nominationRejectedMen = table.getRows().get(5).getCells().size() > 1
				? table.getRows().get(5).getCells().get(1).getContent().trim()
				: null;
		model.setNominatedRejectedMen(
				StringUtil.isEmpty(nominationRejectedMen) ? null : Integer.parseInt(nominationRejectedMen));
		String nominationRejectedtWomen = table.getRows().get(5).getCells().size() > 2
				? table.getRows().get(5).getCells().get(2).getContent().trim()
				: null;
		model.setNominatedRejectedWomen(
				StringUtil.isEmpty(nominationRejectedtWomen) ? null : Integer.parseInt(nominationRejectedtWomen));
		String nominationRejectedTotal = table.getRows().get(5).getCells().size() > 3
				? table.getRows().get(5).getCells().get(3).getContent().trim()
				: null;
		model.setNominatedRejectedTotal(
				StringUtil.isEmpty(nominationRejectedTotal) ? null : Integer.parseInt(nominationRejectedTotal));

		String withdrawnMen = table.getRows().get(7).getCells().size() > 1
				? table.getRows().get(7).getCells().get(1).getContent().trim()
				: null;
		model.setWithdrawnMen(StringUtil.isEmpty(withdrawnMen) ? null : Integer.parseInt(withdrawnMen));
		String withdrawnWomen = table.getRows().get(7).getCells().size() > 2
				? table.getRows().get(7).getCells().get(2).getContent().trim()
				: null;
		model.setWithdrawnWomen(StringUtil.isEmpty(withdrawnWomen) ? null : Integer.parseInt(withdrawnWomen));
		String withdrawnTotal = table.getRows().get(7).getCells().size() > 3
				? table.getRows().get(7).getCells().get(3).getContent().trim()
				: null;
		model.setWithdrawnTotal(StringUtil.isEmpty(withdrawnTotal) ? null : Integer.parseInt(withdrawnTotal));

		String contestedMen = table.getRows().get(9).getCells().size() > 1
				? table.getRows().get(9).getCells().get(1).getContent().trim()
				: null;
		model.setContestedMen(StringUtil.isEmpty(contestedMen) ? null : Integer.parseInt(contestedMen));
		String contestedWomen = table.getRows().get(9).getCells().size() > 2
				? table.getRows().get(9).getCells().get(2).getContent().trim()
				: null;
		model.setContestedWomen(StringUtil.isEmpty(contestedWomen) ? null : Integer.parseInt(contestedWomen));
		String contestedTotal = table.getRows().get(9).getCells().size() > 3
				? table.getRows().get(9).getCells().get(3).getContent().trim()
				: null;
		model.setContestedTotal(StringUtil.isEmpty(contestedTotal) ? null : Integer.parseInt(contestedTotal));

		String forfeitedMen = table.getRows().get(11).getCells().size() > 1
				? table.getRows().get(11).getCells().get(1).getContent().trim()
				: null;
		model.setForfeitedDepositMen(StringUtil.isEmpty(forfeitedMen) ? null : Integer.parseInt(forfeitedMen));
		String forfeitedWomen = table.getRows().get(11).getCells().size() > 2
				? table.getRows().get(11).getCells().get(2).getContent().trim()
				: null;
		model.setForfeitedDepositWomen(StringUtil.isEmpty(forfeitedWomen) ? null : Integer.parseInt(forfeitedWomen));
		String forfeitedTotal = table.getRows().get(11).getCells().size() > 3
				? table.getRows().get(11).getCells().get(3).getContent().trim()
				: null;
		model.setForfeitedDepositTotal(StringUtil.isEmpty(forfeitedTotal) ? null : Integer.parseInt(forfeitedTotal));

	}

	private void extractElectors(Table table, Ls2009Constituency model) {
		// Validation

		if (!table.getRows().get(0).getCells().get(0).getContent().trim().startsWith("ELECTORS")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("ELECTORS : not found while extractElectors");
		}

		if (!table.getRows().get(2).getCells().get(0).getContent().trim().startsWith("1. GENERAL")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("1. GENERAL : not found while extractElectors");
		}

		if (!table.getRows().get(4).getCells().get(0).getContent().trim().startsWith("2. SERVICE")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("2. SERVICE : not found while extractElectors");
		}

		if (!table.getRows().get(5).getCells().get(0).getContent().trim().startsWith("3. TOTAL")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("3. TOTAL : not found while extractElectors");
		}

		// Extracting....
		String electorsGeneralMen = table.getRows().get(2).getCells().size() > 1
				? table.getRows().get(2).getCells().get(1).getContent().trim()
				: null;
		model.setElectorsGeneralMen(
				StringUtil.isEmpty(electorsGeneralMen) ? null : Integer.parseInt(electorsGeneralMen));
		String electorsGeneralWomen = table.getRows().get(2).getCells().size() > 2
				? table.getRows().get(2).getCells().get(2).getContent().trim()
				: null;
		model.setElectorsGeneralWomen(
				StringUtil.isEmpty(electorsGeneralWomen) ? null : Integer.parseInt(electorsGeneralWomen));
		String electorsGeneralTotal = table.getRows().get(2).getCells().size() > 3
				? table.getRows().get(2).getCells().get(3).getContent().trim()
				: null;
		model.setElectorsGeneralTotal(
				StringUtil.isEmpty(electorsGeneralTotal) ? null : Integer.parseInt(electorsGeneralTotal));

		String electorsServiceMen = table.getRows().get(4).getCells().size() > 1
				? table.getRows().get(4).getCells().get(1).getContent().trim()
				: null;
		model.setElectorsServiceMen(
				StringUtil.isEmpty(electorsServiceMen) ? null : Integer.parseInt(electorsServiceMen));
		String electorsServiceWomen = table.getRows().get(4).getCells().size() > 2
				? table.getRows().get(4).getCells().get(2).getContent().trim()
				: null;
		model.setElectorsServiceWomen(
				StringUtil.isEmpty(electorsServiceWomen) ? null : Integer.parseInt(electorsServiceWomen));
		String electorsServiceTotal = table.getRows().get(4).getCells().size() > 3
				? table.getRows().get(4).getCells().get(3).getContent().trim()
				: null;
		model.setElectorsServiceTotal(
				StringUtil.isEmpty(electorsServiceTotal) ? null : Integer.parseInt(electorsServiceTotal));

		String electorsTotalMen = table.getRows().get(5).getCells().size() > 1
				? table.getRows().get(5).getCells().get(1).getContent().trim()
				: null;
		model.setElectorsTotalMen(StringUtil.isEmpty(electorsTotalMen) ? null : Integer.parseInt(electorsTotalMen));
		String electorsTotalWomen = table.getRows().get(5).getCells().size() > 2
				? table.getRows().get(5).getCells().get(2).getContent().trim()
				: null;
		model.setElectorsTotalWomen(
				StringUtil.isEmpty(electorsTotalWomen) ? null : Integer.parseInt(electorsTotalWomen));
		String electorsTotal = table.getRows().get(5).getCells().size() > 3
				? table.getRows().get(5).getCells().get(3).getContent().trim()
				: null;
		model.setElectorsTotal(StringUtil.isEmpty(electorsTotal) ? null : Integer.parseInt(electorsTotal));

	}

	private void extractVoters(Table table, Ls2009Constituency model) {
		// Validation

		if (!table.getRows().get(0).getCells().get(0).getContent().trim().startsWith("VOTERS")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("VOTERS : not found while extractVoters");
		}

		if (!table.getRows().get(2).getCells().get(0).getContent().trim().startsWith("1. GENERAL")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("1. GENERAL : not found while extractVoters");
		}

		if (!table.getRows().get(4).getCells().get(0).getContent().trim().startsWith("2. PROXY")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("2. PROXY : not found while extractVoters");
		}

		if (!table.getRows().get(6).getCells().get(0).getContent().trim().startsWith("3. POSTAL")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("3. POSTAL : not found while extractVoters");
		}

		if (!table.getRows().get(8).getCells().get(0).getContent().trim().startsWith("4. TOTAL")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("4. TOTAL: not found while extractVoters");
		}

		// Extracting....
		String votersGeneralMen = table.getRows().get(2).getCells().size() > 1
				? table.getRows().get(2).getCells().get(1).getContent().trim()
				: null;
		model.setVotersGeneralMen(StringUtil.isEmpty(votersGeneralMen) ? null : Integer.parseInt(votersGeneralMen));
		String votersGeneralWomen = table.getRows().get(2).getCells().size() > 2
				? table.getRows().get(2).getCells().get(2).getContent().trim()
				: null;
		model.setVotersGeneralWomen(
				StringUtil.isEmpty(votersGeneralWomen) ? null : Integer.parseInt(votersGeneralWomen));
		String votersGeneralTotal = table.getRows().get(2).getCells().size() > 3
				? table.getRows().get(2).getCells().get(3).getContent().trim()
				: null;
		model.setVotersGeneralTotal(
				StringUtil.isEmpty(votersGeneralTotal) ? null : Integer.parseInt(votersGeneralTotal));

		String votersProxyMen = table.getRows().get(4).getCells().size() > 1
				? table.getRows().get(4).getCells().get(1).getContent().trim()
				: null;
		model.setVotersProxyMen(StringUtil.isEmpty(votersProxyMen) ? null : Integer.parseInt(votersProxyMen));
		String votersProxyWomen = table.getRows().get(4).getCells().size() > 2
				? table.getRows().get(4).getCells().get(2).getContent().trim()
				: null;
		model.setVotersProxyWomen(StringUtil.isEmpty(votersProxyWomen) ? null : Integer.parseInt(votersProxyWomen));
		String votersProxyTotal = table.getRows().get(4).getCells().size() > 3
				? table.getRows().get(4).getCells().get(3).getContent().trim()
				: null;
		model.setVotersProxyTotal(StringUtil.isEmpty(votersProxyTotal) ? null : Integer.parseInt(votersProxyTotal));

		String votersPostalMen = table.getRows().get(6).getCells().size() > 1
				? table.getRows().get(6).getCells().get(1).getContent().trim()
				: null;
		model.setVotersPostalMen(StringUtil.isEmpty(votersPostalMen) ? null : Integer.parseInt(votersPostalMen));
		String votersPostalWomen = table.getRows().get(6).getCells().size() > 2
				? table.getRows().get(6).getCells().get(2).getContent().trim()
				: null;
		model.setVotersPostalWomen(StringUtil.isEmpty(votersPostalWomen) ? null : Integer.parseInt(votersPostalWomen));
		String votersPostalTotal = table.getRows().get(6).getCells().size() > 3
				? table.getRows().get(6).getCells().get(3).getContent().trim()
				: null;
		model.setVotersPostalTotal(StringUtil.isEmpty(votersPostalTotal) ? null : Integer.parseInt(votersPostalTotal));

		String votersMen = table.getRows().get(8).getCells().size() > 1
				? table.getRows().get(8).getCells().get(1).getContent().trim()
				: null;
		model.setVotersMen(StringUtil.isEmpty(votersMen) ? null : Integer.parseInt(votersMen));
		String votersWomen = table.getRows().get(8).getCells().size() > 2
				? table.getRows().get(8).getCells().get(2).getContent().trim()
				: null;
		model.setVotersWomen(StringUtil.isEmpty(votersWomen) ? null : Integer.parseInt(votersWomen));
		String votersTotal = table.getRows().get(8).getCells().size() > 3
				? table.getRows().get(8).getCells().get(3).getContent().trim()
				: null;
		model.setVotersTotal(StringUtil.isEmpty(votersTotal) ? null : Integer.parseInt(votersTotal));

	}
	

	private void extractVotes(Table table, Ls2009Constituency model) {
		// Validation

		if (!table.getRows().get(0).getCells().get(0).getContent().trim().startsWith("VOTES")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("VOTES : not found while extractVotes");
		}

		if (!table.getRows().get(2).getCells().get(0).getContent().trim().startsWith("1. REJECTED VOTES (POSTAL)")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("1. REJECTED VOTES (POSTAL) : not found while extractVotes");
		}

		if (!table.getRows().get(4).getCells().get(0).getContent().trim().startsWith("2. VOTES NOT RETREIVED FROM EVM")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("2. VOTES NOT RETREIVED FROM EVM : not found while extractVotes");
		}

		if (!table.getRows().get(6).getCells().get(0).getContent().trim().startsWith("3. TOTAL VALID VOTES POLLED")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("3. TOTAL VALID VOTES POLLED : not found while extractVotes");
		}

		if (!table.getRows().get(8).getCells().get(0).getContent().trim().startsWith("4. TENDERED VOTES")) {
			throw new UnxpectedDataFormatInExtractedPDFTable("4. TENDERED VOTES: not found while extractVoters");
		}

		// Extracting....
		String votersGeneralMen = table.getRows().get(2).getCells().size() > 1
				? table.getRows().get(2).getCells().get(1).getContent().trim()
				: null;
		model.setVotersGeneralMen(StringUtil.isEmpty(votersGeneralMen) ? null : Integer.parseInt(votersGeneralMen));
		String votersGeneralWomen = table.getRows().get(2).getCells().size() > 2
				? table.getRows().get(2).getCells().get(2).getContent().trim()
				: null;
		model.setVotersGeneralWomen(
				StringUtil.isEmpty(votersGeneralWomen) ? null : Integer.parseInt(votersGeneralWomen));
		String votersGeneralTotal = table.getRows().get(2).getCells().size() > 3
				? table.getRows().get(2).getCells().get(3).getContent().trim()
				: null;
		model.setVotersGeneralTotal(
				StringUtil.isEmpty(votersGeneralTotal) ? null : Integer.parseInt(votersGeneralTotal));

		String votersProxyMen = table.getRows().get(4).getCells().size() > 1
				? table.getRows().get(4).getCells().get(1).getContent().trim()
				: null;
		model.setVotersProxyMen(StringUtil.isEmpty(votersProxyMen) ? null : Integer.parseInt(votersProxyMen));
		String votersProxyWomen = table.getRows().get(4).getCells().size() > 2
				? table.getRows().get(4).getCells().get(2).getContent().trim()
				: null;
		model.setVotersProxyWomen(StringUtil.isEmpty(votersProxyWomen) ? null : Integer.parseInt(votersProxyWomen));
		String votersProxyTotal = table.getRows().get(4).getCells().size() > 3
				? table.getRows().get(4).getCells().get(3).getContent().trim()
				: null;
		model.setVotersProxyTotal(StringUtil.isEmpty(votersProxyTotal) ? null : Integer.parseInt(votersProxyTotal));

		String votersPostalMen = table.getRows().get(6).getCells().size() > 1
				? table.getRows().get(6).getCells().get(1).getContent().trim()
				: null;
		model.setVotersPostalMen(StringUtil.isEmpty(votersPostalMen) ? null : Integer.parseInt(votersPostalMen));
		String votersPostalWomen = table.getRows().get(6).getCells().size() > 2
				? table.getRows().get(6).getCells().get(2).getContent().trim()
				: null;
		model.setVotersPostalWomen(StringUtil.isEmpty(votersPostalWomen) ? null : Integer.parseInt(votersPostalWomen));
		String votersPostalTotal = table.getRows().get(6).getCells().size() > 3
				? table.getRows().get(6).getCells().get(3).getContent().trim()
				: null;
		model.setVotersPostalTotal(StringUtil.isEmpty(votersPostalTotal) ? null : Integer.parseInt(votersPostalTotal));

		String votersMen = table.getRows().get(8).getCells().size() > 1
				? table.getRows().get(8).getCells().get(1).getContent().trim()
				: null;
		model.setVotersMen(StringUtil.isEmpty(votersMen) ? null : Integer.parseInt(votersMen));
		String votersWomen = table.getRows().get(8).getCells().size() > 2
				? table.getRows().get(8).getCells().get(2).getContent().trim()
				: null;
		model.setVotersWomen(StringUtil.isEmpty(votersWomen) ? null : Integer.parseInt(votersWomen));
		String votersTotal = table.getRows().get(8).getCells().size() > 3
				? table.getRows().get(8).getCells().get(3).getContent().trim()
				: null;
		model.setVotersTotal(StringUtil.isEmpty(votersTotal) ? null : Integer.parseInt(votersTotal));

	}

	private void printHtml(String outDir, int pageId, List<Table> tables) throws IOException {
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
	}

}
