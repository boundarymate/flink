/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.runtime.checkpoint;

import java.io.Serializable;

/** Encapsulates all the metadata for a checkpoint. */
public class CheckpointMetaData implements Serializable {

    private static final long serialVersionUID = -2387652345781312442L;

    /** The ID of the checkpoint. */
    private final long checkpointId;

    /** The timestamp of the checkpoint triggering. */
    private final long timestamp;

    /** The timestamp of the checkpoint receiving by this subtask. */
    private final long receiveTimestamp;

    public CheckpointMetaData(long checkpointId, long timestamp) {
        this(checkpointId, timestamp, System.currentTimeMillis());
    }

    public CheckpointMetaData(long checkpointId, long timestamp, long receiveTimestamp) {
        this.checkpointId = checkpointId;
        this.timestamp = timestamp;
        this.receiveTimestamp = receiveTimestamp;
    }

    public long getCheckpointId() {
        return checkpointId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getReceiveTimestamp() {
        return receiveTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CheckpointMetaData that = (CheckpointMetaData) o;

        return (checkpointId == that.checkpointId)
                && (timestamp == that.timestamp)
                && (receiveTimestamp == that.receiveTimestamp);
    }

    @Override
    public int hashCode() {
        int result = (int) (checkpointId ^ (checkpointId >>> 32));
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + (int) (receiveTimestamp ^ (receiveTimestamp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "CheckpointMetaData{"
                + "checkpointId="
                + checkpointId
                + ", receiveTimestamp="
                + receiveTimestamp
                + ", timestamp="
                + timestamp
                + '}';
    }
}
