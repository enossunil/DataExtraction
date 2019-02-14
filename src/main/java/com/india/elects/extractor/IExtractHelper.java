package com.india.elects.extractor;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IExtractHelper {

	public void validate(Object data, IValidateMappings[] def);

	public void extract(Object model, Object data, IExtractMappings[] def)
			throws IllegalAccessException, InvocationTargetException;

}
