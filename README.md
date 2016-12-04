[![Build Status](https://travis-ci.com/luislorenzom/lubel.svg?token=YocqqhpYyq52H3KH8Q6h&branch=master)](https://travis-ci.com/luislorenzom/lubel)

## What is lubel?
Lubel is multi-type file handler to obtain Apache Lucene Documents from different source files, trying to abstract the developer from the task of documents parsing.

## How to use it?
First of all you need to include lubel in your project. After that, you just need to instance the FileIndexer class in your code in this way:

```java
// All your previous code...

// Get the instance from the factory
FileIndexer fileIndexer = FileIndexerFactory.getFileIndexer();

// Now you can delegate the document creation 
// and the writer addition in the FileIndexer
fileIndexer.index(writer, fileToIndex);

// More code...
```

## What file types are available?
* Plain Text (*.txt)
* PDF
* HTML
* Microsoft Word (\*.doc) and (*.docx)

## TO-DO
* PowerPoint files (\*.ppt) or (*.pptx)
* Excel files (\*.xls) or (*.xlsx)
* ePub
* XML
* JSON
* LibreOffice/OpenOffice writer (*.odt)
* HTML parser optimization
