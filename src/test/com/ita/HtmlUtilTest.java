package test.com.ita;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

import com.ita.util.HtmlUtil;

public class HtmlUtilTest {

	@Test
	public void testReadHtmlFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testReplaceElement() {
		Map<String, String> map = new HashMap<String,String>();
		map.put("title", "this is my page");
		map.put("value", "this is value");
		String html = HtmlUtil.replaceElement("<h>hhhh {{title}} jjjjj {{value}}.</h1>", map);
		assertTrue(html.equals("<h>hhhh this is my page jjjjj this is value.</h1>"));
	}

}
