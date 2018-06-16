package infra;

import javax.swing.JOptionPane;

public abstract class Report {

	abstract String reportType();
	abstract String loadReport();
	abstract String saveReport();
	
	public final void generateReport()
	{
		String showReport = "";
		
		showReport += reportType();
		showReport += loadReport();
		showReport += saveReport();
		
		JOptionPane.showMessageDialog(null, showReport);
		
	}
}
