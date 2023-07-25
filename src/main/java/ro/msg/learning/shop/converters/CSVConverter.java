package ro.msg.learning.shop.converters;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import static com.fasterxml.jackson.databind.type.TypeFactory.rawClass;

@Component
public class CSVConverter<T> extends AbstractGenericHttpMessageConverter<List<T>> {
	private static final MediaType SUPPORTED_MEDIA_TYPE = new MediaType("text", "csv");
	private final CSVConverterUtils<T> csvHelper;

	public CSVConverter(final CSVConverterUtils<T> csvHelper) {
		super(SUPPORTED_MEDIA_TYPE);
		this.csvHelper = csvHelper;
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		// Supports any list type
		return List.class.isAssignableFrom(clazz);
	}

	@Override
	protected void writeInternal(List<T> ts, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		csvHelper.toCsv(rawClass(((ParameterizedType) type).getActualTypeArguments()[0]), ts, outputMessage.getBody());
	}

	@Override
	protected List<T> readInternal(Class clazz, HttpInputMessage inputMessage) throws HttpMessageNotReadableException, IOException {
		return csvHelper.fromCsv(clazz, inputMessage.getBody());
	}

	@Override
	public List<T> read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		return readInternal(contextClass, inputMessage);
	}
}
