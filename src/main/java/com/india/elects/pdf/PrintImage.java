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

public class PrintImage {

	public static void main(String[] args) throws InvalidPasswordException, IOException {
		if (args == null || args.length < 2) {
			System.out.println("Input arguments should be 3 args like $inFilePdf[FullPath]  $pageNr $outImageFile{fullpath] ");
			return;
		}

		PDFTableExtractor extractor = new PDFTableExtractor();
		try {
			extractor.open(args[0]);
			PDPage page = extractor.getDocument().getPage(Integer.parseInt(args[1]));
			System.out.println("Rectange, height/width [" + page.getMediaBox().getHeight() + "],["
					+ page.getMediaBox().getWidth() + "]");
			List<TextPosition> list = extractor.printTextPositions(Integer.parseInt(args[1]));
			printImage(args[2],list,page.getMediaBox().getWidth(),page.getMediaBox().getHeight());
		} finally {
			extractor.close();
		}

	}

	public static void printImage(String fileStr, List<TextPosition> list, float width, float height) throws IOException {
		BufferedImage bufferedImage = new BufferedImage(Math.round(width), Math.round(height), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, Math.round(width), Math.round(height));

		g2d.setColor(Color.black);
		for (TextPosition text : list) {
			g2d.drawString(text.getUnicode(), text.getX(), text.getY());
		}

		File file = new File(fileStr);
		ImageIO.write(bufferedImage, "png", file);

	}

}
