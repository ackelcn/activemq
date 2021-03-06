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
package org.apache.activemq.store.jdbc;

import java.io.IOException;

import org.apache.activemq.Service;

/**
 * Represents some kind of lock service to ensure that a broker is the only master
 *
 * @deprecated As of 5.7.0, use more general {@link org.apache.activemq.broker.Locker} instead
 */
@Deprecated
public interface DatabaseLocker extends Service {

    /**
     * allow the injection of a jdbc persistence adapter
     * @param adapter the persistence adapter to use
     * @throws IOException 
     */
//IC see: https://issues.apache.org/jira/browse/AMQ-1191
    void setPersistenceAdapter(JDBCPersistenceAdapter adapter) throws IOException;
    
    /**
     * Used by a timer to keep alive the lock.
     * If the method returns false the broker should be terminated
     * if an exception is thrown, the lock state cannot be determined
     */
    boolean keepAlive() throws IOException;

    /**
     * set the delay interval in milliseconds between lock acquire attempts
     * @param lockAcquireSleepInterval the sleep interval in miliseconds
     */
    void setLockAcquireSleepInterval(long lockAcquireSleepInterval);
    
}
