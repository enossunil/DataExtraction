package com.india.elects.ls.year1977;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

import com.india.elects.extractor.PDFdbMapper;
import com.india.elects.ls.year1977.def.extraction.Extractions;
import com.india.elects.ls.year1977.def.validation.Validations;
import com.india.elects.ls.year1977.model.Ls1977Constituency;
import com.india.elects.pdf.core.PDFTableExtractor;
import com.india.elects.pdf.model.Table;
import com.india.elects.pdf.model.TableBoundaryIndentificationHelper;

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

	public Ls1977Constituency extractConstituencyInPage(int pageNr) throws InvalidPasswordException, IOException, IllegalAccessException, InvocationTargetException {

		PDFTableExtractor extractor = new PDFTableExtractor();
		Ls1977Constituency model = new Ls1977Constituency();
		try {
			extractor.open(file);

			List<Table> tables = extractor.extract(hints, pageNr);
			if (printHtml) {
				printHtml(outDir, pageNr, tables);
			}



		    PDFdbMapper mapper = new PDFdbMapper();
		    mapper.validate(tables, Validations.values());
//		    mapper.extract(model, tables, Extractions.values());
			
			return model;
		} finally {
			extractor.close();
		}
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
