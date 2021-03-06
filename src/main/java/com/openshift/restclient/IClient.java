/*******************************************************************************
 * Copyright (c) 2015 Red Hat, Inc. Distributed under license by Red Hat, Inc.
 * All rights reserved. This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Red Hat, Inc.
 ******************************************************************************/
package com.openshift.restclient;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.openshift.restclient.authorization.IAuthorizationStrategy;
import com.openshift.restclient.capability.ICapable;
import com.openshift.restclient.model.IList;
import com.openshift.restclient.model.IResource;

/**
 * Client is the the simplest interface for interacting with the OpenShift
 * master server.
 *
 */
public interface IClient extends ICapable{
	
	/**
	 * List all possible resources of the given kind in the default namespace
	 * @param kind
	 * @return
	 */
	<T extends IResource> List<T> list(ResourceKind kind);

	
	/**
	 * list the given given resource kind scoping it to a specific namespace
	 * 
	 * @param kind
	 * @param namespace    The namespace to scope the possible results of this list
	 * @return
	 */
	<T extends IResource> List<T> list(ResourceKind kind, String namespace);
	
	/**
	 * list the given given resource kind scoping it to a specific namespace
	 * 
	 * @param kind
	 * @param namespace    The namespace to scope the possible results of this list
	 * @param labels             The label used to filter the resource
	 * @return
	 */
	<T extends IResource> List<T> list(ResourceKind kind, String namespace, Map<String, String> labels);

	/**
	 * 
	 * @param service
	 * @param name
	 * @return
	 * @throws OpenShiftException if operation not supported for resource type
	 */
	<T extends IResource> T get(ResourceKind kind, String name, String namespace);
	
	/**
	 * Create the given resource in the namespace defined on the 
	 * resource or the default namspace if undefined
	 * @param resource
	 * @return
	 * @throws UnsupportedOperationException if the resource is a list
	 */
	<T extends IResource> T create(T resource);

	/**
	 * Create the given resource in the given namespace
	 * @param resource
	 * @param namespace
	 * @return
	 */
	<T extends IResource> T create(T resource, String namespace);
	
	/**
	 * Create a list of resources in the given namespace
	 * @param list  The resource definitions
	 * @param namespace the namespace for the resources
	 * @return  A collection of the resources created or the status
	 *                 instance of why the creation failed.
	 *  @throws OpenShiftException if a status can not be determined from
	 *                  the exception
	 */
	Collection<IResource> create(IList list, String namespace);
	
	/**
	 * 
	 * @param resource
	 * @return
	 * @throws UnsupportedOperationException if the resource is a list
	 */
	<T extends IResource> T update(T resource);
	
	/**
	 * @param resource
	 * @throws UnsupportedOperationException if the resource is a list
	 */
	<T extends IResource> void delete(T resource);

	/**
	 * 
	 * @return the base URL of this endpoint
	 */
	URL getBaseURL();
	
	/**
	 * The OpenShift API version for this client
	 * @return
	 * @throws UnsupportedVersionException
	 * @throws {@link UnauthorizedException}
	 */
	String getOpenShiftAPIVersion() throws UnsupportedVersionException;
	
	/**
	 * Set the authorization strategy for the client when
	 * making requests to the server
	 * @param strategy
	 */
	void setAuthorizationStrategy(IAuthorizationStrategy strategy);

	/**
	 * The resource factory used to create resources based on the
	 * response from the server
	 * @return
	 */
	IResourceFactory getResourceFactory();
}
