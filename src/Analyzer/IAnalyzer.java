package Analyzer;

import Model.Bucket;
import Model.Stacktrace;

import java.io.File;

/**
 * Created by Junior on 24-10-16.
 */
public interface IAnalyzer {

    Bucket searchBucket(Stacktrace stackTrace);

    String monperrusEvalPrinter(File stackTraceFile, Stacktrace stackTrace);
}
