package com.spring.boot.rocks.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratePdfReport {

	public static ByteArrayInputStream userReport(List<AppUser> users) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(100);
			table.setWidths(new int[] { 100, 100, 100, 100, 100, 100 });

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Id", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Username", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Useremail", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Userfirstname", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Userlastname", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Useraddress", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			for (AppUser user : users) {

				PdfPCell cell;

				cell = new PdfPCell(new Phrase(user.getId().toString()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(user.getUsername()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(user.getUseremail()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(user.getUserfirstname()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(user.getUserlastname()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(user.getUseraddress()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);

			}

			PdfWriter.getInstance(document, out);
			document.open();
			document.add(table);

			document.close();

		} catch (DocumentException ex) {

			Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	public static ByteArrayInputStream oneuserReport(AppUser user) {

		Document document = new Document(PageSize.A4, 36, 36, 90, 36);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfPTable table = new PdfPTable(12);
			table.setWidthPercentage(100);
			table.setWidths(new int[] { 100, 100, 100, 100, 100, 100, 100 , 100, 100, 100, 100, 100});

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Id", headFont));
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("QR Code", headFont));
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Username", headFont));
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("QR Code", headFont));
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Useremail", headFont));
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("QR Code", headFont));
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Userfirstname", headFont));
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("QR Code", headFont));
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Userlastname", headFont));
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("QR Code", headFont));
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Useraddress", headFont));
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("QR Code", headFont));
			table.addCell(hcell);

			PdfPCell cell;

			cell = new PdfPCell(new Phrase(user.getId().toString()));
			table.addCell(cell);
			table.addCell(getQRBarCode(user.getId().toString(), document));

			cell = new PdfPCell(new Phrase(user.getUsername()));
			table.addCell(cell);
			table.addCell(getQRBarCode(user.getUsername(), document));
			
			cell = new PdfPCell(new Phrase(user.getUseremail()));
			table.addCell(cell);
			table.addCell(getQRBarCode(user.getUseremail(), document));
			
			cell = new PdfPCell(new Phrase(user.getUserfirstname()));
			table.addCell(cell);
			table.addCell(getQRBarCode(user.getUserfirstname(), document));
			
			cell = new PdfPCell(new Phrase(user.getUserlastname()));
			table.addCell(cell);
			table.addCell(getQRBarCode(user.getUserlastname(), document));
			
			cell = new PdfPCell(new Phrase(user.getUseraddress()));
			table.addCell(cell);
			table.addCell(getQRBarCode(user.getUseraddress(), document));
			
			PdfWriter.getInstance(document, out);
			document.open();
			document.add(table);

			document.close();

		} catch (DocumentException ex) {

			Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	public static Image getQRBarCode(String someStr, Document document) throws DocumentException {
		BarcodeQRCode barcodeQRCode = new BarcodeQRCode(someStr, 1000, 1000, null);
		Image codeQrImage = barcodeQRCode.getImage();
		codeQrImage.scaleAbsolute(100, 100);
		document.open();
		document.add(codeQrImage);
		return codeQrImage;
	}

}