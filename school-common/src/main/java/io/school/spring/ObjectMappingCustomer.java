package io.school.spring;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

@SuppressWarnings("serial")
public class ObjectMappingCustomer extends ObjectMapper
{

	public ObjectMappingCustomer()
	{
		super();
		// 允许单引号
		this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		// 字段和值都加引号
		this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		// 数字也加引号
		this.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
		this.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, true);
		// 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
		this.setSerializationInclusion(Include.NON_EMPTY);
		// 设置null转换""
		this.getSerializerProvider().setNullValueSerializer(new NullSerializer());
	}

	// 空值处理为空串
	private class NullSerializer extends JsonSerializer<Object>
	{
		@Override
		public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException
		{
			jgen.writeString("");
		}
	}
}