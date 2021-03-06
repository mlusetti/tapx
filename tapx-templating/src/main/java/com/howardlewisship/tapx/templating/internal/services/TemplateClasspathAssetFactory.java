// Copyright 2009 Howard M. Lewis Ship
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.howardlewisship.tapx.templating.internal.services;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.internal.services.AbstractAsset;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.internal.util.ClasspathResource;
import org.apache.tapestry5.services.AssetFactory;

/**
 * Replaces Tapestry's default ClasspathAssetFactory to build assets in terms of {@link
 * com.howardlewisship.tapx.templating.internal.services.MultipartResourceTracker}.
 */
public class TemplateClasspathAssetFactory implements AssetFactory
{
    private final MultipartResourceTracker tracker;

    private final Resource rootResource = new ClasspathResource("");

    public TemplateClasspathAssetFactory(MultipartResourceTracker tracker)
    {
        this.tracker = tracker;
    }

    public Asset createAsset(final Resource resource)
    {
        return new AbstractAsset(false)
        {
            public Resource getResource()
            {
                return resource;
            }

            public String toClientURL()
            {
                return tracker.getContentURL("classpath", resource);
            }
        };
    }

    public Resource getRootResource()
    {
        return rootResource;
    }
}
