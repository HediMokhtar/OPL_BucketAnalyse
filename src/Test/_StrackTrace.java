package Test;

import Model.Stacktrace;
import Model.SubStackTrace;
import org.junit.Test;

public class _StrackTrace {

	SubStackTrace sbt1 = new SubStackTrace("1","_poppler_document_new_from_pdfdoc","poppler-document.cc:89","/usr/lib/libgtk-x11-2.0.so.0");
	SubStackTrace sbt2 = new SubStackTrace("1","_poppler_document_new_from_pdfdoc","poppler-document.cc:89","/usr/lib/libgtk-x11-2.0.so.0");
	
	Stacktrace st1 = new Stacktrace();
	
	
	
	@Test
	public void test() {
		st1.add(sbt1);
		st1.add(sbt2);
	}

}
