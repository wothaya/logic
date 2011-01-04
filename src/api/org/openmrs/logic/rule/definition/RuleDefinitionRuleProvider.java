/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.logic.rule.definition;

import org.openmrs.api.context.Context;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.Rule;
import org.openmrs.logic.rule.provider.AbstractRuleProvider;
import org.openmrs.logic.rule.provider.RuleProvider;
import org.springframework.stereotype.Component;


/**
 * A provider for user-defined {@link RuleDefinition}s.
 */
@Component
public class RuleDefinitionRuleProvider extends AbstractRuleProvider implements RuleProvider {
		
	/**
	 * @see org.openmrs.logic.rule.provider.RuleProvider#getRule(java.lang.String)
	 */
	@Override
	public Rule getRule(String configuration) {
		RuleDefinitionService service = Context.getService(RuleDefinitionService.class);
		RuleDefinition definition = service.getRuleDefinition(Integer.valueOf(configuration));
		
		LanguageHandler handler = service.getLanguageHandler(definition.getLanguage());
		if (handler == null)
			throw new LogicException("Cannot find handler for language: " + definition.getLanguage());
		return handler.handle(definition);
	}
	
}