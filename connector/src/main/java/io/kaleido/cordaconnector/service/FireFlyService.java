// Copyright © 2021 Kaleido, Inc.
//
// SPDX-License-Identifier: Apache-2.0
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

package io.kaleido.cordaconnector.service;

import io.kaleido.cordaconnector.exception.CordaConnectionException;
import io.kaleido.cordaconnector.model.request.BroadcastBatchRequest;
import io.kaleido.cordaconnector.rpc.NodeRPCClient;
import io.kaleido.firefly.cordapp.flows.CreateBroacastBatchFlow;
import net.corda.core.messaging.FlowHandle;
import net.corda.core.transactions.SignedTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FireFlyService {

    @Autowired
    NodeRPCClient rpcClient;

    public FireFlyService() {
    }

    public String broadcastBatch(BroadcastBatchRequest request) throws CordaConnectionException, ExecutionException, InterruptedException {
        SignedTransaction txResult = rpcClient.getRpcProxy().startFlowDynamic(CreateBroacastBatchFlow.class, request.getBatchId(), request.getPayloadRef(), request.getObservers(), request.getGroupId()).getReturnValue().get();
        return txResult.getId().toString();
    }
}
