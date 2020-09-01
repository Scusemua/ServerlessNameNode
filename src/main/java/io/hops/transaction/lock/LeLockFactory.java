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
package io.hops.transaction.lock;

import io.hops.metadata.common.entity.Variable;
import io.hops.metadata.election.entity.LeDescriptorFactory;

public class LeLockFactory {

  private final static LeLockFactory instance = new LeLockFactory();

  private LeLockFactory() {
  }

  public static LeLockFactory getInstance() {
    return instance;
  }

  public Lock getLeVarsLock(Variable.Finder finder,
      TransactionLockTypes.LockType lockType) {
    VariablesLock lock = new VariablesLock();
    lock.addVariable(finder, lockType);
    return lock;
  }

  public Lock getLeVarsLock(Variable.Finder[] finders,
      TransactionLockTypes.LockType[] lockTypes) {
    assert finders.length == lockTypes.length;
    VariablesLock lock = new VariablesLock();
    for (int i = 0; i < finders.length; i++) {
      lock.addVariable(finders[i], lockTypes[i]);
    }
    return lock;
  }

  public Lock getLeDescriptorLock(LeDescriptorFactory leFactory,
      TransactionLockTypes.LockType lockType) {
    return new LeDescriptorLock(leFactory, lockType);
  }
}
