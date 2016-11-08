package Test;

import Model.SubStacktrace;
import org.junit.Test;

import static org.junit.Assert.fail;

public class _SubStackTrace {

	SubStacktrace sbt1 = new SubStacktrace("1","_poppler_document_new_from_pdfdoc","poppler-document.cc:89","/usr/lib/libgtk-x11-2.0.so.0");
	SubStacktrace sbt2 = new SubStacktrace("1","_poppler_document_new_from_pdfdoc","poppler-document.cc:89","/usr/lib/libgtk-x11-2.0.so.0");
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
