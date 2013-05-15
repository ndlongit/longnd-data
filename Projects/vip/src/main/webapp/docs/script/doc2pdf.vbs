Const wdFormatPDF = 17  ' PDF format. 

' Global variables
Dim arguments
Set arguments = WScript.Arguments

Function DOC2PDF( sDocFile, sPDFFile )

  Dim fso 'As FileSystemObject
  Dim wdo 'As Word.Application
  Dim wdoc 'As Word.Document
  Dim wdocs 'As Word.Documents
  Dim sPrevPrinter 'As String

  Set fso = CreateObject("Scripting.FileSystemObject")
  Set wdo = CreateObject("Word.Application")
  Set wdocs = wdo.Documents

  sDocFile = fso.GetAbsolutePathName(sDocFile)

	WScript.Echo "Doc file..." + sDocFile 

  sFolder = fso.GetParentFolderName(sDocFile)

	WScript.Echo "folder..." + sFolder 

  If Len(sPDFFile)=0 Then
    sPDFFile = fso.GetBaseName(sDocFile) + ".pdf"
  End If

  If Len(fso.GetParentFolderName(sPDFFile))=0 Then
    sPDFFile = sFolder + "\" + sPDFFile
  End If

	WScript.Echo "sPDFFile..." + sPDFFile
	WScript.Echo "sDocFile..." + sDocFile


  Set wdoc = wdocs.Open(sDocFile)

  wdoc.SaveAs sPDFFile, wdFormatPDF

  wdoc.Close WdDoNotSaveChanges
  wdo.Quit WdDoNotSaveChanges
  Set wdo = Nothing
  Set fso = Nothing
  
  WScript.Echo "OK"

End Function
' *** MAIN **************************************
Call DOC2PDF( arguments.Unnamed.Item(0), arguments.Named.Item("o") )

Set arguments = Nothing