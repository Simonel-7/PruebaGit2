package reports;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * The {@code ReportManager} class provides functionality to generate and export
 * reports to the file system.
 *
 * <p>
 * Reports are saved in the "src/reports" directory with a filename format:
 * {@code YYYY-MM-DD_HH-mm-ss_TypeOfReport.txt}.
 * </p>
 */

public class ReportManager {

    private static final String REPORTS_PATH = "src\\reports";

    /**
     * Exports a report with the given type and content to the file system.
     *
     * <p>
     * The report is saved in the "src/reports" directory with a filename
     * format: {@code YYYY-MM-DD_HH-mm-ss_TypeOfReport.txt}.
     *
     * @param type    The type of the report (e.g., "Activity", "Summary").
     * @param content The content of the report.
     * @throws IOException If an I/O error occurs during file creation or writing.
     */
    public static void exportReport(String type, String content) throws IOException {

        // Formato: AAAA-MM-DD_HH-mm-ss_TipoReporte.txt

        String fileName = LocalDateTime.now().getYear() + "-" +
                LocalDateTime.now().getMonthValue() + "-" +
                LocalDateTime.now().getDayOfMonth() + "_" +
                LocalDateTime.now().getHour() + "-" +
                LocalDateTime.now().getMinute() + "-" +
                LocalDateTime.now().getSecond() + "_" +
                type;
        String extension = ".txt";

        File report = new File(REPORTS_PATH + "\\" + fileName + extension);

        String header = "Reporte de " + type + "\n";

        // Contenido: header+content;

        report.createNewFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(report));
        writer.write(header + content);
        writer.flush();
        writer.close();

    }

}
