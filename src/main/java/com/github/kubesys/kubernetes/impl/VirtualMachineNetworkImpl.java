/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.kubesys.kubernetes.impl;

import java.util.regex.Pattern;

import com.github.kubesys.kubernetes.api.model.VirtualMachineNetwork;
import com.github.kubesys.kubernetes.api.model.VirtualMachineNetworkList;
import com.github.kubesys.kubernetes.api.model.VirtualMachineNetworkSpec;
import com.github.kubesys.kubernetes.api.model.virtualmachinenetwork.Lifecycle;
import com.github.kubesys.kubernetes.api.model.virtualmachinenetwork.Lifecycle.BindFip;
import com.github.kubesys.kubernetes.api.model.virtualmachinenetwork.Lifecycle.CreateBridge;
import com.github.kubesys.kubernetes.api.model.virtualmachinenetwork.Lifecycle.CreateSwitch;
import com.github.kubesys.kubernetes.api.model.virtualmachinenetwork.Lifecycle.DelBridgeVlan;
import com.github.kubesys.kubernetes.api.model.virtualmachinenetwork.Lifecycle.DeleteBridge;
import com.github.kubesys.kubernetes.api.model.virtualmachinenetwork.Lifecycle.DeleteSwitch;
import com.github.kubesys.kubernetes.api.model.virtualmachinenetwork.Lifecycle.SetBridgeVlan;
import com.github.kubesys.kubernetes.api.model.virtualmachinenetwork.Lifecycle.UnbindFip;
import com.github.kubesys.kubernetes.utils.RegExpUtils;

/**
 * @author  wuheng@otcaix.iscas.ac.cn
 * 
 * @version 1.0.0
 * @since   2019/9/1
 **/
public class VirtualMachineNetworkImpl extends AbstractImpl<VirtualMachineNetwork, VirtualMachineNetworkList, VirtualMachineNetworkSpec> {

	@Override
	public VirtualMachineNetwork getModel() {
		return new VirtualMachineNetwork();
	}

	@Override
	public VirtualMachineNetworkSpec getSpec() {
		return new VirtualMachineNetworkSpec();
	}
	
	@Override
	public Object getLifecycle() {
		return new Lifecycle();
	}

	
	@Override
	public VirtualMachineNetworkSpec getSpec(VirtualMachineNetwork r) {
		return r.getSpec();
	}

	/*************************************************
	 * 
	 * Generated by <code>MethodGenerator<code>
	 * 
	 **************************************************/

	public boolean createBridge(String name, CreateBridge createBridge) throws Exception {
		return createBridge(name, null, createBridge, null);
	}

	public boolean createBridge(String name, String nodeName, CreateBridge createBridge) throws Exception {
		return createBridge(name, nodeName, createBridge, null);
	}

	public boolean createBridge(String name, CreateBridge createBridge, String eventId) throws Exception {
		return createBridge(name, null, createBridge, eventId);
	}

	public boolean createBridge(String name, String nodeName,CreateBridge createBridge, String eventId) throws Exception {
		Pattern pattern = Pattern.compile(RegExpUtils.NAME_PATTERN);
		if (!pattern.matcher(name).matches()) {
			throw new IllegalArgumentException("the length must be between 4 and 100, and it can only includes a-z, 0-9 and -.");
		}
		return create(getModel(), createMetadata(name, nodeName, eventId), 
				createSpec(nodeName, createLifecycle(createBridge)));
	}

	public boolean deleteBridge(String name, DeleteBridge deleteBridge) throws Exception {
		return deleteBridge(name, deleteBridge, null);
	}

	public boolean deleteBridge(String name, DeleteBridge deleteBridge, String eventId) throws Exception {
		Pattern pattern = Pattern.compile(RegExpUtils.NAME_PATTERN);
		if (!pattern.matcher(name).matches()) {
			throw new IllegalArgumentException("the length must be between 4 and 100, and it can only includes a-z, 0-9 and -.");
		}
		return delete(name, updateMetadata(name, eventId), deleteBridge);
	}

	public boolean setBridgeVlan(String name, SetBridgeVlan setBridgeVlan) throws Exception {
		return setBridgeVlan(name, setBridgeVlan, null);
	}

	public boolean setBridgeVlan(String name, SetBridgeVlan setBridgeVlan, String eventId) throws Exception {
		Pattern pattern = Pattern.compile(RegExpUtils.NAME_PATTERN);
		if (!pattern.matcher(name).matches()) {
			throw new IllegalArgumentException("the length must be between 4 and 100, and it can only includes a-z, 0-9 and -.");
		}
		return update(name, updateMetadata(name, eventId), setBridgeVlan);
	}

	public boolean delBridgeVlan(String name, DelBridgeVlan delBridgeVlan) throws Exception {
		return delBridgeVlan(name, delBridgeVlan, null);
	}

	public boolean delBridgeVlan(String name, DelBridgeVlan delBridgeVlan, String eventId) throws Exception {
		Pattern pattern = Pattern.compile(RegExpUtils.NAME_PATTERN);
		if (!pattern.matcher(name).matches()) {
			throw new IllegalArgumentException("the length must be between 4 and 100, and it can only includes a-z, 0-9 and -.");
		}
		return update(name, updateMetadata(name, eventId), delBridgeVlan);
	}

	public boolean createSwitch(String name, CreateSwitch createSwitch) throws Exception {
		return createSwitch(name, null, createSwitch, null);
	}

	public boolean createSwitch(String name, String nodeName, CreateSwitch createSwitch) throws Exception {
		return createSwitch(name, nodeName, createSwitch, null);
	}

	public boolean createSwitch(String name, CreateSwitch createSwitch, String eventId) throws Exception {
		return createSwitch(name, null, createSwitch, eventId);
	}

	public boolean createSwitch(String name, String nodeName,CreateSwitch createSwitch, String eventId) throws Exception {
		Pattern pattern = Pattern.compile(RegExpUtils.NAME_PATTERN);
		if (!pattern.matcher(name).matches()) {
			throw new IllegalArgumentException("the length must be between 4 and 100, and it can only includes a-z, 0-9 and -.");
		}
		return create(getModel(), createMetadata(name, nodeName, eventId), 
				createSpec(nodeName, createLifecycle(createSwitch)));
	}

	public boolean deleteSwitch(String name, DeleteSwitch deleteSwitch) throws Exception {
		return deleteSwitch(name, deleteSwitch, null);
	}

	public boolean deleteSwitch(String name, DeleteSwitch deleteSwitch, String eventId) throws Exception {
		Pattern pattern = Pattern.compile(RegExpUtils.NAME_PATTERN);
		if (!pattern.matcher(name).matches()) {
			throw new IllegalArgumentException("the length must be between 4 and 100, and it can only includes a-z, 0-9 and -.");
		}
		return delete(name, updateMetadata(name, eventId), deleteSwitch);
	}

	public boolean bindFip(String name, BindFip bindFip) throws Exception {
		return bindFip(name, bindFip, null);
	}

	public boolean bindFip(String name, BindFip bindFip, String eventId) throws Exception {
		Pattern pattern = Pattern.compile(RegExpUtils.NAME_PATTERN);
		if (!pattern.matcher(name).matches()) {
			throw new IllegalArgumentException("the length must be between 4 and 100, and it can only includes a-z, 0-9 and -.");
		}
		return update(name, updateMetadata(name, eventId), bindFip);
	}

	public boolean unbindFip(String name, UnbindFip unbindFip) throws Exception {
		return unbindFip(name, unbindFip, null);
	}

	public boolean unbindFip(String name, UnbindFip unbindFip, String eventId) throws Exception {
		Pattern pattern = Pattern.compile(RegExpUtils.NAME_PATTERN);
		if (!pattern.matcher(name).matches()) {
			throw new IllegalArgumentException("the length must be between 4 and 100, and it can only includes a-z, 0-9 and -.");
		}
		return update(name, updateMetadata(name, eventId), unbindFip);
	}

}
