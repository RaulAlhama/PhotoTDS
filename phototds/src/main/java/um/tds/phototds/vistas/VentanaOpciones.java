package um.tds.phototds.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import um.tds.phototds.controlador.Controlador;
import um.tds.phototds.dominio.Usuario;

@SuppressWarnings("serial")
public class VentanaOpciones extends JFrame {

	private JFrame opciones;
	private Controlador controlador;
	private HSSFWorkbook workbook;

	public VentanaOpciones(JFrame framePrincipal, JButton menu) {
		controlador = Controlador.getUnicaInstancia();
		initialize(framePrincipal, menu);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JFrame framePrincipal, JButton menu) {
		opciones = new JFrame();
		opciones.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		opciones.setTitle("Opciones");
		opciones.setBounds(100, 100, 314, 164);
		opciones.addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeactivated(WindowEvent e) {
				opciones.dispose();
			}
		});
		JPanel panel = new JPanel();
		opciones.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnGenerarPDF = new JButton("GenerarPDF");
		btnGenerarPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opciones.dispose();
				generarPDF(framePrincipal);
			}
		});
		panel.add(btnGenerarPDF);

		JButton btnFotosConMas = new JButton("Fotos con mas MG");
		btnFotosConMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opciones.dispose();
				JOptionPane.showMessageDialog(framePrincipal, "FUNCION SIN IMPLEMENTAR", "¡AVISO!",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panel.add(btnFotosConMas);

		JButton btnGenerarexcel = new JButton("GenerarExcel");
		btnGenerarexcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opciones.dispose();
				try {
					generarExcel(framePrincipal);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnGenerarexcel);

		JButton btnPremium = new JButton("Premium");
		if (!controlador.getUsuarioActual().isPremium()) {
			btnGenerarPDF.setEnabled(false);
			btnFotosConMas.setEnabled(false);
			btnGenerarexcel.setEnabled(false);
		}
		btnPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!controlador.getUsuarioActual().isPremium()) {
					controlador.hacerPremium();
					btnGenerarexcel.setEnabled(true);
					btnGenerarPDF.setEnabled(true);
					btnFotosConMas.setEnabled(true);
					opciones.dispose();
					JOptionPane.showMessageDialog(framePrincipal, "Ahora eres usuario PREMIUM", "¡Nuevas Funciones!",
							JOptionPane.INFORMATION_MESSAGE);
					if (controlador.getDescuento().isPresent()) {
						String mensaje = controlador.getDescuento().get().getMensaje();
						JOptionPane.showMessageDialog(framePrincipal, "Descuento aplicado: " + mensaje, "Premium",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(framePrincipal, "No se ha aplicado ningún Descuento.", "Premium",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					opciones.dispose();
					JOptionPane.showMessageDialog(framePrincipal, "Ya eres usuario premium!", "Premium",
							JOptionPane.INFORMATION_MESSAGE);

				}

			}
		});

		panel.add(btnPremium);
		JButton btnCerrarSesin = new JButton("Cerrar Sesión");
		btnCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaLogin ventanaLogin = new VentanaLogin();
				ventanaLogin.mostrarVentana();
				opciones.dispose();
				framePrincipal.dispose();
			}
		});
		panel.add(btnCerrarSesin);
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.RED);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				opciones.dispose();
			}
		});
		panel.add(btnCancelar);
		opciones.setLocationRelativeTo(menu);
		opciones.setResizable(false);
		opciones.setVisible(true);

	}

	private void generarPDF(JFrame framePrincipal) {
		String sep = System.getProperty("os.name").startsWith("Windows") ? "\\" : "/";
		String ruta = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
		FileOutputStream archivo;
		try {
			archivo = new FileOutputStream(ruta + sep + "AppPhotoFollowers.pdf");
			Document documento = new Document();
			PdfWriter.getInstance(documento, archivo);
			rellenarPDF(documento);
			JOptionPane.showMessageDialog(framePrincipal, "El PDF se guardó correctamente en la ruta \"" + ruta + "\"",
					"PDF generated!", JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(framePrincipal, "Error: El pdf no se ha podido crear", "PDF: Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (DocumentException e) {
			JOptionPane.showMessageDialog(framePrincipal, "Error: El pdf no se ha podido crear", "PDF: Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	private void rellenarPDF(Document doc) throws DocumentException {
		doc.open();
		doc.add(new Paragraph("                                                     AppPhoto(c) Premium Document"));
		doc.add(new Paragraph("\n\n"));
		doc.add(new Paragraph("Este documento contiene una lista con los seguidores del ususario "
				+ controlador.getUsuarioActual().getUsername() + ". Tiene un total de " + controlador.getNumSeguidores()
				+ " seguidores y son los siguientes: "));
		for (Usuario seguidor : controlador.getSeguidores()) {
			doc.add(new Paragraph("Nombre: " + seguidor.getNombre() + ". Email: " + seguidor.getEmail()
					+ ". Presentación: " + seguidor.getPresentacion() + ".\n"));
			doc.add(new Paragraph("_______________________________________________________________________\n\n"));
		}
		doc.close();
	}

	private void generarExcel(JFrame framePrincipal) throws Exception {
		workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "Hoja excel");
		String[] headers = new String[] { "Nombre", "Email", "Presentacion" };

		CellStyle headerStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBold(true);
		headerStyle.setFont(font);

		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		HSSFRow headerRow = sheet.createRow(0);
		for (int i = 0; i < headers.length; ++i) {
			String header = headers[i];
			HSSFCell cell = headerRow.createCell(i);
			cell.setCellStyle(headerStyle);
			cell.setCellValue(header);
		}
		int i = 0;
		for (Usuario seguidor : controlador.getSeguidores()) {
			HSSFRow dataRow = sheet.createRow(i + 1);
			dataRow.createCell(0).setCellValue(seguidor.getUsername());
			dataRow.createCell(1).setCellValue(seguidor.getEmail());
			dataRow.createCell(2).setCellValue(seguidor.getPresentacion());
			i++;
		}

		String sep = System.getProperty("os.name").startsWith("Windows") ? "\\" : "/";
		String ruta = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
		FileOutputStream archivo = new FileOutputStream(ruta + sep + "AppPhotoFollowers.xls");
		workbook.write(archivo);
		archivo.close();
		JOptionPane.showMessageDialog(framePrincipal, "El Excel se guardó correctamente en la ruta \"" + ruta + "\"",
				"PDF generated!", JOptionPane.INFORMATION_MESSAGE);
	}

}
