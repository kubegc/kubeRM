/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubestack.client.api.specs;

import io.github.kubestack.client.api.specs.virtualmachine.Domain;
import io.github.kubestack.client.api.specs.virtualmachineimage.Lifecycle;

/**
 * @author wuheng@iscas.ac.cn
 * 
 * @version 2.0.0
 * @since   2022.9.28
 **/
public class VirtualMachineImageSpec extends KubeStackSpec {


	protected Domain domain;

	protected Lifecycle lifecycle;
	
	public VirtualMachineImageSpec() {

	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public Lifecycle getLifecycle() {
		return lifecycle;
	}

	public void setLifecycle(Lifecycle lifecycle) {
		this.lifecycle = lifecycle;
	}

}