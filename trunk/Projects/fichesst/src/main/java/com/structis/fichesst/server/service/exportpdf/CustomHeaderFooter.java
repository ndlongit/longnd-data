package com.structis.fichesst.server.service.exportpdf;
import java.io.IOException;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;


public class CustomHeaderFooter extends PdfPageEventHelper {
	/** Alternating phrase for the header. */
	protected PdfTemplate total;
	protected BaseFont helv;
	private String pageOfPage;
	private String footerText;
	private int editTextAlign = Chunk.ALIGN_CENTER;
	protected int fontSize;
	private float maxheaderLogoHeight = 87;	
	private float maxfooterLogoHeight = 87;
	

	private float[] headerLogoPortrait = {80,3};
	private float[] footerLogoPortrait = {90,5};

	
	private float[] headerLogoLandscape = {80,3};
	private float[] footerLogoLandscape = {80,5};

	
	int pagenumber;
	Image headerLogo, footerLogo;
	Rectangle recHeader, recFooter;
	int topMargin = 20;
	int bottomMargin = 30;
	public CustomHeaderFooter(BaseFont fontBase) {
		pageOfPage = "%s /";
		fontSize = 12;
		footerText = "Edité le ";
		this.helv = fontBase;
		/*
		try {
			helv = BaseFont.createFont(BaseFont.HELVETICA,
					BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
		} catch (Exception e) {
			throw new ExceptionConverter(e);
		}*/
	}
	public void setFooterText(String footerText) {
		this.footerText = footerText;
	}
	public void setPageOfPageText(String pageOfNumberText) {
		pageOfPage = pageOfNumberText;
	}
	public void setFontBase(BaseFont f) {
		this.helv = f;
	}
	public void setEditTextAlign(int chunkAlign) {
		editTextAlign = chunkAlign;
	}

	/**
	 * Initialize one of the headers.
	 * @see com.itextpdf.text.pdf.PdfPageEventHelper#onOpenDocument(
	 *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
	 */
	public void onOpenDocument(PdfWriter writer, Document document) {
		total = writer.getDirectContent().createTemplate(100, 100);
		total.setBoundingBox(new Rectangle(-20, -20, 100, 100));


	}

	public void SetHeader(String path, Document document) {

		try {

			int spacing = 2;
			recHeader = new Rectangle(document.left(), document.top() + 20, document.right(), document.getPageSize().getTop() - topMargin);
			maxheaderLogoHeight = recHeader.getHeight() - spacing * 2;
			headerLogo = Image.getInstance(path);
			headerLogo.setAlignment(Image.LEFT);
			//headerLogo.scaleToFit(recHeader.getWidth(), maxheaderLogoHeight);
			if(path.contains("landscape")){
				headerLogo.scaleToFit(recHeader.getWidth()+headerLogoLandscape[0], maxheaderLogoHeight+headerLogoLandscape[1]);
			}else{
				headerLogo.scaleToFit(recHeader.getWidth()+headerLogoPortrait[0], maxheaderLogoHeight+headerLogoPortrait[1]);
			}
			headerLogo.setAbsolutePosition(recHeader.getLeft() , recHeader.getBottom() + spacing);

		} catch (IOException e) {

		} catch (BadElementException e) {
			// TODO Auto-generated catch block
		}
	}


	public void SetFooter(String path, Document document) {

		try {

			int spacing = 2;
			recFooter = new Rectangle(document.left(), bottomMargin, document.right(), document.bottom());
			maxfooterLogoHeight = recFooter.getHeight() - spacing * 2;
			footerLogo = Image.getInstance(path);
			footerLogo.setAlignment(Image.LEFT);
			if(path.contains("landscape")){
				footerLogo.scaleToFit(recFooter.getWidth()+footerLogoLandscape[0], maxfooterLogoHeight+footerLogoLandscape[1]);
			}else{
				footerLogo.scaleToFit(recFooter.getWidth()+footerLogoPortrait[0], maxfooterLogoHeight+footerLogoPortrait[1]);
			}
			//footerLogo.scaleToFit(recFooter.getWidth(), maxfooterLogoHeight);
			
			footerLogo.setAbsolutePosition(document.left() + spacing, recFooter.getBottom() + spacing);

		} catch (IOException e) {

		} catch (BadElementException e) {
			// TODO Auto-generated catch block
		}
	}



	/**
	 * Increase the page number.
	 * @see com.itextpdf.text.pdf.PdfPageEventHelper#onStartPage(
	 *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
	 */
	public void onStartPage(PdfWriter writer, Document document) {
		pagenumber++;
	}

	/**
	 * Adds the header and the footer.
	 * @see com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(
	 *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
	 */
	public void onEndPage(PdfWriter writer, Document document) {
		PdfContentByte cb = writer.getDirectContent();

		String textPageOfPage = String.format(pageOfPage, writer.getPageNumber());		
		float textBase = 18;
		float textSize = helv.getWidthPoint(textPageOfPage, fontSize);
		float adjust = helv.getWidthPoint("0", fontSize);
		cb.addTemplate(total, document.right() - adjust, textBase);
		cb.saveState();
		cb.beginText();
		cb.setFontAndSize(helv, fontSize);

		cb.setTextMatrix(document.right() - textSize - adjust*2 , textBase);
		cb.showText(textPageOfPage);

		if (editTextAlign == Chunk.ALIGN_LEFT) {
			cb.setTextMatrix(document.left(), textBase);
		} else {
			float editDateTextSize = helv.getWidthPoint(footerText, fontSize);
			cb.setTextMatrix((document.right() - document.left()- editDateTextSize)/2, textBase);
		} 
		cb.showText(footerText);

		cb.endText();

		if (headerLogo != null) {

			try {
				cb.addImage(headerLogo);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
			}
		}
		if (recHeader != null) {
			cb.rectangle(recHeader.getLeft(), recHeader.getBottom(), recHeader.getWidth(), recHeader.getHeight());
			cb.setLineWidth(/*0.5f*/0f);
			//cb.stroke();
		}

		if (footerLogo != null) {

			try {
				cb.addImage(footerLogo);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (recFooter != null) {
			cb.rectangle(recFooter.getLeft(), recFooter.getBottom(), recFooter.getWidth(), recFooter.getHeight());
			cb.setLineWidth(/*0.5f*/0f);
			//cb.stroke();
		}

		cb.restoreState();

	}

	public void onCloseDocument(PdfWriter writer, Document document) {

		total.beginText();
		total.setFontAndSize(helv, fontSize);
		total.setTextMatrix(0, 0);
		total.showText(String.valueOf(writer.getPageNumber() - 1));
		total.endText();

	}

}
