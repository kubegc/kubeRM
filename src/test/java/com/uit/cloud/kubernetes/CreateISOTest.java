/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.uit.cloud.kubernetes;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.virtualmachineimage.Lifecycle.CreateImage;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2019/7/18
 *
 * This code is used to manage CustomResource's lifecycle,
 * such as VirtualMachine
 */
public class CreateISOTest {
	
	
	public static void main(String[] args) throws Exception {

		ExtendedKubernetesClient client = AbstractTest.getClient();
		boolean successful = client.virtualMachineImages()
				.createImage("win10", "node30", get());
		System.out.println(successful);
	}

	protected static CreateImage get() {
		CreateImage createImage = new CreateImage();
		createImage.setDisk("/opt/ISO/cn_windows_10_enterprise_x64_dvd_6846957.iso,device=cdrom,perms=ro");
		return createImage;
	}
}