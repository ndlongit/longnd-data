/*
 * Copyright 2004-2012 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.mayaa.impl.builder.library.tld;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.mayaa.builder.library.LibraryDefinition;
import org.seasar.mayaa.impl.builder.library.entity.J2EEEntities;
import org.seasar.mayaa.impl.util.xml.XMLHandler;

/**
 * @author Koji Suga (Gluegent, Inc.)
 */
public class TLDLibraryDefinitionHandler extends XMLHandler {

    private static final Log LOG = LogFactory.getLog(TLDLibraryDefinitionHandler.class);

    private TaglibTagHandler _rootHandler;

    public TLDLibraryDefinitionHandler() {
        _rootHandler = new TaglibTagHandler();
        setRootHandler(_rootHandler);
        setLog(LOG);
        setNeighborClass(J2EEEntities.class);
        getEntityMap().putAll(J2EEEntities.getEntityMap());
    }

    public LibraryDefinition getLibraryDefinition() {
        return _rootHandler.getLibraryDefinition();
    }

}
