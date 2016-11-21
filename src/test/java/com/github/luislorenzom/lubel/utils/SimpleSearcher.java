package com.github.luislorenzom.lubel.utils;

import java.io.File;
import java.io.IOException;


import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class SimpleSearcher {
	
	public int search(File indexDir, String q) throws IOException, ParseException {
		// Open the index
		StandardAnalyzer analyzer = new StandardAnalyzer();
		Directory directory = FSDirectory.open(indexDir);
		DirectoryReader directoryReader = DirectoryReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
		IndexReader indexReader = DirectoryReader.open(directory);
		
		// Query parsing and searching
		QueryParser queryParser = new QueryParser("content", analyzer);
		Query query = queryParser.parse(QueryParser.escape(q));
		TopDocs topDocs = indexSearcher.search(query, 100);
		
		// Show results
		System.out.println("Totals hits: " + topDocs.totalHits);
		
		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			Document doc = indexReader.document(scoreDoc.doc);
			//System.out.println(scoreDoc.doc + ": " + doc.getField("filename").stringValue());
			System.out.println(scoreDoc.doc + ": " + doc);
		}
		return topDocs.totalHits;
	}

}
