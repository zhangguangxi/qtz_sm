package com.mall.core.xml;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

/**
* <p>Title:JdomTool类</p> 
* <p>Description:用jdom方式来读取XML文件,可以通过XPATH来得到对应元素的值,也可以设置元素及元素属性的值</p> 
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 罗丁丁 - Stank.Luo
 * @version v1.0 2014-9-28
 */

public class JdomTool {
	/**文件*/
    private File file;
    /**jdom中的文档对象(Document)*/
    private Document doc;
	
    /**
     * 构造函数
     * @param file 文件
     * @throws Exception 异常
     */
	
    public JdomTool(File file) throws Exception {
    	if(null == file) {
    		throw new Exception(this.getClass().getName() + ".JdomTool()传的参数不能为空! file : " + file + ",请检查!");
    	}
    	String fileName = file.getPath();
        this.file = file;
        if (!file.exists()) {
            File tempFile;
            tempFile = new File(file.getParentFile(), file.getName() + ".tmp");
            if (tempFile.exists()) {
                System.err.println("WARNING: " + fileName + " was not found, but temp file from " +
                        "previous write operation was. Attempting automatic recovery. Please " +
                        "check file for data consistency.");
                tempFile.renameTo(file);
            }else {
                throw new FileNotFoundException("XML properties file does not exist: " + fileName);
            }
        }
        if (!file.canRead()) {
            throw new IOException("XML properties file must be readable: " + fileName);
        }
        if (!file.canWrite()) {
            throw new IOException("XML properties file must be writable: " + fileName);
        }
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            SAXBuilder builder = new SAXBuilder();
            DataUnformatFilter format = new DataUnformatFilter();
            builder.setXMLFilter(format); // Strip formatting
            doc = builder.build(reader);
        }catch (Exception e) {
            System.err.println("Error creating XML properties file " + fileName + ".");
            e.printStackTrace();
            throw new IOException(e.getMessage());
        }finally {
            try {
            	if(null != reader) {
            		reader.close();
            	}
            }catch (Exception e) {
            	e.printStackTrace();
            	throw e;
            }
        }
    }
	
    /**
     * 构造函数
     * @param fileName 文件路径
     * @throws Exception 异常
     */
	
    public JdomTool(String fileName) throws Exception {
    	if(null == fileName || "".equals(fileName)) {
    		throw new Exception(this.getClass().getName() + ".JdomTool()传的参数不能为空! file : " + file + ",请检查!");
    	}
        this.file = new File(fileName);
        if (!file.exists()) {
            File tempFile;
            tempFile = new File(file.getParentFile(), file.getName() + ".tmp");
            if (tempFile.exists()) {
                System.err.println("WARNING: " + fileName + " was not found, but temp file from " +
                        "previous write operation was. Attempting automatic recovery. Please " +
                        "check file for data consistency.");
                tempFile.renameTo(file);
            }else {
                throw new FileNotFoundException("XML properties file does not exist: " + fileName);
            }
        }
        if (!file.canRead()) {
            throw new IOException("XML properties file must be readable: " + fileName);
        }
        if (!file.canWrite()) {
            throw new IOException("XML properties file must be writable: " + fileName);
        }
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            SAXBuilder builder = new SAXBuilder();
            DataUnformatFilter format = new DataUnformatFilter();
            builder.setXMLFilter(format); // Strip formatting
            doc = builder.build(reader);
        }catch (Exception e) {
            System.err.println("Error creating XML properties file " + fileName + ".");
            e.printStackTrace();
            throw new IOException(e.getMessage());
        }finally {
            try {
            	if(null != reader) {
            		reader.close();
            	}
            }catch (Exception e) {
            	e.printStackTrace();
            	throw e;
            }
        }
    }
	
    /**
     * 构造函数
     * @param fileName 文件名 
     * @param codeType 编码
     * @throws Exception 异常
     */
	
    public JdomTool(String fileName, String codeType) throws Exception {
    	if(null == fileName || "".equals(fileName) || null == codeType || codeType.equals("")) {
    		throw new Exception("fileName is : "+fileName+",codeType is : "+codeType+",please check !");
    	}
        this.file = new File(fileName);
        if (!file.exists()) {
            File tempFile;
            tempFile = new File(file.getParentFile(), file.getName() + ".tmp");
            if (tempFile.exists()) {
                System.err.println("WARNING: " + fileName + " was not found, but temp file from " +
                        "previous write operation was. Attempting automatic recovery. Please " +
                        "check file for data consistency.");
                tempFile.renameTo(file);
            }else {
                throw new FileNotFoundException("XML properties file does not exist: " + fileName);
            }
        }
        if (!file.canRead()) {
            throw new IOException("XML properties file must be readable: " + fileName);
        }
        if (!file.canWrite()) {
            throw new IOException("XML properties file must be writable: " + fileName);
        }
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file), codeType);
            SAXBuilder builder = new SAXBuilder();
            DataUnformatFilter format = new DataUnformatFilter();
            builder.setXMLFilter(format); // Strip formatting
            doc = builder.build(reader);
        }catch (Exception e) {
            System.err.println("Error creating XML properties file " + fileName + ".");
            e.printStackTrace();
            throw new IOException(e.getMessage());
        }finally {
            try {
            	if(null != reader) {
            		reader.close();
            	}
            }catch (Exception e) {
            	e.printStackTrace();
            	throw e;
            }
        }
    }
	
    /**
     * 构造函数
     * @param inputStream xml流数据
     * @throws Exception 异常
     */
	
    public JdomTool(InputStream inputStream) throws Exception {
        Reader reader = null;
        try {
        	if(null != inputStream){
	    		 reader = new InputStreamReader(inputStream, "UTF-8");
	             SAXBuilder builder = new SAXBuilder();
	             DataUnformatFilter format = new DataUnformatFilter();
	             builder.setXMLFilter(format); // Strip formatting
	             doc = builder.build(reader);
        	}
        }catch (Exception e) {
            System.err.println("Error creating XML properties inputStream " + inputStream + ".");
            e.printStackTrace();
            throw new IOException(e.getMessage());
        }finally {
            try {
            	if(null != reader) {
            		reader.close();
            	}
            }catch (Exception e) {
            	e.printStackTrace();
            	throw e;
            }
        }
    }
	
    /**
     * 构造函数
     * @param xmlString xml格式字符串
     * @param xmlCodeType xml编码类型
     * @param inCodeType  流编码类型
     * @throws Exception 异常
     */
	
    public JdomTool(String xmlString, String xmlCodeType, String inCodeType) throws Exception {
    	if(null == xmlString || xmlString.equals("")) {
    		throw new Exception("xmlString is : "+xmlString+",please check !");
    	}
    	if(null == xmlCodeType || xmlCodeType.equals("")) xmlCodeType = "UTF-8";
    	if(null == inCodeType || inCodeType.equals("")) inCodeType = "UTF-8";
		Reader reader = null;
		InputStream inputStream = null;
		try {
			inputStream = new ByteArrayInputStream(xmlString.getBytes(xmlCodeType));
		 	if(null != inputStream) {
				 reader = new InputStreamReader(inputStream, inCodeType);
		         SAXBuilder builder = new SAXBuilder();
		         DataUnformatFilter format = new DataUnformatFilter();
		         builder.setXMLFilter(format); // Strip formatting
		         doc = builder.build(reader);
		 	}
		}catch (Exception e) {
		     System.err.println("Error creating XML properties inputStream " + inputStream + ".");
		     e.printStackTrace();
		     throw new IOException(e.getMessage());
		} finally {
		    try {
		     	if(null != reader) {
		     		reader.close();
		     	}
		    }catch (Exception e) {
		     	e.printStackTrace();
		     	throw e;
		    }
		}
    }
	
    /**
     * 构造函数
     * @param inputStream xml流数据
     * @param codeType 编码
     * @throws Exception 异常
     */
	
    public JdomTool(InputStream inputStream, String codeType) throws Exception {
        Reader reader = null;
        try {
        	if(null != inputStream && null != codeType){
	    		 reader = new InputStreamReader(inputStream, codeType);
	             SAXBuilder builder = new SAXBuilder();
	             DataUnformatFilter format = new DataUnformatFilter();
	             builder.setXMLFilter(format); // Strip formatting
	             doc = builder.build(reader);
        	}
        }catch (Exception e) {
            System.err.println("Error creating XML properties inputStream " + inputStream + ".");
            e.printStackTrace();
            throw new IOException(e.getMessage());
        }finally {
            try {
            	if(null != reader) {
            		reader.close();
            	}
            }catch (Exception e) {
            	e.printStackTrace();
            	throw e;
            }
        }
    }
	
    /**
     * 取得xml中对应xpath路径下元素属性的值
     * @param xpath xpath路径
     * @param attrName 属性名
     * @return 属性值
     * @throws Exception 异常
     */
	
    public synchronized String getElementAttrValue(String xpath, String attrName) throws Exception {
       if(null == xpath || "".equals(xpath)) {
    	   throw new Exception("xpath is : "+xpath+",please check !");
       } else if(null == attrName || "".equals(attrName)) {
    	   throw new Exception("attrName is : "+attrName+",please check !");
       }
    	String result = null;
		try {
			XPath xmlDocPath = XPath.newInstance(xpath);//get Xpath Object by xpath
			Element element = (Element)xmlDocPath.selectSingleNode(doc);
			if(null != element) {
				Attribute attribute = element.getAttribute(attrName);
		        if(null != attribute) {
		        	result = attribute.getValue();
		        	if("".equals(result)) {
						result = null;
					}else {
						result = result.trim();
					}
		        }
			}	
		} catch (JDOMException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
    }
	
    /**
     * 取得xml元素的文本(text)值
     * @param xpath xpath路径
     * @return xml元素的文本(text)值
     * @throws Exception 异常
     */
	
    public synchronized String getElementText(String xpath) throws Exception {
    	if(null == xpath || "".equals(xpath)) {
     	   throw new Exception("xpath is : "+xpath+",please check !");
        }
    	String result = null;
        try {
			XPath xmlDocPath = XPath.newInstance(xpath);//get Xpath Object by xpath
			Element element = (Element)xmlDocPath.selectSingleNode(doc);
			if(null != element) {
				result = element.getText();
				if("".equals(result)) {
					result = null;
				}else {
					result = result.trim();
				}
			}	
		} catch (JDOMException e) {
			e.printStackTrace();
			throw e;
		}
       return result;
    }
	
    /**
     * 取得子元素(Element)列表
     * @param xpath xpath路径
     * @return 子元素(Element)列表
     * @throws Exception 异常
     */	
	
    @SuppressWarnings("rawtypes")
	public List getChildrenElements(String xpath) throws Exception {
    	if(null == xpath || "".equals(xpath)) {
      	   throw new Exception("xpath is : "+xpath+",please check !");
        }
    	List result = null;
    	try {
			XPath xmlDocPath = XPath.newInstance(xpath);//get Xpath Object by xpath
			Element element = (Element)xmlDocPath.selectSingleNode(doc);
			if(null != element) {
				result = element.getChildren();
			}	
		} catch (JDOMException e) {
			e.printStackTrace();
			throw e;
		}
    	return result;
    }
	
    /**
     * 取得当前元素(Element)列表
     * @param xpath xpath路径
     * @return 当前元素(Element)列表
     * @throws Exception 异常
     */
	
    @SuppressWarnings("rawtypes")
	public List getElements(String xpath) throws Exception {
    	if(null == xpath || "".equals(xpath)) {
      	   throw new Exception("xpath is : "+xpath+",please check !");
        }
    	List result = new ArrayList();
    	try {
			XPath xmlDocPath = XPath.newInstance(xpath);//get Xpath Object by xpath
			result = xmlDocPath.selectNodes(doc);	
		} catch (JDOMException e) {
			e.printStackTrace();
			throw e;
		}
    	return result;
    }
	
    /**
     * 取得元素对象
     * @param xpath xpath路径
     * @return 元素对象
     * @throws Exception 异常
     */
	
    public Element getElement(String xpath) throws Exception {
    	if(null == xpath || "".equals(xpath)) {
      	   throw new Exception("xpath is : "+xpath+",please check !");
        }
    	Element result = null;
    	try {
			XPath xmlDocPath = XPath.newInstance(xpath);//get Xpath Object by xpath
			result = (Element)xmlDocPath.selectSingleNode(doc);	
		} catch (JDOMException e) {
			e.printStackTrace();
			throw e;
		}
    	return result;
    }
	
    /**
     * 保存XML文件
     */
	
    private synchronized void saveXMLFile() {
        Writer writer = null;
        boolean error = false;
        File tempFile = null;
        try {
            tempFile = new File(file.getParentFile(), file.getName() + ".tmp");
            XMLOutputter outputter = new XMLOutputter();
            writer = new OutputStreamWriter(new FileOutputStream(tempFile), "UTF-8");
            outputter.output(doc, writer);
        }catch (Exception e) {
            e.printStackTrace();
            error = true;
        }finally {
            try { 
            	writer.close();  
            }catch (Exception e) {
                e.printStackTrace();
                error = true;
            }
        }
        if (!error) {
            error = false;
            if (!file.delete()) {
                System.err.println("Error deleting property file: " + file.getAbsolutePath());
                return;
            }
            try {
                XMLOutputter outputter = new XMLOutputter();
                writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
                outputter.output(doc, writer);
            }catch (Exception e) {
                e.printStackTrace();
                error = true;
            }finally {
                try {  
                	writer.close();  
                }catch (Exception e) {
                    e.printStackTrace();
                    error = true;
                }
            }
            if (!error) {
                tempFile.delete();
            }
        }
    }
	
    /**
     * 设置元素的文本(text)值
     * @param xpath xpath路径
     * @param value 值
     * @throws Exception 异常
     */
	
    public synchronized void setElementText(String xpath, String value) throws Exception {
    	if(null == xpath || "".equals(xpath)) {
       	   	throw new Exception("xpath is : "+xpath+",please check !");
        }else if(null == value || "".equals(value)) {
        	throw new Exception("value is : "+value+",please check !");
        }
    	try {
			XPath xmlDocPath = XPath.newInstance(xpath);//get Xpath Object by xpath
			Element element = (Element)xmlDocPath.selectSingleNode(doc);
			if(null != element) {
				element.setText(value);
				saveXMLFile();
			}
		} catch (JDOMException e) {
			e.printStackTrace();
			throw e;
		}
    }
	
    /**
     * 设置元素中多个属性的值
     * @param xpath xpath路径
     * @param attrMap 属性MAP集合
     * @throws Exception 异常
     */
	
    @SuppressWarnings("rawtypes")
	public synchronized void setElementAttr(String xpath, Map attrMap) throws Exception {
    	if(null == xpath || "".equals(xpath)) {
       	   	throw new Exception("xpath is : "+xpath+",please check !");
        }else if(null == attrMap || attrMap.size() == 0) {
        	throw new Exception("attrMap is : "+attrMap+",please check !");
        }
    	try {
			XPath xmlDocPath = XPath.newInstance(xpath);//get Xpath Object by xpath
			Element element = (Element)xmlDocPath.selectSingleNode(doc);
			if(null != element) {
				Iterator iter = attrMap.keySet().iterator();
				while(iter.hasNext()) {
					String key = (String)iter.next();
					String value = (String)attrMap.get(key);
					Attribute attribute = element.getAttribute(key);
					if(null == attribute) {
						Attribute newAttribute = new Attribute(key,value);
						element.setAttribute(newAttribute);
					}else {
						attribute.setValue(value);
					}
				}
				saveXMLFile();
			}
		} catch (JDOMException e) {
			e.printStackTrace();
			throw e;
		}
    }
	
    /**
     * 在XML文件中创建元素
     * @param xpath xpath路径
     * @param elementName 元素名字
     * @param elementText 元素文本值
     * @param attrMap 属性MAP
     * @throws Exception 异常
     */
	
    @SuppressWarnings("rawtypes")
	public synchronized void createElement(String xpath, String elementName,String elementText,Map attrMap) throws Exception {
    	if(null == xpath || "".equals(xpath)) {
       	   	throw new Exception("xpath is : "+xpath+",please check !");
        }else if(null == elementName || "".equals(elementName)) {
        	throw new Exception("elementName is : "+elementName+",please check !");
        }
    	try {
			XPath xmlDocPath = XPath.newInstance(xpath);//get Xpath Object by xpath
			Element element = (Element)xmlDocPath.selectSingleNode(doc);
			if(null != element) {
				Element newElement = new Element(elementName);
				if(null != elementText && !("".equals(elementText))) {
					newElement.setText(elementText);
				}
				if(null != attrMap && attrMap.size() > 0) {
					Iterator iter = attrMap.keySet().iterator();
					while(iter.hasNext()) {
						String key = (String)iter.next();
						String value = (String)attrMap.get(key);
						Attribute attribute = new Attribute(key, value);
						newElement.setAttribute(attribute);
					}
				}
				element.addContent(newElement);
				saveXMLFile();
			}
		} catch (JDOMException e) {
			e.printStackTrace();
			throw e;
		}
    }
	
    /**
     * 在XML中创建多个元素
     * @param xpath xpath路径
     * @param elementList 元素集合
     * @throws Exception 异常
     */
	
    public synchronized void createElements(String xpath, Map<String,Map<String,String>> elementList) throws Exception {
    	if(null == xpath || "".equals(xpath)) {
       	   	throw new Exception("xpath is : "+xpath+",please check !");
        }else if(null == elementList || elementList.size() == 0) {
        	throw new Exception("elementList is : "+elementList+",please check !");
        }
    	try {
			XPath xmlDocPath = XPath.newInstance(xpath);//get Xpath Object by xpath
			Element element = (Element)xmlDocPath.selectSingleNode(doc);
			if(null != element) {
				Iterator<String> elementIter = elementList.keySet().iterator();
				while(elementIter.hasNext()) {
					String key = (String)elementIter.next();
					Map<String, String> attrMap = (Map<String, String>)elementList.get(key);
					Element newElement = new Element(key);
					Iterator<String> attrIter = attrMap.keySet().iterator();
					while(attrIter.hasNext()) {
						String attrName = (String)attrIter.next();
						String attrValue = (String)attrMap.get(attrName);
						if(null == attrValue)attrValue = "";
						newElement.setAttribute(new Attribute(attrName,attrValue));
					}
					element.addContent(newElement);
				}
				saveXMLFile();
			}
		} catch (JDOMException e) {
			e.printStackTrace();
			throw e;
		}
    }
	
    /**
     * 在XML中删除元素集
     * @param xpath xpath路径
     * @throws Exception 异常
     */
	
    @SuppressWarnings("rawtypes")
	public synchronized void deleteElements(String xpath) throws Exception {
    	if(null == xpath || "".equals(xpath)) {
       	   	throw new Exception("xpath is : "+xpath+",please check !");
        }
    	try {
	    	XPath xmlDocPath = XPath.newInstance(xpath);
	        List delList = xmlDocPath.selectNodes(doc);
	        if(null != delList && delList.size() > 0) {
	        	for(int i = 0 ; i < delList.size() ; i++) {
	        		Element delelement = (Element)delList.get(i);
	    	        if(null != delelement) {
	    	        	Element parentElement = delelement.getParentElement();
	    	        	parentElement.removeChildren(delelement.getName());
	    	        }
	        	}
	        	saveXMLFile();
	        }    
	    } catch (JDOMException e) {
			e.printStackTrace();
			throw e;
		}
    }
}