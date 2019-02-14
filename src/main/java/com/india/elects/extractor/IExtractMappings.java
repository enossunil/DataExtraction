package com.india.elects.extractor;

import com.india.elects.extractor.model.Position;

public interface IExtractMappings {

	public String getTableId();
		
	public Position getPosition();

	public String getFieldName();

	public IPreProcessor getPreProcessor();
}
