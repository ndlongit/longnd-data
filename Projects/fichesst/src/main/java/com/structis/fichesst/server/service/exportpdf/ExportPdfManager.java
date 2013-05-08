package com.structis.fichesst.server.service.exportpdf;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfDestination;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPRow;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.structis.fichesst.server.util.HtmlTableStyleParser;
import com.structis.fichesst.shared.util.Constants;

public abstract class ExportPdfManager{
	protected Document document = new Document();
	PdfWriter pdfWriter;
	OutputStream stream;
	private String headerLogoFileName = "erisklogo";
	private String footerLogoFileName = "erisklogo";
	protected static Font titleL1BoldFont ;
	protected static Font titleL2BoldFont ;
	protected static Font contentBoldGrayFont ;
	protected static Font contentNormalFont  ;
	protected static Font contentItalicFont  ;
	protected static Font contentNormalGrayFont  ;
	protected static Font contentBoldFont ;
	protected static Font symBolFont  ;
	private String fontLocal = "com/structis/fichesst/server/font/";
	protected CustomHeaderFooter headerFooterPageEvent;
	protected SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
	protected NumberFormat d2f;
	public final static String LABEL_SEP = " : ";
	public final static String EMPTY_STR = "";
	public final static String LINE_BREAK = "\n";
	public final static int BREAK_BEFORE = 1;
	public final static int BREAK_AFTER = 2;
	public final static int NO_BREAK = 0;
	protected Locale localtion = Locale.FRENCH;
	public ExportPdfManager(OutputStream stream,Locale locale) throws DocumentException, IOException {
		this.stream = stream;
		if(locale != null)
			this.localtion = locale;
		d2f = NumberFormat.getNumberInstance(localtion);
		d2f.setMaximumFractionDigits(Constants.MAX_FRACTION_DIGITS);

		contentBoldGrayFont = FontFactory.getFont(fontLocal + "CALIBRIB.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12);
		contentBoldGrayFont.setColor(128, 128, 128);
		symBolFont = FontFactory.getFont(fontLocal + "WINGDNG2.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12);
		contentItalicFont= FontFactory.getFont(fontLocal + "CALIBRII.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12);
		titleL1BoldFont = FontFactory.getFont(fontLocal + "CALIBRIB.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12);
		titleL2BoldFont = FontFactory.getFont(fontLocal + "CALIBRIB.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12);
		contentBoldFont = FontFactory.getFont(fontLocal + "CALIBRIB.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 9);
		contentNormalFont = FontFactory.getFont(fontLocal + "CALIBRI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8.5f);
		contentNormalGrayFont = FontFactory.getFont(fontLocal + "CALIBRI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8.5f);
		contentNormalGrayFont.setColor(128, 128, 128);
		Font f = FontFactory.getFont(fontLocal + "CALIBRI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12);
		headerFooterPageEvent = new CustomHeaderFooter(f.getBaseFont());


	}
	public void setHeaderLogoFileName(String headerLogoFileName) {
		this.headerLogoFileName = headerLogoFileName;
	}
	public CustomHeaderFooter getPageEvent() {
		return headerFooterPageEvent;
	}

	public void init() throws DocumentException, MalformedURLException {
		pdfWriter = PdfWriter.getInstance(document, this.stream);		
		headerFooterPageEvent.setFontBase(contentNormalFont.getBaseFont());
		headerFooterPageEvent.SetHeader(this.headerLogoFileName, document);
		headerFooterPageEvent.SetFooter(this.footerLogoFileName, document);
		pdfWriter.setPageEvent(headerFooterPageEvent);
		document.open();
		//set default zoom 100% when open doc
		PdfAction action = PdfAction.gotoLocalPage(1, new PdfDestination(PdfDestination.XYZ, -1, -1, 1f), pdfWriter);
		pdfWriter.setOpenAction(action); 
	}
	public void lineBreak() {
		try {
			this.document.add(new Phrase("\n"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	public void addContent(Element e) throws DocumentException {
		this.document.add(e);
	}
	public void addText(String text, Font font) throws DocumentException {
		if (font == null) {
			font = contentNormalFont; 
		}
		this.document.add(new Phrase(text, font));
	}
	
	public void finish() {
		this.document.close();		
	}
	public abstract void render() throws DocumentException;
	public void process() throws MalformedURLException, DocumentException, IOException {
		init();
		try {
			render();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		finish();		
	}

	public void addCellsToTable(PdfPTable table, PdfPCell[] cells) {
		for (PdfPCell pdfPCell : cells) {
			table.addCell(pdfPCell);
		}
	}
	public void setFooterLogoFileName(String footerLogoFileName) {
		this.footerLogoFileName = footerLogoFileName;
	}
	public String getFooterLogoFileName() {
		return footerLogoFileName;
	}

	@SuppressWarnings("unchecked")
	public void addElementFromRtf(PdfPCell target, String rtfData, Font font,int width) {		
		String htmlStyle = rtfData;
		String htmlStr = rtfData.replace("LINE-HEIGHT: normal;", "");/*.replaceAll("style=\".*?\"", "")*/;
		try {
			Phrase tmpPhrase = new Phrase();
			if (rtfData != null && !rtfData.isEmpty()) {
				List<Element> elist =  HTMLWorker.parseToList(new StringReader(htmlStr), null);
				for (Element element : elist) {
					tmpPhrase.clear();
					if (element instanceof PdfPTable) {						
						List<PdfPRow> rows = ((PdfPTable)element).getRows();
						List<Map<String,String>> style = HtmlTableStyleParser.getStyle(htmlStyle);
						int i = 0;
						for (PdfPRow pdfPRow : rows) {
							if (pdfPRow != null) {
								PdfPCell[] pdfpCells = pdfPRow.getCells();
								if (pdfpCells == null || pdfpCells.length == 0) {
									continue;
								}
								for (PdfPCell pdfpCell : pdfpCells) {
									if (pdfpCell != null) {
										ArrayList<Chunk> cList = pdfpCell.getChunks();
										for (Chunk chunk : cList) {
											Font setFont = FontFactory.getFont(fontLocal + "CALIBRI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8.5f);
											/*processChunkHtml(htmlStr, chunk, setFont);
											htmlStr = htmlStr.replaceFirst(chunk.getContent(), "<span />");*/
											setFont.setColor(chunk.getFont().getColor());
											setFont.setSize(chunk.getFont().getSize());
											setFont.setStyle(chunk.getFont().getStyle());
											chunk.setFont(setFont);
										}										
										pdfpCell.setBorderWidth(0.1f);
										pdfpCell.setPadding(5);
										pdfpCell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
										pdfpCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
										//pdfpCell.setBackgroundColor(new Color(221,221,221));
										if(style != null){
											if(i < style.size()){
												if(style.get(i) != null){
													String bgColor = style.get(i).get(HtmlTableStyleParser.BACKGROUND);
													if(bgColor != null){
														pdfpCell.setBackgroundColor(HtmlTableStyleParser.hex2Rgb(bgColor));
													}else{
														bgColor = style.get(i).get(HtmlTableStyleParser.BACKGROUNDCOLOR);
														if(bgColor != null){
															pdfpCell.setBackgroundColor(HtmlTableStyleParser.hex2Rgb(bgColor));
														}
													}
												}
											}
										}
										i++;
									}
								}
							}
						}		
						target.addElement(Chunk.NEWLINE);
						//tmpPhrase.add(element);
						//((PdfPTable)element).setSpacingBefore(5f);
						//target.addElement(element);	
						((PdfPTable)element).setTotalWidth(width);
						((PdfPTable)element).setLockedWidth(true);
						target.addElement(element);
					} else if (element instanceof Paragraph) {						
						if (font != null) {							
							ArrayList<Chunk> cList = ((Paragraph) element).getChunks();
							//((Paragraph) element).setAlignment(PdfPCell.ALIGN_CENTER);
							for (Chunk chunk : cList) {
								Font setFont = FontFactory.getFont(fontLocal + "CALIBRI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8.5f);
								
								processChunkHtml(htmlStr, chunk, setFont);
								htmlStr = replaceContent(htmlStr, chunk.getContent());
								/*htmlStr = htmlStr.replaceFirst(chunk.getContent(), "<span />");*/
								/*setFont.setColor(chunk.getFont().getColor());
								setFont.setSize(chunk.getFont().getSize());
								setFont.setStyle(chunk.getFont().getStyle());
								chunk.getAttributes();
								chunk.setFont(setFont);*/
							}					
						}						
						//tmpPhrase.add(element);
						target.addElement(element);
					} else if (element instanceof Phrase) {
						if (font != null) {
							ArrayList<Chunk> cList = ((Phrase) element).getChunks();
							for (Chunk chunk : cList) {
								Font setFont = FontFactory.getFont(fontLocal + "CALIBRI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8.5f);
								processChunkHtml(htmlStr, chunk, setFont);
								htmlStr = replaceContent(htmlStr, chunk.getContent());
								/*htmlStr = htmlStr.replaceFirst(chunk.getContent(), "<span />");*/
								/*setFont.setColor(chunk.getFont().getColor());
								setFont.setSize(chunk.getFont().getSize());
								setFont.setStyle(chunk.getFont().getStyle());
								chunk.setFont(setFont);*/
							}					
						}
						target.addElement(/*tmpPhrase*/element);
					} else if (element instanceof Chunk) {
						if (font != null) {
							Font setFont = FontFactory.getFont(fontLocal + "CALIBRI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8.5f);
							processChunkHtml(htmlStr, (Chunk)element, setFont);
							htmlStr = replaceContent(htmlStr, ((Chunk)element).getContent());
							/*htmlStr = htmlStr.replaceFirst(((Chunk)element).getContent(), "<span />");*/
							/*setFont.setColor(((Chunk) element).getFont().getColor());
							setFont.setColor(((Chunk) element).getFont().getColor());
							setFont.setSize(((Chunk) element).getFont().getSize());
							setFont.setStyle(((Chunk) element).getFont().getStyle());
							((Chunk) element).setFont(setFont);*/		
						}
						//tmpPhrase.add(element);
						
						target.addElement(element);
					}else if(element instanceof com.lowagie.text.List){
						com.lowagie.text.List list = (com.lowagie.text.List)element;
						ArrayList<Chunk> cList = list.getChunks();
						for (Chunk chunk : cList) {
							Font setFont = FontFactory.getFont(fontLocal + "CALIBRI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8.5f);
							processChunkHtml(htmlStr, chunk, setFont);
							htmlStr = replaceContent(htmlStr, chunk.getContent());
							/*htmlStr = htmlStr.replaceFirst(chunk.getContent(), "<span />");*/
							/*setFont.setColor(chunk.getFont().getColor());
							setFont.setSize(chunk.getFont().getSize());
							setFont.setStyle(chunk.getFont().getStyle());
							chunk.getAttributes();
							chunk.setFont(setFont);*/
						}
						target.addElement(list);
					}else{
						Phrase p = new Phrase();
						p.add(element);
						ArrayList<Chunk> cList = p.getChunks();
						for (Chunk chunk : cList) {
							Font setFont = FontFactory.getFont(fontLocal + "CALIBRI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8.5f);
							processChunkHtml(htmlStr, chunk, setFont);
							htmlStr = replaceContent(htmlStr, chunk.getContent());
							/*htmlStr = htmlStr.replaceFirst(chunk.getContent(), "<span />");*/
							/*setFont.setColor(chunk.getFont().getColor());
							setFont.setSize(chunk.getFont().getSize());
							setFont.setStyle(chunk.getFont().getStyle());
							chunk.setFont(setFont);*/
						}	
						target.addElement(element);
					}
				}				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void addElementFromRtf(Phrase target, String rtfData, Font font) {
		String htmlStyle = rtfData;
		String htmlStr = rtfData.replace("LINE-HEIGHT: normal;", "");/*.replaceAll("style=\".*?\"", "")*/;
		try {			
			if (rtfData != null && !rtfData.isEmpty()) {
				StyleSheet styles = new StyleSheet(); 
				List<Element> elist =  HTMLWorker.parseToList(new StringReader(htmlStr), styles);
				for (Element element : elist) {
					if (element instanceof Paragraph) {
						if (font != null) {
							ArrayList<Chunk> cList = ((Paragraph) element).getChunks();
							for (Chunk chunk : cList) {
								Font setFont = FontFactory.getFont(fontLocal + "CALIBRI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8.5f);
								setFont.setColor(chunk.getFont().getColor());
								setFont.setSize(chunk.getFont().getSize());
								setFont.setStyle(chunk.getFont().getStyle());
								chunk.setFont(setFont);
							}					
						}						
						target.add(element);
					} else if (element instanceof Phrase) {
						if (font != null) {
							ArrayList<Chunk> cList = ((Phrase) element).getChunks();
							for (Chunk chunk : cList) {
								Font setFont = FontFactory.getFont(fontLocal + "CALIBRI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8.5f);
								setFont.setColor(chunk.getFont().getColor());
								setFont.setSize(chunk.getFont().getSize());
								setFont.setStyle(chunk.getFont().getStyle());
								chunk.setFont(setFont);
							}					
						}
						target.add(element);
					} else if (element instanceof Chunk) {
						if (font != null) {
							Font setFont = FontFactory.getFont(fontLocal + "CALIBRI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8.5f);
							setFont.setColor(((Chunk) element).getFont().getColor());							
							setFont.setSize(((Chunk) element).getFont().getSize());
							setFont.setStyle(((Chunk) element).getFont().getStyle());
							((Chunk) element).setFont(setFont);
						}
						target.add(element);
					} else if (element instanceof PdfPTable) {
						List<PdfPRow> rows = ((PdfPTable)element).getRows();
						List<Map<String,String>> style = HtmlTableStyleParser.getStyle(htmlStyle);
						int i = 0;
						for (PdfPRow pdfPRow : rows) {
							if (pdfPRow != null) {
								PdfPCell[] pdfpCells = pdfPRow.getCells();
								if (pdfpCells == null || pdfpCells.length == 0) {
									continue;
								}
								for (PdfPCell pdfpCell : pdfpCells) {
									if (pdfpCell != null) {
										ArrayList<Chunk> cList = pdfpCell.getChunks();
										for (Chunk chunk : cList) {
											Font setFont = FontFactory.getFont(fontLocal + "CALIBRI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8.5f);
											setFont.setColor(chunk.getFont().getColor());
											setFont.setSize(chunk.getFont().getSize());
											setFont.setStyle(chunk.getFont().getStyle());
											chunk.setFont(setFont);
										}										
										pdfpCell.setBorderWidth(0.1f);		
										pdfpCell.setPadding(5);
										pdfpCell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
										pdfpCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
										if(style != null){
											if(i < style.size()){
												if(style.get(i) != null){
													String bgColor = style.get(i).get(HtmlTableStyleParser.BACKGROUND);
													if(bgColor != null){
														pdfpCell.setBackgroundColor(HtmlTableStyleParser.hex2Rgb(bgColor));
													}
												}
											}
										}
										i++;
									}
								}
							}
						}	
						//((PdfPTable)element).setSpacingBefore(5f);
						((PdfPTable)element).setTotalWidth(500);
						((PdfPTable)element).setLockedWidth(true);
						target.add(Chunk.NEWLINE);
						target.add(element);
					}else{
						Paragraph p = new Paragraph();
						p.add((Element)element);
						ArrayList<Chunk> cList = p.getChunks();
						for (Chunk chunk : cList) {
							Font setFont = FontFactory.getFont(fontLocal + "CALIBRI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8.5f);
							setFont.setColor(chunk.getFont().getColor());
							setFont.setSize(chunk.getFont().getSize());
							setFont.setStyle(chunk.getFont().getStyle());
							chunk.setFont(setFont);
						}	
						target.add(element);
					}
				}				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void addATextLabelContent(String label, Font labelFont, String content, Font contentFont, int breakPosition) {
		if (content != null && !content.trim().isEmpty()) {
			Phrase tmpPhrase = new Phrase();
			tmpPhrase.add(new Chunk(label, labelFont));
			tmpPhrase.add(new Chunk(content, contentFont));

			try {
				if (breakPosition == BREAK_BEFORE) {
					lineBreak();
				}
				addContent(tmpPhrase);
				if (breakPosition == BREAK_AFTER) {
					lineBreak();
				}
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public void writeTextAt(float x, float y, String text, Font font) {
		
			PdfContentByte cb = pdfWriter.getDirectContent();
			cb.saveState();
			cb.setFontAndSize(font.getBaseFont(), font.getSize());
			cb.beginText();
			cb.setTextMatrix(x , y);
			cb.showText(text);
			cb.endText();
			cb.restoreState();
	}
	/**
	 * 
	 * @param table
	 * @param content
	 * @param border
	 * @param colSpan
	 * @param padding
	 * [0]: for all
	 * [1]: top
	 * [2]: bottom
	 * [3]: left
	 * [4]: right
	 */
	public void addTableCell(PdfPTable table, Element content, int border, int colSpan, Float... padding) {
		PdfPCell contentCell = new PdfPCell();
		contentCell.setColspan(colSpan);
		contentCell.setBorder(border);
		if (padding != null) {
			int len = padding.length;
			if (padding[0] != null) {
				contentCell.setPadding(padding[0].floatValue());				
			}
			if (len > 1 && padding[1] != null) {
				contentCell.setPaddingTop(padding[1].floatValue());				
			}
			if (len > 2 && padding[2] != null) {
				contentCell.setPaddingBottom(padding[2].floatValue());				
			}
			if (len > 3 && padding[3] != null) {
				contentCell.setPaddingLeft(padding[3].floatValue());				
			}
			if (len > 4 && padding[4] != null) {
				contentCell.setPaddingLeft(padding[4].floatValue());				
			}
		}
		contentCell.addElement(content);
		table.addCell(contentCell);
	}
	public void setLocaltion(Locale local) {
		this.localtion = local;
	}
	
	public static String processTagWrapText(String html, String text){
		String text2 = ">"+ text;
		while(html.contains(text2)){
			if(text2.contains("/")){
				break;
			}
			int i = html.indexOf(text2);
			while(true){
				i--;
				char c = html.charAt(i);
				text2 = String.valueOf(c) + text2;
				if(c == '<'){
					break;
				}
			}
			text2 = ">"+ text2;
		}
			
		text2 = text2.replaceFirst(">", "");
		return text2;
	}
	
	public Chunk processChunkHtml(String html, Chunk chunk, Font setFont){
		/*html = html.replaceAll("&ndash;", "-");*/
		String wrapHtml = processTagWrapText(html, chunk.getContent());
		
		setFont.setColor(chunk.getFont().getColor());
		setFont.setStyle(chunk.getFont().getStyle());
		setFont.setSize(chunk.getFont().getSize());
		/*setSize(setFont, wrapHtml);*/
		setBackgroundColor(chunk, wrapHtml);
		chunk.setFont(setFont);
		return chunk;
	}
	
	public void setBackgroundColor(Chunk chunk, String wrapHtml){
		if(wrapHtml.contains("background-color")){
			int i = wrapHtml.indexOf("background-color") + "background-color".length();
			String color = "";
			for( ; i <= wrapHtml.length(); i ++){
				char c = wrapHtml.charAt(i);
				if(c == '"' || c == ';'){
					break;
				}
				color += String.valueOf(wrapHtml.charAt(i));
			}
			color = color.replaceAll(":", "").replaceAll(";", "").trim();
			chunk.setBackground(HtmlTableStyleParser.hex2Rgb(color));
		}
	}
	
	public void setSize(Font font, String wrapHtml){
		if(wrapHtml.contains("font-size")){
			int i = wrapHtml.indexOf("font-size") + "font-size".length();
			String size = "";
			for( ; i <= wrapHtml.length(); i ++){
				char c = wrapHtml.charAt(i);
				if(c == '"' || c == ';'){
					break;
				}
				size += String.valueOf(wrapHtml.charAt(i));
			}
			size = size.replaceAll(":", "").replaceAll(";", "").replaceAll("px", "").trim();
			font.setSize(Float.valueOf(size));
		}
	}

	public String replaceContent(String htmlStr, String content){
		if(htmlStr == null || content == null){
			return "";
		}
		if(content.contains("*")){
			content = content.replace("*", "\\*"); 
		}
		htmlStr = htmlStr.replaceFirst(content, "<span />");
		return htmlStr;
	}
}
