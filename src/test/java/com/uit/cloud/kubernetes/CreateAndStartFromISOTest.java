/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.uit.cloud.kubernetes;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.virtualmachine.Lifecycle.CreateAndStartVMFromISO;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2019/7/15
 *
 * This code is used to manage CustomResource's lifecycle,
 * such as VirtualMachine
 */
public class CreateAndStartFromISOTest {
	
	
	public static void main(String[] args) throws Exception {
		ExtendedKubernetesClient client = AbstractTest.getClient();
		CreateAndStartVMFromISO createAndStartVMFromISO = get();
		// name
		boolean successful = client.virtualMachines()
				.createAndStartVMFromISO("950646e8c17a49d0b83c1c797811e041", "node30", createAndStartVMFromISO);
		System.out.println(successful);
	}
	
	
	public static CreateAndStartVMFromISO get() throws Exception {
		
		CreateAndStartVMFromISO createAndStartVMFromISO = new CreateAndStartVMFromISO();
		// default value
		createAndStartVMFromISO.setMetadata("uuid=950646e8-c17a-49d0-b83c-1c797811e041");
		createAndStartVMFromISO.setVirt_type("kvm"); 
		createAndStartVMFromISO.setOs_variant("centos7.0");
		createAndStartVMFromISO.setNoautoconsole(true); 
		
		// calculationSpecification
		calculationSpecification(createAndStartVMFromISO);  
		
		// cdrom
		createAndStartVMFromISO.setCdrom("/opt/ISO/CentOS-7-x86_64-Minimal-1511.iso"); 
		// Disk and QoS for 1 disk and many disks
		createAndStartVMFromISO.setDisk("size=10,read_bytes_sec=1024000000,write_bytes_sec=1024000000 --disk size=20,read_bytes_sec=1024000000,write_bytes_sec=1024000000 " + getOtherCDROMs());
		
		//network and QoS
		createAndStartVMFromISO.setNetwork("bridge=virbr0");  
		
		// consoleMode amd passowrd
		createAndStartVMFromISO.setGraphics("vnc,listen=0.0.0.0" + getconsolePassword("123456"));
//		createAndStartVMFromISO.setGraphics("spice,listen=0.0.0.0" + getconsolePassword("567890")); 
		
		createAndStartVMFromISO.setOs_variant("rhel7");
		return createAndStartVMFromISO;
	}


	protected static void calculationSpecification(CreateAndStartVMFromISO createAndStartVMFromISO) {
		createAndStartVMFromISO.setMemory("1024");    
		createAndStartVMFromISO.setVcpus("1" + getCPUSet("1-4,6,8"));
	}
	
	protected static String getCPUSet(String cpuset) {
		return (cpuset == null || cpuset.length() == 0) 
				? "" :",cpuset=" + cpuset;
	}
	
	protected static String getconsolePassword(String pwd) {
		return (pwd == null || pwd.length() == 0) ? "" : ",password=abcdefg";
	}
	
	protected static String getOtherCDROMs() {
		return "--disk /opt/ISO/CentOS-7-x86_64-Minimal-1511.iso,device=cdrom,perms=ro";
	}
	
	protected static String nameToUUID(String name) {
		StringBuffer sb = new StringBuffer();
		sb.append(name.substring(0, 8)).append("-")
				.append(name.substring(8, 12)).append("-")
				.append(name.substring(12, 16)).append("-")
				.append(name.substring(16, 20)).append("-")
				.append(name.substring(20, 32));
		return sb.toString();
	}
	
}
