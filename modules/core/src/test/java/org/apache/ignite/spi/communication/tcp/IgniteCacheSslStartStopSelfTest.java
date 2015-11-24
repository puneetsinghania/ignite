/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.spi.communication.tcp;

import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.internal.processors.cache.distributed.dht.IgniteCachePutRetryAbstractSelfTest;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.testframework.GridTestUtils;

import static org.apache.ignite.cache.CacheAtomicityMode.ATOMIC;

/**
 *
 */
public class IgniteCacheSslStartStopSelfTest extends IgniteCachePutRetryAbstractSelfTest {
    /** {@inheritDoc} */
    @Override protected IgniteConfiguration getConfiguration(String gridName) throws Exception {
        IgniteConfiguration cfg = super.getConfiguration(gridName);

        cfg.setSslContextFactory(GridTestUtils.sslFactory());
        ((TcpCommunicationSpi)cfg.getCommunicationSpi()).setSocketWriteTimeout(Integer.MAX_VALUE);
        ((TcpCommunicationSpi)cfg.getCommunicationSpi()).setConnectTimeout(Integer.MAX_VALUE);
        ((TcpCommunicationSpi)cfg.getCommunicationSpi()).setMaxConnectTimeout(Integer.MAX_VALUE);

        cfg.setFailureDetectionTimeout(Integer.MAX_VALUE);

        ((TcpDiscoverySpi)cfg.getDiscoverySpi()).setAckTimeout(Integer.MAX_VALUE - 1);
        ((TcpDiscoverySpi)cfg.getDiscoverySpi()).setMaxAckTimeout(Integer.MAX_VALUE);
        ((TcpDiscoverySpi)cfg.getDiscoverySpi()).setNetworkTimeout(Integer.MAX_VALUE);
        ((TcpDiscoverySpi)cfg.getDiscoverySpi()).setSocketTimeout(Integer.MAX_VALUE);

        return cfg;
    }

    /** {@inheritDoc} */
    @Override protected CacheAtomicityMode atomicityMode() {
        return ATOMIC;
    }
}