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
		 * ʹ��dom4j��ȡxml�ļ�
		 */
		SAXReader reader = new SAXReader();
		
		//doc=reader.read(�ļ����ļ���)
		//Document doc=
		//  reader.read(new File("books.xml"));
		
		FileInputStream in = 
				new FileInputStream("books.xml");
		Document doc = reader.read(in);
		in.close();
		
		System.out.println(doc.asXML());
		
		//��ȡ��Ԫ��, Root ���� ElementԪ��
		Element root = doc.getRootElement();
		//�����Ԫ��root�е�����
		System.out.println("��Ԫ�أ�");
		System.out.println(root.asXML()); 
		
		//��ȡԪ�ص�ȫ����Ԫ�� 
		//�����ǻ�ȡ root Ԫ�ص�ȫ��book��Ԫ�� 
		List<Element> list = root.elements();
		System.out.println("��Ԫ�ص�ȫ����Ԫ��"); 
		//Iterator<Element> i = list.iterator();
		//while(i.hasNext()) {
		//	Element e = i.next();
		//	System.out.println(e.asXML()); 
		//}
		for(Element e:list) {
			System.out.println(e.asXML()); 
		}
		
		//��ȡһ��ָ�����ֵ���Ԫ��
		System.out.println("root��ȫ��book��Ԫ�أ�"); 
		List<Element> list2 = root.elements("book");
		for (Element e : list2) {
			// e �� <book> Ԫ��
			System.out.println(e.asXML());
			//��ȡ book Ԫ�ص�name��Ԫ��
			Element name = e.element("name");
			Element author = e.element("author");
			System.out.println(name.asXML());
			System.out.println(author.asXML());
			//��ȡԪ���е��ı�����name.getText()
			String s1 = name.getTextTrim();
			String s2 = author.getTextTrim();
			System.out.println(s1 + ","+s2);
		}
		
		//��ȡԪ�ص����� 
		System.out.println("��ȡԪ�ص����ԣ�"); 
		for (Element book : list2) {
			// attribute ���ԣ� Value ֵ
			//��ȡbookԪ�ص�id����ֵ
			String id = book.attributeValue("id");
			//��ȡbookԪ�ص�lang����ֵ
			String lang = book.attributeValue("lang");
			System.out.println(id+", "+lang);
		}
		
	}

}








