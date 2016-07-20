package com.mall.core.xml;

/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mall.core.common.pay.weChatPay.common.Util;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


/**
 * xml的操作类
 * 
 * @author wanglei
 */
public class XmlUtil {


  /** slf4jlogger 实现方式可以任意切换,默认采用log4j **/
  protected static final Logger logger = LoggerFactory.getLogger(XmlUtil.class.getName());

  /**
   * 封装消息的FilePathMessage的HashMap
   */
  private static Map<String, String> filePathMessageMap;



  /**
   * 根据messageId获取FilePathMessage.xml中对应的信息
   * 
   * @param messageId 信息的id
   * @return xml中对应的信息
   */
  public static String getFilePathMessage(String messageId) {

    // 如果未读取读取过配置文件
    if (filePathMessageMap == null) {
      String fileName = "FilePathMessage.xml";
      InputStream inputStream = XmlUtil.class.getClassLoader().getResourceAsStream(fileName);
      try {
        synchronized (XmlUtil.class) {
          filePathMessageMap = toRead(inputStream);
        }
        inputStream.close();
      } catch (IOException ex) {
        logger.error("TisXmlUtil.getFilePathMessage", ex);
      }
    }
    String messageValue = filePathMessageMap.get(messageId);
    // 取得对应的userMessage
    messageValue = StringUtils.isEmpty(messageValue) ? messageId : messageValue;
    return messageValue;
  }

  /**
   * 获取xml文件的消息并封装为HashMap
   * 
   * @param filename xml文件名（含路径）
   * @return 封装消息的HashMap
   */
  private static Map<String, String> toRead(InputStream inputStream) {
    // 定义返回值
    Map<String, String> userMessages = new HashMap<String, String>();

    try {
      // 构造Document对象
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

      DocumentBuilder builder = factory.newDocumentBuilder();

      Document document = builder.parse(inputStream);

      document.getDocumentElement().normalize();

      Element root = document.getDocumentElement();
      // 获取所有Message节点
      NodeList list = root.getElementsByTagName("Message");
      // 遍历节点，封装为HashMap
      for (int i = 0; i < list.getLength(); i++) {
        Node nodeitm = list.item(i);
        userMessages.put(nodeitm.getAttributes().getNamedItem("ID").getNodeValue(), nodeitm
            .getAttributes().getNamedItem("Value").getNodeValue());
      }
    } catch (Exception e) {
      return null;
    }

    return userMessages;

  }

  public static Map<String, String> getMapFromXML(String xmlString)
      throws ParserConfigurationException, IOException, SAXException {

    InputStream is = Util.getStringStream(xmlString);
    return toRead(is);
  }


  @SuppressWarnings("unchecked")
public static <T> T XMLBean(InputStream input, Class<T> clazz) throws IOException {
    XStream xstream = new XStream(new DomDriver());
    xstream.processAnnotations(clazz);
    return (T) xstream.fromXML(input);
  }
  
  @SuppressWarnings("unchecked")
public static <T> T XMLBean(String xmlString, Class<T> clazz) throws IOException {
    XStream xstream = new XStream(new DomDriver());
    xstream.processAnnotations(clazz);
    xstream.ignoreUnknownElements();
    InputStream is = Util.getStringStream(xmlString);
    return (T) xstream.fromXML(is);
  }
}
