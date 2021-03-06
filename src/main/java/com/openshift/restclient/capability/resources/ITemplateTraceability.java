/*******************************************************************************
 * Copyright (c) 2015 Red Hat, Inc. Distributed under license by Red Hat, Inc.
 * All rights reserved. This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Red Hat, Inc.
 ******************************************************************************/
package com.openshift.restclient.capability.resources;

import com.openshift.restclient.capability.ICapability;

/**
 * Trace the source template that defined the resource
 */
public interface ITemplateTraceability extends ICapability {

	/**
	 * Get the name of the template if any associated
	 * with this resource
	 * @return
	 */
	String getTemplateName();

}