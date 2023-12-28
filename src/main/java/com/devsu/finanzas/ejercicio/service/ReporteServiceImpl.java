package com.devsu.finanzas.ejercicio.service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsu.finanzas.ejercicio.dto.ReporteDTO;
import com.devsu.finanzas.ejercicio.dto.ReporteDataDTO;
import com.devsu.finanzas.ejercicio.exception.BadRequestException;
import com.devsu.finanzas.ejercicio.exception.CustomException;
import com.devsu.finanzas.ejercicio.model.Movimientos;
import com.devsu.finanzas.ejercicio.repository.MovimientoRepository;
import com.devsu.finanzas.ejercicio.service.interfaces.ReporteService;
import com.devsu.finanzas.ejercicio.util.ConstantsUtil;
import com.devsu.finanzas.ejercicio.util.UtilDate;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReporteServiceImpl implements ReporteService, ConstantsUtil {

    private UtilDate utilDate;
    private MovimientoRepository mRepository;

    @Override
    @Transactional(readOnly = true)
    public ReporteDataDTO generarReporte(String idCliente, String fechaInicio, String fechaFin) {
        List<Movimientos> reporte = mRepository.listarReporte(idCliente, fechaInicio, fechaFin);

        if (reporte.size() > 0){
            return generarReportePDF(reporte);
        } else {
            throw new BadRequestException("No hay reportes en las fechas indicadas");
        }
    }

    public ReporteDataDTO generarReportePDF(List<Movimientos> reporte) {
        try {
            ReporteDataDTO rDataDTO = new ReporteDataDTO();

            // Crear un nuevo documento PDF
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Definir el tamaño de la fuente
            int fontSize = 12;
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, fontSize);

            float margin = 50;
            float yStart = page.getMediaBox().getHeight() - margin;
            float yPosition = yStart;
            float margin2 = 50;
            float yPosition2 = yStart - 30;

            // Encabezado
            String encabezado = "Informe de Movimientos";
            contentStream.beginText();
            contentStream.newLineAtOffset(50, yPosition);
            contentStream.showText(encabezado);
            contentStream.endText();

            // Encabezados de las columnas
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, fontSize);
            contentStream.newLineAtOffset(margin2, yPosition2);
            contentStream.showText("Fecha");
            contentStream.newLineAtOffset(90, 0);
            contentStream.showText("N° cuenta");
            contentStream.newLineAtOffset(70, 0);
            contentStream.showText("Tipo movimiento");
            contentStream.newLineAtOffset(120, 0);
            contentStream.showText("Saldo Inicial");
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Valor");
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Saldo");
            contentStream.endText();

            // Datos del informe
            yPosition -= 4 * fontSize;

            List<ReporteDTO> listReporteDTO = new ArrayList<>();
            for (Movimientos item : reporte) {
                Date fecha = item.getFecha();
                String cliente = item.getCuenta().getClienteId().getNombre();
                String numeroCuenta = item.getCuenta().getNumeroCuenta();
                String tipo = item.getTipoMovimiento();
                Double saldoInicial = calcularSaldoInicial(item);
                Boolean estado = item.getCuenta().getEstado();
                Double movimiento = item.getValor();
                Double saldoDisponible = item.getSaldo();

                contentStream.beginText();
                contentStream.newLineAtOffset(margin2, yPosition);
                contentStream.showText(utilDate.formatDateToString(fecha, YYYY_MM_DD));
                contentStream.newLineAtOffset(90, 0);
                contentStream.showText(numeroCuenta);
                contentStream.newLineAtOffset(70, 0);
                contentStream.showText(tipo);
                contentStream.newLineAtOffset(120, 0);
                contentStream.showText(saldoInicial.toString());
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText(movimiento.toString());
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText(saldoDisponible.toString());
                contentStream.endText();
                yPosition -= fontSize;
                    
                listReporteDTO.add(new ReporteDTO(fecha, cliente, numeroCuenta, tipo, saldoInicial, estado, movimiento, saldoDisponible));
            }

            // Cerrar el contenido y guardar el PDF
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            contentStream.close();
            document.save(baos);
            document.close();

            byte[] pdfBytes = baos.toByteArray();
            String pdfBase64 = Base64.getEncoder().encodeToString(pdfBytes);

            rDataDTO.setReporte(listReporteDTO);
            rDataDTO.setPdfBase64(pdfBase64);

            return rDataDTO;
        } catch (Exception e) {
            throw new CustomException("Se genero un erro al momento de generar el PDF");
        }
    }

    private Double calcularSaldoInicial(Movimientos movimiento) {
        if (movimiento.getTipoMovimiento().equals("Ahorros")) {
            return movimiento.getSaldo() + movimiento.getValor();
        } else {
            return movimiento.getSaldo() - movimiento.getValor();
        }
    }

}
