package com.india.elects.pdf;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.TextPosition;

import com.india.elects.pdf.core.PDFTableExtractor;

public class PrintCoordinates {

	public PrintCoordinates() {
	}

	public static void main(String[] args) throws IOException {
		if (args == null || args.length < 2) {
			System.out.println("Input arguments should be 2 args like $INFile{fullpath] $pageNr");
			return;
		}

		printTextArray(args);
	}

	public static void printTextArray(String[] args) throws InvalidPasswordException, IOException {

		PDFTableExtractor extractor = new PDFTableExtractor();
		try {
			extractor.open(args[0]);
			PDPage page = extractor.getDocument().getPage(Integer.parseInt(args[1]));
			System.out.println("Rectange, height/width [" + page.getMediaBox().getHeight() + "],["
					+ page.getMediaBox().getWidth() + "]");
			List<TextPosition> list = extractor.printTextPositions(Integer.parseInt(args[1]));
		} finally {
			extractor.close();
		}
	}


}
