package ro.msg.learning.shop.converters;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Component
public class CSVConverterUtils<T> {
	private static final CsvMapper csvMapper = new CsvMapper();
	public static <T> List<T> fromCsv(Class<?> classType, InputStream inputStream) throws IOException {
		CsvSchema schema = csvMapper.schemaFor(classType).withHeader();
		return (List<T>) csvMapper.readerFor(classType).with(schema).readValues(inputStream).readAll();
	}

	public static <T> void toCsv(Class<?> classType, List<T> data, OutputStream outputStream) throws IOException {
		CsvSchema schema = csvMapper.schemaFor(classType).withHeader();
		csvMapper.writer(schema).writeValues(outputStream).writeAll(data);
	}
}
