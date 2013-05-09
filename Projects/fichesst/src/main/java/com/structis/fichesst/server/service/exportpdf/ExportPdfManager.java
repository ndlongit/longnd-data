package com.structis.fichesst.server.service.exportpdf;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.support.ResourceBundleMessageSource;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfDestination;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPRow;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import com.structis.fichesst.server.bean.domain.FicheSt;
import com.structis.fichesst.server.util.AppUtil;
import com.structis.fichesst.server.util.HtmlTableStyleParser;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.LotDto;
import com.structis.fichesst.shared.util.Constants;

public abstract class ExportPdfManager {

	protected static SimpleDateFormat dateFormat;
	protected static DecimalFormat numberFormat2;
	protected static DecimalFormat integerFormat;

	protected Document document = new Document();
	protected Locale localtion;
	private PdfWriter pdfWriter;
	private String headerLogoFileName = "erisklogo";
	private String footerLogoFileName = "erisklogo";
	protected static Font titleL1BoldFont;
	protected static Font titleL2BoldFont;
	protected static Font contentBoldGrayFont;
	protected static Font contentNormalFont;
	protected static Font contentItalicFont;
	protected static Font contentNormalGrayFont;
	public static Font contentBoldFont;
	protected static Font symBolFont;
	private final String fontLocal = "com/structis/fichesst/server/font/";
	protected CustomHeaderFooter headerFooterPageEvent;
	public final static String LABEL_SEP = " : ";
	public final static String EMPTY_STR = "";
	public final static String LINE_BREAK = "\n";
	public final static int BREAK_BEFORE = 1;
	public final static int BREAK_AFTER = 2;
	public final static int NO_BREAK = 0;

	public static final float NORMAL_FONT_SIZE = 9f;
	public static final float BOLD_FONT_SIZE = 12f;

	protected ResourceBundleMessageSource messageSource;

	protected File pdfFile = createTempFile();
	private OutputStream stream = new FileOutputStream(pdfFile);
	protected String generaleInformation;

	protected static final Color HEADER_COLOR_1 = new Color(Constants.RED, Constants.GREEN, Constants.BLUE);

	protected static final Color HEADER_COLOR_2 = new Color(200, 200, 200);

	private static final float[] TABLE_WIDTHS_1 = new float[] { 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f,
			2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f,
			2.5f, 2.5f, 2.5f, 2.5f };

	private static final int NUM_COLUMNS = TABLE_WIDTHS_1.length;

	public OutputStream getStream() {
		return stream;
	}

	public void setStream(OutputStream stream) {
		this.stream = stream;
	}

	public ExportPdfManager(Locale locale) throws DocumentException, IOException {
		if (locale != null) {
			this.localtion = locale;
		} else {
			localtion = Locale.FRENCH;
		}

		numberFormat2 = (DecimalFormat) NumberFormat.getNumberInstance(this.localtion);
		numberFormat2.applyPattern(Constants.NUMBER_PATTERN);
		integerFormat = (DecimalFormat) NumberFormat.getNumberInstance(this.localtion);
		integerFormat.applyPattern(Constants.INTEGER_PATTERN);
		dateFormat = new SimpleDateFormat(Constants.DATE_PATTERN);

		// landscape
		document.setPageSize(PageSize.A4.rotate());

		contentBoldGrayFont = getFont("CALIBRIB.TTF", BOLD_FONT_SIZE);
		contentBoldGrayFont.setColor(128, 128, 128);
		symBolFont = getFont(fontLocal + "WINGDNG2.TTF", BOLD_FONT_SIZE);
		contentItalicFont = getFont("CALIBRII.TTF", BOLD_FONT_SIZE);
		titleL1BoldFont = getFont("CALIBRIB.TTF", BOLD_FONT_SIZE);
		titleL2BoldFont = getFont("CALIBRIB.TTF", BOLD_FONT_SIZE);
		contentBoldFont = getFont("CALIBRIB.TTF", 7f);
		contentNormalFont = getFont("CALIBRI.TTF", 7f);
		contentNormalGrayFont = getFont("CALIBRI.TTF", 8.5f);
		contentNormalGrayFont.setColor(128, 128, 128);
		Font f = getFont("CALIBRI.TTF", BOLD_FONT_SIZE);
		headerFooterPageEvent = new CustomHeaderFooter(f.getBaseFont());
	}

	public Font getFont(String fontName, float fontSize) {
		return FontFactory.getFont(fontLocal + fontName, BaseFont.IDENTITY_H, fontSize);
	}

	public void setHeaderLogoFileName(String headerLogoFileName) {
		this.headerLogoFileName = headerLogoFileName;
	}

	public CustomHeaderFooter getPageEvent() {
		return headerFooterPageEvent;
	}

	public void init() throws DocumentException, MalformedURLException {
		pdfWriter = PdfWriter.getInstance(this.document, this.stream);
		headerFooterPageEvent.setFontBase(contentNormalFont.getBaseFont());
		headerFooterPageEvent.SetHeader(this.headerLogoFileName, document);
		headerFooterPageEvent.SetFooter(this.footerLogoFileName, document);
		pdfWriter.setPageEvent(headerFooterPageEvent);
		document.open();
		// set default zoom 100% when open doc
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

	public void newPage() {
		this.document.newPage();
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

	public File process() throws Exception {
		try {
			init();
			File f = render();
			return f;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			finish();
		}
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
	public void addElementFromRtf(PdfPCell target, String rtfData, Font font, int width) {
		String htmlStyle = rtfData;
		String htmlStr = rtfData.replace("LINE-HEIGHT: normal;", "");
		try {
			Phrase tmpPhrase = new Phrase();
			if (rtfData != null && !rtfData.isEmpty()) {
				List<Element> elist = HTMLWorker.parseToList(new StringReader(htmlStr), null);
				for (Element element : elist) {
					tmpPhrase.clear();
					if (element instanceof PdfPTable) {
						List<PdfPRow> rows = ((PdfPTable) element).getRows();
						List<Map<String, String>> style = HtmlTableStyleParser.getStyle(htmlStyle);
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
											Font setFont = FontFactory.getFont(fontLocal + "CALIBRI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED,
													8.5f);
											setFont.setColor(chunk.getFont().getColor());
											setFont.setSize(chunk.getFont().getSize());
											setFont.setStyle(chunk.getFont().getStyle());
											chunk.setFont(setFont);
										}
										pdfpCell.setBorderWidth(0.1f);
										pdfpCell.setPadding(5);
										pdfpCell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
										pdfpCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
										// pdfpCell.setBackgroundColor(new Color(221,221,221));
										if (style != null) {
											if (i < style.size()) {
												if (style.get(i) != null) {
													String bgColor = style.get(i).get(HtmlTableStyleParser.BACKGROUND);
													if (bgColor != null) {
														pdfpCell.setBackgroundColor(HtmlTableStyleParser.hex2Rgb(bgColor));
													} else {
														bgColor = style.get(i).get(HtmlTableStyleParser.BACKGROUNDCOLOR);
														if (bgColor != null) {
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
						((PdfPTable) element).setTotalWidth(width);
						((PdfPTable) element).setLockedWidth(true);
						target.addElement(element);
					} else if (element instanceof Paragraph) {
						if (font != null) {
							ArrayList<Chunk> cList = ((Paragraph) element).getChunks();
							for (Chunk chunk : cList) {
								Font setFont = FontFactory.getFont(fontLocal + "CALIBRI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8.5f);

								processChunkHtml(htmlStr, chunk, setFont);
								htmlStr = replaceContent(htmlStr, chunk.getContent());
							}
						}
						// tmpPhrase.add(element);
						target.addElement(element);
					} else if (element instanceof Phrase) {
						if (font != null) {
							ArrayList<Chunk> cList = ((Phrase) element).getChunks();
							for (Chunk chunk : cList) {
								Font setFont = FontFactory.getFont(fontLocal + "CALIBRI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8.5f);
								processChunkHtml(htmlStr, chunk, setFont);
								htmlStr = replaceContent(htmlStr, chunk.getContent());
							}
						}
						target.addElement(/* tmpPhrase */element);
					} else if (element instanceof Chunk) {
						if (font != null) {
							Font setFont = FontFactory.getFont(fontLocal + "CALIBRI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8.5f);
							processChunkHtml(htmlStr, (Chunk) element, setFont);
							htmlStr = replaceContent(htmlStr, ((Chunk) element).getContent());
						}
						// tmpPhrase.add(element);

						target.addElement(element);
					} else if (element instanceof com.lowagie.text.List) {
						com.lowagie.text.List list = (com.lowagie.text.List) element;
						ArrayList<Chunk> cList = list.getChunks();
						for (Chunk chunk : cList) {
							Font setFont = FontFactory.getFont(fontLocal + "CALIBRI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8.5f);
							processChunkHtml(htmlStr, chunk, setFont);
							htmlStr = replaceContent(htmlStr, chunk.getContent());
						}
						target.addElement(list);
					} else {
						Phrase p = new Phrase();
						p.add(element);
						ArrayList<Chunk> cList = p.getChunks();
						for (Chunk chunk : cList) {
							Font setFont = FontFactory.getFont(fontLocal + "CALIBRI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8.5f);
							processChunkHtml(htmlStr, chunk, setFont);
							htmlStr = replaceContent(htmlStr, chunk.getContent());
						}
						target.addElement(element);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void addElementFromRtf(Phrase target, String rtfData, Font font) {
		String htmlStyle = rtfData;
		String htmlStr = rtfData.replace("LINE-HEIGHT: normal;", "");/* .replaceAll("style=\".*?\"", "") */
		;
		try {
			if (rtfData != null && !rtfData.isEmpty()) {
				StyleSheet styles = new StyleSheet();
				List<Element> elist = HTMLWorker.parseToList(new StringReader(htmlStr), styles);
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
						List<PdfPRow> rows = ((PdfPTable) element).getRows();
						List<Map<String, String>> style = HtmlTableStyleParser.getStyle(htmlStyle);
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
											Font setFont = FontFactory.getFont(fontLocal + "CALIBRI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED,
													8.5f);
											setFont.setColor(chunk.getFont().getColor());
											setFont.setSize(chunk.getFont().getSize());
											setFont.setStyle(chunk.getFont().getStyle());
											chunk.setFont(setFont);
										}
										pdfpCell.setBorderWidth(0.1f);
										pdfpCell.setPadding(5);
										pdfpCell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
										pdfpCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
										if (style != null) {
											if (i < style.size()) {
												if (style.get(i) != null) {
													String bgColor = style.get(i).get(HtmlTableStyleParser.BACKGROUND);
													if (bgColor != null) {
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
						// ((PdfPTable)element).setSpacingBefore(5f);
						((PdfPTable) element).setTotalWidth(500);
						((PdfPTable) element).setLockedWidth(true);
						target.add(Chunk.NEWLINE);
						target.add(element);
					} else {
						Paragraph p = new Paragraph();
						p.add(element);
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
				e.printStackTrace();
			}

		}
	}

	public void writeTextAt(float x, float y, String text, Font font) {

		PdfContentByte cb = pdfWriter.getDirectContent();
		cb.saveState();
		cb.setFontAndSize(font.getBaseFont(), font.getSize());
		cb.beginText();
		cb.setTextMatrix(x, y);
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
	 *            [0]: for all [1]: top [2]: bottom [3]: left [4]: right
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

	public void setLocaltion(Locale locale) {
		this.localtion = locale;
	}

	public static String processTagWrapText(String html, String text) {
		String text2 = ">" + text;
		while (html.contains(text2)) {
			if (text2.contains("/")) {
				break;
			}
			int i = html.indexOf(text2);
			while (true) {
				i--;
				char c = html.charAt(i);
				text2 = String.valueOf(c) + text2;
				if (c == '<') {
					break;
				}
			}
			text2 = ">" + text2;
		}

		text2 = text2.replaceFirst(">", "");
		return text2;
	}

	public Chunk processChunkHtml(String html, Chunk chunk, Font setFont) {
		/* html = html.replaceAll("&ndash;", "-"); */
		String wrapHtml = processTagWrapText(html, chunk.getContent());

		setFont.setColor(chunk.getFont().getColor());
		setFont.setStyle(chunk.getFont().getStyle());
		setFont.setSize(chunk.getFont().getSize());
		/* setSize(setFont, wrapHtml); */
		setBackgroundColor(chunk, wrapHtml);
		chunk.setFont(setFont);
		return chunk;
	}

	public void setBackgroundColor(Chunk chunk, String wrapHtml) {
		if (wrapHtml.contains("background-color")) {
			int i = wrapHtml.indexOf("background-color") + "background-color".length();
			String color = "";
			for (; i <= wrapHtml.length(); i++) {
				char c = wrapHtml.charAt(i);
				if (c == '"' || c == ';') {
					break;
				}
				color += String.valueOf(wrapHtml.charAt(i));
			}
			color = color.replaceAll(":", "").replaceAll(";", "").trim();
			chunk.setBackground(HtmlTableStyleParser.hex2Rgb(color));
		}
	}

	public void setSize(Font font, String wrapHtml) {
		if (wrapHtml.contains("font-size")) {
			int i = wrapHtml.indexOf("font-size") + "font-size".length();
			String size = "";
			for (; i <= wrapHtml.length(); i++) {
				char c = wrapHtml.charAt(i);
				if (c == '"' || c == ';') {
					break;
				}
				size += String.valueOf(wrapHtml.charAt(i));
			}
			size = size.replaceAll(":", "").replaceAll(";", "").replaceAll("px", "").trim();
			font.setSize(Float.valueOf(size));
		}
	}

	public String replaceContent(String htmlStr, String content) {
		if (htmlStr == null || content == null) {
			return "";
		}
		if (content.contains("*")) {
			content = content.replace("*", "\\*");
		}
		htmlStr = htmlStr.replaceFirst(content, "<span />");
		return htmlStr;
	}

	protected static File createTempFile() throws IOException {
		String tempFileName = "Export_" + Long.toString(System.nanoTime());
		File f = File.createTempFile(tempFileName, ".pdf");
		return f;
	}

	public static String buildGeneralInfo(FicheStDto ficheStDto) {
		if (ficheStDto == null) {
			return "";
		}

		String societte = ficheStDto.getSociete();
		LotDto lot = ficheStDto.getLot();
		String chainter = lot.getChantier().getNom();
		String lotName = lot.getName();
		String lotType = ficheStDto.getLotType().getName();
		String sitravaux = ficheStDto.getIdSiTravaux();
		Double objectif = ficheStDto.getObjectif();
		return append(chainter, lotName, sitravaux, societte, lotType, objectif);
	}

	protected static String append(Object... params) {
		return AppUtil.append(params);
	}

	public void setGeneraleInformation(String generaleInformation) {
		this.generaleInformation = generaleInformation;
	}

	public String getGeneraleInformation() {
		return generaleInformation;
	}

	protected PdfPTable buildGeneralInfoTable() throws DocumentException {
		String arrGeneraleInformation[] = generaleInformation.split(Constants.SEPRATE);
		PdfPTable generaleInfoTable = new PdfPTable(6);
		PdfPCell generaleInfoCell;
		generaleInfoTable.setWidthPercentage(100f);
		String[] resourceKeys = { "FicheST.chantier", "FicheST.lot", "FicheST.foreman", "pdf.synthese.societe", "FicheST.lotType", "FicheST.montant" };
		for (int i = 0; i < arrGeneraleInformation.length; i++) {
			generaleInfoCell = new PdfPCell(new Phrase(getMessage(resourceKeys[i]) + " : ", contentBoldFont));
			generaleInfoCell.setBorder(Rectangle.NO_BORDER);
			switch (i) {
			case 0:
				generaleInfoCell.enableBorderSide(Rectangle.TOP);
				generaleInfoCell.enableBorderSide(Rectangle.LEFT);
				break;
			case 1:
				generaleInfoCell.enableBorderSide(Rectangle.TOP);
				break;
			case 2:
				generaleInfoCell.enableBorderSide(Rectangle.TOP);
				break;
			case 3:
				generaleInfoCell.enableBorderSide(Rectangle.BOTTOM);
				generaleInfoCell.enableBorderSide(Rectangle.LEFT);
				break;
			case 4:
				generaleInfoCell.enableBorderSide(Rectangle.BOTTOM);
				break;
			case 5:
				generaleInfoCell.enableBorderSide(Rectangle.BOTTOM);
				break;
			}
			generaleInfoTable.addCell(generaleInfoCell);

			String generaleInfo = processNullValue(arrGeneraleInformation[i]);
			generaleInfoCell = new PdfPCell(new Phrase(generaleInfo, contentNormalFont));
			generaleInfoCell.setBorder(Rectangle.NO_BORDER);
			if (i == 0 || i == 1 || i == 2) {
				generaleInfoCell.enableBorderSide(Rectangle.TOP);
				if (i == 2) {
					generaleInfoCell.enableBorderSide(Rectangle.RIGHT);
				}
			}
			if (i == 3 || i == 4 || i == 5) {
				generaleInfoCell.enableBorderSide(Rectangle.BOTTOM);
				if (i == 5) {
					generaleInfoCell.enableBorderSide(Rectangle.RIGHT);
				}
			}

			generaleInfoTable.addCell(generaleInfoCell);
		}

		float[] wfGeneraleInformation = { 3, 6, 3, 6, 7, 6 };
		generaleInfoTable.setWidths(wfGeneraleInformation);

		// Add space
		generaleInfoTable.setSpacingAfter(15f);
		return generaleInfoTable;
	}

	public static String processNullValue(Object value) {
		return AppUtil.processNullValue(value);
	}

	public String getMessage(String key) {
		return getMessage(key, null);
	}

	public String getMessage(String key, Object[] params) {
		return messageSource.getMessage(key, params, localtion);
	}

	protected PdfPTable addSummary(String sumarry) throws DocumentException {
		// block dont
		PdfPTable sumaryTable = new PdfPTable(3);
		float[] wf1 = { 10, 30, 20 };
		sumaryTable.setWidthPercentage(100f);
		sumaryTable.setWidths(wf1);
		String[] tmp = sumarry.split(Constants.SEPRATE);
		PdfPCell cell = new PdfPCell(new Phrase("Dont:", contentBoldFont));
		cell.setBorder(0);
		sumaryTable.addCell(cell);
		cell = new PdfPCell(new Phrase(getMessage("pdf.gestiondto.summary.objectif"), contentBoldFont));
		sumaryTable.addCell(cell);

		cell = createContentCell(tmp[0]);
		cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
		sumaryTable.addCell(cell);

		cell = createEmptyCell();
		sumaryTable.addCell(cell);

		sumaryTable.addCell(new PdfPCell(new Phrase(getMessage("pdf.gestiondto.summary.transferts"), contentBoldFont)));
		cell = new PdfPCell(new Phrase(tmp[1], contentNormalFont));
		cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
		sumaryTable.addCell(cell);

		cell = createEmptyCell();
		sumaryTable.addCell(cell);

		sumaryTable.addCell(new PdfPCell(new Phrase(getMessage("pdf.gestiondto.summary.rexettediverses"), contentBoldFont)));

		cell = new PdfPCell(new Phrase(tmp[2], contentNormalFont));
		cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
		sumaryTable.addCell(cell);

		cell = createEmptyCell();
		sumaryTable.addCell(cell);

		sumaryTable.addCell(new PdfPCell(new Phrase(getMessage("pdf.gestiondto.summary.trxsupp"), contentBoldFont)));
		cell = new PdfPCell(new Phrase(tmp[3], contentNormalFont));
		cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
		sumaryTable.addCell(cell);

		PdfPTable gestionDtoTable = new PdfPTable(3);
		PdfPCell gestionDtoCell;

		gestionDtoCell = createEmptyCell();
		gestionDtoTable.addCell(gestionDtoCell);

		gestionDtoCell = new PdfPCell(sumaryTable);
		gestionDtoCell.setBorder(Rectangle.NO_BORDER);
		gestionDtoTable.addCell(gestionDtoCell);

		gestionDtoCell = createEmptyCell();
		gestionDtoTable.addCell(gestionDtoCell);

		float[] wf = { 79, 29, 7 };
		gestionDtoTable.setWidthPercentage(100f);
		gestionDtoTable.setWidths(wf);
		return gestionDtoTable;
	}

	protected PdfPTable addBudget(String budget) throws DocumentException {
		PdfPTable budgetTable = new PdfPTable(2);
		PdfPCell budgetCell;
		String[] tmp = budget.split(Constants.SEPRATE);
		float[] wf = { 12, 17, };
		budgetTable.setWidths(wf);

		budgetCell = new PdfPCell(new Phrase(getMessage("pdf.gestiondto.content.budgetinitial"), contentNormalFont));
		budgetCell.setBackgroundColor(HEADER_COLOR_1);
		budgetTable.addCell(budgetCell);
		budgetCell = new PdfPCell(new Phrase(processNullValue(tmp[0]), contentNormalFont));
		budgetCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
		budgetTable.addCell(budgetCell);

		budgetCell = new PdfPCell(new Phrase(getMessage("pdf.gestiondto.content.ecartdernierpoint"), contentNormalFont));
		budgetCell.setBackgroundColor(HEADER_COLOR_1);
		budgetTable.addCell(budgetCell);
		budgetCell = new PdfPCell(new Phrase(processNullValue(tmp[1]), contentNormalFont));
		budgetCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
		budgetTable.addCell(budgetCell);

		budgetCell = new PdfPCell(new Phrase(getMessage("pdf.gestiondto.content.datedernier"), contentNormalFont));
		budgetCell.setBackgroundColor(HEADER_COLOR_1);
		budgetTable.addCell(budgetCell);
		budgetCell = new PdfPCell(new Phrase(processNullValue(tmp[2]), contentNormalFont));
		budgetCell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		budgetTable.addCell(budgetCell);
		budgetTable.setWidthPercentage(50f);

		PdfPTable gestionDtoTable = new PdfPTable(3);
		PdfPCell gestionDtoCell;

		gestionDtoCell = createEmptyCell();
		gestionDtoTable.addCell(gestionDtoCell);

		gestionDtoCell = new PdfPCell(budgetTable);
		gestionDtoCell.setBorder(Rectangle.NO_BORDER);
		gestionDtoTable.addCell(gestionDtoCell);

		gestionDtoCell = createEmptyCell();
		gestionDtoTable.addCell(gestionDtoCell);

		float[] wf1 = { 79, 29, 7 };
		gestionDtoTable.setWidthPercentage(100f);
		gestionDtoTable.setWidths(wf1);
		return gestionDtoTable;
	}

	public PdfPTable addInformations(String informationscomplementaires) throws DocumentException {

		PdfPTable informationsTable = new PdfPTable(4);
		PdfPCell informatrionsCell = new PdfPCell();
		// Add Content
		String[] tmp = informationscomplementaires.split(Constants.SEPRATE);

		String[] messages = { getMessage("FicheST.conducteur") + " : ", getMessage("pdf.allfichest.dateOfMarket") + " : " };
		for (int i = 0; i < tmp.length; i++) {
			switch (i) {
			case 0:
				informatrionsCell = new PdfPCell(new Phrase(messages[0], contentBoldFont));
				informatrionsCell.setBorder(Rectangle.NO_BORDER);
				informationsTable.addCell(informatrionsCell);
				break;
			case 1:
				informatrionsCell = new PdfPCell(new Phrase(messages[1], contentBoldFont));
				informatrionsCell.setBorder(Rectangle.NO_BORDER);
				informationsTable.addCell(informatrionsCell);
				break;
			}
			informatrionsCell = new PdfPCell(new Phrase(tmp[i].equals("null") ? "" : tmp[i], contentNormalFont));
			informatrionsCell.setBorder(Rectangle.NO_BORDER);
			informationsTable.addCell(informatrionsCell);
		}
		float[] wf = { 10, 10, 20, 10 };
		informationsTable.setWidthPercentage(30f);
		informationsTable.setWidths(wf);
		return informationsTable;
	}

	public PdfPTable addCaution(String informationscomplementaires, String cautionFournies) throws DocumentException {

		PdfPTable informations = addInformations(informationscomplementaires);
		PdfPTable cautionTable = new PdfPTable(2);
		PdfPCell cell = new PdfPCell(informations);
		cell.setColspan(2);
		cautionTable.addCell(cell);
		cell = createEmptyCell();
		cell.setColspan(2);
		cautionTable.addCell(cell);
		cell = createContentCell("CAUTION FOURNIE");
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		cell.setBorderColor(Color.WHITE);
		cautionTable.addCell(cell);

		cell = createHeaderCell("Date");
		cell.setHorizontalAlignment(PdfTable.ALIGN_LEFT);
		cautionTable.addCell(cell);

		cell = createHeaderCell("Montant" + getMessage("pdf.gestiondto.content.euro"));
		cell.setHorizontalAlignment(PdfTable.ALIGN_RIGHT);
		cautionTable.addCell(cell);

		PdfPCell cautionCell = new PdfPCell();
		String[] tmp = cautionFournies.split(Constants.SEPRATE);
		for (int i = 0; i < tmp.length; i++) {
			cautionCell = new PdfPCell(new Phrase(processNullValue(tmp[i]), contentNormalFont));
			if (i % 2 == 0) {
				cautionCell.setHorizontalAlignment(PdfPCell.LEFT);
			} else {
				cautionCell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			}
			cautionTable.addCell(cautionCell);
		}
		float[] wf = { 10, 10 };
		cautionTable.setWidthPercentage(30f);
		cautionTable.setWidths(wf);
		return cautionTable;
	}

	protected static String createBudget(FicheSt ficheSt) {
		Double ecartDernierPoint = 0.0;
		if (ficheSt.getEcartDernierPoint() != null) {
			ecartDernierPoint = ficheSt.getEcartDernierPoint();
		}
		String budget = append(ficheSt.getGestBudgetInitial(), ecartDernierPoint, ficheSt.getGestDateDernierPt());
		return budget;
	}

	// Add Gestion Report
	public void addGestionDto(List<String> gestions, List<String> totalgestions, String alltotalgestion, String summary, String budget)
			throws DocumentException {

		PdfPTable headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);

		PdfPCell headerCell = new PdfPCell(new Phrase(getMessage("FicheST.gestion"), contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerTable.addCell(headerCell);
		addContent(headerTable);

		// Add Group Header
		PdfPCell[] groupHeaderCell = new PdfPCell[6];
		groupHeaderCell[0] = createEmptyCell();
		groupHeaderCell[1] = createHeaderCell(getMessage("pdf.allfichest.traite"));
		groupHeaderCell[2] = createHeaderCell(getMessage("pdf.allfichest.atraite"));
		groupHeaderCell[3] = createHeaderCell(getMessage("pdf.allfichest.budgetconforme"));

		groupHeaderCell[4] = createEmptyCell();
		groupHeaderCell[5] = createEmptyCell();

		float[] wfGroupHeader = { 41, 18, 10, 22, 17, 7 };
		PdfPTable groupHeaderTable = new PdfPTable(6);
		groupHeaderTable.setWidthPercentage(100f);
		groupHeaderTable.setWidths(wfGroupHeader);
		addCellsToTable(groupHeaderTable, groupHeaderCell);
		addContent(groupHeaderTable);

		// Content Header
		PdfPCell[] riskTableHeader = new PdfPCell[16];
		String[] resourceKeys = { "FicheST.devis", "FicheST.status", "pdf.gestiondto.content.libelle", "pdf.gestiondto.content.commentaires",
				"pdf.gestiondto.content.montant", "pdf.gestiondto.content.marche_avenants", "pdf.gestiondto.content.arrete",
				"pdf.gestiondto.content.nonarrete", "pdf.gestiondto.content.provision", "pdf.gestiondto.content.devisrefuse",
				"pdf.gestiondto.content.totalfdc", "pdf.gestiondto.content.activitereel", "FicheST.type", "pdf.gestiondto.content.libelle_",
				"pdf.gestiondto.content.montant", "pdf.gestiondto.content.ecart" };

		for (int i = 0; i < riskTableHeader.length; i++) {
			riskTableHeader[i] = new PdfPCell(new Phrase(getMessage(resourceKeys[i]), contentBoldFont));
			riskTableHeader[i].setBackgroundColor(HEADER_COLOR_1);
			riskTableHeader[i].setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
		// End

		PdfPTable gestionDtoTable = new PdfPTable(16);
		gestionDtoTable.setWidthPercentage(100f);
		addCellsToTable(gestionDtoTable, riskTableHeader);
		float[] wf = { 7, 7, 7, 13, 7, 18, 5, 5, 5, 5, 7, 5, 5, 7, 5, 7 };

		gestionDtoTable.setWidthPercentage(100f);
		gestionDtoTable.setWidths(wf);

		// Add Header
		addContent(gestionDtoTable);

		for (int j = 0; j < gestions.size(); j++) {
			gestionDtoTable = new PdfPTable(16);
			String param = gestions.get(j);
			if (param != null && param.length() > 0) {
				String arr[] = param.split(Constants.SEPRATE);
				PdfPCell pdfPCell = null;
				for (int i = 0; i < arr.length; i++) {
					pdfPCell = new PdfPCell(new Phrase(processNullValue(arr[i]), contentNormalFont));
					setCellAlignment2(pdfPCell, i);
					gestionDtoTable.addCell(pdfPCell);
				}
			}
			gestionDtoTable.setWidthPercentage(100f);
			gestionDtoTable.setWidths(wf);
			gestionDtoTable.setSpacingAfter(3f);
			addContent(gestionDtoTable);

			PdfPTable subTotalTable = addtotalSum(totalgestions.get(j));
			subTotalTable.setWidthPercentage(100f);
			subTotalTable.setSpacingAfter(3f);
			addContent(subTotalTable);
		}

		PdfPTable alltotalSumTable = addalltotalSum(alltotalgestion);
		try {
			alltotalSumTable.setWidthPercentage(100f);
			alltotalSumTable.setSpacingAfter(3f);
			addContent(alltotalSumTable);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		PdfPTable sumarryTable = addSummary(summary);
		try {
			sumarryTable.setHorizontalAlignment(PdfPTable.ALIGN_RIGHT);
			sumarryTable.setSpacingAfter(3f);
			addContent(sumarryTable);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		// todo:check null
		PdfPTable budgetTable = addBudget(budget);
		try {
			budgetTable.setHorizontalAlignment(PdfPTable.ALIGN_RIGHT);
			addContent(budgetTable);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public PdfPTable addtotalSum(String totalgestions) throws DocumentException {
		PdfPTable gestionDtoTable = new PdfPTable(11);
		PdfPCell gestionDtoCell = new PdfPCell(new Phrase(getMessage("pdf.gestiondto.soustotaldumarche"), contentBoldFont));
		gestionDtoCell.setBackgroundColor(HEADER_COLOR_2);

		gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
		gestionDtoTable.addCell(gestionDtoCell);
		if (totalgestions != null && totalgestions.length() > 0) {
			String[] tmp = totalgestions.split(Constants.SEPRATE);
			for (String cellValue : tmp) {
				gestionDtoCell = new PdfPCell(new Phrase(cellValue, contentNormalFont));
				gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
				gestionDtoCell.setBackgroundColor(HEADER_COLOR_2);
				gestionDtoTable.addCell(gestionDtoCell);
			}

			float[] wf = { 34, // CR 3
					7, // Niveau de risque 5
					18, // Phase
					5, // Statut
					5, // Date de création
					5, // Date de levée
					5, // Responsable
					7, // Cout UO
					5, // Cout ST
					17, // Vu
					7 };
			gestionDtoTable.setWidths(wf);
		}

		return gestionDtoTable;
	}

	public PdfPTable addalltotalSum(String alltotalgestions) throws DocumentException {
		if (alltotalgestions == null) {
			alltotalgestions = "";
		}
		PdfPTable gestionDtoTable = new PdfPTable(11);
		PdfPCell gestionDtoCell = createHeaderCell(getMessage("FicheST.rexAmount"));
		gestionDtoCell.setBackgroundColor(HEADER_COLOR_1);
		gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
		gestionDtoTable.addCell(gestionDtoCell);

		String[] tmp = alltotalgestions.split(Constants.SEPRATE);
		for (String value : tmp) {
			gestionDtoCell = createHeaderCell(value);
			gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
			gestionDtoTable.addCell(gestionDtoCell);
		}

		float[] wf = { 34, // CR 3
				7, // Niveau de risque 5
				18, // Phase
				5, // Statut
				5, // Date de création
				5, // Date de levée
				5, 7, // Cout UO
				5, // Cout ST
				17, // Vu
				7 };
		gestionDtoTable.setWidths(wf);
		return gestionDtoTable;
	}

	// Add Process Report
	public void addProcessDto(String processes, String totalsituation, String commentaireAvancement, String grid2, String grid3, String etatAvancement)
			throws DocumentException {
		PdfPTable headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);
		PdfPCell headerCell = new PdfPCell(new Phrase("SUIVI DES AVANCEMENTS", contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerCell.setHorizontalAlignment(PdfTable.ALIGN_LEFT);
		headerTable.addCell(headerCell);
		headerTable.setSpacingAfter(0f);
		addContent(headerTable);

		headerTable = new PdfPTable(2);
		headerTable.setWidthPercentage(40f);
		headerTable.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
		headerTable.setSpacingAfter(15f);
		headerCell = new PdfPCell(new Phrase("Etat d’avancement: ", contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerTable.addCell(headerCell);

		headerCell = new PdfPCell(new Phrase(etatAvancement + " % AVANCEMENT / MARCHE", contentNormalFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerTable.addCell(headerCell);
		headerTable.setSpacingAfter(0f);
		float[] wf1 = { 5, 10 };
		headerTable.setWidths(wf1);
		addContent(headerTable);

		PdfPTable parent = new PdfPTable(3);
		parent.setSpacingBefore(0f);
		parent.setWidthPercentage(100f);
		float[] wfParent = { 70, 5, 25 };
		parent.setWidths(wfParent);
		PdfPCell cellLeft, cellMiddle, cellRight;

		// ///////
		// Add Group Header
		PdfPCell[] groupHeaderCell = new PdfPCell[3];
		groupHeaderCell[0] = createEmptyCell();
		groupHeaderCell[0].setBorder(Rectangle.NO_BORDER);
		groupHeaderCell[1] = createHeaderCell("AVANCEMENT");
		groupHeaderCell[2] = createHeaderCell("RETENUES");

		float[] wfGroupHeader = { 25, 20, 20 };
		for (PdfPCell pdfPCell : groupHeaderCell) {
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		PdfPTable groupHeaderTable = new PdfPTable(3);
		groupHeaderTable.setWidthPercentage(100f);
		groupHeaderTable.setWidths(wfGroupHeader);
		addCellsToTable(groupHeaderTable, groupHeaderCell);

		cellLeft = new PdfPCell(groupHeaderTable);
		cellLeft.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellLeft);

		cellMiddle = new PdfPCell();
		cellMiddle.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellMiddle);

		cellRight = new PdfPCell(new Phrase("Commentaires / Informations : ", contentBoldFont));
		cellRight.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellRight);

		// Header
		// create table header
		PdfPCell[] processTableHeader = new PdfPCell[7];
		String[] resourceKeys = { "FicheST.number", "pdf.process.titlebox.libelle", "pdf.process.titlebox.date", "FicheST.cumule", "FicheST.mois",
				"FicheST.cumule2", "FicheST.mois" };

		for (int i = 0; i < processTableHeader.length; i++) {
			processTableHeader[i] = createHeaderCell(getMessage(resourceKeys[i]));
			// if (i < 3) {
			// processTableHeader[i].setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			// } else {
			// processTableHeader[i].setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			// }
		}
		// End
		PdfPTable processDtoTable = new PdfPTable(7);
		processDtoTable.setWidthPercentage(100f);
		addCellsToTable(processDtoTable, processTableHeader);

		float[] wf = { 5, // No 3
				10, // Libellé risque codification
				10, // Origine de détection
				10, // CR 3
				10, // Niveau de risque 5
				10, 10 };
		processDtoTable.setWidthPercentage(70f);
		processDtoTable.setWidths(wf);

		cellLeft = new PdfPCell(processDtoTable);
		cellLeft.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellLeft);

		cellMiddle = new PdfPCell();
		cellMiddle.setBorder(Rectangle.NO_BORDER);

		parent.addCell(cellMiddle);

		cellRight = new PdfPCell();
		cellRight.setBorder(Rectangle.NO_BORDER);
		cellRight.enableBorderSide(Rectangle.TOP);
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		parent.addCell(cellRight);

		processDtoTable = new PdfPTable(7);
		if (processes != null && processes.length() > 0) {
			String arr[] = processes.split(Constants.SEPRATE);
			for (int i = 0; i < arr.length; i++) {
				PdfPCell cell = createContentCell(arr[i]);
				if (i % 7 < 3) {
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				} else {
					cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
				}
				processDtoTable.addCell(cell);
			}
			processDtoTable.setWidthPercentage(70f);
			processDtoTable.setWidths(wf);

			cellLeft = new PdfPCell(processDtoTable);
			parent.addCell(cellLeft);

			cellMiddle = createEmptyCell();
			parent.addCell(cellMiddle);

			PdfPTable commanttaire = addProcessCommantaire(commentaireAvancement);
			commanttaire.setWidthPercentage(70f);

			cellRight = new PdfPCell(commanttaire);
			cellRight.setBorder(Rectangle.NO_BORDER);
			cellRight.enableBorderSide(Rectangle.RIGHT);
			cellRight.enableBorderSide(Rectangle.LEFT);
			parent.addCell(cellRight);
		}
		// Total Situation
		processDtoTable = addtotalsituation(totalsituation);
		processDtoTable.setWidthPercentage(70f);
		processDtoTable.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		cellLeft = new PdfPCell(processDtoTable);
		parent.addCell(cellLeft);

		cellMiddle = createEmptyCell();
		parent.addCell(cellMiddle);

		cellRight = createEmptyCell();
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		parent.addCell(cellRight);

		// Space
		cellLeft = createEmptyCell();
		parent.addCell(cellLeft);
		cellMiddle = createEmptyCell();
		parent.addCell(cellMiddle);
		cellRight = new PdfPCell();
		cellRight.setBorder(Rectangle.NO_BORDER);
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		parent.addCell(cellRight);

		cellLeft = createEmptyCell();
		parent.addCell(cellLeft);
		cellMiddle = createEmptyCell();
		parent.addCell(cellMiddle);
		cellRight = createEmptyCell();
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		parent.addCell(cellRight);

		cellLeft = createEmptyCell();
		parent.addCell(cellLeft);
		cellMiddle = createEmptyCell();
		parent.addCell(cellMiddle);
		cellRight = createEmptyCell();
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		parent.addCell(cellRight);
		// End

		cellLeft = new PdfPCell(new Phrase("DETAIL DES RETENUES APPLIQUES", contentBoldFont));
		cellLeft.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellLeft);

		cellMiddle = createEmptyCell();
		parent.addCell(cellMiddle);

		cellRight = createEmptyCell();
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		parent.addCell(cellRight);

		processDtoTable = addGrid2(grid2);
		processDtoTable.setWidthPercentage(70f);
		cellLeft = new PdfPCell(processDtoTable);
		cellLeft.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellLeft);

		cellMiddle = createEmptyCell();
		parent.addCell(cellMiddle);

		cellRight = createEmptyCell();
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		parent.addCell(cellRight);

		cellLeft = createEmptyCell();
		parent.addCell(cellLeft);

		cellMiddle = createEmptyCell();
		parent.addCell(cellMiddle);

		cellRight = createEmptyCell();
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		parent.addCell(cellRight);
		// Add Sapce
		// Space
		cellLeft = createEmptyCell();
		parent.addCell(cellLeft);
		cellMiddle = createEmptyCell();
		parent.addCell(cellMiddle);

		cellRight = createEmptyCell();
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		parent.addCell(cellRight);

		cellLeft = createEmptyCell();
		parent.addCell(cellLeft);
		cellMiddle = createEmptyCell();
		parent.addCell(cellMiddle);
		cellRight = createEmptyCell();
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		parent.addCell(cellRight);

		cellLeft = createEmptyCell();
		parent.addCell(cellLeft);
		cellMiddle = createEmptyCell();
		parent.addCell(cellMiddle);
		cellRight = createEmptyCell();
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		parent.addCell(cellRight);

		// End
		processDtoTable = addGrid3(grid3);
		processDtoTable.setWidthPercentage(70f);
		processDtoTable.setHorizontalAlignment(PdfPTable.ALIGN_RIGHT);
		cellLeft = new PdfPCell(processDtoTable);
		cellLeft.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellLeft);

		cellMiddle = createEmptyCell();
		parent.addCell(cellMiddle);

		cellRight = createEmptyCell();
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		cellRight.enableBorderSide(Rectangle.BOTTOM);
		parent.addCell(cellRight);
		addContent(parent);
	}

	public PdfPTable addProcessCommantaire(String commantaire) {
		// create table header
		PdfPTable commantaireTable = new PdfPTable(1);
		PdfPCell pdfPCell = createContentCell(commantaire);
		pdfPCell.setBorder(Rectangle.NO_BORDER);
		commantaireTable.addCell(pdfPCell);
		float[] wf = { 15 };
		try {
			commantaireTable.setWidths(wf);
			commantaireTable.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return commantaireTable;
	}

	public PdfPTable addtotalsituation(String totalsituation) throws DocumentException {
		PdfPTable processDtoTable = new PdfPTable(5);
		PdfPCell cell = createHeaderCell("TOTAL SITUATION");
		cell.setHorizontalAlignment(PdfTable.ALIGN_CENTER);
		processDtoTable.addCell(cell);

		String[] tmp = totalsituation.split(Constants.SEPRATE);
		if (tmp != null && tmp.length > 3) {
			cell = setCellAlign(tmp[0], contentNormalFont);
			cell.setBackgroundColor(HEADER_COLOR_1);
			processDtoTable.addCell(cell);
			cell = setCellAlign(tmp[1], contentNormalFont);
			cell.setBackgroundColor(HEADER_COLOR_1);
			processDtoTable.addCell(cell);
			cell = setCellAlign(tmp[2], contentNormalFont);
			cell.setBackgroundColor(HEADER_COLOR_1);
			processDtoTable.addCell(cell);
			cell = setCellAlign(tmp[3], contentNormalFont);
			cell.setBackgroundColor(HEADER_COLOR_1);
			processDtoTable.addCell(cell);
		} else {
			processDtoTable.addCell(createEmptyCell());
			processDtoTable.addCell(createEmptyCell());
			processDtoTable.addCell(createEmptyCell());
			processDtoTable.addCell(createEmptyCell());
		}
		float[] wf = { 25, 10, 10, 10, 10 };
		processDtoTable.setWidths(wf);
		return processDtoTable;
	}

	/**
	 * DETAIL DES RETENUES APPLIQUES
	 * 
	 * @param grid2
	 * @return
	 */
	public PdfPTable addGrid2(String grid2) {
		// create table header
		PdfPCell[] grid2TableHeader = new PdfPCell[3];

		grid2TableHeader[0] = createEmptyCell();
		grid2TableHeader[1] = createHeaderCell(getMessage("FicheST.amount"));
		grid2TableHeader[1].setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		grid2TableHeader[2] = createHeaderCell(getMessage("pdf.allfichest.pourcentageDuRegularise"));
		grid2TableHeader[2].setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

		PdfPTable grid2Table = new PdfPTable(3);
		PdfPCell grid2Cell;

		addCellsToTable(grid2Table, grid2TableHeader);
		String[] tmp = grid2.split(Constants.SEPRATE);
		String[] messages = { "Refacturations", "Prorata", "Refacturations dont prorata", "Refacturations dont prorata et autre", "Pénalités" };
		for (int i = 0; i < tmp.length; i++) {
			switch (i) {
			case 0:
			case 2:
			case 4:
			case 6:
			case 8:
				grid2Cell = createHeaderCell(messages[i / 2]);
				grid2Table.addCell(grid2Cell);
				break;
			}
			grid2Cell = setCellAlign(tmp[i], contentBoldFont);
			grid2Table.addCell(grid2Cell);
		}
		float[] wf = { 25, 10, 20 };
		try {
			grid2Table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
			grid2Table.setWidths(wf);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return grid2Table;
	}

	public PdfPCell setCellAlign(String tmp, Font font) {
		PdfPCell grid2Cell = new PdfPCell(new Phrase(tmp, font));
		grid2Cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		return grid2Cell;
	}

	public PdfPTable addGrid3(String grid3) {
		// create table header
		int numColumns = 8;
		PdfPCell[] grid3TableHeader = new PdfPCell[numColumns];

		grid3TableHeader[0] = createEmptyCell();
		grid3TableHeader[1] = createHeaderCell(getMessage("FicheST.canto"));
		grid3TableHeader[2] = createHeaderCell(getMessage("FicheST.badge"));
		grid3TableHeader[3] = createHeaderCell(getMessage("FicheST.grue"));
		grid3TableHeader[4] = createHeaderCell(getMessage("FicheST.lift"));
		grid3TableHeader[5] = createHeaderCell(getMessage("FicheST.benne"));
		grid3TableHeader[6] = createHeaderCell(getMessage("FicheST.nettoyage"));
		grid3TableHeader[7] = createHeaderCell(getMessage("FicheST.autres"));

		PdfPTable grid3Table = new PdfPTable(numColumns);
		addCellsToTable(grid3Table, grid3TableHeader);
		String[] tmp = grid3.split(Constants.SEPRATE);

		String[] resourceKeys = { "FicheST.nombre", "FicheST.amount" };
		PdfPCell grid3Cell;
		for (int i = 0; i < tmp.length; i++) {
			switch (i) {
			case 0:
				grid3Cell = new PdfPCell(new Phrase(getMessage(resourceKeys[0]), contentBoldFont));
				grid3Cell.setHorizontalAlignment(PdfTable.ALIGN_CENTER);
				grid3Cell.setBackgroundColor(HEADER_COLOR_1);
				grid3Table.addCell(grid3Cell);
				break;
			case 7:
				grid3Cell = new PdfPCell(new Phrase(getMessage(resourceKeys[1]), contentBoldFont));
				grid3Cell.setHorizontalAlignment(PdfTable.ALIGN_CENTER);
				grid3Cell.setBackgroundColor(HEADER_COLOR_1);
				grid3Table.addCell(grid3Cell);
				break;
			}
			grid3Cell = new PdfPCell(new Phrase(tmp[i], contentBoldFont));
			grid3Cell.setHorizontalAlignment(PdfTable.ALIGN_RIGHT);
			grid3Table.addCell(grid3Cell);
		}
		float[] wf = { 15, 10, 10, 10, 10, 10, 10, 10 };
		try {
			grid3Table.setWidths(wf);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return grid3Table;
	}

	// Add Deduction Report
	public void addDeductionDto(String deductions, String prestations, String totaldeduction, String penaltyStr, String amount, String commentaire,
			String internalCommentaire) throws DocumentException {

		PdfPTable headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);
		// Add deduction
		PdfPTable deductionTable = addDeductionTable(prestations, deductions);
		addContent(deductionTable);
		// Add total deduction
		PdfPTable totalTable = addTotalDeduction(totaldeduction);
		addContent(totalTable);
		lineBreak();

		PdfPCell leftCell, middleCell, rightCell;
		PdfPTable parent = new PdfPTable(3);
		parent.setSpacingBefore(0f);
		parent.setWidthPercentage(100f);
		float[] wfParent = { 70, // Libellé risque codification
				5, // Origine de détection
				25 };
		parent.setWidths(wfParent);

		// Add Penalties
		leftCell = new PdfPCell(new Phrase(getMessage("eport.pdf.penalites"), contentBoldFont));
		leftCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(leftCell);

		middleCell = new PdfPCell();
		middleCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(middleCell);

		rightCell = new PdfPCell(new Phrase(getMessage("FicheST.comment"), contentBoldFont));
		rightCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(rightCell);

		PdfPTable penaltyTable = addPenaltiesTable(penaltyStr);
		leftCell = new PdfPCell(penaltyTable);
		parent.addCell(leftCell);

		middleCell = createEmptyCell();
		parent.addCell(middleCell);

		PdfPTable commentairePdfPTable = addDeductionCommantaire(commentaire, internalCommentaire);
		rightCell = new PdfPCell(commentairePdfPTable);
		parent.addCell(rightCell);

		leftCell = new PdfPCell(addAmountTable(amount));
		parent.addCell(leftCell);

		middleCell = createEmptyCell();
		parent.addCell(middleCell);

		rightCell = createEmptyCell();
		parent.addCell(rightCell);

		addContent(parent);
	}

	public PdfPTable addDeductionTable(String prestations, String deductions) throws DocumentException {
		// Title
		PdfPCell[] groupTitleCell = new PdfPCell[1];
		groupTitleCell[0] = new PdfPCell(new Phrase(getMessage("pdf.process.accomptes.retenuesEffectuees"), contentBoldFont));
		groupTitleCell[0].setBorder(Rectangle.NO_BORDER);
		groupTitleCell[0].setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

		PdfPTable titleTable = new PdfPTable(1);
		titleTable.setWidthPercentage(100f);
		titleTable.setWidths(new float[] { 100 });
		addCellsToTable(titleTable, groupTitleCell);
		addContent(titleTable);

		// Add Group Header
		PdfPCell[] groupHeaderCell = new PdfPCell[4];
		groupHeaderCell[0] = createEmptyCell();
		groupHeaderCell[1] = createHeaderCell(getMessage("pdf.process.accomptes.quante"));
		groupHeaderCell[2] = createHeaderCell(getMessage("pdf.process.accomptes.montant"));
		groupHeaderCell[3] = createEmptyCell();

		float[] wfGroupHeader = { 10, 60, 20, 10 };
		PdfPTable groupHeaderTable = new PdfPTable(4);
		groupHeaderTable.setWidthPercentage(100f);
		groupHeaderTable.setWidths(wfGroupHeader);
		addCellsToTable(groupHeaderTable, groupHeaderCell);
		addContent(groupHeaderTable);
		String[] arrPrestations = prestations.split(Constants.SEPRATE);
		// create table header retenus applique
		int numColumns = 10;
		PdfPCell[] deductionTableHeader = new PdfPCell[numColumns];
		deductionTableHeader[0] = new PdfPCell(new Phrase(getMessage("FicheST.date"), contentBoldFont));
		deductionTableHeader[1] = new PdfPCell(new Phrase(getMessage("pdf.process.accomptes.canto") + arrPrestations[3] + "\u20AC", contentBoldFont));
		deductionTableHeader[2] = new PdfPCell(new Phrase(getMessage("pdf.process.accomptes.badge") + arrPrestations[4] + "\u20AC", contentBoldFont));
		deductionTableHeader[3] = new PdfPCell(new Phrase(getMessage("pdf.process.accomptes.grue") + arrPrestations[5] + "\u20AC", contentBoldFont));
		deductionTableHeader[4] = new PdfPCell(new Phrase(getMessage("pdf.process.accomptes.lift") + arrPrestations[6] + "\u20AC", contentBoldFont));
		deductionTableHeader[5] = new PdfPCell(new Phrase(getMessage("pdf.process.accomptes.bene") + arrPrestations[7] + "\u20AC", contentBoldFont));
		deductionTableHeader[6] = new PdfPCell(new Phrase(getMessage("pdf.process.accomptes.nettoyage") + arrPrestations[8] + "\u20AC",
				contentBoldFont));
		deductionTableHeader[7] = new PdfPCell(new Phrase(getMessage("pdf.process.accomptes.autres"), contentBoldFont));
		deductionTableHeader[8] = new PdfPCell(new Phrase(getMessage("pdf.process.accomptes.prorata") + arrPrestations[2] + "%", contentBoldFont));
		deductionTableHeader[9] = new PdfPCell(new Phrase(getMessage("pdf.process.accomptes.refacturations"), contentBoldFont));

		for (PdfPCell pdfPCell : deductionTableHeader) {
			pdfPCell.setBackgroundColor(HEADER_COLOR_1);
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
		PdfPTable deductionTable = new PdfPTable(10);

		// Add Header
		addCellsToTable(deductionTable, deductionTableHeader);

		// Add Content
		String[] tmp = deductions.split(Constants.SEPRATE);
		for (int i = 0; i < tmp.length; i++) {
			PdfPCell cell = createContentCell(tmp[i]);
			if (i % numColumns == 0) {
				cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			} else {
				cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			}
			deductionTable.addCell(cell);
		}

		float[] wf = { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
		deductionTable.setWidthPercentage(100f);
		deductionTable.setWidths(wf);

		return deductionTable;
	}

	public PdfPTable addTotalDeduction(String totaldeduction) throws DocumentException {
		PdfPTable totalDeduction = new PdfPTable(10);
		String[] tmp = (getMessage("FicheST.total") + Constants.SEPRATE + totaldeduction).split(Constants.SEPRATE);
		for (int i = 0; i < tmp.length; i++) {
			PdfPCell cell = createHeaderCell(tmp[i]);
			if (i > 0) {
				cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			}
			totalDeduction.addCell(cell);
		}
		float[] wf = { 10, // Libellé risque codification
				10, // Origine de détection
				10, // CR 3
				10, // Niveau de risque 5
				10, 10, 10, 10, 10, 10 };
		totalDeduction.setWidths(wf);
		totalDeduction.setWidthPercentage(100f);

		return totalDeduction;
	}

	public PdfPTable addPenaltiesTable(String penaltys) throws DocumentException {
		// create table header
		String[] keys = { "FicheST.date", "pdf.process.penalties.montant", "pdf.process.penalties.commentaires" };
		int numColumns = keys.length;
		PdfPCell[] penaltyHeaderTable = new PdfPCell[keys.length];

		for (int i = 0; i < keys.length; i++) {
			penaltyHeaderTable[i] = createHeaderCell(getMessage(keys[i]));
		}

		PdfPTable penaltyTable = new PdfPTable(numColumns);
		addCellsToTable(penaltyTable, penaltyHeaderTable);

		penaltys = penaltys.replace("?", " ");
		String[] tmp = penaltys.split(Constants.SEPRATE);

		for (int i = 0; i < tmp.length; i++) {
			PdfPCell cell = setCellAlign(processNullValue(tmp[i]), contentNormalFont);
			int index = i % numColumns;
			if (index == 0) {
				cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			} else if (index == 1) {
				cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			} else {
				cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			}
			penaltyTable.addCell(cell);
		}
		float[] wf = { 15, 15, 15 };

		penaltyTable.setWidths(wf);
		return penaltyTable;
	}

	public PdfPTable addAmountTable(String amount) throws DocumentException {
		PdfPTable amountTable = new PdfPTable(3);
		PdfPCell amountCell;
		String[] tmp = amount.split(Constants.SEPRATE);

		for (int i = 0; i < tmp.length; i++) {
			if (i == 0) {
				amountCell = createHeaderCell("Total");
				amountTable.addCell(amountCell);

				amountCell = createContentCell(tmp[i]);
				amountCell.setBackgroundColor(HEADER_COLOR_1);
				amountCell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
				amountTable.addCell(amountCell);

				amountCell = createEmptyCell();
				amountCell.setBackgroundColor(HEADER_COLOR_1);
				amountTable.addCell(amountCell);
			}
		}
		float[] wf = { 15, 15, 15 };

		amountTable.setWidths(wf);
		return amountTable;
	}

	public PdfPTable addDeductionCommantaire(String commentaire, String internalCommentaire) {
		// create table header
		PdfPCell[] commantaireTableHeader = new PdfPCell[1];
		commantaireTableHeader[0] = createContentCell("Commentaires / Informations : ");
		commantaireTableHeader[0].setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

		PdfPTable commantaireTable = new PdfPTable(1);

		String allComment = "";
		if (commentaire != null) {
			allComment += commentaire;
		}

		if (internalCommentaire != null) {
			if (allComment == "") {
				allComment += internalCommentaire;
			} else {
				allComment += "\n" + internalCommentaire;
			}
		}

		PdfPCell pdfPCell = new PdfPCell(new Phrase(allComment, contentNormalFont));
		pdfPCell.setBorder(Rectangle.NO_BORDER);
		commantaireTable.addCell(pdfPCell);
		float[] wf = { 15 };
		try {
			commantaireTable.setWidths(wf);
			commantaireTable.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return commantaireTable;
	}

	// synthese export
	public void addInformationChantier(String informationChantier) throws DocumentException {

		// Title
		PdfPTable titleTable = new PdfPTable(1);
		titleTable.setWidthPercentage(100f);
		PdfPCell titleCell = new PdfPCell(new Phrase(getMessage("pdf.synthese.title"), contentBoldFont));
		titleCell.setBorder(Rectangle.NO_BORDER);
		titleCell.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
		titleTable.addCell(titleCell);
		addContent(titleTable);
		PdfPTable informationChantierTable = new PdfPTable(1);
		informationChantierTable.setWidthPercentage(100f);
		PdfPCell informationChantierCell;
		informationChantierTable.setWidthPercentage(100f);
		informationChantierCell = new PdfPCell(new Phrase(getMessage("pdf.synthese.nomduchantier") + Constants.SPACE + informationChantier,
				contentBoldFont));
		informationChantierCell.setColspan(1);
		informationChantierCell.setBorder(Rectangle.NO_BORDER);
		informationChantierTable.addCell(informationChantierCell);
		;
		addContent(informationChantierTable);
	}

	public void addSyntheseDesFichesST(List<String> grid, String totalGrid, List<String> subTotalGrid) throws DocumentException {
		PdfPCell[] headerCell = new PdfPCell[NUM_COLUMNS];
		String[] resourceKeys = { "pdf.synthese.lot", "pdf.synthese.objecif", "pdf.synthese.societe", "pdf.synthese.obj", "pdf.synthese.+-",
				"pdf.synthese.rd", "pdf.synthese.ts", "FicheST.total", "pdf.synthese.traite", "pdf.synthese.arrete", "pdf.synthese.atraiter",
				"pdf.synthese.provision", "pdf.synthese.totalfinal", "pdf.synthese.devisrefuse", "pdf.synthese.ecartm", "pdf.synthese.ecartm-1",
				"pdf.synthese.variation", "pdf.synthese.ecartdernierpoint", "pdf.synthese.ecartdernierpoint/m", "pdf.synthese.%avctba",
				"pdf.synthese.montnatba", "pdf.synthese.%avctreel", "pdf.synthese.montantreel", "pdf.synthese.canto", "pdf.synthese.badge",
				"pdf.synthese.grue", "pdf.synthese.lift", "pdf.synthese.benne", "pdf.synthese.nettoy", "pdf.synthese.autres", "pdf.synthese.prorata",
				"FicheST.total", "pdf.synthese.prestation/traite", "pdf.synthese.penalitefacture", "pdf.synthese.prorataappliqusest",
				"pdf.synthese.proratamarche", "pdf.synthese.proratasurrad", "pdf.synthese.marchesrestantatraiter%",
				"pdf.synthese.marchesrestantatraiter", "pdf.synthese.variationsuauxtransferts", "pdf.synthese.manqueagagnerstsansprorata" };

		for (int i = 0; i < headerCell.length; i++) {
			headerCell[i] = createHeaderCell(getMessage(resourceKeys[i]));
			headerCell[i].setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		PdfPTable headerTable = new PdfPTable(NUM_COLUMNS);
		headerTable.setWidthPercentage(100f);
		headerTable.setWidths(TABLE_WIDTHS_1);
		addCellsToTable(headerTable, headerCell);
		addContent(headerTable);

		// Content
		PdfPTable grid1Table = new PdfPTable(NUM_COLUMNS);
		String tmp;
		for (int j = 0; j < grid.size(); j++) {
			grid1Table = new PdfPTable(NUM_COLUMNS);
			String param = grid.get(j);
			if (param != null && param.length() > 0) {
				String arr[] = param.split(Constants.SEPRATE);
				for (int i = 0; i < arr.length; i++) {
					tmp = arr[i];
					PdfPCell cell = new PdfPCell(new Phrase(tmp.equals("null") ? "" : tmp, contentNormalFont));
					setCellAlignment1(cell, i);
					grid1Table.addCell(cell);
				}
			}
			grid1Table.setWidthPercentage(100f);
			grid1Table.setWidths(TABLE_WIDTHS_1);
			grid1Table.setSpacingAfter(3f);
			addContent(grid1Table);

			// Sub-total
			if (subTotalGrid != null && subTotalGrid.size() > 0) {
				addSumGroupGrid1(subTotalGrid.get(j), TABLE_WIDTHS_1);
			}
		}
		addSumGroupGrid1(totalGrid, TABLE_WIDTHS_1);
	}

	protected static void setCellAlignment1(PdfPCell cell, int index) {
		if (cell == null) {
			return;
		}

		if (index == 0 || index == 2) {
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		}
	}

	protected static void setCellAlignment2(PdfPCell cell, int index) {
		if (cell == null) {
			return;
		}

		if (index > 3 && index != 12 && index != 13) {
			cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		} else {
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
	}

	public void addSumGroupGrid1(String sumGroup, float[] wf) throws DocumentException {
		PdfPTable table = new PdfPTable(wf.length);
		PdfPCell cell;
		if (sumGroup != null && sumGroup.length() > 0) {
			String[] tmp = sumGroup.split(Constants.SEPRATE);
			for (int i = 0; i < tmp.length; i++) {
				cell = createHeaderCell(tmp[i]);
				setCellAlignment1(cell, i);
				table.addCell(cell);
			}
		}

		table.setWidthPercentage(100f);
		table.setWidths(wf);
		table.setSpacingAfter(3f);
		addContent(table);
	}

	public void addInformationProrata(String informationProrata) throws DocumentException {
		// Title
		PdfPTable titleTable = new PdfPTable(1);
		titleTable.setWidthPercentage(100f);
		PdfPCell titleCell = new PdfPCell(new Phrase(getMessage("pdf.synthese.synthesedesfichesst"), contentBoldFont));
		titleCell.setBorder(Rectangle.NO_BORDER);
		titleCell.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		titleTable.addCell(titleCell);
		addContent(titleTable);

		PdfPTable informationChantierTable = new PdfPTable(1);
		PdfPCell informationChantierCell;
		informationChantierTable.setWidthPercentage(100f);
		informationChantierCell = new PdfPCell(new Phrase(getMessage("pdf.synthese.proratatheorique") + Constants.SPACE + ":  "
				+ (informationProrata.equals("null") ? "" : informationProrata), contentBoldFont));
		informationChantierCell.setBorder(Rectangle.NO_BORDER);
		informationChantierTable.addCell(informationChantierCell);
		addContent(informationChantierTable);
	}

	void addSyntheseDesFichesST2(List<String> grid2, String sumTotalGrid2) throws DocumentException {

		// Content
		PdfPTable grid2Table = new PdfPTable(NUM_COLUMNS);
		for (int j = 0; j < grid2.size(); j++) {
			grid2Table = new PdfPTable(NUM_COLUMNS);
			String param = grid2.get(j);
			if (param != null && param.length() > 0) {
				String arr[] = param.split(Constants.SEPRATE);
				for (int i = 0; i < arr.length; i++) {
					PdfPCell cell = createContentCell(arr[i]);
					setCellAlignment1(cell, i);
					grid2Table.addCell(cell);
				}
			}
			grid2Table.setWidthPercentage(100f);
			grid2Table.setWidths(TABLE_WIDTHS_1);
			grid2Table.setSpacingAfter(3f);
			addContent(grid2Table);
		}

		addSumGrid2(sumTotalGrid2);
	}

	public void addSumGrid2(String sumTotalGrid2) throws DocumentException {
		PdfPTable table = new PdfPTable(NUM_COLUMNS);
		table.setWidthPercentage(100f);
		PdfPCell cell = new PdfPCell(new Phrase("HONORAIRES", contentNormalFont));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setBackgroundColor(HEADER_COLOR_1);
		table.addCell(cell);
		if (sumTotalGrid2 != null && sumTotalGrid2.length() > 0) {
			String[] tmp = sumTotalGrid2.split(Constants.SEPRATE);
			for (int i = 1; i < tmp.length; i++) {
				cell = createHeaderCell(tmp[i]);
				setCellAlignment1(cell, i);
				table.addCell(cell);
			}
		}
		table.setWidths(TABLE_WIDTHS_1);
		table.setSpacingAfter(3f);
		addContent(table);
	}

	public void addSyntheseGrid3(String grid3) throws DocumentException {
		// Title
		PdfPTable titleTable = new PdfPTable(1);
		titleTable.setWidthPercentage(100f);
		PdfPCell titleCell = new PdfPCell(new Phrase(getMessage("pdf.synthese.synthesedestranfpertpp"), contentBoldFont));
		titleCell.setBorder(Rectangle.NO_BORDER);
		titleCell.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		titleTable.addCell(titleCell);
		addContent(titleTable);
		// Header
		int numColumns = 7;
		PdfPTable headerTable = new PdfPTable(numColumns);
		headerTable.setWidthPercentage(100f);
		PdfPCell[] headerCell = new PdfPCell[numColumns];
		headerCell[0] = new PdfPCell(new Phrase(getMessage("pdf.synthese.libelle"), contentBoldFont));
		headerCell[1] = new PdfPCell(new Phrase(getMessage("pdf.synthese.objecif"), contentBoldFont));
		headerCell[2] = new PdfPCell(new Phrase(getMessage("pdf.synthese.obj"), contentBoldFont));
		headerCell[3] = new PdfPCell(new Phrase(getMessage("pdf.synthese.+-"), contentBoldFont));
		headerCell[4] = new PdfPCell(new Phrase(getMessage("pdf.synthese.rd"), contentBoldFont));
		headerCell[5] = new PdfPCell(new Phrase(getMessage("pdf.synthese.ts"), contentBoldFont));
		headerCell[6] = new PdfPCell(new Phrase("Total", contentBoldFont));
		for (PdfPCell pdfPCell : headerCell) {
			pdfPCell.setBackgroundColor(HEADER_COLOR_1);
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
		addCellsToTable(headerTable, headerCell);
		addContent(headerTable);

		// Content
		PdfPTable grid3Table = new PdfPTable(numColumns);
		grid3Table.setWidthPercentage(100f);
		String[] arrGrid3 = grid3.split(Constants.SEPRATE);
		String tmp;
		for (int i = 0; i < arrGrid3.length; i++) {
			tmp = arrGrid3[i];
			PdfPCell cell = new PdfPCell(new Phrase(tmp.equals("null") ? "" : tmp, contentNormalFont));
			if (i % numColumns == 0) {
				cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			} else {
				cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			}
			grid3Table.addCell(cell);
		}
		addContent(grid3Table);
	}

	public void addSumGrid3(String sumGrid3) throws DocumentException {
		// Content
		PdfPTable sumGrid3Table = new PdfPTable(7);
		String[] arrSumGrid3 = sumGrid3.split(Constants.SEPRATE);
		for (int i = 0; i < arrSumGrid3.length; i++) {
			PdfPCell cell;
			if (i == 0) {
				cell = new PdfPCell(new Phrase((processNullValue(arrSumGrid3[i])), contentNormalFont));
				cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			} else {
				cell = new PdfPCell(new Phrase((processNullValue(arrSumGrid3[i])), contentNormalFont));
				cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			}
			sumGrid3Table.addCell(cell);

		}
		sumGrid3Table.setWidthPercentage(100f);
		addContent(sumGrid3Table);
	}

	public void addGrid4() throws DocumentException {
		// Title
		PdfPTable titleTable = new PdfPTable(1);
		titleTable.setWidthPercentage(100f);
		PdfPCell titleCell = new PdfPCell(new Phrase(getMessage("pdf.synthese.totalchantier"), contentBoldFont));
		titleCell.setBorder(Rectangle.NO_BORDER);
		titleCell.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		titleTable.addCell(titleCell);
		addContent(titleTable);
		float[] wf = { 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f,
				2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f };

		// //Header
		PdfPCell[] headerCell = new PdfPCell[NUM_COLUMNS];
		String[] keys = { "pdf.synthese.lot", "pdf.synthese.objecif", "pdf.synthese.societe", "pdf.synthese.obj", "pdf.synthese.+-",
				"pdf.synthese.rd", "pdf.synthese.ts", "FicheST.total", "pdf.synthese.traite", "pdf.synthese.arrete", "pdf.synthese.atraiter",
				"pdf.synthese.provision", "pdf.synthese.totalfinal", "pdf.synthese.devisrefuse", "pdf.synthese.ecartm", "pdf.synthese.ecartm-1",
				"pdf.synthese.variation", "pdf.synthese.ecartdernierpoint", "pdf.synthese.ecartdernierpoint/m", "pdf.synthese.%avctba",
				"pdf.synthese.montnatba", "pdf.synthese.%avctreel", "pdf.synthese.montantreel", "pdf.synthese.canto", "pdf.synthese.badge",
				"pdf.synthese.grue", "pdf.synthese.lift", "pdf.synthese.benne", "pdf.synthese.nettoy", "pdf.synthese.autres", "pdf.synthese.prorata",
				"FicheST.total", "pdf.synthese.prestation/traite", "pdf.synthese.penalitefacture", "pdf.synthese.prorataappliqusest",
				"pdf.synthese.proratamarche", "pdf.synthese.proratasurrad", "pdf.synthese.marchesrestantatraiter%",
				"pdf.synthese.marchesrestantatraiter", "pdf.synthese.variationsuauxtransferts", "pdf.synthese.manqueagagnerstsansprorata" };

		for (int i = 0; i < keys.length; i++) {
			headerCell[i] = new PdfPCell(new Phrase(getMessage(keys[i]), contentBoldFont));
			headerCell[i].setBackgroundColor(HEADER_COLOR_1);
			headerCell[i].setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		PdfPTable headerTable = new PdfPTable(NUM_COLUMNS);
		headerTable.setWidthPercentage(100f);
		headerTable.setWidths(wf);
		addCellsToTable(headerTable, headerCell);
		addContent(headerTable);
	}

	public void addSumGrid4(String sumGrid4) throws DocumentException {
		// Content
		PdfPTable sumGrid4Table = new PdfPTable(NUM_COLUMNS);
		String[] arrSumGrid4 = sumGrid4.split(Constants.SEPRATE);
		String tmp;
		for (int i = 0; i < arrSumGrid4.length; i++) {
			PdfPCell cell;
			tmp = arrSumGrid4[i];
			if (tmp.equalsIgnoreCase("null")) {
				tmp = "";
			}
			if (i == 0) {
				// cell = new PdfPCell(new Phrase(tmp.equals("null") ? "" : (tmp), contentNormalFont));
				cell = new PdfPCell(new Phrase(tmp, contentNormalFont));
				cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			} else {
				// cell = new PdfPCell(new Phrase(tmp.equals("null") ? "" : (tmp), contentNormalFont));
				cell = new PdfPCell(new Phrase(tmp, contentNormalFont));
				cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			}

			cell.setBackgroundColor(HEADER_COLOR_1);
			sumGrid4Table.addCell(cell);
		}
		sumGrid4Table.setWidthPercentage(100f);
		addContent(sumGrid4Table);
	}

	protected static PdfPCell createHeaderCell(String content) {
		PdfPCell cell = createNewCell(content, contentBoldFont, true);
		cell.setBackgroundColor(HEADER_COLOR_1);
		cell.setHorizontalAlignment(PdfTable.ALIGN_CENTER);
		return cell;
	}

	protected static PdfPCell createContentCell(String content) {
		return createNewCell(content, contentBoldFont, true);
	}

	protected static PdfPCell createEmptyCell() {
		PdfPCell cell = createNewCell("", contentBoldFont, true);
		cell.setBorder(Rectangle.NO_BORDER);
		return cell;
	}

	protected static PdfPCell createNewCell(String content, Font font, boolean border) {
		if ("null".equalsIgnoreCase(content)) {
			content = "";
		}

		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		if (!border) {
			cell.setBorder(Rectangle.NO_BORDER);
		}

		return cell;
	}

	public abstract File render() throws Exception;
}
