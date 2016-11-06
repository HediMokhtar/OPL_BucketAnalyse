package Analyzer;

import Model.Bucket;
import Model.Buckets;
import Model.Stacktrace;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;

/**
 * Created by Junior on 02-11-16.
 */
public class NeuralAnalyzer extends Analyzer {

    public NeuralAnalyzer(Buckets buckets) {
        super(buckets);
    }

    @Override
    public Bucket searchBucket(Stacktrace stackTrace) {

        NeuralNetwork neuralNetwork = new Perceptron(3, buckets.size());
        DataSet trainingSet = new DataSet(3,1);
        trainingSet.addRow(new DataSetRow());

        return null;
    }
}
