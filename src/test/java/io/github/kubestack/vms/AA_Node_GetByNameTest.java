/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubestack.vms;

import io.github.kubestack.client.KubeStackClient;


/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2019/7/15
 *
 * This code is used to manage CustomResource's lifecycle,
 * such as VirtualMachine
 */
public class AA_Node_GetByNameTest {
	
	public static void main(String[] args) throws Exception {

		KubeStackClient client = io.github.kubestack.vms.AbstractTest.getClient();
		System.out.println(client.nodes().get("vm.node133").getMetadata().getName());
	}
	
}