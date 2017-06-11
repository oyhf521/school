package io.school.spring;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

//import com.sun.beans.editors.DoubleEditor;
//import com.sun.beans.editors.FloatEditor;
//import com.sun.beans.editors.LongEditor;

/**
 * spring3 mvc 的日期传递[前台-后台]bug: org.springframework.validation.BindException 的解决方式.包括xml的配置 new SimpleDateFormat("yyyy-MM-dd"); 这里的日期格式必须与提交的日期格式一致
 * 
 * @author 馨园信息 2014-3-20
 */

public class SpringMVCDateConverter implements WebBindingInitializer
{

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request)
	{
		binder.registerCustomEditor(Timestamp.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
				true));

		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));

		// 2.去除参数两边的空格;
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}

}
