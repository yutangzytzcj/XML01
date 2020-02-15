package xml;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Demo01 {

	public static void main(String[] args) 
		throws Exception {
		/*
		 * 使用dom4j读取xml文件
		 */
		SAXReader reader = new SAXReader();
		
		//doc=reader.read(文件、文件流)
		//Document doc=
		//  reader.read(new File("books.xml"));
		
		FileInputStream in = 
				new FileInputStream("books.xml");
		Document doc = reader.read(in);
		in.close();
		
		System.out.println(doc.asXML());
		
		//读取根元素, Root 根， Element元素
		Element root = doc.getRootElement();
		//输出根元素root中的内容
		System.out.println("根元素：");
		System.out.println(root.asXML()); 
		
		//获取元素的全部子元素 
		//这里是获取 root 元素的全部book子元素 
		List<Element> list = root.elements();
		System.out.println("根元素的全部子元素"); 
		//Iterator<Element> i = list.iterator();
		//while(i.hasNext()) {
		//	Element e = i.next();
		//	System.out.println(e.asXML()); 
		//}
		for(Element e:list) {
			System.out.println(e.asXML()); 
		}
		
		//获取一批指定名字的子元素
		System.out.println("root的全部book子元素："); 
		List<Element> list2 = root.elements("book");
		for (Element e : list2) {
			// e 是 <book> 元素
			System.out.println(e.asXML());
			//获取 book 元素的name子元素
			Element name = e.element("name");
			Element author = e.element("author");
			System.out.println(name.asXML());
			System.out.println(author.asXML());
			//获取元素中的文本内容name.getText()
			String s1 = name.getTextTrim();
			String s2 = author.getTextTrim();
			System.out.println(s1 + ","+s2);
		}
		
		//读取元素的属性 
		System.out.println("读取元素的属性："); 
		for (Element book : list2) {
			// attribute 属性， Value 值
			//读取book元素的id属性值
			String id = book.attributeValue("id");
			//读取book元素的lang属性值
			String lang = book.attributeValue("lang");
			System.out.println(id+", "+lang);
		}
		
	}

}








