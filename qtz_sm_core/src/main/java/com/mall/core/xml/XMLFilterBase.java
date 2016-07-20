package com.mall.core.xml;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.XMLFilterImpl;

/**
* <p>Title:XMLFilterBase类</p> 
* <p>Description:XML过滤基类</p> 
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 罗丁丁 - Stank.Luo
 * @version v1.0 2014-9-28
 */

class XMLFilterBase extends XMLFilterImpl {
	
    ////////////////////////////////////////////////////////////////////
    // Constants.
    ////////////////////////////////////////////////////////////////////
    protected static final Attributes EMPTY_ATTS = new AttributesImpl();
    
    ////////////////////////////////////////////////////////////////////
    // Constructors.
    ////////////////////////////////////////////////////////////////////

    /**
     * Construct an XML filter with no parent.
     *
     * <p>This filter will have no parent: you must assign a parent
     * before you start a parse or do any configuration with
     * setFeature or setProperty.</p>
     *
     * @see com.jivesoftware.sax.XMLReader#setFeature
     * @see com.jivesoftware.sax.XMLReader#setProperty
     */
    public XMLFilterBase() {
    }

    /**
     * Create an XML filter with the specified parent.
     *
     * <p>Use the XMLReader provided as the source of events.</p>
     *
     * @param xmlreader The parent in the filter chain.
     */
    public XMLFilterBase(XMLReader parent) {
        super(parent);
    }

    ////////////////////////////////////////////////////////////////////
    // Convenience methods.
    ////////////////////////////////////////////////////////////////////

    /**
     * Start a new element without a qname or attributes.
     *
     * <p>This method will provide a default empty attribute
     * list and an empty string for the qualified name.
     * It invokes {@link
     * #startElement(String, String, String, Attributes)}
     * directly.</p>
     *
     * @param uri The element's Namespace URI.
     * @param localName The element's local name.
     * @exception com.jivesoftware.sax.SAXException If a filter
     *            further down the chain raises an exception.
     * @see com.jivesoftware.sax.ContentHandler#startElement
     */
    public void startElement (String uri, String localName) throws SAXException {
        startElement(uri, localName, "", EMPTY_ATTS);
    }

    /**
     * Start a new element without a qname, attributes or a Namespace URI.
     *
     * <p>This method will provide an empty string for the
     * Namespace URI, and empty string for the qualified name,
     * and a default empty attribute list. It invokes
     * #startElement(String, String, String, Attributes)}
     * directly.</p>
     *
     * @param localName The element's local name.
     * @exception com.jivesoftware.sax.SAXException If a filter
     *            further down the chain raises an exception.
     * @see com.jivesoftware.sax.ContentHandler#startElement
     */
    public void startElement (String localName) throws SAXException {
        startElement("", localName, "", EMPTY_ATTS);
    }

    /**
     * End an element without a qname.
     *
     * <p>This method will supply an empty string for the qName.
     * It invokes {@link #endElement(String, String, String)}
     * directly.</p>
     *
     * @param uri The element's Namespace URI.
     * @param localName The element's local name.
     * @exception com.jivesoftware.sax.SAXException If a filter
     *            further down the chain raises an exception.
     * @see com.jivesoftware.sax.ContentHandler#endElement
     */
    public void endElement (String uri, String localName) throws SAXException {
        endElement(uri, localName, "");
    }

    /**
     * End an element without a Namespace URI or qname.
     *
     * <p>This method will supply an empty string for the qName
     * and an empty string for the Namespace URI.
     * It invokes {@link #endElement(String, String, String)}
     * directly.</p>
     *
     * @param localName The element's local name.
     * @exception com.jivesoftware.sax.SAXException If a filter
     *            further down the chain raises an exception.
     * @see com.jivesoftware.sax.ContentHandler#endElement
     */
    public void endElement (String localName) throws SAXException {
        endElement("", localName, "");
    }

    /**
     * Add an empty element.
     *
     * Both a {@link #startElement startElement} and an
     * {@link #endElement endElement} event will be passed on down
     * the filter chain.
     *
     * @param uri The element's Namespace URI, or the empty string
     *        if the element has no Namespace or if Namespace
     *        processing is not being performed.
     * @param localName The element's local name (without prefix).  This
     *        parameter must be provided.
     * @param qName The element's qualified name (with prefix), or
     *        the empty string if none is available.  This parameter
     *        is strictly advisory: the writer may or may not use
     *        the prefix attached.
     * @param atts The element's attribute list.
     * @exception com.jivesoftware.sax.SAXException If a filter
     *            further down the chain raises an exception.
     * @see com.jivesoftware.sax.ContentHandler#startElement
     * @see com.jivesoftware.sax.ContentHandler#endElement
     */
    public void emptyElement (String uri, String localName, String qName,
            Attributes atts) throws SAXException {
        startElement(uri, localName, qName, atts);
        endElement(uri, localName, qName);
    }

     /**
      * Add an empty element without a qname or attributes.
      *
      * <p>This method will supply an empty string for the qname
      * and an empty attribute list.  It invokes
      * {@link #emptyElement(String, String, String, Attributes)}
      * directly.</p>
      *
      * @param uri The element's Namespace URI.
      * @param localName The element's local name.
      * @exception com.jivesoftware.sax.SAXException If a filter
      *            further down the chain raises an exception.
      * @see #emptyElement(String, String, String, Attributes)
      */
    public void emptyElement (String uri, String localName) throws SAXException {
        emptyElement(uri, localName, "", EMPTY_ATTS);
    }

    /**
     * Add an empty element without a Namespace URI, qname or attributes.
     *
     * <p>This method will supply an empty string for the qname,
     * and empty string for the Namespace URI, and an empty
     * attribute list.  It invokes
     * {@link #emptyElement(String, String, String, Attributes)}
     * directly.</p>
     *
     * @param localName The element's local name.
     * @exception com.jivesoftware.sax.SAXException If a filter
     *            further down the chain raises an exception.
      * @see #emptyElement(String, String, String, Attributes)
     */
    public void emptyElement (String localName) throws SAXException {
        emptyElement("", localName, "", EMPTY_ATTS);
    }

    /**
     * Add an element with character data content.
     *
     * <p>This is a convenience method to createUser a complete element
     * with character data content, including the start tag
     * and end tag.</p>
     *
     * <p>This method invokes
     * {@link @see com.jivesoftware.sax.ContentHandler#startElement},
     * followed by
     * {@link #characters(String)}, followed by
     * {@link @see com.jivesoftware.sax.ContentHandler#endElement}.</p>
     *
     * @param uri The element's Namespace URI.
     * @param localName The element's local name.
     * @param qName The element's default qualified name.
     * @param atts The element's attributes.
     * @param content The character data content.
     * @exception com.jivesoftware.sax.SAXException If a filter
     *            further down the chain raises an exception.
     * @see com.jivesoftware.sax.ContentHandler#startElement
     * @see #characters(String)
     * @see com.jivesoftware.sax.ContentHandler#endElement
     */
    public void dataElement (String uri, String localName, String qName,
            Attributes atts, String content) throws SAXException {
        startElement(uri, localName, qName, atts);
        characters(content);
        endElement(uri, localName, qName);
    }

    /**
     * Add an element with character data content but no attributes.
     *
     * <p>This is a convenience method to createUser a complete element
     * with character data content, including the start tag
     * and end tag.  This method provides an empty string
     * for the qname and an empty attribute list.</p>
     *
     * <p>This method invokes
     * {@link @see com.jivesoftware.sax.ContentHandler#startElement},
     * followed by
     * {@link #characters(String)}, followed by
     * {@link @see com.jivesoftware.sax.ContentHandler#endElement}.</p>
     *
     * @param uri The element's Namespace URI.
     * @param localName The element's local name.
     * @param content The character data content.
     * @exception com.jivesoftware.sax.SAXException If a filter
     *            further down the chain raises an exception.
     * @see com.jivesoftware.sax.ContentHandler#startElement
     * @see #characters(String)
     * @see com.jivesoftware.sax.ContentHandler#endElement
     */
    public void dataElement (String uri, String localName, String content)
            throws SAXException {
        dataElement(uri, localName, "", EMPTY_ATTS, content);
    }

    /**
     * Add an element with character data content but no attributes or
     * Namespace URI.
     *
     * <p>This is a convenience method to createUser a complete element
     * with character data content, including the start tag
     * and end tag.  The method provides an empty string for the
     * Namespace URI, and empty string for the qualified name,
     * and an empty attribute list.</p>
     *
     * <p>This method invokes
     * {@link @see com.jivesoftware.sax.ContentHandler#startElement},
     * followed by
     * {@link #characters(String)}, followed by
     * {@link @see com.jivesoftware.sax.ContentHandler#endElement}.</p>
     *
     * @param localName The element's local name.
     * @param content The character data content.
     * @exception com.jivesoftware.sax.SAXException If a filter
     *            further down the chain raises an exception.
     * @see com.jivesoftware.sax.ContentHandler#startElement
     * @see #characters(String)
     * @see com.jivesoftware.sax.ContentHandler#endElement
     */
    public void dataElement (String localName, String content)
            throws SAXException {
        dataElement("", localName, "", EMPTY_ATTS, content);
    }

    /**
     * Add a string of character data, with XML escaping.
     *
     * <p>This is a convenience method that takes an XML
     * String, converts it to a character array, then invokes
     * {@link @see com.jivesoftware.sax.ContentHandler#characters}.</p>
     *
     * @param data The character data.
     * @exception com.jivesoftware.sax.SAXException If a filter
     *            further down the chain raises an exception.
     * @see @see com.jivesoftware.sax.ContentHandler#characters
     */
    public void characters (String data) throws SAXException {
        char ch[] = data.toCharArray();
        characters(ch, 0, ch.length);
    }

}
