package com.bridglabz.day29;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.StreamSupport;

public class CensusAnalyserUC1 {
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException{
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
			CsvToBeanBuilder<IndiaCensusCSV> csvToBEanBuilder = new CsvToBeanBuilder<>(reader);
			csvTobeanBuilder.withType(IndiaCensusCSV.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
			Iterator<IndiaCensusCSV> csvIterable = () -> censusCSVItrator;
			int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			return numOfEntries;
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.UNABLE_TO_PASS);
		}
	}
}
