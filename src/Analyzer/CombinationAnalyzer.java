package Analyzer;

import java.util.LinkedList;
import java.util.List;

import Model.Bucket;
import Model.Buckets;
import Model.Stacktrace;

public class CombinationAnalyzer extends Analyzer{
	
	NaiveAnalyzer naiveAnalyzer;
	HashAnalyzer hashAnalyzer;
	SimilarityAnalyzerV2 similarityAnalyzer;
	
	public CombinationAnalyzer(Buckets buckets){
		super(buckets);
		this.naiveAnalyzer = new NaiveAnalyzer(buckets);
		this.hashAnalyzer = new HashAnalyzer(buckets);
		this.similarityAnalyzer = new SimilarityAnalyzerV2(buckets);
	}
	
	@Override
	public Bucket searchBucket(Stacktrace stackTrace) {
		// TODO Auto-generated method stub
		return combinateSearch(stackTrace);
	}

	private void naiveSearch(Stacktrace stackTrace){
		naiveAnalyzer.searchBucket(stackTrace);
	}
	
	private void hashSearch(Stacktrace stackTrace){
		hashAnalyzer.searchBucket(stackTrace);
	}
	
	private Bucket combinateSearch(Stacktrace stackTrace){
		Buckets filtredBuckets;
		
		naiveSearch(stackTrace);
		
		filtredBuckets = this.naiveAnalyzer.potentialBuckets.get(stackTrace);
		if(filtredBuckets.size() == 1){
			return filtredBuckets.get(0);
		}
		if(filtredBuckets.size() == 0){
			return this.similarityAnalyzer.searchBucket(stackTrace);
		}
		/**
		this.hashAnalyzer = new HashAnalyzer(filtredBuckets);
		hashSearch(stackTrace);
		filtredBuckets = this.hashAnalyzer.potentialBuckets.get(stackTrace);
		if(filtredBuckets.size() == 1){
			return filtredBuckets.get(0);
		}
		if(filtredBuckets.size() == 0){
			return this.similarityAnalyzer.searchBucket(stackTrace);
		}
		**/
		this.similarityAnalyzer = new SimilarityAnalyzerV2(filtredBuckets);

		return similarityAnalyzer.searchBucket(stackTrace);
	}
	
}
