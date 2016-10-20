package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.SubStackTrace;

public class _SubStackTrace {

	SubStackTrace sbt1 = new SubStackTrace(1,"_poppler_document_new_from_pdfdoc","poppler-document.cc:89","/usr/lib/libgtk-x11-2.0.so.0");
	SubStackTrace sbt2 = new SubStackTrace(1,"_poppler_document_new_from_pdfdoc","poppler-document.cc:89","/usr/lib/libgtk-x11-2.0.so.0");
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
