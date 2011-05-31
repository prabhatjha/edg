/*
 * Copyright 2011 Red Hat, Inc. and/or its affiliates.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA
 */
package org.jboss.datagrid;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.*;

import java.util.Locale;
import java.util.ResourceBundle;

import org.jboss.dmr.ModelNode;
import org.jboss.dmr.ModelType;


/**
 * Detyped descriptions of Messaging subsystem resources and operations.
 *
 * @author Brian Stansberry (c) 2011 Red Hat Inc.
 */
class DataGridSubsystemDescriptions {

    private final String subsystemName;
    private final String resourceName;

    DataGridSubsystemDescriptions(String subsystemName, Package pkg) {
        this.subsystemName = subsystemName;
        resourceName = pkg.getName() + ".LocalDescriptions";
    }

    private ResourceBundle getResourceBundle(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return ResourceBundle.getBundle(resourceName, locale);
    }

    ModelNode getRootResource(Locale locale) {
        final ResourceBundle bundle = getResourceBundle(locale);

        final ModelNode node = new ModelNode();
        node.get(TYPE).set(ModelType.OBJECT);
        node.get(DESCRIPTION).set(bundle.getString(subsystemName));

        node.get(ATTRIBUTES, DataGridConstants.CONFIG_PATH).set(
                getPathDescription(DataGridConstants.CONFIG_PATH, bundle));

        return node;
    }

    ModelNode getSubsystemAdd(Locale locale) {
        final ResourceBundle bundle = getResourceBundle(locale);

        final ModelNode node = new ModelNode();
        node.get(OPERATION_NAME).set(ADD);
        node.get(DESCRIPTION).set(bundle.getString(subsystemName + ".add"));
        node.get(REQUEST_PROPERTIES, DataGridConstants.CONFIG_PATH).set(
                getPathDescription(DataGridConstants.CONFIG_PATH, bundle));

        return node;
    }

    ModelNode getSubsystemRemove(@SuppressWarnings("unused") Locale locale) {
        return new ModelNode();
    }

    ModelNode getSubsystemDescribe(@SuppressWarnings("unused") Locale locale) {
        return new ModelNode();
    }

    private static ModelNode getPathDescription(final String description, final ResourceBundle bundle) {
        final ModelNode node = new ModelNode();

        node.get(TYPE).set(ModelType.OBJECT);
        node.get(DESCRIPTION).set(bundle.getString(description));
        node.get(ATTRIBUTES, PATH, TYPE).set(ModelType.STRING);
        node.get(ATTRIBUTES, PATH, DESCRIPTION).set(bundle.getString("path.path"));
        node.get(ATTRIBUTES, PATH, REQUIRED).set(false);
        node.get(ATTRIBUTES, RELATIVE_TO, TYPE).set(ModelType.STRING);
        node.get(ATTRIBUTES, RELATIVE_TO, DESCRIPTION).set(bundle.getString("path.relative-to"));
        node.get(ATTRIBUTES, RELATIVE_TO, REQUIRED).set(false);

        return node;
    }
}
