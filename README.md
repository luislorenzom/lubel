## What is lubel?
Lubel is multi-type file handler to obtain Apache Lucene Documents from different source files, trying to abstract the developer from the task of documents parsing.

## How to use it?
First of all you need to include lubel on your project. After that, you just need to instance the FileIndexer class in your code, in this way:

```java
// All your previous code...

// Get the instance from the factory
FileIndexer fileIndexer = FileIndexerFactory.getFileIndexer();

// Now you can delegate the document creation 
// and the writer addition in the FileIndexer
fileIndexer.index(writer, fileToIndex);

// More code...
```

## What kind of files are available?
* Plain Text (*.txt)
* PDF
* Microsoft Word (*.doc)

## TO-DO
* For Microsoft Word 2007 onwards (*.docx)
* PowerPoint files (*.ppt) or (*.pptx)
* Excel files (*.xls) or (*.xlsx)
* ePub
* XML
* JSON
* LibreOffice/OpenOffice (*.odt)