package com.india.elects.extractor;

import com.india.elects.extractor.model.Position;

public interface IValidateMappings {

	public String getTableId();

	public Position getPosition();

	public String getExpectedValue();

	public IPreProcessor getPreProcessor();
}
