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
		 * ��ȡweb.xml�д洢���ļ�������Ϣ
		 * 
		 * 1. ��ȡweb.xml Ϊdocument
		 * 2. ��ȡ��Ԫ�� web-app
		 * 3. ��ȡ��Ԫ���� mime-mapping ������Ԫ��
		 * 4. ����ÿ��mime-mapping
		 * 		1. ��ȡ mime-mapping �� extension ��Ԫ�أ�
		 * 		����ȡ��Ԫ���е��ı���
		 *		2. ��ȡ mime-mapping �� mime-type ��Ԫ�أ�
		 * 		����ȡ��Ԫ���е��ı���
		 */
		
		//��ȡweb.xml�ļ�Ϊdom����
		SAXReader reader=new SAXReader();
		Document doc = 
				reader.read(new File("conf/web.xml"));
		//��ȡ��Ԫ�� web-app
		Element root=doc.getRootElement();
		//��ȡȫ�� mime-mapping ��Ԫ��
		List<Element> list=root.elements("mime-mapping");
		System.out.println("list size:"+list.size()); 
		for(Element e:list) {
			//e ��ÿ�� mime-mapping Ԫ��
			//��ȡ��Ԫ�ز���ֱ�Ӷ�ȡ��Ԫ�ص��ı�����
			String ext=e.element("extension").getTextTrim();
			//elementTextTrimֱ�ӻ�ȡ��Ԫ�����ı�
			String type=e.elementTextTrim("mime-type");
			//�����֤��ȡ���
			System.out.println(ext+", "+type); 
		}
	}

}







