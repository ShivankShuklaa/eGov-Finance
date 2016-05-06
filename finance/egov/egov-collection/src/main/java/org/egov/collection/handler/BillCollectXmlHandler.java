/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2015>  eGovernments Foundation
 *
 *     The updated version of eGov suite of products as by eGovernments Foundation
 *     is available at http://www.egovernments.org
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program. If not, see http://www.gnu.org/licenses/ or
 *     http://www.gnu.org/licenses/gpl.html .
 *
 *     In addition to the terms of the GPL license to be adhered to in using this
 *     program, the following additional terms are to be complied with:
 *
 *         1) All versions of this program, verbatim or modified must carry this
 *            Legal Notice.
 *
 *         2) Any misrepresentation of the origin of the material is prohibited. It
 *            is required that all modified versions of this material be marked in
 *            reasonable ways as different from the original version.
 *
 *         3) This license does not grant any rights to any user of the program
 *            with regards to rights under trademark law for use of the trade names
 *            or trademarks of eGovernments Foundation.
 *
 *   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */
package org.egov.collection.handler;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import org.egov.collection.integration.models.BillDetails;
import org.egov.collection.integration.models.BillInfoImpl;

public class BillCollectXmlHandler {
    private static final String DATE_FORMAT_DDMMYYY = "dd/MM/yyyy";

    public String toXML(final Object obj) {
        final XStream xStream = createXStream();
        final String[] array = { DATE_FORMAT_DDMMYYY };
        xStream.registerConverter(new DateConverter(DATE_FORMAT_DDMMYYY, array));
        xStream.registerConverter(BooleanConverter.BINARY);
        xStream.aliasAttribute(BillDetails.class, "billDate", "billDate");
        return xStream.toXML(obj);
    }

    protected XStream createXStream() {
        final XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);
        return xstream;
    }

    public Object toObject(final String xml) {
        final XStream xStream = createXStream();
        xStream.alias("bill-collect", BillInfoImpl.class);
        final String[] array = { DATE_FORMAT_DDMMYYY };
        xStream.registerConverter(new DateConverter(DATE_FORMAT_DDMMYYY, array));
        xStream.registerConverter(BooleanConverter.BINARY);
        return xStream.fromXML(xml);
    }
}
