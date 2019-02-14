package com.india.elects.extractor;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.india.elects.extractor.exception.UnxpectedDataFormatInExtractedPDFTable;
import com.india.elects.pdf.model.Table;
import com.india.elects.utils.Utils;

public class PDFdbMapper implements IExtractHelper {

	@Override
	public void extract(Object model, Object data, IExtractMappings[] mappings)
			throws IllegalAccessException, InvocationTargetException {

		Map<String, Table> map = getMap(data);

		for (IExtractMappings def : mappings) {
			Table table = map.get(def.getTableId());

			String value = table.getRows().get(def.getPosition().getRow()).getCells().size() > def.getPosition()
					.getColumn()
							? table.getRows().get(def.getPosition().getRow()).getCells()
									.get(def.getPosition().getColumn()).getContent().trim()
							: null;
			if (def.getPreProcessor() != null) {
				value = def.getPreProcessor().process(value);
			}

			BeanUtils.setProperty(model, def.getFieldName(), Utils.getTrimmed(value));

		}

	}

	@Override
	public void validate(Object data, IValidateMappings[] mappings) {

		Map<String, Table> map = getMap(data);

		for (IValidateMappings def : mappings) {
			Table table = map.get(def.getTableId());

			String value = table.getRows().get(def.getPosition().getRow()).getCells().size() > def.getPosition()
					.getColumn()
							? table.getRows().get(def.getPosition().getRow()).getCells()
									.get(def.getPosition().getColumn()).getContent().trim()
							: null;

			if (def.getPreProcessor() != null) {
				value = def.getPreProcessor().process(value);
			}

			if (value == null || !value.startsWith(def.getExpectedValue())) {
				throw new UnxpectedDataFormatInExtractedPDFTable(
						"Error in Table :" + def.getTableId() + "" + def.getPosition());
			}

		}
	}

	private Map<String, Table> getMap(Object data) {
		List<Table> list = null;

		if (!(data instanceof List)) {
			throw new RuntimeException("Invalid setup");
		} else {
			list = (List<Table>) data;
		}

		Map<String, Table> map = new HashMap<>();
		for (Table aTbl : list) {
			map.put(aTbl.getHelper().getTableId(), aTbl);
		}

		return map;
	}

}
