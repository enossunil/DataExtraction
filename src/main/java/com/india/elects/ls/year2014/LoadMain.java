package com.india.elects.ls.year2014;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.india.elects.ls.year2014.model.Ls2014Constituency;

public class LoadMain {

	   private static final String PERSISTENCE_UNIT_NAME = "ElectionDb";
	   private static EntityManagerFactory factory;

	   public LoadMain() {
		   init();
	   }
	   
	   public void init() {
	        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	   }
	    
	   public void persist(Ls2014Constituency aRow) {
	        EntityManager em = factory.createEntityManager();
	        em.getTransaction().begin();

	        em.persist(aRow);
	        
	        em.getTransaction().commit();
	        em.close();

	   }
	   
	    public static void main(String[] args) throws Exception {
	        // create new record

	    	LoadMain load = new LoadMain();

	    	ExcelReader xlReader = new ExcelReader(new File(args[0]));
	    	
	    	for(int i=0;i<xlReader.getTotalConstituencies();i++) {
	    		Ls2014Constituency aRow = xlReader.readConstituency(i);
		        load.persist(aRow);
	    	}
	    	
	    	
	        
	        
	        
	        
	    }

}
