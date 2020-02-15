# XML

什么是XML： 可以扩展的标记语言

## 处理节点

xml 推荐在第一行，必须写在第一行，可以省略。

	<?xml version="1.0" encoding="UTF-8"?>

## 标记（标签） tag

语法：
	
	开始标记 <标记名>	
	结束标记	</标记名>

标记规则：

1. 中文英文都可以作为标记名，建议使用英文
2. 区分大小写
3. 开始标记和结束标记要成对使用
4. 不能交叉嵌套
5. 一个XML中只有唯一的根标记

标记名可以扩展
标记的嵌套关系可以扩展

## 注释 Comment

语法：

	<!-- 写啥都行! -->

注释不能嵌套使用！！

## 内容 Content

语法： 开始标记和结束标记之间的信息称为内容。

	<name>檀香刑</name>
	<book><name>檀香刑</name></book>
	<book>
		<name>檀香刑</name>
		<author>莫言</author>
	</book>

内容：

1. 文本内容
2. 标记
3. 多个标记和文本的混合
4. 内容是可扩展的！

## 元素 Element

语法： 

	标记 + 内容 = 元素

1. XML 只有一个根元素
2. 元素中的元素称为子元素，外层元素称为父元素

## 属性

语法：

	<book id="b1" lang="cn">
	<book lang="cn" id="b1">

1. 在开始标记上定义元素的属性
2. 可以定义多个属性
3. 多个属性不分先后次序
4. 属性之间需要有空格
5. 属性名="属性值" 属性和值之间等号前后不用写空格，属性值必须用 引号 

## 实体 Entity

实体： 类似于 Java 的字符串中的转义字符，一些用于替换表示XML语法的字符。 用来解决XML文字解析错误

常用实体：

	< &lt;
	> &gt;
	& &amp;

## CDATA

使用CDATA包裹的文本，可以写任何字符，无需进行实体替换
	
	<![CDATA[ 文本内容 ]]>

## XML可扩展性

1. 元素（标记）名可以扩展
2. 元素的嵌套关系可以扩展
3. 元素的属性可以扩展
4. 属性名可以扩展
5. 内容可以扩展

XML案例：

	<?xml version="1.0" encoding="UTF-8"?>
	<books count="2">
		<book id="b1" lang="cn">
			<name>
				&lt;檀香刑&gt;
			</name>
			<!-- author ：作者 
				 这是一个注释 -->
			<author>莫言</author>
		</book>
		<book lang="cn" id="b2">
			<name>18岁给我一个姑娘</name>
			<author>冯唐&amp;朋友</author>
		</book>
		<book>
			<name><![CDATA[<和空姐同居的日子>]]></name>
			<author>三十</author>
		</book>
		<comment>Hello</comment>
	</books>


# 在Java中使用XML

Java 业界有API可以读取XML文件。读取以后可以按照元素、属性进行结构分解。

Dom4J 读作 Dom for J

Dom4J 的底层

	IO 流 -> W3C Dom -> Dom4J 

## 利用Maven导入dom4j API

得到Dom4j 的maven组件坐标   	

	<dependency>
		<groupId>org.dom4j</groupId>
		<artifactId>dom4j</artifactId>
		<version>2.1.1</version>
	</dependency>

将组件坐标添加到 pom.xml 文件中

	<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	  <modelVersion>4.0.0</modelVersion>
	  <groupId>cn.tedu</groupId>
	  <artifactId>XML01</artifactId>
	  <version>0.0.1-SNAPSHOT</version>
	  <dependencies>
	  	<dependency>
	  		<groupId>org.dom4j</groupId>
	  		<artifactId>dom4j</artifactId>
	  		<version>2.1.1</version>
	  	</dependency>
	  </dependencies>
	</project>

# Dom4j API 的使用

## 读取XML

Dom4j提供了API将XML文件读取为Dom对象（Document）

		/*
		 * 使用dom4j读取xml文件
		 */
		SAXReader reader = new SAXReader();
		
		//doc=reader.read(文件、文件流)
		Document doc=
		  reader.read(new File("books.xml"));
		
		System.out.println(doc.asXML());

## 利用Dom API（Document）可以访问Dom树中的数据

1. 访问根元素

		//读取根元素, Root 根， Element元素
		Element root = doc.getRootElement();
		//输出根元素root中的内容
		System.out.println("根元素：");
		System.out.println(root.asXML()); 

2. 可以获取元素中全部的子元素

		List<Element> list=root.elements();

3. 可以获取一批名字一样的子元素
	
		List<Element> list= root.elements("book");

4. 获取一个指定名字的子元素, 适合元素的子元素名字都不同，获取其中的一个子元素时候使用。

		Element e = book.element("name");

5. 获取元素中文字内容

		String s1 = name.getText()
		String s2 = name.getTextTrim() //去除前后空白，常用！

6. 获取元素的属性值

		<book id="b1" >

		String s = book.attributeValue("id"); 

7. 直接获取子元素的文本内容

		String name = book.elementTextTrim("name");

完整演示案例：
	
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

# 案例

读取 web.xml 文件中，文件后缀和文件类型信息

1. 了解web.xml文件结构

		<web-app>
			<servlet>
				<!-- ... -->
			</serlvet>
    		<mime-mapping>
        		<extension>rif</extension>
        		<mime-type>application/reginfo+xml</mime-type>
    		</mime-mapping>	
		    <mime-mapping>
		        <extension>jpg</extension>
		        <mime-type>image/jpeg</mime-type>
		    </mime-mapping>
			<!-- ... -->
		</web-app>

2. 设计程序
	1. 读取web.xml 为document
	2. 获取根元素 web-app
	3. 获取根元素中 mime-mapping 所有子元素
	4. 遍历每个mime-mapping
		1. 获取 mime-mapping 的 extension 子元素，并获取子元素中的文本。
		2. 获取 mime-mapping 的 mime-type 子元素，并获取子元素中的文本。

代码：

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

## 重构WebServer，初始化mimi_mapping

代码：

	/**
	 * 初始化资源类型
	 * 读取conf/web.xml中资源类型列表，添加到mime_mapping中
	 * 可以解决大部分市面上常见文件类型与资源类型的映射关系
	 * 
	 * 异常处理原则：能处理尽量处理，处理不了抛出去
	 */
	private static void initMimeMapping() {
		try {
			SAXReader reader=new SAXReader();
			Document doc = reader.read(
				new File("conf/web.xml"));
			Element root=doc.getRootElement();
			List<Element> list=
					root.elements("mime-mapping");
			for (Element e : list) {
				String ext=e.elementTextTrim("extension");
				String type=e.elementTextTrim("mime-type");
				mime_mapping.put(ext, type);
			}
			//System.out.println(mime_mapping); 
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}



# XML 总结

1. XML 可扩展标记语言
	- 统一了数据文件的格式！
	- 经常用于数据存储格式，数据交互格式，配置文件格式
2. dom4j
	- 统一的访问API，大大简化编程