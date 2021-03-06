/*
 * $Id: UsingFontFactory.java,v 1.1 2010/04/14 17:50:51 kwart Exp $
 *
 * This code is part of the 'iText Tutorial'.
 * You can find the complete tutorial at the following address:
 * http://itextdocs.lowagie.com/tutorial/
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * itext-questions@lists.sourceforge.net
 */

package com.lowagie.examples.fonts.getting;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Special rendering of Chunks.
 * 
 * @author blowagie
 */

public class UsingFontFactory {

	/**
	 * Special rendering of Chunks.
	 * 
	 * @param args no arguments needed here
	 */
	public static void main(String[] args) {

		System.out.println("Fonts in the FontFactory");

		// step 1: creation of a document-object
		Document document = new Document();
		try {
			// step 2:
			// we create a writer that listens to the document
			PdfWriter.getInstance(document,
					new FileOutputStream("FontFactory.pdf"));

			// step 3: we open the document
			document.open();
			// step 4:
			String name;
			Paragraph p = new Paragraph("Font Families", FontFactory.getFont(FontFactory.HELVETICA, 16f));
			document.add(p);
			FontFactory.registerDirectories();
			TreeSet families = new TreeSet(FontFactory.getRegisteredFamilies());
			int c = 0;
			for (Iterator i = families.iterator(); i.hasNext() && c < 15; ) {
				name = (String) i.next();
				p = new Paragraph(name);
				document.add(p);
				c++;
			}
			document.newPage();
			String quick = "quick brown fox jumps over the lazy dog";
			p = new Paragraph("Fonts", FontFactory.getFont(FontFactory.HELVETICA, 16f));
			for (Iterator i = families.iterator(); i.hasNext() && c > 0; ) {
				name = (String) i.next();
				p = new Paragraph(name);
				document.add(p);
				try {
					p = new Paragraph(quick, FontFactory.getFont(name, BaseFont.WINANSI, BaseFont.EMBEDDED));
					document.add(p);
				}
				catch (Exception e) {
					document.add(new Paragraph(e.getMessage()));
				}
				c--;
			}
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}

		// step 5: we close the document
		document.close();
	}
}