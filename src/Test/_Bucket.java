package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Bucket;
import Model.Stacktrace;
import Model.SubStackTrace;

public class _Bucket {
	
	Bucket bucket = new Bucket();
	
	String functionName = "_poppler_document_new_from_pdfdoc";
	
	SubStackTrace sbt1 = new SubStackTrace(1,functionName,"poppler-document.cc:89","/usr/lib/libgtk-x11-2.0.so.0");
	SubStackTrace sbt2 = new SubStackTrace(1,functionName,"poppler-document.cc:89","/usr/lib/libgtk-x11-2.0.so.0");
	
	Stacktrace st1 = new Stacktrace();
	
	
	
	@Test
	public void test() {
		st1.add(sbt1);
		
		bucket.add(st1);
		boolean test = bucket.getFunctionNameRanking().get(functionName) == 1;
		assertEquals(test , true);
		
	}

}
