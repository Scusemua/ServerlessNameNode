/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package namenode.startupprogress;

import org.apache.hadoop.classification.InterfaceAudience;

/**
 * Indicates run status of a {@link Phase}.
 */
@InterfaceAudience.Private
public enum Status {
  /**
   * The phase has not yet started running.
   */
  PENDING,

  /**
   * The phase is running right now.
   */
  RUNNING,

  /**
   * The phase has already completed.
   */
  COMPLETE
}