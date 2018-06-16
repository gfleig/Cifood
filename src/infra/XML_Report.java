package infra;

public class XML_Report extends Report{

	String reportType() {
		return "\t\tRelatório XML\n\n\n";
	}

	String loadReport() {
		return "\t\tColetando dados e gerando código XML...\n\n\n";
	}

	String saveReport() {
		return "\t\tSalvando relatório XML...\n\n\n";
	}

}
