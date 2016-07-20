package com.mall.core.xml;

import java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
/**
 * Filter for removing formatting from data-or field-oriented XML.
 *
 * <i>Code and comments adapted from DataWriter-0.2, written
 * by David Megginson and released into the public domain,
 * without warranty.</i>
 *
 * <p>This filter removes leading and trailing whitespace from
 * field-oriented XML without mixed content. Note that this class will
 * likely not yield appropriate results for document-oriented XML like
 * XHTML pages, which mix character data and elements together.</p>
 * @see DataFormatFilter
 */

/**
 * <p>Title:DataUnformatFilter类</p> 
 * <p>Description:数据格式化类</p> 
 * <i>Code and comments adapted from DataWriter-0.2, written
 * by David Megginson and released into the public domain,
 * without warranty.</i>
 * <p>This filter removes leading and trailing whitespace from
 * field-oriented XML without mixed content. Note that this class will
 * likely not yield appropriate results for document-oriented XML like
 * XHTML pages, which mix character data and elements together.</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 罗丁丁 - Stank.Luo
 * @version v1.0 2014-9-28
 */

class DataUnformatFilter extends XMLFilterBase {

    ////////////////////////////////////////////////////////////////////
    // Constants.
    ////////////////////////////////////////////////////////////////////

    private static final Object SEEN_NOTHING = new Object();
    private static final Object SEEN_ELEMENT = new Object();
    private static final Object SEEN_DATA = new Object();


    ////////////////////////////////////////////////////////////////////
    // Internal state.
    ////////////////////////////////////////////////////////////////////

    private Object state = SEEN_NOTHING;
    private Stack<Object> stateStack = new Stack<Object>();

    private StringBuffer whitespace = new StringBuffer();

    ////////////////////////////////////////////////////////////////////
    // Constructors.
    ////////////////////////////////////////////////////////////////////

    /**
     * Create a new filter.
     */
    public DataUnformatFilter() {
    }

    /**
     * Create a new filter.
     *
     * <p>Use the XMLReader provided as the source of events.</p>
     *
     * @param xmlreader The parent in the filter chain.
     */
    public DataUnformatFilter(XMLReader xmlreader) {
        super(xmlreader);
    }

    ////////////////////////////////////////////////////////////////////
    // Public methods.
    ////////////////////////////////////////////////////////////////////

    /**
     * Reset the filter so that it can be reused.
     *
     * <p>This method is especially useful if the filter failed
     * with an exception the last time through.</p>
     */
    public void reset () {
        state = SEEN_NOTHING;
        stateStack = new Stack<Object>();
        whitespace = new StringBuffer();
    }

    ////////////////////////////////////////////////////////////////////
    // Methods from com.jivesoftware.sax.ContentHandler.
    ////////////////////////////////////////////////////////////////////

    /**
     * Filter a start document event.
     *
     * <p>Reset state and pass the event on for further processing.</p>
     *
     * @exception com.jivesoftware.sax.SAXException If a filter
     *            further down the chain raises an exception.
     * @see com.jivesoftware.sax.ContentHandler#startDocument
     */
    public void startDocument () throws SAXException {
        reset();
        super.startDocument();
    }

    /**
     * Filter a start element event.
     *
     * @param uri The element's Namespace URI.
     * @param localName The element's local name.
     * @param qName The element's qualified (prefixed) name.
     * @param atts The element's attribute list.
     * @exception com.jivesoftware.sax.SAXException If a filter
     *            further down the chain raises an exception.
     * @see com.jivesoftware.sax.ContentHandler#startElement
     */
    public void startElement (String uri, String localName,
                              String qName, Attributes atts)
    						  throws SAXException {
        clearWhitespace();
        stateStack.push(SEEN_ELEMENT);
        state = SEEN_NOTHING;
        super.startElement(uri, localName, qName, atts);
    }

    /**
     * Filter an end element event.
     *
     * @param uri The element's Namespace URI.
     * @param localName The element's local name.
     * @param qName The element's qualified (prefixed) name.
     * @exception com.jivesoftware.sax.SAXException If a filter
     *            further down the chain raises an exception.
     * @see com.jivesoftware.sax.ContentHandler#endElement
     */
    public void endElement (String uri, String localName, String qName)
    throws SAXException {
        if (state == SEEN_ELEMENT) {
            clearWhitespace();
        } else {
            emitWhitespace();
        }
        state = stateStack.pop();
        super.endElement(uri, localName, qName);
    }

    /**
     * Filter a character data event.
     *
     * @param ch The characters to write.
     * @param start The starting position in the array.
     * @param length The number of characters to use.
     * @exception com.jivesoftware.sax.SAXException If a filter
     *            further down the chain raises an exception.
     * @see com.jivesoftware.sax.ContentHandler#characters
     */
    public void characters (char ch[], int start, int length)
    throws SAXException {
        if (state != SEEN_DATA) {

            /* Look for non-whitespace. */
            int end = start + length;
            while (end-- > start) {
                if (!isXMLWhitespace(ch[end]))
                    break;
            }

            /*
             * If all the characters are whitespace, save them for later.
             * If we've got some data, emit any saved whitespace and updateGroup
             * our state to show we've seen data.
             */
            if (end < start) {
                saveWhitespace(ch, start, length);
            } else {
                state = SEEN_DATA;
                emitWhitespace();
            }
        }

        /* Pass on everything inside a data field. */
        if (state == SEEN_DATA) {
            super.characters(ch, start, length);
        }
    }

     /**
      * Filter an ignorable whitespace event.
      *
      * @param ch The array of characters to write.
      * @param start The starting position in the array.
      * @param length The number of characters to write.
      * @exception com.jivesoftware.sax.SAXException If a filter
      *            further down the chain raises an exception.
      * @see com.jivesoftware.sax.ContentHandler#ignorableWhitespace
      */
    public void ignorableWhitespace (char ch[], int start, int length)
    throws SAXException {
        emitWhitespace();
        // ignore
    }

    /**
     * Filter a processing instruction event.
     *
     * @param target The PI target.
     * @param data The PI data.
     * @exception com.jivesoftware.sax.SAXException If a filter
     *            further down the chain raises an exception.
     * @see com.jivesoftware.sax.ContentHandler#processingInstruction
     */
    public void processingInstruction (String target, String data)
    throws SAXException {
        emitWhitespace();
        super.processingInstruction(target, data);
    }

    ////////////////////////////////////////////////////////////////////
    // Internal methods.
    ////////////////////////////////////////////////////////////////////

    /**
     * Saves trailing whitespace.
     */
    protected void saveWhitespace (char[] ch, int start, int length) {
        whitespace.append(ch, start, length);
    }

    /**
     * Passes saved whitespace down the filter chain.
     */
    protected void emitWhitespace () throws SAXException {
        char[] data = new char[whitespace.length()];
        if (whitespace.length() > 0) {
            whitespace.getChars(0, data.length, data, 0);
            whitespace.setLength(0);
            super.characters(data, 0, data.length);
        }
    }

    /**
     * Discards saved whitespace.
     */
    protected void clearWhitespace () {
        whitespace.setLength(0);
    }

    /**
     * Returns <var>true</var> if character is XML whitespace.
     */
    private boolean isXMLWhitespace (char c) {
        return c == ' ' || c == '\t' || c == '\r' || c == '\n';
    }

}