/*******************************************************************************
 * Copyright (c) 2015 Red Hat, Inc. Distributed under license by Red Hat, Inc.
 * All rights reserved. This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Red Hat, Inc.
 ******************************************************************************/
package com.openshift.internal.restclient.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.jboss.dmr.ModelNode;
import org.jboss.dmr.ModelType;
import org.junit.BeforeClass;
import org.junit.Test;

import com.openshift.client.utils.Samples;
import com.openshift.internal.restclient.model.Build;
import com.openshift.internal.restclient.model.KubernetesResource;
import com.openshift.internal.restclient.model.properties.ResourcePropertiesRegistry;
import com.openshift.restclient.IClient;
import com.openshift.restclient.ResourceKind;
import com.openshift.restclient.model.IResource;

public class V1Beta1ResourceTest {
	
	private static IResource resource;
	
	@BeforeClass
	public static void setup(){
		IClient client = mock(IClient.class);
		ModelNode node = ModelNode.fromJSONString(Samples.V1BETA1_SERVICE.getContentAsString());
		resource = new KubernetesResource(node, client, ResourcePropertiesRegistry.getInstance().get("v1beta1", ResourceKind.Service));
	}
	
	@Test
	public void getNamespace(){
		assertEquals("hello-openshift-project", resource.getNamespace());
	}

	@Test
	public void getNamespaceWhenUndefinedShouldReturnEmptyString(){
		IClient client = mock(IClient.class);
		ModelNode node = ModelNode.fromJSONString(Samples.V1BETA1_SERVICE.getContentAsString());
		node.get("namespace").set(new ModelNode(ModelType.UNDEFINED));
		resource = new Build(node, client, ResourcePropertiesRegistry.getInstance().get("v1beta1", ResourceKind.Project));
		assertEquals("", resource.getNamespace());
	}

	
}
