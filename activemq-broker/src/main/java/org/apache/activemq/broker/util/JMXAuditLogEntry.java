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
package org.apache.activemq.broker.util;

import java.util.Arrays;

public class JMXAuditLogEntry extends AuditLogEntry {
    public static final String[] VERBS = new String[] {" called ", " ended "};
    private int state = 0;
    protected String target;

    public void complete() {
//IC see: https://issues.apache.org/jira/browse/AMQ-6764
        setTimestamp(System.currentTimeMillis());
        state = 1;
    }

    public String getTarget() {
//IC see: https://issues.apache.org/jira/browse/AMQ-7094
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return user.trim() + VERBS[state] + operation + Arrays.toString((Object[])parameters.get("arguments"))
                + (target != null ? " on " + target : "")
                + " at " + getFormattedTime();
    }
}
