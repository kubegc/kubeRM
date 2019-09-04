/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.kubesys.kubernetes.impl;

import com.github.kubesys.kubernetes.api.model.VirtualMachineDisk;
import com.github.kubesys.kubernetes.api.model.VirtualMachineDiskList;
import com.github.kubesys.kubernetes.api.model.VirtualMachineDiskSpec;
import com.github.kubesys.kubernetes.api.model.virtualmachinedisk.Lifecycle;
import com.github.kubesys.kubernetes.api.model.virtualmachinedisk.Lifecycle.CloneDisk;
import com.github.kubesys.kubernetes.api.model.virtualmachinedisk.Lifecycle.CreateDisk;
import com.github.kubesys.kubernetes.api.model.virtualmachinedisk.Lifecycle.CreateDiskFromDiskImage;
import com.github.kubesys.kubernetes.api.model.virtualmachinedisk.Lifecycle.DeleteDisk;
import com.github.kubesys.kubernetes.api.model.virtualmachinedisk.Lifecycle.ResizeDisk;

/**
 * @author  wuheng@otcaix.iscas.ac.cn
 * 
 * @version 1.0.0
 * @since   2019/9/1
 **/
public class VirtualMachineDiskImpl extends AbstractImpl<VirtualMachineDisk, VirtualMachineDiskList, VirtualMachineDiskSpec> {

	static {
		cmds.add("createDisk");
		cmds.add("deleteDisk");
		cmds.add("resizeDisk");
		cmds.add("cloneDisk");
	}

	@Override
	public VirtualMachineDisk getModel() {
		return new VirtualMachineDisk();
	}

	@Override
	public VirtualMachineDiskSpec getSpec() {
		return new VirtualMachineDiskSpec();
	}
	

	@Override
	public Object getLifecycle() {
		return new Lifecycle();
	}


	@Override
	public VirtualMachineDiskSpec getSpec(VirtualMachineDisk r) {
		return r.getSpec();
	}

	/*************************************************
	 * 
	 * Generated
	 * 
	 **************************************************/

	public boolean createDisk(String name, CreateDisk createDisk) throws Exception {
		return createDisk(name, null, createDisk, null);
	}

	
	public boolean createDisk(String name, String nodeName, CreateDisk createDisk) throws Exception {
		return createDisk(name, nodeName, createDisk, null);
	}
	
	public boolean createDisk(String name, CreateDisk createDisk, String eventId) throws Exception {
		return createDisk(name, null, createDisk, eventId);
	}
	
	public boolean createDisk(String name, String nodeName, CreateDisk createDisk, String eventId) throws Exception {
		return create(getModel(), createMetadata(name, nodeName, eventId), 
				createSpec(nodeName, createLifecycle(createDisk)));
	}
	
	public boolean deleteDisk(String name, DeleteDisk deleteDisk) throws Exception {
		return deleteDisk(name, deleteDisk, null);
	}
	
	public boolean deleteDisk(String name, DeleteDisk deleteDisk, String eventId) throws Exception {
		return delete(name, updateMetadata(name, eventId), deleteDisk);
	}
	
	public boolean resizeDisk(String name, ResizeDisk resizeDisk) throws Exception {
		return resizeDisk(name, resizeDisk, null);
	}

	public boolean resizeDisk(String name, ResizeDisk resizeDisk, String eventId) throws Exception {
		return update(name, updateMetadata(name, eventId), resizeDisk);
	}
	
	public boolean cloneDisk(String name, CloneDisk cloneDisk) throws Exception {
		return cloneDisk(name, cloneDisk, null);
	}
	
	public boolean cloneDisk(String name, CloneDisk cloneDisk, String eventId) throws Exception {
		return update(name, updateMetadata(name, eventId), cloneDisk);
	}
}