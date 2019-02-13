package com.india.elects.ls.year2009;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.india.elects.ls.year2009.model.Ls2009Constituency;

public class LoadMain {

	private static final String PERSISTENCE_UNIT_NAME = "ElectionDb";
	private static EntityManagerFactory factory;

	public LoadMain() {
		init();
	}

	public void init() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	public void persist(Ls2009Constituency aRow) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		em.persist(aRow);

		em.getTransaction().commit();
		em.close();

	}

	public static void main(String[] args) throws Exception {
		// create new record
		if (args == null || args.length < 2) {
			System.out.println("Input arguments required $InputFile $printHtmlonly");
			return;
		}
		boolean printHtml = false;
		if ("Y".equalsIgnoreCase(args[1])) {
			printHtml = true;
		}

		LoadMain load = new LoadMain();
		PDFReader reader = new PDFReader(new File(args[0]), TableBoundaryHints.getHints(), printHtml);
		for (int pageNr = 0; pageNr < 543; pageNr++) {
			Ls2009Constituency model = reader.extractConstituencyInPage(pageNr);
			load.persist(model);
		}

	}
}
