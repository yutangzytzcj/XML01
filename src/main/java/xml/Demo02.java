package xml;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Demo02 {

	public static void main(String[] args) 
		throws Exception{
		/*
		 * 读取web.xml中存储的文件类型信息
		 * 
		 * 1. 读取web.xml 为document
		 * 2. 获取根元素 web-app
		 * 3. 获取根元素中 mime-mapping 所有子元素
		 * 4. 遍历每个mime-mapping
		 * 		1. 获取 mime-mapping 的 extension 子元素，
		 * 		并获取子元素中的文本。
		 *		2. 获取 mime-mapping 的 mime-type 子元素，
		 * 		并获取子元素中的文本。
		 */
		
		//读取web.xml文件为dom对象
		SAXReader reader=new SAXReader();
		Document doc = 
				reader.read(new File("conf/web.xml"));
		//获取根元素 web-app
		Element root=doc.getRootElement();
		//获取全部 mime-mapping 子元素
		List<Element> list=root.elements("mime-mapping");
		System.out.println("list size:"+list.size()); 
		for(Element e:list) {
			//e 是每个 mime-mapping 元素
			//获取子元素并且直接读取子元素的文本内容
			String ext=e.element("extension").getTextTrim();
			//elementTextTrim直接获取子元素中文本
			String type=e.elementTextTrim("mime-type");
			//输出验证读取结果
			System.out.println(ext+", "+type); 
		}
	}

}







