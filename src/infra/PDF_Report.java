package infra;

public class PDF_Report extends Report{

	String reportType() {
		return "\t\tRelatório PDF\n\n\n";
	}

	String loadReport() {
		return "\t\tColetando informações e gerando arquivo PDF...\n\n\n";
	}

	String saveReport() {
		return "\t\tSalvando relatório PDF...\n\n\n";
	}

}
