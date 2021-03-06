/**
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
package org.apache.activemq.openwire;

import org.apache.activemq.ActiveMQConnectionMetaData;
import org.apache.activemq.command.WireFormatInfo;
import org.apache.activemq.wireformat.WireFormat;
import org.apache.activemq.wireformat.WireFormatFactory;

/**
 *
 */
public class OpenWireFormatFactory implements WireFormatFactory {

    //
    // The default values here are what the wire format changes to after a
    // default negotiation.
    //

    private int version = OpenWireFormat.DEFAULT_WIRE_VERSION;
    private boolean stackTraceEnabled = true;
    private boolean tcpNoDelayEnabled = true;
    private boolean cacheEnabled = true;
    private boolean tightEncodingEnabled = true;
    private boolean sizePrefixDisabled;
    private long maxInactivityDuration = 30*1000;
    private long maxInactivityDurationInitalDelay = 10*1000;
    private int cacheSize = 1024;
    private long maxFrameSize = OpenWireFormat.DEFAULT_MAX_FRAME_SIZE;
    private String host=null;
    private String providerName = ActiveMQConnectionMetaData.PROVIDER_NAME;
    private String providerVersion = ActiveMQConnectionMetaData.PROVIDER_VERSION;
    private String platformDetails = ActiveMQConnectionMetaData.DEFAULT_PLATFORM_DETAILS;
    private boolean includePlatformDetails = false;

    @Override
    public WireFormat createWireFormat() {
        WireFormatInfo info = new WireFormatInfo();
        info.setVersion(version);

        try {
            info.setStackTraceEnabled(stackTraceEnabled);
            info.setCacheEnabled(cacheEnabled);
            info.setTcpNoDelayEnabled(tcpNoDelayEnabled);
            info.setTightEncodingEnabled(tightEncodingEnabled);
            info.setSizePrefixDisabled(sizePrefixDisabled);
            info.setMaxInactivityDuration(maxInactivityDuration);
            info.setMaxInactivityDurationInitalDelay(maxInactivityDurationInitalDelay);
            info.setCacheSize(cacheSize);
//IC see: https://issues.apache.org/jira/browse/AMQ-498
            info.setMaxFrameSize(maxFrameSize);
//IC see: https://issues.apache.org/jira/browse/AMQ-5050
            if( host!=null ) {
                info.setHost(host);
            }
//IC see: https://issues.apache.org/jira/browse/AMQ-6379
            info.setProviderName(providerName);
            info.setProviderVersion(providerVersion);
//IC see: https://issues.apache.org/jira/browse/AMQ-6871
            if (includePlatformDetails) {
                platformDetails = ActiveMQConnectionMetaData.PLATFORM_DETAILS;
            }
            info.setPlatformDetails(platformDetails);
        } catch (Exception e) {
            IllegalStateException ise = new IllegalStateException("Could not configure WireFormatInfo");
            ise.initCause(e);
            throw ise;
        }

//IC see: https://issues.apache.org/jira/browse/AMQ-3236
        OpenWireFormat f = new OpenWireFormat(version);
//IC see: https://issues.apache.org/jira/browse/AMQ-498
        f.setMaxFrameSize(maxFrameSize);
        f.setPreferedWireFormatInfo(info);
        return f;
    }

    public boolean isStackTraceEnabled() {
        return stackTraceEnabled;
    }

    public void setStackTraceEnabled(boolean stackTraceEnabled) {
        this.stackTraceEnabled = stackTraceEnabled;
    }

    public boolean isTcpNoDelayEnabled() {
        return tcpNoDelayEnabled;
    }

    public void setTcpNoDelayEnabled(boolean tcpNoDelayEnabled) {
        this.tcpNoDelayEnabled = tcpNoDelayEnabled;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isCacheEnabled() {
        return cacheEnabled;
    }

    public void setCacheEnabled(boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
    }

    public boolean isTightEncodingEnabled() {
        return tightEncodingEnabled;
    }

    public void setTightEncodingEnabled(boolean tightEncodingEnabled) {
        this.tightEncodingEnabled = tightEncodingEnabled;
    }

    public boolean isSizePrefixDisabled() {
        return sizePrefixDisabled;
    }

    public void setSizePrefixDisabled(boolean sizePrefixDisabled) {
        this.sizePrefixDisabled = sizePrefixDisabled;
    }

    public long getMaxInactivityDuration() {
//IC see: https://issues.apache.org/jira/browse/AMQ-643
        return maxInactivityDuration;
    }

    public void setMaxInactivityDuration(long maxInactivityDuration) {
        this.maxInactivityDuration = maxInactivityDuration;
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public long getMaxInactivityDurationInitalDelay() {
        return maxInactivityDurationInitalDelay;
    }

    public void setMaxInactivityDurationInitalDelay(
            long maxInactivityDurationInitalDelay) {
        this.maxInactivityDurationInitalDelay = maxInactivityDurationInitalDelay;
    }

    public long getMaxFrameSize() {
//IC see: https://issues.apache.org/jira/browse/AMQ-498
        return maxFrameSize;
    }

    public void setMaxFrameSize(long maxFrameSize) {
        this.maxFrameSize = maxFrameSize;
    }

    public String getHost() {
//IC see: https://issues.apache.org/jira/browse/AMQ-5050
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getProviderName() {
//IC see: https://issues.apache.org/jira/browse/AMQ-6379
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderVersion() {
        return providerVersion;
    }

    public void setProviderVersion(String providerVersion) {
        this.providerVersion = providerVersion;
    }

    public String getPlatformDetails() {
        return platformDetails;
    }

    public void setPlatformDetails(String platformDetails) {
        this.platformDetails = platformDetails;
    }

    public boolean isIncludePlatformDetails() {
//IC see: https://issues.apache.org/jira/browse/AMQ-6871
        return includePlatformDetails;
    }

    public void setIncludePlatformDetails(boolean includePlatformDetails) {
        this.includePlatformDetails = includePlatformDetails;
    }
}
