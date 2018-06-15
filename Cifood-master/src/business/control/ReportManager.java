package business.control;

import javax.swing.JOptionPane;

import infra.InfraException;
import infra.PDF_Report;
import infra.Report;
import infra.XML_Report;

public class ReportManager {
	private static ReportManager instance = new ReportManager(); //Singleton
	
	Report report;
	
	private ReportManager(){ }
	
	public static ReportManager getInstance()
	{
		if(instance==null)
		{
			instance = new ReportManager();
		}
		return instance;
	}	

	public void generateReport(String typeReport) {
		
		if(typeReport.contains("PDF") || typeReport.contains("pdf"))
		{
			report = new PDF_Report();
			report.generateReport();
		}
		else if(typeReport.contains("XML") || typeReport.contains("xml"))
		{
			report = new XML_Report();
			report.generateReport();
		}
		
	}

}
